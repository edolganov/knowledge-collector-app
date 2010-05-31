package knowledge.persist.fs.model;

import java.io.File;

import knowledge.persist.fs.exception.RenameException;
import knowledge.persist.fs.tools.DelManager;
import knowledge.persist.fs.tools.FileNameUtil;
import ru.chapaj.util.bean.Pair;
import model.knowledge.Link;
import model.knowledge.Root;

public class LinkManager extends AbstractNodeManager implements INodeManager<Link> {
	
	public LinkManager(DelManager delManager) {
		super(delManager);
	}

	@Override
	protected String getDirName(String name) {
		return FileNameUtil.SYSTEM_CHAR+"link"+FileNameUtil.SYSTEM_CHAR+FileNameUtil.convertToValidFSName(name);
	}

	@Override
	public String getDirPath(Link node) {
		return getNodeDirPath(node);
	}

	@Override
	public Pair<String, String> move(Root oldRoot, Link node) {
		String dirName = getDirName(node);
		String oldPath = FileNameUtil.getFilePath(oldRoot.getDirPath(), dirName);
		String newRootPath = node.getParent().getDirPath();
		String newPath = FileNameUtil.getFilePath(newRootPath, dirName);
		//System.out.println("DirKeeper: " + oldPath + " -> " + newPath);
		File oldFile = new File(oldPath);
		if(oldFile.exists()){
			if(!oldFile.renameTo(new File(newPath))){
				throw new RenameException(oldPath);
			}
			return new Pair<String, String>(oldPath, newPath);
		}
		return null;

	}

	@Override
	public String delete(Link node) throws Exception {
		return deleteNode(node);
	}



}
