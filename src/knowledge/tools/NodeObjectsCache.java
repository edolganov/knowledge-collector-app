package knowledge.tools;

import java.util.HashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import knowledge.AppContext;
import model.knowledge.RootElement;

/**
 * Кеш для сеансового хранения данных, привязанных к объекту
 * @author jenua.dolganov
 *
 */
public class NodeObjectsCache {
	
	AppContext appContext;
	
	
	public NodeObjectsCache() {
		super();
	}

	public void init(AppContext appContext) {
		this.appContext = appContext;
		appContext.getEventManager().addObjectMethodListeners(this);
	}
	
	

	ReadWriteLock lock = new ReentrantReadWriteLock();
	
	HashMap<String, HashMap<String, Object>> objectsMap = new HashMap<String, HashMap<String, Object>>();


	
	
	public void put(RootElement node, String key, Object object){
		lock.writeLock().lock();
		try {
			HashMap<String, Object> nodeObjects = getNodeObjects(node);
			nodeObjects.put(key, object);
		} finally {
			lock.writeLock().unlock();
		}

	}
	


	

	@SuppressWarnings("unchecked")
	public <T> T get(RootElement node, String key){
		lock.readLock().lock();
		try {
			HashMap<String, Object> nodeObjects = getNodeObjects(node);
			return (T)nodeObjects.get(key);
		} finally {
			lock.readLock().unlock();
		}

	}
	
	private HashMap<String, Object> getNodeObjects(RootElement node) {
		String key = getNodeKey(node);
		HashMap<String, Object> out = objectsMap.get(key);
		if(out == null){
			out = new HashMap<String, Object>();
			objectsMap.put(key, out);
		}
		return out;
	}

	private String getNodeKey(RootElement node) {
		return node.getUuid();
	}
	
	

	


	void remove(RootElement node) {
		lock.writeLock().lock();
		try {
			objectsMap.remove(getNodeKey(node));
		} finally {
			lock.writeLock().unlock();
		}
	}



}
