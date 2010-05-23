package ru.edolganov.knowledge.core.command;

import ru.edolganov.knowledge.AppContext;

public class CommandService {
	
	public CommandService() {
		super();
	}
	
	public <T> T invoke(Command<T> c, AppContext appContext) {
		c.setAppContext(appContext);
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
			b.setAppContext(a.appContext);
			return (T)b.doAction();
		} catch (Exception e) {
			throw new CommandInvokeException(e);
		}
	}
	

}
