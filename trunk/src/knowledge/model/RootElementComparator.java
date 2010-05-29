package knowledge.model;

import java.util.Comparator;

import model.knowledge.RootElement;

public class RootElementComparator implements Comparator<RootElement> {

	@Override
	public int compare(RootElement a, RootElement b) {
		return ModelClassComparator.compare(ModelClassComparator.index(a), ModelClassComparator.index(b));
	}

}
