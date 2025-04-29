package finalfinal;

public class ControlLogic{
	Task task;
	protected static AirplaneList planesList= new AirplaneList();
	protected static Traffic_Network groundNetwork = new Traffic_Network();
	protected static FlightSchedule flights = new FlightSchedule();
	protected static Details flightDetails = new Details();
	/*List for primary label:
	 * 0. AIRPLANE_DEPARTURE_EVENT 
	 * 1. RUNWAY_EVENT
	 * 2. TAXIWAY_EVENT
	 * 3. GATE_EVENT
	 * 4. CONTROL_LOGIC
	 * 5. AIRPLANE_ARRIVAL_EVENT
	 * */
	
	/*List for secondary label:
	 * For 0. AIRPLANE_EVENT, first column for task, second column for airplane id:
	 * 		0. land_airplane // will tell that its in departure flow
	 * 		1. liftoff_airplane // will tell that its in arrival flow
	 * 		2. move_airplane_to_gate
	 * 		3. move_airplane_to_taxiway
	 * 		4. move_airplane_to_runway
	 * 		5. free_airplane
	 *		6. delete_airplane
	 *		
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
	 * 		2. hold runway booking
	 * 		3. hold taxiway booking
	 * 		4. hold gate booking
	 * */
	
	/*list for supplemental data:
	 * For 0:
	 * 		0. any data that comes denote the area it is coming from (y coordinate will be 100, aka out of bounds)
	 * 		1. shows destination it has to go to 
	 * 		2. move from where to where (link wise)
	 * 
	 * For 1-3 all points, supplemental data will tell which link exact
	 * 
	 * For 4, location of place traveling to or coming from
	 */	

	public ControlLogic() {
		task = null;
	}
	
	public void setTask(Task t) {
		task = t;
	}
	
	//airplane tasks left in run task
	public void runTask() {
		//a.k.a deciphering the task and calling appropriate method
		switch(task.primaryLabel) {
			case 0:
				switch(task.secondaryLabel[0]) {
				case 0:
					planeLand();
					break;
				case 1:
					planeLiftOff();
					break;
				case 2:
					planeMoveGate();
					break;
				case 3:
					planeMoveTaxiway();
					break;
				case 4:
					planeMoveRunway();
					break;
				case 5:
					planeFree();
					break;
				case 6:
					planeDelete();
					break;
				}
				break;
			case 1://RUNWAY EVENT
				switch(task.secondaryLabel[0]) {
					case 0:
						runwayFree();
						break;
					case 1:
						runwayBook();
						break;
				}
				break;
			case 2://TAXIWAY EVENT
				switch(task.secondaryLabel[0]) {
					case 0:
						taxiwayFree();
						break;
					case 1:
						taxiwayBook();
						break;
				}
				break;
			case 3://GATE EVENT
				switch(task.secondaryLabel[0]) {
					case 0:
						gateFree();
						break;
					case 1:
						gateBook();
						break;
				}
				break;
			case 4: //CONTROL LOGIC
				switch(task.secondaryLabel[0]) {
					case 0:
						startDeparture();
						break;
					case 1:
						startArrival();
						break;
					case 2:
						holdRunway();
				}
				break;
		}
	}
	
	//once task is complete, leave task empty for next task
	public void deleteTask() {
		task = null;
	}
	
