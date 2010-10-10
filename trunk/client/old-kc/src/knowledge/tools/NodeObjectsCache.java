package knowledge.tools;

import java.util.HashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import ru.chapaj.util.event.annotation.EventListener;
import ru.kc.model.knowledge.Element;

import knowledge.AppContext;
import knowledge.event.persist.NodeDeleted;
import knowledge.event.persist.SubNodeDeleted;

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


	
	
	public void put(Element node, String key, Object object){
		lock.writeLock().lock();
		try {
			HashMap<String, Object> nodeObjects = getNodeObjects(node);
			nodeObjects.put(key, object);
		} finally {
			lock.writeLock().unlock();
		}

	}
	


	

	@SuppressWarnings("unchecked")
	public <T> T get(Element node, String key){
		lock.readLock().lock();
		try {
			HashMap<String, Object> nodeObjects = getNodeObjects(node);
			return (T)nodeObjects.get(key);
		} finally {
			lock.readLock().unlock();
		}

	}
	
	private HashMap<String, Object> getNodeObjects(Element node) {
		String key = getNodeKey(node);
		HashMap<String, Object> out = objectsMap.get(key);
		if(out == null){
			out = new HashMap<String, Object>();
			objectsMap.put(key, out);
		}
		return out;
	}

	private String getNodeKey(Element node) {
		return node.getUuid();
	}
	
	

	

	@EventListener(NodeDeleted.class)
	void remove(NodeDeleted deleted) {
		lock.writeLock().lock();
		try {
			objectsMap.remove(getNodeKey(deleted.getData()));
		} finally {
			lock.writeLock().unlock();
		}
	}
	
	@EventListener(SubNodeDeleted.class)
	void remove(SubNodeDeleted deleted){
		lock.writeLock().lock();
		try {
			for(Element node : deleted.getData()){
				objectsMap.remove(getNodeKey(node));
			}
		} finally {
			lock.writeLock().unlock();
		}
	}



}
