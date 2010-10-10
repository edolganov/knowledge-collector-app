package knowledge.command.link;

import javax.swing.tree.DefaultMutableTreeNode;

import ru.kc.model.knowledge.Element;
import ru.kc.model.knowledge.NodeLink;

import knowledge.command.tree.AddTreeNode;
import knowledge.command.tree.GetMoveElements;
import knowledge.core.command.Command;
import knowledge.main.MainConst;


public class CreateNodeLink extends Command<Void> {

	@Override
	protected Void doAction() {
		DefaultMutableTreeNode element = appContext.getSessionCache().get(MainConst.node_link_candidate.toString());
		DefaultMutableTreeNode curNode = mainWindow.tree.getCurrentNode();
		
		
		Element[] nodes = invokeNext(new GetMoveElements(curNode, element));
		if(nodes != null){
			Element parent = nodes[0];
			Element donor = nodes[1];
			NodeLink link = new NodeLink();
			link.setNodeUuid(donor.getUuid());
			link.setNodeRootUuid(donor.getParent().getUuid());
			invokeNext(new AddTreeNode(parent, link));
		}
		
		return null;
	}

}
