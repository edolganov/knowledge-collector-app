package ru.edolganov.knowledge.command.link;

import javax.swing.tree.DefaultMutableTreeNode;

import model.knowledge.NodeLink;
import model.knowledge.RootElement;
import ru.edolganov.knowledge.command.tree.AddTreeNode;
import ru.edolganov.knowledge.command.tree.GetMoveElements;
import ru.edolganov.knowledge.core.command.Command;
import ru.edolganov.knowledge.main.MainConst;

public class CreateNodeLink extends Command<Void> {

	@Override
	protected Void doAction() {
		DefaultMutableTreeNode element = appContext.getSessionCache().get(MainConst.node_link_candidate.toString());
		DefaultMutableTreeNode curNode = mainWindow.tree.getCurrentNode();
		
		
		RootElement[] nodes = invokeNext(new GetMoveElements(curNode, element));
		if(nodes != null){
			RootElement parent = nodes[0];
			RootElement donor = nodes[1];
			NodeLink link = new NodeLink();
			link.setNodeUuid(donor.getUuid());
			link.setNodeRootUuid(donor.getParent().getUuid());
			invokeNext(new AddTreeNode(parent, link));
		}
		
		return null;
	}

}
