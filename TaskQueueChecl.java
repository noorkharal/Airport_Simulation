package finalfinal;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;

public class TaskQueueChecl {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			TaskQueueChecl window = new TaskQueueChecl();
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
		shell.setSize(1077, 598);
		shell.setText("SWT Application");
		
		Label lblTasks = new Label(shell, SWT.NONE);
		lblTasks.setBounds(53, 21, 945, 485);
		lblTasks.setText("tasks:\n" + Main.mainTasks.tasksDisplayGUI().toString());

	}
}
