package knowledge.event.persist;



import ru.chapaj.util.event.Event;
import ru.kc.model.knowledge.Element;

public class NodeUpdated extends Event<Element> {

	public NodeUpdated(Element data) {
		super(data);
	}
	
	

}
