package knowledge.persist.fs.model;

import model.knowledge.Link;
import model.knowledge.Root;

public class LinkManager extends AbstractNodeManager implements INodeManager<Link> {

	@Override
	public String getDirPath(Link node) {
		return getNodeDirPath(node);
	}

	@Override
	public void move(Root oldRoot, Link node) {
		// TODO Auto-generated method stub
		
	}

}