	//control logic tasks
	public void startArrival() {
		//	FINAL APPROACH, FIND FREE RUNWAY FOR PLANE
		// 	on startArrival t we will create all the tasks for plane arrival, and add them to the taskQueue
		//	first we will create an airplane
		// 	find free runway and book it for airplane on arrival time
		// 	find free taxiway connected to runway and book it 10 mins after arrival time 
		//	change state of runway taxiway and airplane
		//	15 mins after arrival move plane to gate and start unloading
		// 	50 mins after arrival move plane away from gate
		task.display();
		System.out.println("Arrival");
		Airplane a = new Airplane();
		int id = a.airplaneID;
		a.booked=true;
		a.state=1;
		ControlLogic.planesList.addPlane(a);
		//Finding free runway and taxiway
		int freeRunway = groundNetwork.findFreeRunway();
		int freeTaxiway = groundNetwork.findFreeTaxiway(freeRunway);
		//book runway at arrival time
		groundNetwork.bookRunway(task.taskExecution, freeRunway);
		//create task for changing state of plane and runway
		Task planeMoveRunway = new Task(0,4,id,freeRunway,task.taskExecution);
		Task runwayBook = new Task(1,1,freeRunway,task.taskExecution);
		Main.mainTasks.addTask(planeMoveRunway);
		Main.mainTasks.addTask(runwayBook);
		//find time for plane to taxiway
		Time planeToTaxiway = task.taskExecution.addTime(1);
		//book taxiway
		groundNetwork.bookTaxiway(planeToTaxiway, freeTaxiway);
		//create task for changing state of plane runway and taxiway
		Task planeMoveTaxiway = new Task(0,3,id,freeTaxiway,planeToTaxiway);
		Task runwayFree = new Task(1,0,freeRunway,planeToTaxiway);
		Task taxiwayBook = new Task(2,1,freeTaxiway,planeToTaxiway);
		Main.mainTasks.addTask(planeMoveTaxiway);
		Main.mainTasks.addTask(runwayFree);
		Main.mainTasks.addTask(taxiwayBook);
		//move plane to gate //find time
		Time planeToGate = task.taskExecution.addTime(2);
		//book gate
		int g = groundNetwork.findFreeGate();
		groundNetwork.bookGate(planeToGate, g);
		//create tasks for moving plane, and changing state of taxiway and gate
		Task planeMoveGate = new Task(0,2,id,g,planeToGate);
		Task gateBook = new Task(3,1,g,planeToGate);
		Task taxiwayFree = new Task(2,0,freeTaxiway,planeToGate);
		Main.mainTasks.addTask(planeMoveGate);
		Main.mainTasks.addTask(gateBook);
		Main.mainTasks.addTask(taxiwayFree);
		//after unboarding, gate is free. change states of plane and gate
		Time planeFree = task.taskExecution.addTime(3);
		Task gateFree = new Task(3,0,g,planeFree);
		Task planeIsFree = new Task(0,5,id,planeFree);
		Main.mainTasks.addTask(gateFree);
		Main.mainTasks.addTask(planeIsFree);
		//add arrival to flight scheduler
		addArrival(id);
	}
	public void addArrival(int planeID) {
		Flight f = new Flight(false, task.supplementalData,task.taskExecution,planeID);
		flights.addFlight(f);
		flights.sortFlight();
	}
	
	public void startDeparture() {
		// on startDeparture t we will create all the tasks for plane departure, and add them to the taskQueue
		// tasks include the following:
		// find free gate and airplane
		// send airplane to free gate 50 mins before departure there and announce boarding starts
		// change states of airplane and gate
		// 15 mins before departure announce boarding has stopped
		// find free taxiway plus conjoined runway
		// move plane to taxiway
		// change state of plane, taxiway and gate
		// move plane to runway after 5 mins
		// change states of runway, taxiway and plane
		// on departure plane leaves
		System.out.println("Departure");
		//start with check for free gate, runway and taxiway. if one is not free then:
		//Finding free gate, taxiway and runway;		
		int g = groundNetwork.findFreeGate();
		int freeRunway = groundNetwork.findFreeRunway();
		int freeTaxiway = groundNetwork.findFreeTaxiway(freeRunway);
		Time planeToGate = task.taskExecution.subtractTime(flightDetails.minsBoarding);
		Time planeToTaxiway = task.taskExecution.subtractTime(flightDetails.minsTaxiway);
		Time planeToRunway = task.taskExecution.subtractTime(flightDetails.minsRunway);
		Time planeDelete = task.taskExecution.addTime(flightDetails.minsTakeOff);
		System.out.println(freeRunway);
		if(freeRunway==100) {//out of range, meaning no free runways
			int freeFutureRunway = groundNetwork.findFirstFreeRunway();
			Time holdTime;
			if (!groundNetwork.futureRunwaysTime[freeFutureRunway].greaterThan(planeToRunway)) {
				holdTime = groundNetwork.futureRunwaysTime[freeFutureRunway].addTime((flightDetails.minsRunway+1)*groundNetwork.bookRunway);
				if(holdTime.lessThan(planeToRunway)) {
					Task holdRunway = new Task(4,2,freeFutureRunway, holdTime, planeToRunway);
					Main.mainTasks.addTask(holdRunway);
				}
			}
		}else {
			groundNetwork.bookRunway(planeToRunway, freeRunway);
		}
		
			//ideal scenario, we have all three completely free
			//sending airplane to gate 
			//booking gate for future operation
			groundNetwork.bookGate(planeToGate, g);
			int f = planesList.findFreePlane();
			int planeID = planesList.getID(f);
			planesList.planes.get(f).bookAirplane();
			Task planeMoveGate = new Task(0,2,planeID,g,planeToGate);
			Task gateBook = new Task(3,1,g,planeToGate);
			Main.mainTasks.addTask(planeMoveGate);
			Main.mainTasks.addTask(gateBook);
			groundNetwork.bookTaxiway(planeToTaxiway, freeTaxiway);
			//move plane to taxiway
			Task planeMoveTaxiway = new Task(0,3,planeID,freeTaxiway,planeToTaxiway);
			Main.mainTasks.addTask(planeMoveTaxiway);
			//change states of taxiway, and gate
			Task gateFree = new Task(3,0,g,planeToTaxiway);	
			Task taxiwayBook = new Task(2,1,freeTaxiway,planeToTaxiway);
			Main.mainTasks.addTask(gateFree);
			Main.mainTasks.addTask(taxiwayBook);
			//move plane to runway
			Task planeMoveRunway = new Task(0,4,planeID,freeRunway,planeToRunway);
			Main.mainTasks.addTask(planeMoveRunway);
			//change states of runway and taxiway
			Task taxiwayFree = new Task(2,0,freeTaxiway,planeToRunway);
			Task runwayBook = new Task(1,1,freeRunway,planeToRunway);
			Main.mainTasks.addTask(taxiwayFree);
			Main.mainTasks.addTask(runwayBook);
			//plane departure
			//since the task t is only helping us know when to do plane departure task, we will need to make a runway free task and plane departure task sahi wala
			Task runwayFree = new Task(1,0,freeRunway,task.taskExecution);
			Task planeDeparture = new Task(0,1,planeID,task.supplementalData, task.taskExecution);
			Main.mainTasks.addTask(runwayFree);
			Main.mainTasks.addTask(planeDeparture);
			Task planeDeletion = new Task(0,6,planeID,planeDelete);
			Main.mainTasks.addTask(planeDeletion);
			addDeparture(planeID);
	}
	public void addDeparture(int planeID) {
		Flight f = new Flight(true, task.supplementalData,task.taskExecution,planeID);
		flights.addFlight(f);
		flights.sortFlight();
	}
	
