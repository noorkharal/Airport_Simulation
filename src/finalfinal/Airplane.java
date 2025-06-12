package finalfinal;

public class Airplane {
	protected int airplaneID;
	protected int currentPoint;
	protected static int noOfAirplanes;
	protected int state;
	protected Destination destination;
	protected boolean booked;
	/*Current point states where it is in the whole groundnetwork
	 * it is garbage for state 1.
	 * can be used in conjunction with state to find exact location
	 * */
	
	/*orientation/state list:
	0. creation (meaning ke its doing nothing rn, the default orientation
	1. plane is landing
	2. plane is lifting off
	3. Touchdown/Takeoff  (at runway)
	4. Taxiing plane is coming in or out of taxiway
	5. Loading (at gates to load/unload)
	6. Holding (pausing operation for other stuff if necessary)
	*/
	
	public Airplane() {
		airplaneID = noOfAirplanes;
		noOfAirplanes++;
		state =0;
		booked = false;
	}
	
	public Airplane(int s) {
		airplaneID = noOfAirplanes;
		noOfAirplanes++;
		state =s;
		booked = false;
	}
	
	public void move(int i) {
		//set new orientation
		state = i;
	}
	
	
	public void setDestination(Destination d) {
		destination =d;
	}
	
	public boolean isFree() {
		if(!booked) {
			return true;
		}
		return false;
	}
	
	public void setCurrentPoint(int cp) {
		currentPoint = cp;
	}
	
	public void bookAirplane() {
		booked = true;
	}
	
	public void unbookAirplane() {
		booked = false;
	}
	
	public String displayStatus() {
		String plane;
		if(booked) {
			plane = "Plane no. " +airplaneID + " is booked";
		}else {
			plane = "Plane no. " +airplaneID + " is free";
		}
		return plane;
	}
	
	public String displayState() {
		String plane = null;
		switch(state) {
		case 0: 
			plane = "Plane no. " +airplaneID + " is free";
			break;
		case 1:
			plane = "Plane no. " +airplaneID + " has not\narrived";
			break;
		case 2:
			plane = "Plane no. " +airplaneID + " is in air";
			break;
		case 3:
			plane = "Plane no. " + airplaneID + " is on\n Runway no. " + currentPoint;
			break;
		case 4:
			plane = "Plane no. " + airplaneID + " is on\n Taxiway no. " + currentPoint;
			break;
		case 5:
			plane = "Plane no. " + airplaneID + " is on\n Gate no. " + currentPoint;
			break;
		
		}
		return plane;
	}
}
