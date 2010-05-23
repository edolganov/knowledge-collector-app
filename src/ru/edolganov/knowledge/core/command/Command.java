package ru.edolganov.knowledge.core.command;

import ru.edolganov.knowledge.AppContext;
import ru.edolganov.knowledge.main.ui.MainWindow;
import ru.edolganov.knowledge.persist.PersistService;

public abstract class Command<T> {
	
	protected AppContext appContext;
	protected MainWindow mainWindow;
	protected PersistService persistService;
	
	public Command() {
		super();
	}

	public void setAppContext(AppContext appContext) {
		this.appContext = appContext;
		mainWindow = appContext.getMainWindow();
		persistService = appContext.getPersistService();
	}
	
	protected abstract T doAction();
	
	protected <B> B invokeNext(Command<B> b) {
		return appContext.getCommandService().invokeNext(this, b);
	}

}
