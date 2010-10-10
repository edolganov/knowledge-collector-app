package knowledge.model;

import java.util.Comparator;

import ru.chapaj.util.lang.ClassUtil;

import model.knowledge.Dir;
import model.knowledge.LocalLink;
import model.knowledge.NetworkLink;
import model.knowledge.Node;
import model.knowledge.Element;
import model.knowledge.TextData;

public class ModelClassComparator implements Comparator<Class<?>> {

	@SuppressWarnings("unchecked")
	@Override
	public int compare(Class<?> o1, Class<?> o2) {
		return compare(index((Class<? extends Node>)o1), index((Class<? extends Node>)o2));
	}
	
	public static int index(Element meta){
		return index(meta.getClass());
	}
	
	public static int index(Class<? extends Element> candidat){
		int out = Integer.MAX_VALUE;
		if(ClassUtil.isValid(candidat, Dir.class)) out = 0;
		else if(ClassUtil.isValid(candidat, TextData.class)) out = 10;
		else if(ClassUtil.isValid(candidat,LocalLink.class)) out = 20;
		else if(ClassUtil.isValid(candidat,NetworkLink.class)) out = 30;
		return out;
	}
	
	public static int compare(int na, int nb){
		return (na<nb ? -1 : (na==nb ? 0 : 1));
	}

}
