package knowledge.command.tree;

import knowledge.core.command.Command;
import model.knowledge.RootElement;

public class GetCurNode extends Command<RootElement> {

	@Override
	protected RootElement doAction() {
		Object ob = mainWindow.tree.getCurrentObject();
		if(ob == null) return null;
		
		if (ob instanceof RootElement) {
			return (RootElement) ob;
		}
		
		return null;
	}

}
