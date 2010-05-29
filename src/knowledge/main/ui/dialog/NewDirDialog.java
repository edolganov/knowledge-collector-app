package knowledge.main.ui.dialog;

import javax.swing.JPanel;
import javax.swing.JDialog;

import ru.chapaj.util.swing.dialog.ExtendDialog;

import java.awt.Rectangle;
import javax.swing.JButton;
import java.awt.Dimension;

import knowledge.main.ui.component.PropertyTextPanel;

public class NewDirDialog extends ExtendDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	public PropertyTextPanel namePanel = null;
	public JButton ok = null;

	/**
	 * @param owner
	 */
	public NewDirDialog() {
		super((JDialog)null);
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 104);
		this.setSize(new Dimension(296, 104));
		this.setTitle("New dir");
		this.setContentPane(getJContentPane());
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getNamePanel(), null);
			jContentPane.add(getOk(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes namePanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getNamePanel() {
		if (namePanel == null) {
			namePanel = new PropertyTextPanel();
			namePanel.setBounds(new Rectangle(2, 3, 287, 35));
			namePanel.label.setText("name");
		}
		return namePanel;
	}

	/**
	 * This method initializes ok	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getOk() {
		if (ok == null) {
			ok = new JButton();
			ok.setBounds(new Rectangle(116, 50, 55, 19));
			ok.setText("Ok");
		}
		return ok;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
