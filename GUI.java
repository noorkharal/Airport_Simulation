package finalfinal;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class GUI {
	private Display display;
    private Shell shell;
    private Label timerLabel;
    private Button startButton;
    private Button stopButton;
    private Button resetButton;
    private Button btnCheckComponentsBooking;
    protected static Global_Clock clock = new Global_Clock(0,0,0);
    public static void main(String[] args) {
        new GUI().run();
    }
    
    

    public void run() {
        display = new Display();
        shell = new Shell(display);
        shell.setSize(1091, 631);
        shell.setText("The REAL Toxic People Airport ~ Islamabad");

        timerLabel = new Label(shell, SWT.NONE);
        timerLabel.setBounds(10, 10, 63, 30);
        timerLabel.setText(clock.getCurrentTime());

        startButton = new Button(shell, SWT.PUSH);
        startButton.setBounds(79, 5, 80, 30);
        startButton.setText("Start");
        startButton.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseDoubleClick(MouseEvent e) {
        		clock.startClock2();
        		 Thread clockThread = new Thread(clock);
        	     clockThread.start();
        	     updateTimer.run();
        	     display.timerExec(100, updateTimer);
        	     startButton.setEnabled(false);
        	     stopButton.setEnabled(true);
        	     btnAddArrival.setEnabled(true);
        	     btnAddDeparture.setEnabled(true);
        	     btnCheckComponentsBooking.setEnabled(true);
        	     btnTaskQueue.setEnabled(true);
        	     btnAddAirplane.setEnabled(true);
        	     btnChangeDetails.setEnabled(true);
        	}
        });

        stopButton = new Button(shell, SWT.PUSH);
        stopButton.setBounds(166, 5, 80, 30);
        stopButton.setText("Stop");
        stopButton.setEnabled(false);
        stopButton.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseDoubleClick(MouseEvent e) {
        		 clock.isRunning = false;
        	     startButton.setEnabled(true);
        	     stopButton.setEnabled(false);
        	}
        });
        resetButton = new Button(shell, SWT.PUSH);
        resetButton.setBounds(252, 5, 80, 30);
        resetButton.setText("Reset");
        
        btnAddArrival = new Button(shell, SWT.NONE);
        btnAddArrival.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseDoubleClick(MouseEvent e) {
        		try {
					planeArrivalAdd window = new planeArrivalAdd();
					window.open();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
        	}
        });
        btnAddArrival.setEnabled(false);
        btnAddArrival.setText("Add Arrival");
        btnAddArrival.setBounds(933, 5, 130, 30);
        
        btnAddDeparture = new Button(shell, SWT.NONE);
        btnAddDeparture.setEnabled(false);
        btnAddDeparture.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseDoubleClick(MouseEvent e) {
				try {
					planeDepartureAdd window = new planeDepartureAdd();
					window.open();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
        });
        btnAddDeparture.setText("Add Departure");
        btnAddDeparture.setBounds(933, 41, 130, 30);
        
        btnChangeDetails = new Button(shell, SWT.NONE);
        btnChangeDetails.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseDoubleClick(MouseEvent e) {
            		try {
    					ChangeDetailsForm window = new ChangeDetailsForm();
    					window.open();
    				} catch (Exception e1) {
    					e1.printStackTrace();
    				}
        	}
        });
        btnChangeDetails.setEnabled(false);
        btnChangeDetails.setText("Change Details");
        btnChangeDetails.setBounds(933, 79, 130, 30);
        
        lblFlightSchedule = new Label(shell, SWT.NONE);
        lblFlightSchedule.setText("Flight Schedule:");
        lblFlightSchedule.setBounds(10, 94, 146, 190);
        
        lblCurrentComponentsStatus = new Label(shell, SWT.NONE);
        lblCurrentComponentsStatus.setText("Current Components Status:");
        lblCurrentComponentsStatus.setBounds(354, 54, 197, 30);
        
        btnCheckComponentsBooking = new Button(shell, SWT.WRAP);
        btnCheckComponentsBooking.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseDoubleClick(MouseEvent e) {
        		try {
					ComponentsBooking window = new ComponentsBooking();
					window.open();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
        	}
        });
        btnCheckComponentsBooking.setEnabled(false);
        btnCheckComponentsBooking.setBounds(933, 115, 130, 63);
        btnCheckComponentsBooking.setText("Check Components \nBooking");
        
        lblGates = new Label(shell, SWT.BORDER);
        lblGates.setText("Gates:");
        lblGates.setBounds(166, 93, 146, 262);
        
        lblGates_1 = new Label(shell, SWT.BORDER);
        lblGates_1.setText("Taxiways:");
        lblGates_1.setBounds(318, 93, 146, 262);
        
        lblGates_2 = new Label(shell, SWT.BORDER);
        lblGates_2.setText("Runways:");
        lblGates_2.setBounds(470, 93, 146, 262);
        
        lblGates_3 = new Label(shell, SWT.BORDER);
        lblGates_3.setText("Airplanes:");
        lblGates_3.setBounds(620, 93, 197, 262);
        
        btnAddAirplane = new Button(shell, SWT.NONE);
        btnAddAirplane.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseDoubleClick(MouseEvent e) {
        		Airplane a = new Airplane();
        		ControlLogic.planesList.addPlane(a);
        	}
        });
        btnAddAirplane.setText("Add Airplane");
        btnAddAirplane.setEnabled(false);
        btnAddAirplane.setBounds(933, 181, 130, 30);
        
        btnTaskQueue = new Button(shell, SWT.NONE);
        btnTaskQueue.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseDoubleClick(MouseEvent e) {
        		try {
					TaskQueueChecl window = new TaskQueueChecl();
					window.open();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
        	}
        });
        btnTaskQueue.setText("Task Queue");
        btnTaskQueue.setEnabled(false);
        btnTaskQueue.setBounds(933, 214, 130, 30);
        resetButton.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseDoubleClick(MouseEvent e) {
        		clock.setTime(0, 0, 0);
        		clock.isRunning = false;;
                timerLabel.setText(clock.getCurrentTime());
                startButton.setEnabled(true);
                stopButton.setEnabled(false);
                Main.mainTasks=new TaskQueue();
                ControlLogic.flightDetails = new Details();
                ControlLogic.flights = new FlightSchedule();
                ControlLogic.groundNetwork = new Traffic_Network();
                ControlLogic.planesList = new AirplaneList();
        	}
        });
        
     // Create an image
        //Image image = new Image(display, "\"C:\\Users\\noora\\Downloads\\LABFINAL0.png\"");
        shell.open();

        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        
        //image.dispose();
        display.dispose();
    }

    private Runnable updateTimer = new Runnable(){
    	@Override
    	public void run() {
    		if(!clock.isRunning) return;
            timerLabel.setText(clock.getCurrentTime());
//            lblTaskQueue.setText(Main.mainTasks.getInt());
            lblGates.setText(ControlLogic.groundNetwork.currentStatusGate().toString());
            lblGates_1.setText(ControlLogic.groundNetwork.currentStatusTaxiways().toString());
            lblGates_2.setText(ControlLogic.groundNetwork.currentStatusRunways().toString());
            lblGates_3.setText(ControlLogic.planesList.planeStatus().toString());
            lblFlightSchedule.setText(ControlLogic.flights.flightDisplay().toString());
            display.timerExec(100, updateTimer);
    	}
    };
    
    private Button btnAddArrival;
    private Button btnAddDeparture;
    private Button btnChangeDetails;
    private Label lblFlightSchedule;
    private Label lblCurrentComponentsStatus;
    private Label lblGates;
    private Label lblGates_1;
    private Label lblGates_2;
    private Label lblGates_3;
    private Button btnAddAirplane;
    private Button btnTaskQueue;
}
