package knowledge.persist.fs.command.main;

import knowledge.event.persist.NodeDeleted;
import knowledge.persist.fs.command.Command;
import knowledge.persist.fs.model.INodeManager;
import model.knowledge.Root;
import model.knowledge.RootElement;

public class DeleteElement extends Command<Void>{
	
	RootElement node;
	
	

	public DeleteElement(RootElement node) {
		super();
		this.node = node;
	}



	@SuppressWarnings("unchecked")
	@Override
	protected Void doAction() throws Exception {
		Root root = node.getParent();
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
