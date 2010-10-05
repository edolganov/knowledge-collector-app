package knowledge.persist.fs.command.main;

import java.util.List;

import model.knowledge.Container;
import model.knowledge.Element;
import knowledge.persist.fs.command.Command;

public class FindElement extends Command<Element>{
	
	String rootUuid; 
	String nodeUuid;
	
	

	public FindElement(String rootUuid, String nodeUuid) {
		super();
		this.rootUuid = rootUuid;
		this.nodeUuid = nodeUuid;
	}



	@Override
	protected Element doAction() throws Exception {
		if(rootUuid == null || nodeUuid == null) return null;
		
		Container root = context.getRootCache().getRootByUuid(rootUuid);
		if(root == null) return null;
		
		List<Element> nodes = root.getNodes();
		for (Element rootElement : nodes) {
			if(rootElement.getUuid().equals(nodeUuid)){
				return rootElement;
			}
		}
		return null;
	}

}
