package knowledge.persist.fs;

import java.util.List;

import ru.kc.model.knowledge.Container;
import ru.kc.model.knowledge.Dir;
import ru.kc.model.knowledge.Element;
import ru.kc.model.knowledge.Link;
import ru.kc.model.knowledge.Note;

import knowledge.AppContext;
import knowledge.persist.fs.command.Command;
import knowledge.persist.fs.command.CommandService;
import knowledge.persist.fs.command.main.AddChild;
import knowledge.persist.fs.command.main.DeleteElement;
import knowledge.persist.fs.command.main.FindElement;
import knowledge.persist.fs.command.main.GetChildren;
import knowledge.persist.fs.command.main.GetRoot;
import knowledge.persist.fs.command.main.SaveRoot;
import knowledge.persist.fs.model.DirManager;
import knowledge.persist.fs.model.LinkManager;
import knowledge.persist.fs.model.NodeManagerMap;
import knowledge.persist.fs.model.NoteManager;
import knowledge.persist.fs.tools.DataStore;
import knowledge.persist.fs.tools.DelManager;
import knowledge.persist.fs.tools.RootCache;



public class FSPersist  {
	
	PersistContext context;
	CommandService commandService;
	DataStore dataStore;
	RootCache rootCache;
	NodeManagerMap nodeManagerMap;
	DelManager delManager;
	
	
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
		
		delManager = new DelManager();
		context.setDelManager(delManager);
		
		nodeManagerMap = new NodeManagerMap();
		nodeManagerMap.put(Dir.class, new DirManager(delManager));
		nodeManagerMap.put(Link.class, new LinkManager(delManager));
		nodeManagerMap.put(Note.class, new NoteManager(delManager));
		context.setNodeManagerMap(nodeManagerMap);
		

		
		commandService = new CommandService();
		commandService.setContext(context);
		
		invoke(new GetRoot(dirPath,true));
	}
	
	private <T> T invoke(Command<T> c) {
		return commandService.invoke(c);
	}


	public void addChild(Element parent, Element child) {
		invoke(new AddChild(parent, child));
	}

	public void updateRoot(Container root) {
		if(root == null) return;
		invoke(new SaveRoot(root));
	}

	public Container getRoot() {
		Container out = invoke(new GetRoot(mainDirPath,false));
		return out;
	}

	public List<Element> getChildren(Element node) {
		List<Element> out = invoke(new GetChildren(node));
		return out;
	}

	public Element find(String nodeRootUuid, String nodeUuid) {
		return invoke(new FindElement(nodeRootUuid, nodeUuid));
	}

	public void delete(Element node) {
		invoke(new DeleteElement(node));
	}
	
	
	
	


}
