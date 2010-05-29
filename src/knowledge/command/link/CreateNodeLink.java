package knowledge.command.link;

import javax.swing.tree.DefaultMutableTreeNode;

import knowledge.command.tree.AddTreeNode;
import knowledge.command.tree.GetMoveElements;
import knowledge.core.command.Command;
import knowledge.main.MainConst;

import model.knowledge.NodeLink;
import model.knowledge.RootElement;

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
