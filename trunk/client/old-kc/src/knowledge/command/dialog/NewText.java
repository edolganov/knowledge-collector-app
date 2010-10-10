package knowledge.command.dialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import knowledge.core.command.Command;
import knowledge.main.ui.dialog.NewDirDialog;

import ru.chapaj.util.Check;
import ru.chapaj.util.swing.listener.KeyEnterAdapter;
import ru.kc.model.knowledge.Note;
import ru.kc.model.knowledge.TextData;

public class NewText extends Command<TextData>{

	@Override
	protected TextData doAction() {
		TextData out = null;
		
		final NewDirDialog newText = new NewDirDialog();
		newText.setTitle("New Text");
		newText.init(mainWindow);
		
		newText.ok.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				newText.setConfirmedActionAndExit(true);
			}
		});
		
		newText.namePanel.textField.addKeyListener(new KeyEnterAdapter(){

			@Override
			public void onEnter() {
				newText.setConfirmedActionAndExit(true);
			}
			
		});
		
		newText.setVisible(true);
		
		if(newText.isConfirmedAction()){
			String name = newText.namePanel.textField.getText();
			if(!Check.isEmpty(name)){
				out = new Note();
				out.setName(name);
			}
		}
		newText.remove();
		return out;
	}

}
