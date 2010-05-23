package ru.edolganov.knowledge.command.dialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.knowledge.Dir;
import ru.chapaj.util.Check;
import ru.chapaj.util.swing.listener.KeyEnterAdapter;
import ru.edolganov.knowledge.core.command.Command;
import ru.edolganov.knowledge.main.ui.dialog.NewDirDialog;

public class NewDir extends Command<Dir> {

	@Override
	protected Dir doAction() {
		Dir out = null;
		
		final NewDirDialog newDir = new NewDirDialog();
		newDir.init(mainWindow);
		
		newDir.ok.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				newDir.setConfirmedActionAndExit(true);
			}
		});
		
		newDir.namePanel.textField.addKeyListener(new KeyEnterAdapter(){

			@Override
			public void onEnter() {
				newDir.setConfirmedActionAndExit(true);
			}
			
		});
		
		newDir.setVisible(true);
		
		if(newDir.isConfirmedAction()){
			String name = newDir.namePanel.textField.getText();
			if(!Check.isEmpty(name)){
				out = new Dir();
				out.setName(name);
			}
		}
		newDir.remove();
		return out;
	}

}