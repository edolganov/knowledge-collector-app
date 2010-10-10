package knowledge.core.command;

import knowledge.AppContext;
import knowledge.main.ui.MainWindow;
import ru.chapaj.util.event.Event;

public abstract class Command<T> {
	
	protected AppContext appContext;
	protected MainWindow mainWindow;
	
	public Command() {
		super();
	}

	public void setAppContext(AppContext appContext) {
		this.appContext = appContext;
		mainWindow = appContext.getMainWindow();
	}
	
	protected abstract T doAction();
	
	protected <B> B invokeNext(Command<B> b) {
		return appContext.getCommandService().invokeNext(this, b);
	}
	
	protected void fireEvent(Event<?> event){
		appContext.getEventManager().fireEvent(this, event);
	}

}
