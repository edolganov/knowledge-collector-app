package knowledge.event.persist;


import java.util.List;

import model.knowledge.Element;

import ru.chapaj.util.event.Event;

public class SubNodeDeleted extends Event<List<Element>> {

	public SubNodeDeleted(List<Element> data) {
		super(data);
	}
	
	

}
