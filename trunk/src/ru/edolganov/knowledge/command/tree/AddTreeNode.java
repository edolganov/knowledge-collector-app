package ru.edolganov.knowledge.command.tree;

import ru.chapaj.util.Check;
import ru.edolganov.knowledge.core.command.Command;
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
		
		
		appContext.getPersist().addChild(parent,node);
		return null;
	}

}
