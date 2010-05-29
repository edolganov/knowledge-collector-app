package knowledge.persist.fs;

import java.util.List;

import knowledge.AppContext;

import model.knowledge.Root;
import model.knowledge.RootElement;


public class FSPersist  {
	
	AppContext appContext;

	public FSPersist() {
	}

	public void setAppContext(AppContext appContext) {
		this.appContext = appContext;
		appContext.getEventManager().addObjectMethodListeners(this);
	}

	public void addChild(RootElement parent, RootElement node) {
		// TODO Auto-generated method stub
		
	}

	public void updateRoot(Root root) {
		// TODO Auto-generated method stub
		
	}

	public Root getRoot() {
		// TODO Auto-generated method stub
		return new Root();
	}

	public List<RootElement> getChildren(RootElement node) {
		// TODO Auto-generated method stub
		return null;
	}

	public RootElement find(String nodeRootUuid, String nodeUuid) {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(RootElement node) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	


}
