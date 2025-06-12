package finalfinal;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.ColorDescriptor;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.jface.resource.FontDescriptor;

public class planeDepartureAdd {

	protected Shell shell;
	private LocalResourceManager localResourceManager;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			planeDepartureAdd window = new planeDepartureAdd();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		createResourceManager();
		shell.setSize(450, 195);
		shell.setText("Add Plane Departure");
		
		final Combo combo = new Combo(shell, SWT.NONE);
		combo.setItems(new String[] {"Karachi", "Multan", "Quetta", "Turbat", "Skardu", "Sukkur", "Gilgit", "Lahore", "Faisalabad", "Gwadar", "Sialkot", "Peshawar", "Rahim Yar Khan ", "Bahawalpur"});
		combo.setBounds(88, 10, 97, 28);
		combo.setText("Location");
		
		final Combo combo_1 = new Combo(shell, SWT.NONE);
		combo_1.setItems(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"});
		combo_1.setBounds(118, 52, 63, 28);
		
		final Combo combo_2 = new Combo(shell, SWT.NONE);
		combo_2.setItems(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"});
		combo_2.setBounds(232, 52, 63, 28);
		
		final Combo combo_3 = new Combo(shell, SWT.NONE);
		combo_3.setItems(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"});
		combo_3.setBounds(349, 52, 63, 28);
		
		final Label lblNewLabel = new Label(shell, SWT.WRAP);
		lblNewLabel.setFont(localResourceManager.create(FontDescriptor.createFrom("Segoe UI", 9, SWT.ITALIC)));
		lblNewLabel.setForeground(localResourceManager.create(ColorDescriptor.createFrom(new RGB(255, 0, 0))));
		lblNewLabel.setBounds(12, 123, 410, 28);
		lblNewLabel.setText("Error This time will create errors for file, choose another time");
		lblNewLabel.setVisible(false);

	
		Button btnSubmit = new Button(shell, SWT.NONE);
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				int f = ControlLogic.planesList.findFreePlane();
				if(f==100) {
					Airplane a = new Airplane();
					ControlLogic.planesList.addPlane(a);
				}
				int hours = combo_1.getSelectionIndex();
				int minutes = combo_2.getSelectionIndex();
				int seconds = combo_3.getSelectionIndex();
				Time t = new Time(hours,minutes,seconds);
				if (t.subtractTime(ControlLogic.flightDetails.minsBoarding).greaterThan(GUI.clock.globalTime)) {
					int location = combo.getSelectionIndex();
					int c = location;
					Task temp = new Task(4,1,c, t);
					Main m1 = new Main();
					m1.mainBrain.setTask(temp);
					m1.mainBrain.startDeparture();
					shell.close();
				}else {
					lblNewLabel.setVisible(true);
				}
			}
		});
		btnSubmit.setText("Submit");
		btnSubmit.setBounds(153, 87, 90, 30);
		
		Label lblLocation = new Label(shell, SWT.NONE);
		lblLocation.setBounds(12, 13, 70, 20);
		lblLocation.setText("Location:");
		
		Label lblto = new Label(shell, SWT.NONE);
		lblto.setText("(to)");
		lblto.setBounds(191, 13, 70, 20);
		
		Label lblTime = new Label(shell, SWT.NONE);
		lblTime.setText("Time:");
		lblTime.setBounds(12, 55, 42, 20);
		
		Label lblHours = new Label(shell, SWT.NONE);
		lblHours.setText("Hours:");
		lblHours.setBounds(68, 55, 44, 20);
		
		Label lblMins = new Label(shell, SWT.NONE);
		lblMins.setText("Mins:");
		lblMins.setBounds(187, 55, 44, 20);
		
		Label lblSecs = new Label(shell, SWT.NONE);
		lblSecs.setText("Secs:");
		lblSecs.setBounds(303, 55, 44, 20);
		
		}
	private void createResourceManager() {
		localResourceManager = new LocalResourceManager(JFaceResources.getResources(),shell);
	}
}
