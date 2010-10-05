package knowledge.event.persist;


import model.knowledge.Element;

import ru.chapaj.util.event.Event;

public class NodeUpdated extends Event<Element> {

	public NodeUpdated(Element data) {
		super(data);
	}
	
	

}
