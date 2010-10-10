package knowledge.persist.fs.model;

import ru.kc.model.knowledge.Node;
import knowledge.persist.fs.tools.DelManager;
import knowledge.persist.fs.tools.FileNameUtil;

public abstract class AbstractNodeManager {
	
	DelManager delManager;
	
	
	public AbstractNodeManager(DelManager delManager) {
		super();
		this.delManager = delManager;
	}

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
	

	public String deleteNode(Node node) throws Exception {
		String deletedFolder = getNodeDirPath(node);
		String name = getDirName(node.getName());
		delManager.delete(node.getParent().getDirPath(), name);
		return deletedFolder;
	}

}
