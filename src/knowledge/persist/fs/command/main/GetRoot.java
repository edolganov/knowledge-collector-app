package knowledge.persist.fs.command.main;

import java.io.File;

import ru.chapaj.util.UuidGenerator;

import model.knowledge.Container;
import model.knowledge.Element;
import knowledge.persist.fs.command.Command;

public class GetRoot extends Command<Container>{

	String dirPath;
	boolean createIfNotExist;
	
	
	
	public GetRoot(String dirPath, boolean createIfNotExist) {
		super();
		this.dirPath = dirPath;
		this.createIfNotExist = createIfNotExist;
	}


	@Override
	protected Container doAction() {
		Container root = context.getRootCache().getRoot(dirPath);
		if(root != null) return root;

		String filePath = invokeNext(new GetRootFilePath(dirPath));
		File metaFile = new File(filePath);
		if(!metaFile.exists()){
			if(!createIfNotExist) return null;
			new File(dirPath).mkdir();
			root = new Container();
		}
		else {
			try {
				root = context.getDataStore().loadFile(metaFile);
				//add uuid to the root if it not set yet
				if(root.getUuid() == null){
					root.setUuid(UuidGenerator.simpleUuid());
					context.getDataStore().saveFile(metaFile, root, true);
				}
				
				for(Element meta : root.getNodes()){
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
