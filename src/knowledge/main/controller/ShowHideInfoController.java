package knowledge.main.controller;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import knowledge.core.controller.Controller;
import knowledge.core.controller.ControllerInfo;
import knowledge.event.ui.HideActionPanel;
import knowledge.event.ui.NeedShowOrHideActionPanel;
import knowledge.event.ui.ShowActionPanel;
import knowledge.main.ui.MainWindow;

import ru.chapaj.util.event.EventListener;
import ru.chapaj.util.swing.IconHelper;

@ControllerInfo(target=MainWindow.class)
public class ShowHideInfoController extends Controller<MainWindow> {
	
	MainWindow ui;
	ImageIcon leftIcon = IconHelper.get("/images/kc/app/left.png");
	ImageIcon rightIcon = IconHelper.get("/images/kc/app/right.png");

	@Override
	public void init(final MainWindow ui) {
		this.ui = ui;
		ui.showHideInfoB.setText("");

		ui.showHideInfoB.setIcon(rightIcon);
		ui.showHideInfoB.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				showHideInfo();
			}
		});
		
		
		addListener(new EventListener<NeedShowOrHideActionPanel>(NeedShowOrHideActionPanel.class) {
			
			@Override
			public void onAction(Object source, NeedShowOrHideActionPanel event) {
				showHideInfo();
			}
		});
		
		showHideInfo();
	}
	
	private void showHideInfo(){
		if(ui.infoPanel.isVisible()){
			ui.infoPanel.setVisible(false);
			Rectangle infoBounds = ui.infoPanel.getBounds();
			Rectangle treeBounds = ui.treePanel.getBounds();
			int wi = infoBounds.width;
			int wt = treeBounds.width;
			ui.treePanel.setBounds(
					treeBounds.x, 
					treeBounds.y, 
					wt + wi, 
					treeBounds.height);
			ui.treePanel.repaint();
			ui.showHideInfoB.setIcon(leftIcon);
			
			fireEvent(new HideActionPanel());
		}
		else {
			Rectangle infoBounds = ui.infoPanel.getBounds();
			Rectangle treeBounds = ui.treePanel.getBounds();
			int wi = infoBounds.width;
			int wt = treeBounds.width;
			ui.treePanel.setBounds(
					treeBounds.x, 
					treeBounds.y, 
					wt - wi, 
					treeBounds.height);
			ui.treePanel.repaint();
			ui.showHideInfoB.setIcon(rightIcon);
			ui.infoPanel.setVisible(true);
			
			fireEvent(new ShowActionPanel());
		}
	}
	

}
