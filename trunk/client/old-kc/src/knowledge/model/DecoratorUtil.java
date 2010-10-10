package knowledge.model;

import model.knowledge.Dir;
import model.knowledge.LocalLink;
import model.knowledge.NetworkLink;
import model.knowledge.Node;
import model.knowledge.Element;
import model.knowledge.TextData;
import ru.chapaj.util.lang.ClassUtil;

public class DecoratorUtil {
	
	public static String name(Element meta){
		return name(meta.getClass());
	}
	
	public static String name(Class<? extends Element> candidat){
		if(ClassUtil.isValid(candidat, Dir.class)) return "Dir";
		else if(ClassUtil.isValid(candidat, TextData.class)) return "Text";
		else if(ClassUtil.isValid(candidat,LocalLink.class)) return "Local link";
		else if(ClassUtil.isValid(candidat,NetworkLink.class)) return "Link";
		return "Node";
	}
}
