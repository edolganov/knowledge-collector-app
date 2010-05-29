package knowledge.event.persist;


import model.knowledge.RootElement;

import ru.chapaj.util.event.Event;

public class NodeUpdated extends Event<RootElement> {

	public NodeUpdated(RootElement data) {
		super(data);
	}
	
	

}
