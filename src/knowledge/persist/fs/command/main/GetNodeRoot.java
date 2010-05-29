package knowledge.persist.fs.command.main;

import model.knowledge.Root;
import model.knowledge.RootElement;
import knowledge.persist.fs.command.Command;
import knowledge.persist.fs.model.INodeManager;

public class GetNodeRoot extends Command<Root> {
	
	RootElement node;
	boolean createIfNotExist;
	
	

	public GetNodeRoot(RootElement meta, boolean createIfNotExist) {
		super();
		this.node = meta;
		this.createIfNotExist = createIfNotExist;
	}


	@SuppressWarnings("unchecked")
	@Override
	protected Root doAction() {
		INodeManager nodeManager = context.getNodeManagerMap().get(node.getClass());
		if(nodeManager == null) return null;
		
		String path = nodeManager.getDirPath(node);
		if(path == null) return null;
		return invokeNext(new GetRoot(path,createIfNotExist));
	}

}
