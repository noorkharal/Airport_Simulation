package finalfinal;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.ColorDescriptor;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class ChangeDetailsForm {

	protected Shell shell;
	private LocalResourceManager localResourceManager;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ChangeDetailsForm window = new ChangeDetailsForm();
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
		shell.setSize(450, 365);
		shell.setText("SWT Application");
		
		Label lblBoarding = new Label(shell, SWT.NONE);
		lblBoarding.setBounds(20, 43, 115, 20);
		lblBoarding.setText("Boarding start:");
		
		Label lblTitle = new Label(shell, SWT.NONE);
		lblTitle.setBounds(20, 10, 389, 20);
		lblTitle.setText("Choose how many minutes before departure does:");
		
		Label lblBoardingEnds = new Label(shell, SWT.NONE);
		lblBoardingEnds.setText("Boarding ends:");
		lblBoardingEnds.setBounds(20, 88, 115, 20);
		
		Label lblPlaneReachRunway = new Label(shell, SWT.NONE);
		lblPlaneReachRunway.setText("Plane reach runway:");
		lblPlaneReachRunway.setBounds(20, 135, 174, 20);
		
		final Combo combo_2 = new Combo(shell, SWT.NONE);
		combo_2.setItems(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"});
		combo_2.setBounds(126, 40, 63, 28);
		
		final Combo combo_2_1 = new Combo(shell, SWT.NONE);
		combo_2_1.setItems(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"});
		combo_2_1.setBounds(126, 85, 63, 28);
		
		final Combo combo_2_1_1 = new Combo(shell, SWT.NONE);
		combo_2_1_1.setItems(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"});
		combo_2_1_1.setBounds(155, 132, 63, 28);
		
		Label lblChooseHowMany = new Label(shell, SWT.WRAP);
		lblChooseHowMany.setText("Choose how many minutes after departure does the plane get deleted:");
		lblChooseHowMany.setBounds(20, 176, 186, 67);
		
		final Combo combo_2_1_1_1 = new Combo(shell, SWT.NONE);
		combo_2_1_1_1.setItems(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"});
		combo_2_1_1_1.setBounds(212, 196, 63, 28);
		
		final Label lblError = new Label(shell, SWT.NONE);
		lblError.setForeground(localResourceManager.create(ColorDescriptor.createFrom(new RGB(255, 0, 0))));
		lblError.setText("Error: Values entered will cause an error");
		lblError.setBounds(20, 285, 174, 20);
		lblError.setVisible(false);

		Button btnSubmit = new Button(shell, SWT.NONE);
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				/*minsBoarding = 3;
		minsTaxiway = 2;
		minsRunway = 1;
		minsTakeOff = 1;
				 * */
				int minsB = combo_2.getSelectionIndex();
				int minsT = combo_2_1.getSelectionIndex();
				int minsR = combo_2_1_1.getSelectionIndex();
				int minsTO = combo_2_1_1_1.getSelectionIndex();
				if((minsB<minsT)||(minsT<minsR)) {
					lblError.setVisible(true);
				}else {
					ControlLogic.flightDetails.changeDetails(minsB, minsT, minsR, minsTO);
					shell.close();
				}
			}
		});
		btnSubmit.setBounds(155, 249, 90, 30);
		btnSubmit.setText("Submit");
		
		}
	private void createResourceManager() {
		localResourceManager = new LocalResourceManager(JFaceResources.getResources(),shell);
	}
}
