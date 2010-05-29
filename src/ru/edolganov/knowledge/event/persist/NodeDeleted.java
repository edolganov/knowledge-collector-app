package ru.edolganov.knowledge.event.persist;


import model.knowledge.RootElement;

import ru.chapaj.util.event.Event;

public class NodeDeleted extends Event<RootElement> {

	public NodeDeleted(RootElement data) {
		super(data);
	}
	
	

}
