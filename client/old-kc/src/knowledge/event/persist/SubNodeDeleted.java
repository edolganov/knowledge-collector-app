package knowledge.event.persist;


import java.util.List;


import ru.chapaj.util.event.Event;
import ru.kc.model.knowledge.Element;

public class SubNodeDeleted extends Event<List<Element>> {

	public SubNodeDeleted(List<Element> data) {
		super(data);
	}
	
	

}
