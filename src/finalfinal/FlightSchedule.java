package finalfinal;

import java.util.ArrayList;
import java.util.Collections;

public class FlightSchedule {
	protected ArrayList<Flight> flights = new ArrayList<Flight>();

	public FlightSchedule() {
		
	}
	
	public void addFlight(Flight f) {
		flights.add(f);
	}
	
	public void sortFlight() {
		for (int i =0; i< flights.size(); i++) {
			for (int j = i+1; j<flights.size();j++ ) {
				if(!flights.get(j).planeTime.greaterThan(flights.get(i).planeTime)) {
					Collections.swap(flights, i, j);
				}
			}
		}
	}
	
	public void deleteFlight() {
		flights.remove(0);
	}
	
	public StringBuilder flightDisplay() {
		StringBuilder s = new StringBuilder();
		for(int i=0; i<flights.size();i++) {
			s.append(flights.get(i).flightDetails().toString());
		}
		return s;
	}
}
