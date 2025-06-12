package finalfinal;

import java.util.ArrayList;

public class AirplaneList {
	protected ArrayList <Airplane> planes = new ArrayList<Airplane>();
	
	public AirplaneList() {
		for(int i=0; i<2; i++) {
			Airplane p = new Airplane();
			planes.add(p);
		}
	}
	
	public void addPlane(Airplane newPlane) {
		planes.add(newPlane);
	}
	
	public void removePlane(int id) {
		if(checkID(id)) {
			int temp = findPlane(id);
			planes.remove(temp);
		}
	}
	
	public int findPlane(int id) {
		//return index of plane
		int index = 0;
		for(int i=0; i<planes.size(); i++) {
			Airplane temp = planes.get(i);
			if (temp.airplaneID==id) {
				index = i;
			}
		}
		return index;
	}
	
	public boolean checkID(int id) {
		//check list if id is real or not
		boolean check=false;
		for(int i=0; i<planes.size(); i++) {
			Airplane temp = planes.get(i);
			if (temp.airplaneID==id) {
				check = true;
			}
		}
		return check;
	}
	
	public void assignTask(int id, int state) {
		int temp = findPlane(id);
		planes.get(temp).move(state);//plane state has changed
	}
	
	public void setDestination(int id, Destination d) {
		int temp = findPlane(id);
		planes.get(temp).setDestination(d);	
	}
	
	public int findFreePlane() {
		int free = 100;
		for(int i=0; i<planes.size(); i++) {
			Airplane temp = planes.get(i);
			if (temp.isFree()) {
				free=i;
			}
		}
		return free;
	}
	
	public int getID(int index) {
		return planes.get(index).airplaneID;
	}
	
	public void movePlane(int id, int state) {
		planes.get(id).move(state);
	}
	
	public void deletePlane(int id) {
		planes.remove(id);
	}
	public void setCurrentPoint(int index,int cp) {
		planes.get(index).currentPoint=cp;
	}
	public StringBuilder planeBooking() {
		 StringBuilder concatenatedString = new StringBuilder();
		 for (int i=0; i<planes.size(); i++) {
			 if(planes.get(i).isFree()) {
				 concatenatedString.append("Plane ID: " + planes.get(i).airplaneID + ": Not Booked\n");
			 }else {
				 concatenatedString.append("Plane ID: " + planes.get(i).airplaneID + ": Booked\n");
			 }
		 }
		 return concatenatedString;
	}
	
	public StringBuilder planeStatus() {
		 StringBuilder concatenatedString = new StringBuilder();
		 for (int i=0; i<planes.size(); i++) {
			 concatenatedString.append(planes.get(i).displayState()+"\n");
		 }
		 return concatenatedString;
	}
}
