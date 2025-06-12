package finalfinal;

public class Flight {
	protected boolean departure; // if true; plane is in departure flow, else it is in arrival flow
	protected String location;
	protected Time planeTime;
	protected int planeID;
	
	/*Karachi
Multan
Quetta
Turbat
Skardu
Sukkur
Gilgit*/
	
	public Flight(boolean d, int l, Time pt, int id) {
		departure =d;
		planeTime = pt;
		planeID= id;
		switch(l) {
		case 0:
			location = "Karachi";
			break;
		case 1:
			location = "Multan";
			break;
		case 2:
			location = "Quetta";
			break;
		case 3:
			location = "Turbat";
			break;
		case 4:
			location = "Skardu";
			break;
		case 5:
			location = "Sukkur";
			break;
		case 6:
			location = "Gilgit";
			break;
		}
	}
	
	public StringBuilder flightDetails() {
		StringBuilder s = new StringBuilder();
		if(departure) {
			s.append("Departure, Plane no."+planeID+ "\nLocation: "+location+"\nTime:" + planeTime.getTime()+"\n");
		}else {
			s.append("Arrival, Plane no. "+planeID+"\nComing from: "+location+"\nTime:" + planeTime.getTime()+"\n");
		}
		
		return s;
	}
}
