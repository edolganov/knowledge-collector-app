package knowledge.persist.fs;

import java.util.List;

import knowledge.AppContext;
import knowledge.persist.fs.command.Command;
import knowledge.persist.fs.command.CommandService;
import knowledge.persist.fs.command.main.GetRoot;
import knowledge.persist.fs.tools.DataStore;
import knowledge.persist.fs.tools.RootCache;

import model.knowledge.Root;
import model.knowledge.RootElement;


public class FSPersist  {
	
	PersistContext context;
	CommandService commandService;
	DataStore dataStore;
	RootCache rootCache;
	
	
	String mainDirPath;
	
	
	public void init(String dirPath, AppContext appContext){
		this.mainDirPath = dirPath;
		
		appContext.getEventManager().addObjectMethodListeners(this);
		
		context = new PersistContext();
		context.setAppContext(appContext);
		
		dataStore = new DataStore();
		context.setDataStore(dataStore);
		
		rootCache = new RootCache();
		

		
		commandService = new CommandService();
		commandService.setContext(context);
		
		invoke(new GetRoot(dirPath,true));
	}
	
	private <T> T invoke(Command<T> c) {
		return commandService.invoke(c);
	}


	public void addChild(RootElement parent, RootElement node) {
		// TODO Auto-generated method stub
		
	}

	public void updateRoot(Root root) {
		// TODO Auto-generated method stub
		
	}

	public Root getRoot() {
		return invoke(new GetRoot(mainDirPath,false));
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
