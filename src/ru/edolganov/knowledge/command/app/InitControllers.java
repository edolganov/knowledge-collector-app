package ru.edolganov.knowledge.command.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import ru.chapaj.util.lang.ClassUtil;
import ru.chapaj.util.lang.PackageExplorer;
import ru.edolganov.knowledge.core.command.Command;
import ru.edolganov.knowledge.core.controller.Controller;
import ru.edolganov.knowledge.core.controller.ControllerInfo;
import ru.edolganov.knowledge.main.ui.MainWindow;

public class InitControllers extends Command<Void> {
	
	private static class CE {
		Class<?> clazz;
		Object target;
		public CE(Class<?> clazz, Object target) {
			super();
			this.clazz = clazz;
			this.target = target;
		}
	}
		
	private HashMap<String, ArrayList<CE>> queue = new HashMap<String, ArrayList<CE>>();
	private HashSet<Controller<?>> afterAllInitSet = new HashSet<Controller<?>>();

	@Override
	protected Void doAction() {
		PackageExplorer.find("ru.edolganov", new PackageExplorer.Callback(){

			@Override
			public void found(Class<?> clazz) {
				ControllerInfo ci = clazz.getAnnotation(ControllerInfo.class);
				if(ci != null){
					try {
						Class<?> targetClass = ci.target();
						if(targetClass == null) throw new IllegalStateException("null target of controller:"+clazz);
						Object target = null;
						if(targetClass.equals(MainWindow.class)){
							target = appContext.getMainWindow();
						}
						else {
							throw new IllegalStateException("unknow controller's target:"+targetClass);
						}
						Class<?> dependenceClass =  ci.dependence();
						String dependenceClassName = dependenceClass.getName();
						if(dependenceClass.equals(Object.class)){
							initController(clazz, target);
							String name = clazz.getName();
							if(queue.containsKey(name)){
								for(CE ce : queue.get(name)){
									initController(ce.clazz, ce.target);
								}
								queue.remove(name);
							}
						}
						else if(!ClassUtil.isValid(dependenceClass, Controller.class)){
							throw new IllegalStateException("unknow controller's dependence class:"+dependenceClass);
						}
						else {
							if(Controller.controllers.containsKey(dependenceClassName)){
								initController(clazz, target);
							}
							else {
								ArrayList<CE> list = queue.get(dependenceClassName);
								if(list == null){
									list = new ArrayList<CE>();
									queue.put(dependenceClassName, list);
								}
								list.add(new CE(clazz, target));
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

			
		});
		return null;
	}
	
	private void initController(Class<?> clazz, Object target) throws InstantiationException, IllegalAccessException {
		Controller<?> c = (Controller<?>) clazz.newInstance();
		// System.out.println("init c:" + c);
		c.setAppContext(appContext);
		c.initUnsaveObject(target);
		afterAllInitSet.add(c);
	}

}
