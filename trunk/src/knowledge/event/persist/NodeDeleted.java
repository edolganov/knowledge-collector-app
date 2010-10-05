package knowledge.event.persist;


import model.knowledge.Element;

import ru.chapaj.util.event.Event;

public class NodeDeleted extends Event<Element> {

	public NodeDeleted(Element data) {
		super(data);
	}
	
	

}
