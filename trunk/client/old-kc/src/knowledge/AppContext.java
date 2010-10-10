package knowledge;

import java.awt.Toolkit;

import knowledge.core.command.CommandService;
import knowledge.main.ExceptionHandler;
import knowledge.main.ui.MainWindow;
import knowledge.persist.fs.FSPersist;
import knowledge.tools.NodeObjectsCache;
import knowledge.tools.SessionCache;

import ru.chapaj.util.event.EventManager;

public class AppContext {
	
	private Toolkit toolkit;
	private MainWindow mainWindow;
	private EventManager eventManager;
	private CommandService commandService;
	private ExceptionHandler exceptionHandler;
	private NodeObjectsCache nodeObjectsCache;
	private FSPersist fsPersist;
	private SessionCache sessionCache;

	public SessionCache getSessionCache() {
		return sessionCache;
	}

	public void setSessionCache(SessionCache sessionCache) {
		this.sessionCache = sessionCache;
	}

	public FSPersist getPersist() {
		return fsPersist;
	}

	public void setPersist(FSPersist fsPersist) {
		this.fsPersist = fsPersist;
	}

	public NodeObjectsCache getNodeObjectsCache() {
		return nodeObjectsCache;
	}

	public void setNodeObjectsCache(NodeObjectsCache nodeObjectsCache) {
		this.nodeObjectsCache = nodeObjectsCache;
	}

	public ExceptionHandler getExceptionHandler() {
		return exceptionHandler;
	}

	public void setExceptionHandler(ExceptionHandler exceptionHandler) {
		this.exceptionHandler = exceptionHandler;
	}

	public CommandService getCommandService() {
		return commandService;
	}

	public void setCommandService(CommandService commandService) {
		this.commandService = commandService;
	}

	public EventManager getEventManager() {
		return eventManager;
	}

	public void setEventManager(EventManager eventManager) {
		this.eventManager = eventManager;
	}

	public MainWindow getMainWindow() {
		return mainWindow;
	}

	public void setMainWindow(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}

	public Toolkit getToolkit() {
		return toolkit;
	}

	public void setToolkit(Toolkit toolkit) {
		this.toolkit = toolkit;
	}
	
	

}
