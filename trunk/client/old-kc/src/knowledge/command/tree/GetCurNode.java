package knowledge.command.tree;

import ru.kc.model.knowledge.Element;
import knowledge.core.command.Command;

public class GetCurNode extends Command<Element> {

	@Override
	protected Element doAction() {
		Object ob = mainWindow.tree.getCurrentObject();
		if(ob == null) return null;
		
		if (ob instanceof Element) {
			return (Element) ob;
		}
		
		return null;
	}

}
