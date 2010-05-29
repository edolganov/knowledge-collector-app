package knowledge.event.persist;


import model.knowledge.RootElement;

import ru.chapaj.util.bean.Pair;
import ru.chapaj.util.event.Event;

public class ChildAdded extends Event<Pair<RootElement, RootElement>> {

	public ChildAdded(Pair<RootElement, RootElement> data) {
		super(data);
	}
	
	

}
