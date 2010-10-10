package knowledge.model;

import java.util.Comparator;

import ru.kc.model.knowledge.Element;


public class RootElementComparator implements Comparator<Element> {

	@Override
	public int compare(Element a, Element b) {
		return ModelClassComparator.compare(ModelClassComparator.index(a), ModelClassComparator.index(b));
	}

}
