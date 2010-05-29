package knowledge.main.controller;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import knowledge.core.controller.Controller;
import knowledge.core.controller.ControllerInfo;
import knowledge.event.ui.NeedShowApp;
import knowledge.event.ui.ShowOnTop;
import knowledge.main.ui.MainWindow;

import ru.chapaj.util.event.EventListener;


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
