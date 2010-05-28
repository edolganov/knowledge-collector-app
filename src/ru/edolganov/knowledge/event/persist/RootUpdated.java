package ru.edolganov.knowledge.event.persist;

import model.knowledge.Root;
import ru.chapaj.util.event.Event;

public class RootUpdated extends Event<Root> {

	public RootUpdated(Root data) {
		super(data);
	}

}
