package knowledge.persist.fs.model;

import java.io.File;

import ru.chapaj.util.bean.Pair;

import knowledge.persist.fs.exception.RenameException;
import knowledge.persist.fs.tools.DelManager;
import knowledge.persist.fs.tools.FileNameUtil;

import model.knowledge.Dir;
import model.knowledge.Root;

public class DirManager extends AbstractNodeManager implements INodeManager<Dir> {

	public DirManager(DelManager delManager) {
		super(delManager);
	}

	@Override
	public String getDirPath(Dir node) {
		return getNodeDirPath(node);
	}

	@Override
	public Pair<String, String> move(Root oldRoot, Dir node) {
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
	protected String getDirName(String name) {
		return FileNameUtil.convertToValidFSName(name);
	}

	@Override
	public String delete(Dir node) throws Exception {
		return deleteNode(node);
	}



}
