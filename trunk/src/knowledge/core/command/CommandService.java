package knowledge.core.command;

import knowledge.AppContext;

public class CommandService {
	
	AppContext appContext;
	
	public CommandService() {
		super();
	}
	
	
	public void setAppContext(AppContext appContext) {
		this.appContext = appContext;
	}



	public <T> T invoke(Command<T> c) {
		c.setAppContext(appContext);
		try {
			T result = (T)c.doAction();
			return result;
		}
		catch (CommandInvokeException e) {
			throw e;
		}
		catch (Exception e) {
			//appContext.getExceptionHandler().handle(e); //не можем так делать - обработка должна быть выше
			throw new CommandInvokeException(e);
		}
	}


	<T> T invokeNext(Command<?> a, Command<T> b) {
		try {
			b.setAppContext(a.appContext);
			return (T)b.doAction();
		} catch (Exception e) {
			throw new CommandInvokeException(e);
		}
	}
	

}
