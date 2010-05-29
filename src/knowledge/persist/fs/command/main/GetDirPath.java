package knowledge.persist.fs.command.main;

import ru.dolganov.tool.knowledge.collector.dao.fs.DU;
import model.knowledge.Dir;
import model.knowledge.Node;
import model.knowledge.TextData;
import knowledge.persist.fs.command.Command;

public class GetDirPath extends Command<String>{
	
	Node node;
	

	public GetDirPath(Node node) {
		super();
		this.node = node;
	}


	@Override
	protected String doAction() {
		String parentDirPath = node.getParent().getDirPath();
		String dirName = null;
		if(node instanceof Dir){
			dirName = dirKeeper.getDirName(meta);
		}
		else if(node instanceof TextData){
			dirName = textKeeper.getDirName(meta);
		}
		
		if(dirName == null) return null;
		return DU.getFilePath(parentDirPath, dirName);
	}

}
