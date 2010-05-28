package ru.edolganov.knowledge.command.tree;

import ru.chapaj.util.Check;
import ru.chapaj.util.bean.Pair;
import ru.edolganov.knowledge.core.command.Command;
import ru.edolganov.knowledge.event.persist.NeedAddChild;
import model.knowledge.Link;
import model.knowledge.RootElement;

public class AddTreeNode extends Command<Void> {

	
	RootElement parent, node;
	

	public AddTreeNode(RootElement parent, RootElement node) {
		super();
		this.parent = parent;
		this.node = node;
	}

	@Override
	public Void doAction() {
		if(parent == null || node == null) return null;
		if(node instanceof Link){
			Link l = (Link) node;
			String name = l.getName();
			String url = l.getUrl();
			if(Check.isEmpty(name)){
				l.setName(url);
			}
			else if(Check.isEmpty(url)){
				l.setUrl(name);
			}
		}
		
		fireEvent(new NeedAddChild(new Pair<RootElement, RootElement>(parent, node)));
		return null;
	}

}
