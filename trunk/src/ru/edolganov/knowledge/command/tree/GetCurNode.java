package ru.edolganov.knowledge.command.tree;

import model.knowledge.RootElement;
import ru.edolganov.knowledge.core.command.Command;

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
