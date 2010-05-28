package ru.edolganov.knowledge.event.persist;


import model.knowledge.RootElement;

import ru.chapaj.util.bean.Pair;
import ru.chapaj.util.event.Event;

public class NeedAddChild extends Event<Pair<RootElement, RootElement>> {

	public NeedAddChild(Pair<RootElement, RootElement> data) {
		super(data);
	}
	
	

}
