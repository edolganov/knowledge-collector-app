package ru.edolganov.knowledge.main.controller;

import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import ru.edolganov.knowledge.core.controller.Controller;
import ru.edolganov.knowledge.core.controller.ControllerInfo;
import ru.edolganov.knowledge.main.ui.MainWindow;


@ControllerInfo(target=MainWindow.class)
public class ShowOnTopController extends Controller<MainWindow> {
	
	
	boolean showOnTop = false;
	
	@Override
	public void init(MainWindow ui) {
		
		App.getDefault().addListener(new AppListener() {
			
			@Override
			public void onAction(Object source, String action, Object... data) {
				if("show-on-top".equals(action)){
					if(data != null && data[0] instanceof Boolean){
						showOnTop = (Boolean)data[0];
						//System.out.println("showOnTop:"+showOnTop);
					}
				}
				
			}
		});
		
		ui.addWindowFocusListener(new WindowFocusListener() {
			
			@Override
			public void windowLostFocus(WindowEvent e) {
				//System.out.println("focusLost");
				if(showOnTop) App.getDefault().show();
				
			}
			
			@Override
			public void windowGainedFocus(WindowEvent e) {
				
			}
		});

	}

}
