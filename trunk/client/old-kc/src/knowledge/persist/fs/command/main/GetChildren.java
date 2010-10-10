package knowledge.persist.fs.command.main;

import java.util.Collections;
import java.util.List;

import ru.kc.model.knowledge.Container;
import ru.kc.model.knowledge.Element;
import ru.kc.model.knowledge.Node;

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
