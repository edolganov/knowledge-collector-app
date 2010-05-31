package knowledge.persist.fs.command;

import knowledge.persist.fs.PersistContext;
import ru.chapaj.util.event.Event;

public abstract class Command<T> {
	
	protected PersistContext context;
	CommandService commandService;
	
	public Command() {
		super();
	}

	
	protected abstract T doAction() throws Exception;
	
	protected <B> B invokeNext(Command<B> b) {
		return commandService.invokeNext(this, b);
	}
	
	protected void fireEvent(Event<?> event){
		context.getAppContext().getEventManager().fireEvent(this, event);
	}

}
