package ru.edolganov.knowledge.core.controller;

import java.util.HashMap;

import ru.chapaj.util.event.Event;
import ru.chapaj.util.event.EventListener;
import ru.chapaj.util.ui.controller.GenericController;
import ru.edolganov.knowledge.AppContext;
import ru.edolganov.knowledge.core.command.Command;

public abstract class Controller<T> extends GenericController<T> {
	
	public static HashMap<String, Controller<?>> controllers = new HashMap<String, Controller<?>>();
	
	public Controller() {
		controllers.put(this.getClass().getName(), this);
	}
	
	
	private AppContext appContext;

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
	
	

}
