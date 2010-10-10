package knowledge.event.persist;



import ru.chapaj.util.event.Event;
import ru.kc.model.knowledge.Element;

public class NodeDeleted extends Event<Element> {

	public NodeDeleted(Element data) {
		super(data);
	}
	
	

}
