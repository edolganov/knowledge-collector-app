package util;

import java.util.Collection;

public class Util {
	
	public static boolean isEmpty(String s){
		if(s == null || s.length() == 0) return true;
		return false;
	}
	
	public static boolean isEmpty(Collection<?> collection){
		if(collection == null || collection.size() == 0) return true;
		return false;
	}
}
