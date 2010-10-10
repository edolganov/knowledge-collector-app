package knowledge.persist.fs.command.main.check;

import ru.kc.model.knowledge.Container;
import ru.kc.model.knowledge.Element;
import ru.kc.model.knowledge.Node;
import ru.kc.model.knowledge.role.Parent;
import knowledge.persist.fs.command.Command;
import knowledge.persist.fs.command.main.GetNodeRoot;

public class CheckNoExist extends Command<Void>{
	
	Parent parent; 
	Element child;
	

	public CheckNoExist(Parent parent, Element child) {
		super();
		this.parent = parent;
		this.child = child;
	}



	@Override
	protected Void doAction() {
		if(child instanceof Node){
			Node childNode = (Node)child;
			Container root = null;
			if (parent instanceof Container) {
				root = (Container) parent;
			}
			else if (parent instanceof Node) {
				Node parentNode = (Node) parent;
				root = invokeNext(new GetNodeRoot(parentNode,true));
			}
			
			if(root == null) return null;
			
			String name = childNode.getName();
			Class<? extends Node> childClass = childNode.getClass();
			invokeNext(new CheckNoExistByNameAndType(root, name, childClass));
		}
		
		return null;
	}

}
