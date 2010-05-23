package ru.edolganov.knowledge.main.controller;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import ru.chapaj.util.event.EventListener;
import ru.edolganov.knowledge.core.controller.Controller;
import ru.edolganov.knowledge.core.controller.ControllerInfo;
import ru.edolganov.knowledge.event.ui.NeedShowApp;
import ru.edolganov.knowledge.event.ui.ShowOnTop;
import ru.edolganov.knowledge.main.ui.MainWindow;


@ControllerInfo(target=MainWindow.class)
public class ShowOnTopController extends Controller<MainWindow> {
	
	
	boolean showOnTop = false;
	
	@Override
	public void init(MainWindow ui) {
		
		addListener(new EventListener<ShowOnTop>(ShowOnTop.class) {
			
			@Override
			public void onAction(Object source, ShowOnTop event) {
				showOnTop = event.getData();
			}
		});
		
		
		ui.addWindowFocusListener(new WindowFocusListener() {
			
			@Override
			public void windowLostFocus(WindowEvent e) {
				//System.out.println("focusLost");
				if(showOnTop) fireEvent(new NeedShowApp());
				
			}
			
			@Override
			public void windowGainedFocus(WindowEvent e) {
				
			}
		});

	}

}
