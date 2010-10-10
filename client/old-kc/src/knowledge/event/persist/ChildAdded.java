package knowledge.event.persist;


import model.knowledge.Element;

import ru.chapaj.util.bean.Pair;
import ru.chapaj.util.event.Event;

public class ChildAdded extends Event<Pair<Element, Element>> {

	public ChildAdded(Pair<Element, Element> data) {
		super(data);
	}
	
	

}
