package Model;

import java.util.List;
import java.util.ArrayList;
import Model.Floyd;

public class LineMap<T> {

	protected int[][] subway;
	
	private static final int MAX = 999;//最大权值=999表示没有路径
	
	private int[] distance;
	public List<T> vertex;
	
	public int[][] getSubway(){
		return subway;
	}
	
	public List<T> getVertex(){
		return vertex;
	}
	
	public void setVertex(List<T> ver) {
		this.vertex = ver;
	}
	
	public int[] getDistance() {
		return distance;
	}
	
	public void setDistance(int[] dis) {
		this.distance=dis;
	}
	
	public int getVertexsize(){
		return this.vertex.size(); 
	}
	
	public int vertexCount() {
		return subway.length;
	}
	
	public LineMap(int size) {
		this.vertex = new ArrayList<T>();
		this.subway = new int [size][size];
		this.distance = new int [size];
		for(int i=0;i<size;i++) {
			for(int j = 0;j<size;j++) {
				this.subway[i][j] = (i==j) ? 0 : MAX;
			}
		}
	}
	
	public LineMap(List<T> ver) {
		this.vertex = ver;
		int size = getVertexsize();
		this.subway = new int [size][size];
		this.distance = new int [size];
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				this.subway[i][j] = (i==j) ? 0 : MAX;
			}
		}
	}
	
	public int getVertexSite(T site) {
		return vertex.indexOf(site);
	}
	
	public void insertPath(T begin,T stop,int weight) {
		int n = subway.length;
		int i = getVertexSite(begin);
		int j = getVertexSite(stop);
		if(i>=0 && i<n && j>=0 && j<n && this.subway[i][j]==MAX && i!=j) {
			this.subway[i][j] = weight;
			this.subway[j][i] = weight;
		    
			
		}
	}
	
	public void addPath(T begin,T stop,int weight) {
		this.insertPath(begin, stop, weight);
	}
	
	public void removePath(String begin,String stop) {
		int i = vertex.indexOf(begin);
		int j = vertex.indexOf(stop);
		if (i >= 0 && i < vertexCount() && j >= 0 && j < vertexCount()
				&& i != j)
			this.subway[i][j] = MAX;// 没有路径的两个点之间的路径为默认最大

				
	}
	
	//相同换乘车站
	public String transferStation(String x,String y,List<Station> route) {
		String routes="";
		List<String> routes1=new ArrayList<>();
		List<String> routes2=new ArrayList<>();
		for(Station r:route) {
			for(String s:r.getStationinfo()) {
				if(s.equals(x)) 
					routes1.add(r.getStationnum());
				if(s.equals(y)) 
					routes2.add(r.getStationnum());
			}
		}
		for(int i=0;i<routes1.size();i++) 
			for(int j=0;j<routes2.size();j++) 
				if(routes1.get(i).equals(routes2.get(j))) 
					routes=routes1.get(i);
		return routes;
	}
	
	public StringBuilder search(T begin,T stop,int[][] subway,List<Station> route) {
		List<String> transfer = new ArrayList<>();
		Floyd floyd = new Floyd(subway);
		int beginsite = getVertexSite(begin);
		int stopsite = getVertexSite(stop);
		int[] path = floyd.getRoute(beginsite, stopsite);
		StringBuilder line = new StringBuilder();
		line.append(begin + " 前往 " + stop + " 的线路为： ");
		int k=0;
		for (int i : path) {
			k++;
			line.append(vertex.get(i) + " --> ");
			if(k%4==0) {
				line.append(System.lineSeparator());
			}
		}
		line.delete(line.lastIndexOf(" --> "), line.length());
		
		for(int j=1;j<(path.length)-1;j++) {
			int i=path[j];
			
			String s1=transferStation(vertex.get(path[j-1])+"", vertex.get(i)+"", route);
			String s2=transferStation(vertex.get(i)+"", vertex.get(path[j+1])+"", route);
			if(!s1.equals(s2)) {
				transfer.add(vertex.get(i)+"");
			}
		}
		System.out.println(line.toString());
		System.out.println("换乘站：");
		line.append(System.lineSeparator()+"换乘站："+System.lineSeparator());
		for(String s:transfer) {
			System.out.println(s);
			line.append(s+System.lineSeparator());
		}
		System.out.println(begin + " -> " + stop + " 距离目的地站数： " + path.length);
		line.append(begin + " -> " + stop + " 距离目的地站数： " + path.length);
		
		
		return line;
	}
}

