package knowledge.persist.fs.command.main.check;

import java.util.List;

import ru.chapaj.util.lang.ClassUtil;
import model.knowledge.Node;
import model.knowledge.Root;
import model.knowledge.RootElement;
import knowledge.exception.NodeExistException;
import knowledge.persist.fs.command.Command;

public class CheckNoExistByNameAndType extends Command<Void>{
	
	Root root;
	String name; 
	Class<? extends Node> childClass;
	
	

	public CheckNoExistByNameAndType(Root root, String name,
			Class<? extends Node> childClass) {
		super();
		this.root = root;
		this.name = name;
		this.childClass = childClass;
	}



	@Override
	protected Void doAction() {
		List<RootElement> nodes = root.getNodes();
		for (RootElement n : nodes) {
			if(n instanceof Node){
				if(((Node)n).getName().equals(name) && ClassUtil.isValid(childClass, n.getClass())){
					throw new NodeExistException(name);
				}
			}
		}
		return null;
	}

}
