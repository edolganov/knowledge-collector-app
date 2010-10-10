package knowledge.command.dialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import knowledge.command.model.link.IsInetLink;
import knowledge.command.model.link.IsLocalLink;
import knowledge.core.command.Command;
import knowledge.main.ui.dialog.NewLinkDialog;

import ru.chapaj.util.Check;
import ru.chapaj.util.swing.listener.KeyEnterAdapter;
import ru.kc.model.knowledge.Link;
import ru.kc.model.knowledge.LocalLink;
import ru.kc.model.knowledge.NetworkLink;

public class NewLink extends Command<Link> {

	@Override
	protected Link doAction() {
Link out = null;
		
		final NewLinkDialog newLink = new NewLinkDialog();
		newLink.init(mainWindow);
		
		newLink.ok.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				newLink.setConfirmedActionAndExit(true);
			}
		});
		
		KeyEnterAdapter keyEnterAdapter = new KeyEnterAdapter(){

			@Override
			public void onEnter() {
				newLink.setConfirmedActionAndExit(true);
			}
			
		};
		newLink.namePanel.textField.addKeyListener(keyEnterAdapter);
		newLink.urlPanel.textField.addKeyListener(keyEnterAdapter);
		
		newLink.setVisible(true);
		
		if(newLink.isConfirmedAction()){
			String name = newLink.namePanel.textField.getText();
			String url = newLink.urlPanel.textField.getText();
			if(!Check.isEmpty(name) || !Check.isEmpty(url)) {
				if(!Check.isEmpty(url)){
					if(invokeNext(new IsInetLink(url))){
						out = new NetworkLink();
					}
					else if(invokeNext(new IsLocalLink(url))){
						out = new LocalLink();
					}
					else {
						out = new NetworkLink();
					}
					out.setUrl(url);
				}else{
					out = new NetworkLink();
				}
				
				if(!Check.isEmpty(name)){
					out.setName(name);
				}
				else {
					out.setName(url);
				}
			}
		}
		newLink.remove();
		
		
		return out;
	}

}
