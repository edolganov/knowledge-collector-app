package knowledge.persist.fs.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import knowledge.event.persist.SubNodeDeleted;

import ru.chapaj.util.event.EventManager;
import ru.kc.model.knowledge.Container;
import util.Util;


public class RootCache {
	
	EventManager eventManager;

	ReadWriteLock lock = new ReentrantReadWriteLock();
	
	//[path - root]
	HashMap<String, Container> rootsPathMap = new HashMap<String, Container>();
	//[uuid - root]
	HashMap<String, Container> rootsUuidMap = new HashMap<String, Container>();
	
	// [14.10.2009] jenua.dolganov: более корректная модель для кеширования рутов 
	// строим дерево из рутов, храня название их папки (или весь путь для корневого рута)
	// при удалении, перемещении, переименовании не нужно пробегать весь кеш - достаточно
	// только исправить один элемент
	@Deprecated
	static class RootElement {
		Container root;
		List<Container> chidren = new LinkedList<Container>();
		Container parent;
		String dirName;
	}
	
	
	public RootCache(EventManager eventManager) {
		super();
		this.eventManager = eventManager;
	}
	
	public void putRoot(Container root){
		lock.writeLock().lock();
		try {
			rootsPathMap.put(root.getDirPath(), root);
			rootsUuidMap.put(root.getUuid(), root);
		} finally {
			lock.writeLock().unlock();
		}
	}
	
	public Container getRoot(String path){
		lock.readLock().lock();
		try {
			return rootsPathMap.get(path);
		} finally {
			lock.readLock().unlock();
		}
	}
	
	public Container getRootByUuid(String rootUuid) {
		lock.readLock().lock();
		try {
			return rootsUuidMap.get(rootUuid);
		} finally {
			lock.readLock().unlock();
		}
	}
	
	/**
	 * Не очень красивое (полный перебор), но простое решение обновления кеша
	 * @param pathPattern
	 */
	public void deleteAllRoots(String pathPattern) {
		ArrayList<ru.kc.model.knowledge.Element> out = new ArrayList<ru.kc.model.knowledge.Element>();
		
		lock.writeLock().lock();
		try {
			pathPattern = pathPattern.replace('\\', '/');
			LinkedList<String> keys = new LinkedList<String>();
			for(String key : rootsPathMap.keySet())
				if(key.startsWith(pathPattern))keys.add(key);
			for(String key : keys){
				Container root = rootsPathMap.remove(key);
				rootsUuidMap.remove(root.getUuid());
				out.addAll(root.getNodes());
			}
			
		} finally {
			lock.writeLock().unlock();
		}
		
		if(!Util.isEmpty(out)){
			eventManager.fireEvent(this, new SubNodeDeleted(out));
		}
	}
	
	/**
	 * Не очень красивое (полный перебор), но простое решение обновления кеша
	 * @param pathPattern
	 */
	public void renameAllRoots(String oldPathPattern, String newPathPattern) {
		
		lock.writeLock().lock();
		try {
			oldPathPattern = oldPathPattern.replace('\\', '/');
			newPathPattern = newPathPattern.replace('\\', '/');
			int oldPatternLenth = oldPathPattern.length();
			LinkedList<String> keys = new LinkedList<String>();
			for(String key : rootsPathMap.keySet())
				if(key.startsWith(oldPathPattern))keys.add(key);
			for(String key : keys){
				Container root = rootsPathMap.remove(key);
				String dirPath = root.getDirPath();
				String newPath = newPathPattern;
				String part = dirPath.substring(oldPatternLenth);
				if(part.length() > 0) newPath = newPath + part;
				root.setDirPath(newPath);
				rootsPathMap.put(root.getDirPath(), root);
				
			}
		} finally {
			lock.writeLock().unlock();
		}
		
	}

}
