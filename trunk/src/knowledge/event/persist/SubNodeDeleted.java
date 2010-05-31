package knowledge.event.persist;


import java.util.List;

import model.knowledge.RootElement;

import ru.chapaj.util.event.Event;

public class SubNodeDeleted extends Event<List<RootElement>> {

	public SubNodeDeleted(List<RootElement> data) {
		super(data);
	}
	
	

}
