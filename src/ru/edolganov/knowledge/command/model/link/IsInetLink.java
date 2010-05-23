package ru.edolganov.knowledge.command.model.link;

import ru.edolganov.knowledge.core.command.Command;

public class IsInetLink extends Command<Boolean>{

	String url;
	
	
	
	public IsInetLink(String url) {
		super();
		this.url = url;
	}

	@Override
	protected Boolean doAction() {
		if(url.startsWith("http")) return true;
		if(url.startsWith("www")) return true;
		return false;
	}

}
