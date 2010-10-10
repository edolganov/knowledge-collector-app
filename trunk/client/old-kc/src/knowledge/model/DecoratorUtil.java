package knowledge.model;

import ru.chapaj.util.lang.ClassUtil;
import ru.kc.model.knowledge.Dir;
import ru.kc.model.knowledge.Element;
import ru.kc.model.knowledge.LocalLink;
import ru.kc.model.knowledge.NetworkLink;
import ru.kc.model.knowledge.Node;
import ru.kc.model.knowledge.TextData;

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
