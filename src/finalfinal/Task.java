package finalfinal;

public class Task {
	protected int primaryLabel;
	protected int[] secondaryLabel;
	protected int supplementalData;
	protected Time taskExecution;
	protected Time holdTime;
	
	/*List for primary label:
	 * 0. AIRPLANE_EVENT 
	 * 1. RUNWAY_EVENT
	 * 2. TAXIWAY_EVENT
	 * 3. GATE_EVENT
	 * 4. CONTROL_LOGIC
	 * */
	
	/*List for secondary label:
	 * For 0. AIRPLANE_EVENT, first index for task, second index for airplane id:
	 * 		0. land_airplane // will tell that its in departure flow
	 * 		1. liftoff_airplane // will tell that its in arrival flow
	 * 		2. move_airplane_to_gate
	 * 		3. move_airplane_to_taxiway
	 * 		4. move_airplane_to_runway
	 * 		5. hold_airplane
	 *		6. park_airplane
	 * 
	 * For 1. RUNWAY_EVENT:
	 * 		0. state_empty
	 * 		1. state_busy
	 * 
	 * For 2. TAXIWAY_EVENT:
	 * 		0. state_empty
	 * 		1. state_busy
	 * 
	 * For 3. GATE_EVENT:
	 * 		0. state_empty
	 * 		1. state_busy
	 * 
	 * For 4. CONTROL_LOGIC:
	 * 		0. start departure
	 * 		1. start arrival
	 * 
	 * */
	
	/*list for supplemental data:
	 * For 0:
	 * 		0. any data that comes denote the area it is coming from (y coordinate will be 100, aka out of bounds)
	 * 		1. shows destination it has to go to 
	 * 		2. move from where to where (link wise)
	 * 		3. hold, for how long
	 * 		4. park where
	 * 
	 * For 1-3 all points, supplemental data will tell which link exact
	 * 
	 * For 4, garbage?
	 */	
	
	public Task(int pl, int sl, int sd, Time t) {
		primaryLabel=pl;
		secondaryLabel = new int[1];
		secondaryLabel[0] = sl;
		supplementalData=sd;
		taskExecution = t;
		holdTime=null;
	}
	
	public Task(int pl, int sl, int id,int sd, Time t) {
		primaryLabel=pl;
		secondaryLabel = new int[2];
		secondaryLabel[0] = sl;
		secondaryLabel[1] =id;
		supplementalData=sd;
		taskExecution = t;
		holdTime=null;
	}
	
	public Task(int pl, int sl, int sd, Time t, Time h) {
		primaryLabel=pl;
		secondaryLabel = new int[1];
		secondaryLabel[0] = sl;
		supplementalData=sd;
		taskExecution = t;
		holdTime=h;
	}
	
	public void display() {
		if( (primaryLabel!=0)) {
			System.out.println("Primary: " + primaryLabel);
			System.out.println("Secondary: " + secondaryLabel[0]);
			System.out.println("Supplemental: " + supplementalData );
			System.out.println("Time: " + taskExecution.hour +":" + taskExecution.min + ":" + taskExecution.sec);
		}else {
			System.out.println("Primary: " + primaryLabel);
			System.out.println("Secondary: " + secondaryLabel[0]);
			System.out.println("Airplane ID: " + secondaryLabel[1]);
			System.out.println("Supplemental: " + supplementalData );
			System.out.println("Time: " + taskExecution.hour +":" + taskExecution.min + ":" + taskExecution.sec);
		}
	}
	
	public StringBuilder tasksTranslation() {
		StringBuilder s = new StringBuilder();
		switch(primaryLabel) {
		case 4:
			if(secondaryLabel[0]==0) {
				s.append("Control Logic Task, Departure Task, Area: "+supplementalData);
			}else if(secondaryLabel[0]==1){
				s.append("Control Logic Task, Arrival Task, Area: "+supplementalData);
			}else {
				s.append("Control Logic Task, Hold Runway Task, Runway No.: "+supplementalData);
				s.append("/n runway book time: " +holdTime.getTime());
			}
			break;
		case 3:
			if(secondaryLabel[0]==0) {
				s.append("Gate Task, Gate No. "+ supplementalData +" is free");
			}else {
				s.append("Gate Task, Gate No. "+ supplementalData +" is busy");
			}
			break;
		case 2:
			if(secondaryLabel[0]==0) {
				s.append("Taxiway Task, Taxiway No. "+ supplementalData +" is free");
			}else {
				s.append("Taxiway Task, Taxiway No. "+ supplementalData +" is busy");
			}
			break;
		case 1:
			if(secondaryLabel[0]==0) {
				s.append("Runway Task, Runway No. "+ supplementalData +" is free");
			}else {
				s.append("Runway Task, Runway No. "+ supplementalData + " is busy");
			}
			break;
		case 0:
			if(secondaryLabel[0]==0) {
				s.append("Airplane Task, Airplane No. "+ secondaryLabel[1]+" is landing");
			}else if(secondaryLabel[0]==1) {
				s.append("Airplane Task, Airplane No. "+ secondaryLabel[1]+" is taking off");
			}else if(secondaryLabel[0]==2) {
				s.append("Airplane Task, Airplane No. "+ secondaryLabel[1]+" is moving to Gate No." + supplementalData);
			}else if(secondaryLabel[0]==3) {
				s.append("Airplane Task, Airplane No. "+ secondaryLabel[1]+" is moving to Taxiway No." + supplementalData);
			}else if(secondaryLabel[0]==4) {
				s.append("Airplane Task, Airplane No. "+ secondaryLabel[1]+" is moving to Runway No." + supplementalData);
			}else if(secondaryLabel[0]==5) {
				s.append("Airplane Task, Airplane No. "+ supplementalData+" is free");
			}else if(secondaryLabel[0]==6) {
				s.append("Airplane Task, Airplane No. "+ supplementalData+" is deleted");
			}
		}
		s.append(" Time: " +taskExecution.getTime() +"\n");
		return s;
	}
	
	
}
