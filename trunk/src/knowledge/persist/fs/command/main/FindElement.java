package knowledge.persist.fs.command.main;

import java.util.List;

import model.knowledge.Root;
import model.knowledge.RootElement;
import knowledge.persist.fs.command.Command;

public class FindElement extends Command<RootElement>{
	
	String rootUuid; 
	String nodeUuid;
	
	

	public FindElement(String rootUuid, String nodeUuid) {
		super();
		this.rootUuid = rootUuid;
		this.nodeUuid = nodeUuid;
	}



	@Override
	protected RootElement doAction() throws Exception {
		if(rootUuid == null || nodeUuid == null) return null;
		
		Root root = context.getRootCache().getRootByUuid(rootUuid);
		if(root == null) return null;
		
		List<RootElement> nodes = root.getNodes();
		for (RootElement rootElement : nodes) {
			if(rootElement.getUuid().equals(nodeUuid)){
				return rootElement;
			}
		}
		return null;
	}

}
