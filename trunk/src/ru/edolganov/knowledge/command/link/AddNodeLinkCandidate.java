package ru.edolganov.knowledge.command.link;

import javax.swing.tree.DefaultMutableTreeNode;

import ru.edolganov.knowledge.core.command.Command;
import ru.edolganov.knowledge.main.MainConst;

public class AddNodeLinkCandidate extends Command<Void> {

	@Override
	protected Void doAction() {
		DefaultMutableTreeNode candidat = mainWindow.tree.getCurrentNode();
		appContext.getSessionCache().put(MainConst.node_link_candidate.toString(), candidat);
		return null;
	}

}
