package finalfinal;
public class Traffic_Network {
	//for current operation
	protected boolean[] runways; // for each runway, two ends
	protected boolean[] taxiways; // for each taxiway, two ends
	protected boolean[] gates;
	
	//for future bookings + time they're booked at
	protected boolean[] futureRunways;
	protected Time[] futureRunwaysTime;
	protected int bookRunway;
	protected boolean[] futureTaxiways;
	protected Time[] futureTaxiwaysTime;
	protected boolean[] futureGates;
	protected Time[] futureGatesTime;
	
	//initialization
	public Traffic_Network() {
		runways = new boolean[4];
		taxiways = new boolean[8];
		gates = new boolean[8];
		
		futureRunways = new boolean[4];
		futureTaxiways = new boolean[8];
		futureGates = new boolean[8];
		
		futureRunwaysTime = new Time[4];
		futureTaxiwaysTime = new Time[8];
		futureGatesTime = new Time[8];
	}
	
	//finding unbooked areas for operation
	public int findFreeRunway() {
		int runwayC = 100;
		for(int i=0;i<4;i++) {
			if(futureRunways[i]==false) {
				runwayC = i;
			}
		}
		return runwayC;
	}	
	public int findFreeTaxiway(int r) {
		// r shows which runway the taxiway should be connected to
		// r [0][] is connected to taxiway[0] to taxiway[3]
		// r [1][] is connected to taxiway[4] to taxiway[7]
		int taxiwayC = 100;
		//for runway 0 and 1
		if((r==0)||(r==1)) {
			for(int i=0;i<3;i++) {
				if((!futureTaxiways[i])) {
					taxiwayC = i;
				}
			}
		}else {
			//for runway 2 and 3
			for(int i=4;i<8;i++) {
				if((!futureTaxiways[i])) {
					taxiwayC = i;
				}
			}
		}
		return taxiwayC;
	}
	public int findFreeGate() {
		int gateC = 100;
		for(int i=0;i<8;i++) {
			if(!futureGates[i]) {
				gateC = i;
			}
		}
		return gateC;
	}
	
	//if no free areas, then find the areas that would be free first
	public int findFirstFreeRunway() {
		bookRunway++;
		Time min = new Time(23,59,59);
		int minIndex =0;
		for(int i=0; i<4;i++) {
			if(!futureRunwaysTime[i].greaterThan(min)) {
				min = futureRunwaysTime[i];
				minIndex =i;
			}
		}
		System.out.println(min.getTime());
		return minIndex;
	}
	//booking areas in network for the future
	public void bookRunway(Time t, int c) {
		futureRunways[c] = true;
		futureRunwaysTime[c] = t;
	}
	public void bookTaxiway(Time t, int c) {
		futureTaxiways[c] = true;
		futureTaxiwaysTime[c] = t;
	}
	public void bookGate(Time t, int c) {
		futureGates[c] = true;
		futureGatesTime[c] = t;
	}
	
	//unbook after its been used a.k.a work is done
	public void unbookRunway(int c) {
		futureRunways[c] = false;
		futureRunwaysTime[c] = null;
	}
	public void unbookTaxiway(int c) {
		futureTaxiways[c] = false;
		futureTaxiwaysTime[c] = null;
	}
	public void unbookGate(int c) {
		futureGates[c] = false;
		futureGatesTime[c] = null;
	}
	
	//change state (just in case if its needed)
	public void changeRunwayState(int c, boolean state) {
		runways[c]=state;
	}
	public void changeTaxiwayState(int c, boolean state) {
		taxiways[c]=state;
	}
	public void changeGateState(int x, boolean state) {
		gates[x]=state;
	}
	
	//get current status as string to display on gui
	public StringBuilder currentStatusGate() {
		 StringBuilder concatenatedString = new StringBuilder();
		 for (int i=0; i<8; i++) {
			 if(gates[i]) {
				 concatenatedString.append("Gate no. " + i + ": busy\n");
			 }else {
				 concatenatedString.append("Gate no. " + i + ": free\n");
			 }
		 }
		 return concatenatedString;
	}
	public StringBuilder currentStatusTaxiways() {
		 StringBuilder concatenatedString = new StringBuilder();
		 for (int i=0; i<8; i++) {
			 if(taxiways[i]) {
				 concatenatedString.append("Taxiway no. " + i + ": busy\n");
			 }else {
				 concatenatedString.append("Taxiway no. " + i + ": free\n");
			 }
		 }
		 return concatenatedString;
	}
	public StringBuilder currentStatusRunways() {
		 StringBuilder concatenatedString = new StringBuilder();
		 for (int i=0; i<4; i++) {
			 if(runways[i]) {
				 concatenatedString.append("Runway no. " + i + ": busy\n");
			 }else {
				 concatenatedString.append("Runway no. " + i + ": free\n");
			 }
		 }
		 return concatenatedString;
	}

	//get bookings as string to display on components booking
	public StringBuilder futureStatusGate() {
		 StringBuilder concatenatedString = new StringBuilder();
		 for (int i=0; i<8; i++) {
			 if(futureGates[i]) {
				 concatenatedString.append("Gate no. " + i + ": Booked\n");
				 concatenatedString.append("Time: " + futureGatesTime[i].getTime() + "\n");
			 }else {
				 concatenatedString.append("Gate no. " + i + ": Not booked\n");
			 }
		 }
		 return concatenatedString;
	}
	public StringBuilder futureStatusTaxiways() {
		 StringBuilder concatenatedString = new StringBuilder();
		 for (int i=0; i<8; i++) {
			 if(futureTaxiways[i]) {
				 concatenatedString.append("Taxiway no. " + i + ": Booked\n");
				 concatenatedString.append("Time: " + futureTaxiwaysTime[i].getTime() + "\n");
			 }else {
				 concatenatedString.append("Taxiway no. " + i + ": Not Booked\n");
			 }
		 }
		 return concatenatedString;
	}
	public StringBuilder futureStatusRunways() {
		 StringBuilder concatenatedString = new StringBuilder();
		 for (int i=0; i<4; i++) {
			 if(futureRunways[i]) {
				 concatenatedString.append("Runway no. " + i + ": Booked\n");
				 concatenatedString.append("Time: " + futureRunwaysTime[i].getTime() + "\n");
			 }else {
				 concatenatedString.append("Runway no. " + i + ": Not Booked\n");
			 }
		 }
		 return concatenatedString;
	}

}