package knowledge.persist.fs;

import knowledge.AppContext;
import knowledge.persist.fs.model.NodeManagerMap;
import knowledge.persist.fs.tools.DataStore;
import knowledge.persist.fs.tools.RootCache;

public class PersistContext {
	
	AppContext appContext;
	DataStore dataStore;
	RootCache rootCache;
	Constants constants = new Constants();
	NodeManagerMap nodeManagerMap;
	
	

	public NodeManagerMap getNodeManagerMap() {
		return nodeManagerMap;
	}

	public void setNodeManagerMap(NodeManagerMap nodeManagerMap) {
		this.nodeManagerMap = nodeManagerMap;
	}

	public Constants getConstants() {
		return constants;
	}

	public RootCache getRootCache() {
		return rootCache;
	}

	public void setRootCache(RootCache rootCache) {
		this.rootCache = rootCache;
	}

	public DataStore getDataStore() {
		return dataStore;
	}

	public void setDataStore(DataStore dataStore) {
		this.dataStore = dataStore;
	}

	public AppContext getAppContext() {
		return appContext;
	}

	public void setAppContext(AppContext appContext) {
		this.appContext = appContext;
	}
	
	

}
