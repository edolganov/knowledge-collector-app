package knowledge.persist.fs.model;

import knowledge.tools.FileNameUtil;
import model.knowledge.Node;

public class AbstractNodeManager {
	
	public String getNodeDirPath(Node node){
		String parentDirPath = node.getParent().getDirPath();
		String name = node.getName();
		String dirName = FileNameUtil.convertToValidFSName(name);
		String out = FileNameUtil.getFilePath(parentDirPath, dirName);
		return out;
	}

}
