package knowledge.command.tree;

import javax.swing.tree.DefaultMutableTreeNode;

import knowledge.core.command.Command;

import model.knowledge.RootElement;

public class MoveNode extends Command<Void>{

	
	DefaultMutableTreeNode tagretNode;
	DefaultMutableTreeNode draggedNode;
	
	
	public MoveNode(DefaultMutableTreeNode tagretNode,
			DefaultMutableTreeNode draggedNode) {
		super();
		this.tagretNode = tagretNode;
		this.draggedNode = draggedNode;
	}


	@Override
	protected Void doAction() {
		RootElement[] nodes = invokeNext(new GetMoveElements(tagretNode, draggedNode));
		if(nodes != null){
			invokeNext(new AddTreeNode(nodes[0], nodes[1]));
		}
		return null;
	}
	
	

}
