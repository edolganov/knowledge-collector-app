package knowledge.main.controller;

import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import knowledge.core.controller.Controller;
import knowledge.core.controller.ControllerInfo;
import knowledge.event.TryExit;
import knowledge.event.ui.NeedShowApp;
import knowledge.main.ui.MainWindow;



@ControllerInfo(target=MainWindow.class)
public class IconController extends Controller<MainWindow> {
	
	//Image appIcon = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/kc/app.png"));
	
	@Override
	public void init(MainWindow ui) {
		final TrayIcon trayIcon;

		if (SystemTray.isSupported()) {

		    SystemTray tray = SystemTray.getSystemTray();

		    MouseListener mouseListener = new MouseAdapter() {
		                
		        public void mouseClicked(MouseEvent e) {
		        	if(e.getButton() == 1){
		        		fireEvent(new NeedShowApp());
		        	}
		        }
		        
		    };

		    ActionListener exitListener = new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		            fireEvent(new TryExit());
		        }
		    };
		            
		    PopupMenu popup = new PopupMenu();
		    MenuItem defaultItem = new MenuItem("Exit");
		    defaultItem.addActionListener(exitListener);
		    popup.add(defaultItem);

		    trayIcon = new TrayIcon(ui.getIconImages().get(0), ui.getTitle(), popup);

//		    ActionListener actionListener = new ActionListener() {
//		        public void actionPerformed(ActionEvent e) {
//		            trayIcon.displayMessage("Action Event", 
//		                "An Action Event Has Been Performed!",
//		                TrayIcon.MessageType.INFO);
//		        }
//		    };
		            
		    trayIcon.setImageAutoSize(true);
//		    trayIcon.addActionListener(actionListener);
		    trayIcon.addMouseListener(mouseListener);

		    try {
		        tray.add(trayIcon);
		    } catch (AWTException e) {
		        System.err.println("TrayIcon could not be added.");
		    }

		} else {

		    //  System Tray is not supported

		}

	}

}