	public void holdRunway() {
		if (!groundNetwork.futureRunways[task.supplementalData]) {
			groundNetwork.bookRunway(task.holdTime,task.supplementalData);
		}else {
			
		}
		
	}
	//plane tasks
	/*orientation/state list:
	0. creation (meaning ke its doing nothing rn, the default orientation
	1. plane is landing
	2. plane is lifting off
	3. Touchdown/Takeoff  (at runway)
	4. Taxiing plane is coming in or out of taxiway
	5. Loading (at gates to load/unload)
	6. Holding (pausing operation for other stuff if necessary)
	*/

	public void addPlane(Airplane a) {
		planesList.addPlane(a);
	}
	public void planeMoveGate() {
		//changing airplane state and gate state
		int planeIndex = planesList.findPlane(task.secondaryLabel[1]);
		planesList.movePlane(planeIndex, 5);
		planesList.setCurrentPoint(planeIndex, task.supplementalData);
	}
	public void planeMoveTaxiway() {
		//changing airplane state and gate state
		int planeIndex = planesList.findPlane(task.secondaryLabel[1]);
		planesList.movePlane(planeIndex, 4);
		planesList.setCurrentPoint(planeIndex, task.supplementalData);
	}
	public void planeMoveRunway() {
		//changing airplane state and gate state
		int planeIndex = planesList.findPlane(task.secondaryLabel[1]);
		if(planesList.planes.get(planeIndex).currentPoint==1) {
			//if it was in arrival flow
			flights.deleteFlight();
		}
		planesList.movePlane(planeIndex, 3);
		planesList.setCurrentPoint(planeIndex, task.supplementalData);
	}
	public void planeLiftOff() {
		//change plane state + delete plane	
		int planeIndex = planesList.findPlane(task.secondaryLabel[1]);
		planesList.movePlane(planeIndex, 2);
	}
	public void planeLand() {
		int planeIndex =planesList.findPlane(task.secondaryLabel[1]);
		planesList.movePlane(planeIndex, 1);
	}
	public void planeFree() {
		int planeIndex = planesList.findPlane(task.supplementalData);
		planesList.movePlane(planeIndex, 0);
	}
	public void planeDelete() {
		int planeIndex = planesList.findPlane(task.supplementalData);
		planesList.deletePlane(planeIndex);
		flights.flights.remove(0);
	}
	
	// gate tasks
	public void gateFree() {
		groundNetwork.changeGateState(task.supplementalData, false);
		groundNetwork.unbookGate(task.supplementalData);
	}
	public void gateBook() {
		groundNetwork.changeGateState(task.supplementalData,true);
	}
	
	//taxiway tasks
	public void taxiwayFree() {
		groundNetwork.changeTaxiwayState(task.supplementalData, false);
		groundNetwork.unbookTaxiway(task.supplementalData);
	}
	public void taxiwayBook() {
		groundNetwork.changeTaxiwayState(task.supplementalData, true);
	}
	
	//runway tasks
	public void runwayFree() {
		groundNetwork.changeRunwayState(task.supplementalData, false);
		groundNetwork.unbookRunway(task.supplementalData);
	}
	public void runwayBook() {
		groundNetwork.changeRunwayState(task.supplementalData, true);
		groundNetwork.bookRunway(task.taskExecution, task.supplementalData);
	}
		
}
