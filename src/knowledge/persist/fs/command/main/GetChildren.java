package knowledge.persist.fs.command.main;

import java.util.Collections;
import java.util.List;

import model.knowledge.Node;
import model.knowledge.Root;
import model.knowledge.RootElement;
import knowledge.persist.fs.command.Command;

public class GetChildren extends Command<List<RootElement>>{
	
	RootElement node;
	
	

	public GetChildren(RootElement node) {
		super();
		this.node = node;
	}



	@Override
	protected List<RootElement> doAction() {

		Root root = invokeNext(new GetNodeRoot(node, false));
		if(root != null) return root.getNodes();
		
		return Collections.emptyList();
	}

}
