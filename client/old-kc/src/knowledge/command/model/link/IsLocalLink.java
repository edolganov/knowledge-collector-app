package knowledge.command.model.link;

import knowledge.core.command.Command;

public class IsLocalLink extends Command<Boolean>{

	String url;
	
	
	
	public IsLocalLink(String url) {
		super();
		this.url = url;
	}

	@Override
	protected Boolean doAction() {
		if(url.indexOf(':')==1) return true;
		if(url.indexOf('/')==0) return true;
		return false;
	}

}
