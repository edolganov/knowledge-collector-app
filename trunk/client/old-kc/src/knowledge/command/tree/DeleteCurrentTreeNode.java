package knowledge.command.tree;

import ru.kc.model.knowledge.Element;
import knowledge.core.command.Command;

public class DeleteCurrentTreeNode extends Command<Void> {

	@Override
	public Void doAction() {
		Element node = invokeNext(new GetCurNode());
		if(node == null) return null;
		
		appContext.getPersist().delete(node);
		return null;
	}

}
