package ru.edolganov.knowledge;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import ru.chapaj.util.event.EventListener;
import ru.chapaj.util.event.EventManager;
import ru.edolganov.knowledge.event.TryExit;
import ru.edolganov.knowledge.main.MainWindow;


public class App {

	private static final App instance = new App();
	
	public static App getDefault(){
		return instance;
	}
	
	

	private MainWindow ui;
	private AppContext context;
	private EventManager eventManager;
	
	
	
	private App(){
		
	}

	public void start() {
		initContext();
		initUI();
		//controllers
		
		
		eventManager.addLastListener(new EventListener<TryExit>(TryExit.class) {
			
			@Override
			public void onAction(Object source, TryExit event) {
				exit();
			}
		});
		ui.setVisible(true);
		
	}
	
	private void initContext(){
		context = new AppContext();
		context.setToolkit(Toolkit.getDefaultToolkit());
		
		eventManager = new EventManager();
		context.setEventManager(eventManager);
	}
	

	private void initUI(){
		ui = new MainWindow(null);
		ui.setTitle("Knowledge Collector");
		ui.setIconImage(context.getToolkit().getImage(this.getClass().getResource("/images/kc/app.png")));
		ui.setLocationByPlatform(true);
		ui.jButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				eventManager.fireEvent(this, new TryExit());
			}
			
		});
		ui.jButton1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				hideIfNeed();
			}
			
		});
		ui.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				hideIfNeed();
			}
		});
		
		
		context.setMainWindow(ui);
	}
	
	private void exit() {
		System.exit(0);
	}
	
	public void show(){
		ui.setVisible(true);
		ui.toFront();
	}
	
	public void hideIfNeed() {
		if(ui.isActive()) ui.setVisible(false);	
	}
	
}
