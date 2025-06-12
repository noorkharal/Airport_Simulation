package finalfinal;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.ColorDescriptor;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;

public class shortestPath {

	protected Shell shell;
	private LocalResourceManager localResourceManager;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			shortestPath window = new shortestPath();
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
		shell.setSize(1034, 628);
		shell.setText("SWT Application");
		shell.setLayout(null);
		
		
		
		final Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBackground(localResourceManager.create(ColorDescriptor.createFrom(new RGB(255, 0, 0))));
		lblNewLabel.setBounds(92, 441, 70, 20);
		lblNewLabel.setText("New Label");
		
		Button btnPress = new Button(shell, SWT.NONE);
		btnPress.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
//				Color c = new Color();
				lblNewLabel.setBackground(localResourceManager.create(ColorDescriptor.createFrom(new RGB(0, 255, 0))));
			}
		});
		btnPress.setBounds(172, 27, 90, 30);
		btnPress.setText("press");
		
		Combo combo = new Combo(shell, SWT.NONE);
		combo.setBounds(268, 32, 97, 28);
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setText("New Label");
		lblNewLabel_1.setBackground(localResourceManager.create(ColorDescriptor.createFrom(new RGB(255, 0, 0))));
		lblNewLabel_1.setBounds(241, 223, 70, 20);
		
		Label lblNewLabel_2 = new Label(shell, SWT.NONE);
		lblNewLabel_2.setBackground(localResourceManager.create(ColorDescriptor.createFrom(new RGB(255, 0, 0))));
		lblNewLabel_2.setBounds(142, 409, 153, 20);

	}
	private void createResourceManager() {
		localResourceManager = new LocalResourceManager(JFaceResources.getResources(),shell);
	}
}
