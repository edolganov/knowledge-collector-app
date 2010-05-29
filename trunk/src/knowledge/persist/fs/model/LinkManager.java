package knowledge.persist.fs.model;

import model.knowledge.Link;

public class LinkManager extends AbstractNodeManager implements INodeManager<Link> {

	@Override
	public String getDirPath(Link node) {
		return getNodeDirPath(node);
	}

}
