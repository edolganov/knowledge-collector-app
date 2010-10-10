package knowledge;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import knowledge.command.app.InitControllers;
import knowledge.core.command.CommandService;
import knowledge.event.TryExit;
import knowledge.event.ui.NeedShowApp;
import knowledge.main.ExceptionHandler;
import knowledge.main.ui.MainWindow;
import knowledge.persist.fs.FSPersist;
import knowledge.tools.NodeObjectsCache;
import knowledge.tools.SessionCache;

import ru.chapaj.util.event.EventManager;
import ru.chapaj.util.event.annotation.LastEventListener;


public class App {

	private static final App instance = new App();
	
	public static App getDefault(){
		return instance;
	}
	
	

	private AppContext appContext;
	private MainWindow ui;
	private EventManager eventManager;
	private CommandService commandService;
	private ExceptionHandler exceptionHandler;
	private NodeObjectsCache nodeObjectsCache;
	private FSPersist fsPersist;
	private SessionCache sessionCache;
	
	
	private App(){
		
	}

	public void start() {
		initContext();
		initUI();
		postInitContext();

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
		
		nodeObjectsCache = new NodeObjectsCache();
		appContext.setNodeObjectsCache(nodeObjectsCache);
		
		fsPersist = new FSPersist();
		appContext.setPersist(fsPersist);
		
		sessionCache = new SessionCache();
		appContext.setSessionCache(sessionCache);
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
		nodeObjectsCache.init(appContext);
		fsPersist.init("./know",appContext);
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
