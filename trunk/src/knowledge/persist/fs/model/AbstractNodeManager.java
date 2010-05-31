package knowledge.persist.fs.model;

import knowledge.persist.fs.tools.FileNameUtil;
import model.knowledge.Node;

public abstract class AbstractNodeManager {
	
	public String getDirName(Node node){
		String name = node.getName();
		String dirName = getDirName(name);
		return dirName;
	}
	
	protected abstract String getDirName(String name);
	
	public String getNodeDirPath(Node node){
		String parentDirPath = node.getParent().getDirPath();
		String dirName = getDirName(node);
		String out = FileNameUtil.getFilePath(parentDirPath, dirName);
		return out;
	}

}
