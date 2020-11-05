package Model;

import java.util.List;
import java.util.ArrayList;

public class Station {
	
	public String stationnum;
	public List<String> stationinfo = new ArrayList<String>();
	
	public String getStationnum() {
		return stationnum;
	}
	
	public void setStationnum(String stationnum) {
		this.stationnum = stationnum;
	}

	public List<String> getStationinfo(){
		return stationinfo;
	}
	
	public void setStationinfo(List<String> stationinfo) {
		this.stationinfo = stationinfo;
	}
}
