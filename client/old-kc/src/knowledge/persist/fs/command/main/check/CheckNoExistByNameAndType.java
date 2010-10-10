package knowledge.persist.fs.command.main.check;

import java.util.List;

import ru.chapaj.util.lang.ClassUtil;
import ru.kc.model.knowledge.Container;
import ru.kc.model.knowledge.Element;
import ru.kc.model.knowledge.Node;
import knowledge.exception.NodeExistException;
import knowledge.persist.fs.command.Command;

public class CheckNoExistByNameAndType extends Command<Void>{
	
	Container root;
	String name; 
	Class<? extends Node> childClass;
	
	

	public CheckNoExistByNameAndType(Container root, String name,
			Class<? extends Node> childClass) {
		super();
		this.root = root;
		this.name = name;
		this.childClass = childClass;
	}



	@Override
	protected Void doAction() {
		List<Element> nodes = root.getNodes();
		for (Element n : nodes) {
			if(n instanceof Node){
				if(((Node)n).getName().equals(name) && ClassUtil.isValid(childClass, n.getClass())){
					throw new NodeExistException(name);
				}
			}
		}
		return null;
	}

}
