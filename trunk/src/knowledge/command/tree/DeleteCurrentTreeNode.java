package knowledge.command.tree;

import knowledge.core.command.Command;
import model.knowledge.RootElement;

public class DeleteCurrentTreeNode extends Command<Void> {

	@Override
	public Void doAction() {
		RootElement node = invokeNext(new GetCurNode());
		if(node == null) return null;
		
		appContext.getPersist().delete(node);
		return null;
	}

}
