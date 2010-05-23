package ru.edolganov.knowledge;

import java.awt.Toolkit;

import ru.chapaj.util.event.EventManager;
import ru.edolganov.knowledge.core.command.CommandService;
import ru.edolganov.knowledge.main.ui.MainWindow;
import ru.edolganov.knowledge.persist.PersistService;

public class AppContext {
	
	private Toolkit toolkit;
	private MainWindow mainWindow;
	private EventManager eventManager;
	private CommandService commandService;
	private PersistService persistService;
	
	
	

	public PersistService getPersistService() {
		return persistService;
	}

	public void setPersistService(PersistService persistService) {
		this.persistService = persistService;
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
