package knowledge.event.persist;



import ru.chapaj.util.bean.Pair;
import ru.chapaj.util.event.Event;
import ru.kc.model.knowledge.Element;

public class ChildAdded extends Event<Pair<Element, Element>> {

	public ChildAdded(Pair<Element, Element> data) {
		super(data);
	}
	
	

}
