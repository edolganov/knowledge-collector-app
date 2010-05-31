package knowledge.persist.fs.command.main.check;

import model.knowledge.Node;
import model.knowledge.Root;
import model.knowledge.RootElement;
import model.knowledge.role.Parent;
import knowledge.persist.fs.command.Command;
import knowledge.persist.fs.command.main.GetNodeRoot;

public class CheckNoExist extends Command<Void>{
	
	Parent parent; 
	RootElement child;
	

	public CheckNoExist(Parent parent, RootElement child) {
		super();
		this.parent = parent;
		this.child = child;
	}



	@Override
	protected Void doAction() {
		if(child instanceof Node){
			Node childNode = (Node)child;
			Root root = null;
			if (parent instanceof Root) {
				root = (Root) parent;
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
