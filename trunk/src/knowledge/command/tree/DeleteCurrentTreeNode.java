package knowledge.command.tree;

import knowledge.core.command.Command;
import model.knowledge.Element;

public class DeleteCurrentTreeNode extends Command<Void> {

	@Override
	public Void doAction() {
		Element node = invokeNext(new GetCurNode());
		if(node == null) return null;
		
		appContext.getPersist().delete(node);
		return null;
	}

}
