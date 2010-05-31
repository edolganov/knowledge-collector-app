package knowledge.persist.fs;

import java.util.List;

import knowledge.AppContext;
import knowledge.persist.fs.command.Command;
import knowledge.persist.fs.command.CommandService;
import knowledge.persist.fs.command.main.AddChild;
import knowledge.persist.fs.command.main.GetChildren;
import knowledge.persist.fs.command.main.GetRoot;
import knowledge.persist.fs.model.DirManager;
import knowledge.persist.fs.model.LinkManager;
import knowledge.persist.fs.model.NodeManagerMap;
import knowledge.persist.fs.model.NoteManager;
import knowledge.persist.fs.tools.DataStore;
import knowledge.persist.fs.tools.RootCache;

import model.knowledge.Dir;
import model.knowledge.Link;
import model.knowledge.Note;
import model.knowledge.Root;
import model.knowledge.RootElement;


public class FSPersist  {
	
	PersistContext context;
	CommandService commandService;
	DataStore dataStore;
	RootCache rootCache;
	NodeManagerMap nodeManagerMap;
	
	
	String mainDirPath;
	
	
	public void init(String dirPath, AppContext appContext){
		this.mainDirPath = dirPath;
		
		appContext.getEventManager().addObjectMethodListeners(this);
		
		context = new PersistContext();
		context.setAppContext(appContext);
		
		dataStore = new DataStore();
		context.setDataStore(dataStore);
		
		rootCache = new RootCache(appContext.getEventManager());
		context.setRootCache(rootCache);
		
		nodeManagerMap = new NodeManagerMap();
		nodeManagerMap.put(Dir.class, new DirManager());
		nodeManagerMap.put(Link.class, new LinkManager());
		nodeManagerMap.put(Note.class, new NoteManager());
		context.setNodeManagerMap(nodeManagerMap);
		
		commandService = new CommandService();
		commandService.setContext(context);
		
		invoke(new GetRoot(dirPath,true));
	}
	
	private <T> T invoke(Command<T> c) {
		return commandService.invoke(c);
	}


	public void addChild(RootElement parent, RootElement child) {
		invoke(new AddChild(parent, child));
	}

	public void updateRoot(Root root) {
		// TODO Auto-generated method stub
		
	}

	public Root getRoot() {
		Root out = invoke(new GetRoot(mainDirPath,false));
		return out;
	}

	public List<RootElement> getChildren(RootElement node) {
		List<RootElement> out = invoke(new GetChildren(node));
		return out;
	}

	public RootElement find(String nodeRootUuid, String nodeUuid) {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(RootElement node) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	


}
