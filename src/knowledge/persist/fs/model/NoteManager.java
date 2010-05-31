package knowledge.persist.fs.model;

import java.io.File;

import knowledge.persist.fs.exception.RenameException;
import knowledge.persist.fs.tools.FileNameUtil;
import ru.chapaj.util.bean.Pair;
import model.knowledge.Note;
import model.knowledge.Root;

public class NoteManager extends AbstractNodeManager implements INodeManager<Note> {
	
	@Override
	protected String getDirName(String name) {
		return FileNameUtil.SYSTEM_CHAR+"note"+FileNameUtil.SYSTEM_CHAR+FileNameUtil.convertToValidFSName(name);
	}

	@Override
	public String getDirPath(Note node) {
		return getNodeDirPath(node);
	}

	@Override
	public Pair<String, String> move(Root oldRoot, Note node) {
		String name = node.getName();
		String dirName = getDirName(name);
		String textName = getTextFileName(name);
		String oldDirPath = FileNameUtil.getFilePath(oldRoot.getDirPath(), dirName);
		String oldTextPath = FileNameUtil.getFilePath(oldRoot.getDirPath(), textName);
		String newRootPath = node.getParent().getDirPath();
		String newDirPath = FileNameUtil.getFilePath(newRootPath, dirName);
		String newTextPath = FileNameUtil.getFilePath(newRootPath, textName);
		//System.out.println("TextKeeper: " + oldDirPath + " -> " + newDirPath);
		//System.out.println("TextKeeper: " + oldTextPath + " -> " + newTextPath);
		File oldTextFile = new File(oldTextPath);
		if(!oldTextFile.exists()){
			throw new IllegalStateException(oldTextFile.getPath()+" not exist");
		}

		if(!oldTextFile.renameTo(new File(newTextPath))){
			throw new RenameException(oldTextPath);
		}
		
		File oldDir = new File(oldDirPath);
		if(!oldDir.exists()){
			throw new IllegalStateException(oldDir.getPath()+" not exist");
		}

		if(!oldDir.renameTo(new File(newDirPath))){
			throw new RenameException(oldDirPath);
		}
		return new Pair<String, String>(oldDirPath, newDirPath);
		
	}
	
	private String getTextFileName(String name) {
		return FileNameUtil.convertToValidFSName(name)+".txt";
	}

}
