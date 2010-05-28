package ru.edolganov.knowledge.event.persist;

import model.knowledge.Root;
import ru.chapaj.util.event.Event;

public class NeedUpdateRoot extends Event<Root> {

	public NeedUpdateRoot(Root data) {
		super(data);
	}

}
