package knowledge.persist.fs.command.main;

import model.knowledge.Container;
import model.knowledge.Element;
import knowledge.persist.fs.command.Command;
import knowledge.persist.fs.model.INodeManager;

public class GetNodeRoot extends Command<Container> {
	
	Element node;
	boolean createIfNotExist;
	
	

	public GetNodeRoot(Element meta, boolean createIfNotExist) {
		super();
		this.node = meta;
		this.createIfNotExist = createIfNotExist;
	}


	@SuppressWarnings("unchecked")
	@Override
	protected Container doAction() {
		INodeManager nodeManager = context.getNodeManagerMap().get(node.getClass());
		if(nodeManager == null) return null;
		
		String path = nodeManager.getDirPath(node);
		if(path == null) return null;
		return invokeNext(new GetRoot(path,createIfNotExist));
	}

}
