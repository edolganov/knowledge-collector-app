package knowledge.persist.fs.command.main;

import knowledge.event.persist.NodeDeleted;
import knowledge.persist.fs.command.Command;
import knowledge.persist.fs.model.INodeManager;
import model.knowledge.Container;
import model.knowledge.Element;

public class DeleteElement extends Command<Void>{
	
	Element node;
	
	

	public DeleteElement(Element node) {
		super();
		this.node = node;
	}



	@SuppressWarnings("unchecked")
	@Override
	protected Void doAction() throws Exception {
		Container root = node.getParent();
		root.getNodes().remove(node);
		
		INodeManager nodeManager = context.getNodeManagerMap().get(node.getClass());
		if(nodeManager != null){
			String deletetDirPath = nodeManager.delete(node);
			context.getRootCache().deleteAllRoots(deletetDirPath);
		}
		
		invokeNext(new SaveRoot(root));
		fireEvent(new NodeDeleted(node));
		
		return null;
	}

}
