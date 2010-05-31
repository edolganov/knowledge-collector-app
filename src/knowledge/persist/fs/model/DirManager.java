package knowledge.persist.fs.model;

import model.knowledge.Dir;
import model.knowledge.Root;

public class DirManager extends AbstractNodeManager implements INodeManager<Dir> {

	@Override
	public String getDirPath(Dir node) {
		return getNodeDirPath(node);
	}



}
