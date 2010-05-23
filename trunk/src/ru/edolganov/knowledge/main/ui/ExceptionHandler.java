package ru.edolganov.knowledge.main.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ru.chapaj.util.event.ListenerExceptionHandler;
import ru.chapaj.util.log.Log;
import ru.edolganov.knowledge.AppContext;
import ru.edolganov.knowledge.exception.NodeExistException;
import ru.edolganov.knowledge.main.ui.dialog.ErrorDialog;

public class ExceptionHandler implements ListenerExceptionHandler{
	
	static final Log log = Log.getInstance(ExceptionHandler.class);
	
	AppContext appContext;



	public void setAppContext(AppContext appContext) {
		this.appContext = appContext;
	}


	@Override
	public void handle(Exception e) {
		String msg = null;
		
		if(e instanceof NodeExistException){
			NodeExistException ex = (NodeExistException)e;
			msg = "Node '"+ex.getName()+"' already exist";
		}
		else {
			msg = "System error: "+e.getMessage();
			log.error(e);
		}
		
		if(msg != null){
			final ErrorDialog errorDialog = new ErrorDialog();
			errorDialog.init(appContext.getMainWindow());
			errorDialog.ok.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					errorDialog.setConfirmedActionAndExit(true);
				}
			});
			
			errorDialog.label2.setText(msg);
			errorDialog.setVisible(true);
		}
	}

}
