package knowledge.command.tree;

import knowledge.core.command.Command;
import model.knowledge.Element;

public class AddNode extends Command<Void> {

	
	Element node;
	

	public AddNode(Element node) {
		super();
		this.node = node;
	}

	@Override
	public Void doAction() {
		Element curNode = invokeNext(new GetCurNode());
		if(curNode == null) return null;
		
		return invokeNext(new AddTreeNode(curNode, node));
	}

}
