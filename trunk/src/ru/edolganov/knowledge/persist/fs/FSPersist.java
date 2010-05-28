package ru.edolganov.knowledge.persist.fs;

import ru.chapaj.util.event.annotation.LastEventListener;
import ru.edolganov.knowledge.AppContext;
import ru.edolganov.knowledge.event.persist.NeedAddChild;


public class FSPersist  {
	
	AppContext appContext;

	public FSPersist(AppContext appContext) {
		this.appContext = appContext;
	}
	
	@LastEventListener(NeedAddChild.class)
	public void addChild(NeedAddChild event){
		System.out.println("call addChild");
	}
	


}
