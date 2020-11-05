package FileData;

import java.io.*;
import java.util.*;
import Model.Station;

public class ReadData {

	 public List<Station> readText(String Path){
		  List<Station> routes=new ArrayList<Station>();
		  try {
		      File file=new File(Path);

		      if(file.isFile() && file.exists()){ 
		    	  InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
		    	  BufferedReader bufferedReader = new BufferedReader(reader);
		          String stationline = null;
		          while((stationline = bufferedReader.readLine()) != null){       

		        	  Station station = new Station();
		        	  String string = "";	
		              String tmp[] = stationline.split(" ");
		              string = tmp[0];
		              station.setStationnum(string);
		              List<String> stations = new ArrayList<>();
		              //遍历后添加经过站点
		              for(String s:tmp) {
		            	  stations.add(s);
		              }
		              stations.remove(0);
		              station.setStationinfo(stations);
		              routes.add(station);
		          }
		          reader.close();
		          bufferedReader.close();
		      }
		      else
		          System.out.println("文件路径错误");
		    } catch (Exception e) {
		        System.out.println("文件名字或格式有误");
		        e.printStackTrace();
		     }
		  return routes;
		 }
}
