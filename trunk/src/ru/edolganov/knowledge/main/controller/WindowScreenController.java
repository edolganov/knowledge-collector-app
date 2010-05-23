package ru.edolganov.knowledge.main.controller;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import ru.chapaj.util.event.EventListener;
import ru.chapaj.util.swing.IconHelper;
import ru.edolganov.knowledge.core.controller.Controller;
import ru.edolganov.knowledge.core.controller.ControllerInfo;
import ru.edolganov.knowledge.event.settings.LoadSettings;
import ru.edolganov.knowledge.event.settings.SaveSettings;
import ru.edolganov.knowledge.main.ui.MainWindow;
import ru.edolganov.knowledge.model.SettingsMap;

@ControllerInfo(target=MainWindow.class)
public class WindowScreenController extends Controller<MainWindow> {

	
	int oldW;
	int oldH;
	int oldPX;
	int oldPY;
	
	boolean fullScreen = false;
	
	MainWindow ui;

	@Override
	public void init(final MainWindow ui_) {
		this.ui = ui_;
		
		Dimension oldSize = ui_.getSize();
		oldW = oldSize.width;
		oldH = oldSize.height;
		oldPX = ui_.getX();
		oldPY = ui_.getY();
		
		updateUI();
		
		ui_.fullStandart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				

				if(!fullScreen){
					Dimension oldSize = ui_.getSize();
					oldW = oldSize.width;
					oldH = oldSize.height;
					oldPX = ui_.getX();
					oldPY = ui_.getY();
					
					
					doFullScreen();
					fullScreen = true;
				}
				else{
					
					doOldBound();
					fullScreen = false;
				}
				
				updateUI();
				
			}


		});
		
		
		addListener(new EventListener<LoadSettings>(LoadSettings.class) {

			@Override
			public void onAction(Object source, LoadSettings event) {
				loadPosition(event.getData());
			}
		});
		
		addListener(new EventListener<SaveSettings>(SaveSettings.class) {

			@Override
			public void onAction(Object source, SaveSettings event) {
				savePosition(event.getData());
			}
		});
		
		
	}
	
	private void doOldBound() {
		ui.setBounds(oldPX, oldPY, oldW, oldH);
	}

	private void doFullScreen() {
		//get local graphics environment
		GraphicsEnvironment graphicsEnvironment=GraphicsEnvironment.getLocalGraphicsEnvironment();
		//get maximum window bounds
		Rectangle maximumWindowBounds=graphicsEnvironment.getMaximumWindowBounds();
		
		int height = maximumWindowBounds.height;
		int width = maximumWindowBounds.width;
		
		Dimension minimumSize = ui.getMinimumSize();
		ui.setBounds(0, 0, Math.max(width, minimumSize.width), Math.max(height, minimumSize.height));
	}
	
	
	private void loadPosition(SettingsMap settings) {
		
		String width = settings.map().get("ui-width");
		String height = settings.map().get("ui-height");
		String x = settings.map().get("ui-x");
		String y = settings.map().get("ui-y");
		
		oldW = Integer.parseInt(width);
		oldH = Integer.parseInt(height);
		oldPX = Integer.parseInt(x);
		oldPY =Integer.parseInt(y);
		
		String fullscreen = settings.map().get("ui-fullscreen");
		if("true".equals(fullscreen)) fullScreen = true;
		
		if(fullScreen){
			doFullScreen();
		}
		else {
			doOldBound();
		}
		
		updateUI();
	}
	
	private void savePosition(SettingsMap settings) {
		if(!fullScreen){
			settings.map().put("ui-width",""+ui.getWidth());
			settings.map().put("ui-height",""+ui.getHeight());
			settings.map().put("ui-x",""+ui.getX());
			settings.map().put("ui-y",""+ui.getY());
		}
		else {
			settings.map().put("ui-width",""+oldW);
			settings.map().put("ui-height",""+oldH);
			settings.map().put("ui-x",""+oldPX);
			settings.map().put("ui-y",""+oldPY);
		}
		
		settings.map().put("ui-fullscreen",""+fullScreen);
	}

	private void updateUI() {
		JButton fullStandart = ui.fullStandart;
		if(!fullScreen) {
			fullStandart.setIcon(IconHelper.get("/images/kc/app/maximize.png"));
			//ui.setResizable(true);
		}
		else {
			fullStandart.setIcon(IconHelper.get("/images/kc/app/minimize.png"));
			//ui.setResizable(false);
		}
		
	}
	

}
