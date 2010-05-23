package ru.edolganov.knowledge;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import ru.chapaj.util.event.EventListener;
import ru.chapaj.util.event.EventManager;
import ru.edolganov.knowledge.command.app.InitControllers;
import ru.edolganov.knowledge.core.command.CommandService;
import ru.edolganov.knowledge.event.TryExit;
import ru.edolganov.knowledge.event.ui.NeedShowApp;
import ru.edolganov.knowledge.main.ui.ExceptionHandler;
import ru.edolganov.knowledge.main.ui.MainWindow;
import ru.edolganov.knowledge.persist.fs.PersistServiceImpl;


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
		commandService.invoke(new InitControllers());
		
		initListeners();
		

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
		
		appContext.setPersistService(new PersistServiceImpl());
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
		commandService.setAppContext(appContext);
	}
	
	private void initListeners() {
		eventManager.addLastListener(new EventListener<TryExit>(TryExit.class) {
			
			@Override
			public void onAction(Object source, TryExit event) {
				exit();
			}
		});
		
		eventManager.addLastListener(new EventListener<NeedShowApp>(NeedShowApp.class) {
			
			@Override
			public void onAction(Object source, NeedShowApp event) {
				show();
			}
		});
		
	}
	
	private void exit() {
		System.exit(0);
	}
	
	private void show(){
		ui.setVisible(true);
		ui.toFront();
	}
	
	private void hideIfNeed() {
		if(ui.isActive()) ui.setVisible(false);	
	}
	
		

	


	
}
