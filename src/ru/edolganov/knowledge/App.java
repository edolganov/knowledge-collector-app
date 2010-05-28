package ru.edolganov.knowledge;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import ru.chapaj.util.event.EventManager;
import ru.chapaj.util.event.annotation.LastEventListener;
import ru.edolganov.knowledge.command.app.InitControllers;
import ru.edolganov.knowledge.core.command.CommandService;
import ru.edolganov.knowledge.event.TryExit;
import ru.edolganov.knowledge.event.ui.NeedShowApp;
import ru.edolganov.knowledge.main.ui.ExceptionHandler;
import ru.edolganov.knowledge.main.ui.MainWindow;
import ru.edolganov.knowledge.persist.fs.FSPersist;


public class App {

	private static final App instance = new App();
	
	public static App getDefault(){
		return instance;
	}
	
	

	private MainWindow ui;
	private AppContext appContext;
	private EventManager eventManager;
	private CommandService commandService;
	private ExceptionHandler exceptionHandler;
	
	
	private App(){
		
	}

	public void start() {
		initContext();
		initUI();
		postInitContext();

		
		new FSPersist(appContext);
		commandService.invoke(new InitControllers());
		
		

		ui.setVisible(true);
		
	}
	




	private void initContext(){
		appContext = new AppContext();
		appContext.setToolkit(Toolkit.getDefaultToolkit());
		
		eventManager = new EventManager();
		appContext.setEventManager(eventManager);
		
		commandService = new CommandService();
		appContext.setCommandService(commandService);
		
		exceptionHandler = new ExceptionHandler();
		appContext.setExceptionHandler(exceptionHandler);
	}
	

	private void initUI(){
		ui = new MainWindow(null);
		ui.setTitle("Knowledge Collector");
		ui.setIconImage(appContext.getToolkit().getImage(this.getClass().getResource("/images/kc/app.png")));
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
		
		
		appContext.setMainWindow(ui);
	}
	
	private void postInitContext() {
		eventManager.setExceptionHandler(exceptionHandler);
		eventManager.addObjectMethodListeners(this);
		
		commandService.setAppContext(appContext);
	}
	
	
	@LastEventListener(TryExit.class)
	public void exit() {
		System.exit(0);
	}
	
	@LastEventListener(NeedShowApp.class)
	public void show(){
		ui.setVisible(true);
		ui.toFront();
	}
	
	private void hideIfNeed() {
		if(ui.isActive()) ui.setVisible(false);	
	}
	
		

	


	
}
