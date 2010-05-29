package knowledge.persist.fs.command.main;

import model.knowledge.Node;
import model.knowledge.Root;
import knowledge.persist.fs.command.Command;

public class GetNodeRoot extends Command<Root> {
	
	Node meta;
	boolean createIfNotExist;
	
	

	public GetNodeRoot(Node meta, boolean createIfNotExist) {
		super();
		this.meta = meta;
		this.createIfNotExist = createIfNotExist;
	}


	@Override
	protected Root doAction() {
		String path = getDirPath(meta);
		if(path == null) return null;
		return invokeNext(new GetRoot(path,createIfNotExist));
	}

}
