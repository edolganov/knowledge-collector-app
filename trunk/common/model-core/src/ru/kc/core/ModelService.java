package ru.kc.core;

import java.io.File;

import ru.chapaj.util.UuidGenerator;
import ru.kc.core.store.StoreService;

import model.knowledge.Container;

public class ModelService {
	
	private String rootPath;
	
	private StoreService storeService = new StoreService();
	
	public void init(String dirPath){
		File knowDir = new File(dirPath+"/know");
		if(!knowDir.exists()){
			if(!knowDir.mkdirs())throw new IllegalStateException("can't create dir "+dirPath);
		}
		rootPath = knowDir.getPath();
		
		storeService.init();
	}
	
	public Container getContainer(String path) throws Exception{
		String dataFile = getDataPath(path);
		return storeService.load(dataFile);
	}
	
	public Container createContainer(String path) throws Exception {
		String dataFile = getDataPath(path);
		File file = new File(dataFile);
		if(file.exists()) throw new IllegalArgumentException("file "+path+" already exist");
		
		Container container = new Container();
		container.setUuid(UuidGenerator.simpleUuid());
		
		return storeService.save(file,container);
	}

	private String getDataPath(String path) {
		if(path == null) path = rootPath;
		String dataFile = path +"/"+ Constants.DATA_FILE_NAME;
		return dataFile;
	}
	
	
	//test
	public static void main(String[] args) throws Exception {
		ModelService service = new ModelService();
		service.init("e:\\_kc_test");
		
		Container container = service.getContainer(null);
		if(container == null) {
			container = service.createContainer(null);
		}
		
		System.out.println(container);
	}


}
