package knowledge.persist.fs.command;

import knowledge.core.command.CommandInvokeException;
import knowledge.persist.fs.PersistContext;

public class CommandService {
	
	PersistContext context;
	
	public CommandService() {
		super();
	}
	
	
	public void setContext(PersistContext context) {
		this.context = context;
	}



	public <T> T invoke(Command<T> c) {
		c.context = context;
		c.commandService = this;
		try {
			T result = (T)c.doAction();
			return result;
		}
		catch (CommandInvokeException e) {
			throw e;
		}
		catch (Exception e) {
			throw new CommandInvokeException(e);
		}
	}


	<T> T invokeNext(Command<?> a, Command<T> b) {
		try {
			b.context = a.context;
			b.commandService = a.commandService;
			return (T)b.doAction();
		} catch (Exception e) {
			throw new CommandInvokeException(e);
		}
	}
	

}
