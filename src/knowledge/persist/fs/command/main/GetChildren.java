package knowledge.persist.fs.command.main;

import java.util.Collections;
import java.util.List;

import model.knowledge.Node;
import model.knowledge.Container;
import model.knowledge.Element;
import knowledge.persist.fs.command.Command;

public class GetChildren extends Command<List<Element>>{
	
	Element node;
	
	

	public GetChildren(Element node) {
		super();
		this.node = node;
	}



	@Override
	protected List<Element> doAction() {

		Container root = invokeNext(new GetNodeRoot(node, false));
		if(root != null) return root.getNodes();
		
		return Collections.emptyList();
	}

}
