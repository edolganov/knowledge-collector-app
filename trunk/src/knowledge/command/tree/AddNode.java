package knowledge.command.tree;

import knowledge.core.command.Command;
import model.knowledge.RootElement;

public class AddNode extends Command<Void> {

	
	RootElement node;
	

	public AddNode(RootElement node) {
		super();
		this.node = node;
	}

	@Override
	public Void doAction() {
		RootElement curNode = invokeNext(new GetCurNode());
		if(curNode == null) return null;
		
		return invokeNext(new AddTreeNode(curNode, node));
	}

}
