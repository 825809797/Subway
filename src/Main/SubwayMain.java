package Main;

import java.io.*;
import java.util.*;
import Model.*;
import FileData.ReadData;

public class SubwayMain {

	private static LineMap<String> Graph;
	public static void main(String[] args) {
		String Path = "/Users/yhf/eclipse-workspace/Subway/src/data.txt";
		List<Station> routes = new ReadData().readText(Path);
		List<String> vertex = new ArrayList<String>();
		for(Station station:routes) {
			String[] string = station.getStationinfo().toArray(new String[station.getStationinfo().size()]);
			for(String s : string) {
				if(!vertex.contains(s)) {
					vertex.add(s);
				}
			}
		}
		
		Graph = new LineMap<String>(vertex);
		for(Station station:routes) {
			String[] s = station.getStationinfo().toArray(new String[station.getStationinfo().size()]);
			for(int i=0;i<s.length-1;i++) {
				Graph.addPath(s[i], s[i+1], 1);
			}
		}
		
		Scanner input = new Scanner(System.in);
		System.out.print("请输入起点：");
		String begin = input.nextLine().trim();
		System.out.print("请输入目的地：");
		String stop = input.nextLine().trim();
		if(!Graph.vertex.contains(begin)||!Graph.vertex.contains(stop)) {
			System.out.print("地铁线路中不包含此站");
			input.close();
			return;
		}
		StringBuilder stringBuilder = Graph.search(begin, stop,Graph.getSubway(),routes);// 自身站点
//		File file=new File("D:\\Javacode\\subwaydjs\\src\\result.txt");
//		input.close();
//		try {
//			if(file.exists()) {
//				file.delete();
//			}
//			BufferedWriter bw=new BufferedWriter(new FileWriter(file));
//			bw.write(stringBuilder.toString());
//			bw.flush();
//			bw.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}
