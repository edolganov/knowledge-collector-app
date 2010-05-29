package ru.edolganov.knowledge.tools;

import java.util.HashMap;

public class SessionCache {
	
	HashMap<String, Object> session = new HashMap<String, Object>();
	
	public void put(String key, Object value){
		session.put(key, value);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T get(String key){
		return (T)session.get(key);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T remove(String key){
		return (T)session.remove(key);
	}

}
