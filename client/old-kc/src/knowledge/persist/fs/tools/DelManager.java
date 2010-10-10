package knowledge.persist.fs.tools;

import java.io.File;

import knowledge.persist.fs.exception.RenameException;


public class DelManager {
	
	private static final String DEL_DIR = FileNameUtil.SYSTEM_CHAR+"del"+FileNameUtil.SYSTEM_CHAR;
	
	public DelManager() {
	}
	
	public void delete(String parentPath,String fileName) throws Exception{
		delete(parentPath, fileName, System.currentTimeMillis());
	}
	
	public void delete(String parentPath,String fileName,long timestamp) throws Exception{
		String delDirPath = FileNameUtil.getFilePath(parentPath, DEL_DIR);
		File delDir = new File(delDirPath);
		delDir.mkdir();
		
		File file = new File(FileNameUtil.getFilePath(parentPath, fileName));
		if(!file.exists())return;
		String newName = generateDeleteFileName(fileName,timestamp);
		if(!file.renameTo(new File(FileNameUtil.getFilePath(delDirPath, newName))))
			throw new RenameException(file.getPath());
	}
	
	private String generateDeleteFileName(String name, long timestamp) {
		StringBuilder sb = new StringBuilder().append(name).append(FileNameUtil.SYSTEM_CHAR).append(timestamp);
		return sb.toString();
	}

}
