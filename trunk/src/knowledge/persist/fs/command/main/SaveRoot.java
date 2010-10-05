package knowledge.persist.fs.command.main;

import java.io.File;

import model.knowledge.Container;
import knowledge.persist.fs.command.Command;

public class SaveRoot extends Command<Void> {
	
	Container root;
	

	public SaveRoot(Container root) {
		super();
		this.root = root;
	}



	@Override
	protected Void doAction() throws Exception {
		String dirPath = root.getDirPath();
		
		//save data.xml
		File dirFile = new File(dirPath);
		dirFile.mkdirs();
		
		String filePath = invokeNext(new GetRootFilePath(dirPath));
		System.out.println("saving " + filePath);
		context.getDataStore().saveFile(new File(filePath),root, true);
		return null;
			
	}

}
