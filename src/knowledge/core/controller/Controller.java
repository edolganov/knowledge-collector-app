package knowledge.core.controller;

import java.util.HashMap;

import knowledge.AppContext;
import knowledge.core.command.Command;
import knowledge.persist.fs.FSPersist;
import knowledge.tools.NodeObjectsCache;

import ru.chapaj.util.event.Event;
import ru.chapaj.util.event.EventCallback;
import ru.chapaj.util.event.EventListener;
import ru.chapaj.util.ui.controller.GenericController;

public abstract class Controller<T> extends GenericController<T> {
	
	public static HashMap<String, Controller<?>> controllers = new HashMap<String, Controller<?>>();
	
	public Controller() {
		controllers.put(this.getClass().getName(), this);
	}
	
	
	protected AppContext appContext;

	public AppContext getAppContext() {
		return appContext;
	}

	public void setAppContext(AppContext appContext) {
		this.appContext = appContext;
		appContext.getEventManager().addObjectMethodListeners(this);
	}
	
	protected <N> N invoke(Command<N> command){
		return appContext.getCommandService().invoke(command);
	}
	
	protected void fireEvent(Event<?> event){
		appContext.getEventManager().fireEvent(this, event);
	}
	
	protected <N extends Event<?>> void addListener(EventListener<N> listener){
		appContext.getEventManager().addListener(listener);
	}
	
	protected NodeObjectsCache getCache(){
		return appContext.getNodeObjectsCache();
	}
	
	protected FSPersist getPersist(){
		return appContext.getPersist();
	}

}
