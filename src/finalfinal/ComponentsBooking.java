package finalfinal;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;

public class ComponentsBooking {

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ComponentsBooking window = new ComponentsBooking();
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
		Shell shell = new Shell();
		shell.setSize(1176, 592);
		shell.setText("SWT Application");
		
		Label lblGates = new Label(shell, SWT.BORDER);
		lblGates.setText("Gates:\n" + ControlLogic.groundNetwork.futureStatusGate().toString());
		lblGates.setBounds(10, 49, 260, 417);
		
		Label lblComponentsBookingwith = new Label(shell, SWT.NONE);
		lblComponentsBookingwith.setAlignment(SWT.CENTER);
		lblComponentsBookingwith.setText("Components booking (with time)");
		lblComponentsBookingwith.setBounds(240, 10, 311, 30);
		
		Label lblGates_1 = new Label(shell, SWT.BORDER);
		lblGates_1.setText("Taxiways:\n" + ControlLogic.groundNetwork.futureStatusTaxiways().toString());
		lblGates_1.setBounds(276, 49, 259, 411);
		
		Label lblGates_2 = new Label(shell, SWT.BORDER);
		lblGates_2.setText("Runways:\n" + ControlLogic.groundNetwork.futureStatusRunways().toString());
		lblGates_2.setBounds(541, 49, 260, 411);
		
		Label lblGates_2_1 = new Label(shell, SWT.BORDER);
		lblGates_2_1.setText("Airplanes:\n"+ControlLogic.planesList.planeBooking().toString());
		lblGates_2_1.setBounds(807, 49, 260, 411);

		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

}
