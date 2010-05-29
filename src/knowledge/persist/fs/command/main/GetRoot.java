package knowledge.persist.fs.command.main;

import java.io.File;

import model.knowledge.Root;
import model.knowledge.RootElement;
import knowledge.persist.fs.command.Command;

public class GetRoot extends Command<Root>{

	String dirPath;
	boolean createIfNotExist;
	
	
	
	public GetRoot(String dirPath, boolean createIfNotExist) {
		super();
		this.dirPath = dirPath;
		this.createIfNotExist = createIfNotExist;
	}


	@Override
	protected Root doAction() {
		Root root = context.getRootCache().getRoot(dirPath);
		if(root != null) return root;

		String filePath = invokeNext(new GetRootFilePath(dirPath));
		File metaFile = new File(filePath);
		if(!metaFile.exists()){
			if(!createIfNotExist) return null;
			new File(dirPath).mkdir();
			root = new Root();
		}
		else {
			try {
				root = context.getDataStore().loadFile(metaFile);
				//add uuid to the root if it not set yet
				if(root.getUnsafeUuid() == null){
					root.getUuid();
					context.getDataStore().saveFile(metaFile, root, true);
				}
				
				for(RootElement meta : root.getNodes()){
					meta.setParent(root);
				}
			} catch (Exception e) {
				throw new IllegalArgumentException("can't load data from store",e);
			}
		}
		root.setDirPath(dirPath);
		
		context.getRootCache().putRoot(root);
		return root;
	}

}
