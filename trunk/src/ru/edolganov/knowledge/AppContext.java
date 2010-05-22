package ru.edolganov.knowledge;

import java.awt.Toolkit;

import ru.chapaj.util.event.EventManager;
import ru.edolganov.knowledge.main.MainWindow;

public class AppContext {
	
	private Toolkit toolkit;
	private MainWindow mainWindow;
	private EventManager eventManager;
	
	
	

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
