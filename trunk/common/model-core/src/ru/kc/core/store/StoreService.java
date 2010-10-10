package ru.kc.core.store;

import java.io.File;
import java.util.Set;

import model.knowledge.Container;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.reflections.Reflections;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import ru.chapaj.util.store.XmlStore;
import ru.chapaj.util.xml.ObjectToXMLConverter;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class StoreService {
	
	private static Log log = LogFactory.getLog(StoreService.class);
	
	XmlStore<Container> xmlStore;
	
	public void init(){
		xmlStore = new XmlStore<Container>() {
			
			@Override
			protected void config(ObjectToXMLConverter<Container> converter) {
				Reflections reflections = new Reflections(new ConfigurationBuilder()
		        .setUrls(ClasspathHelper.getUrlsForPackagePrefix("model"))
		        .setScanners(new TypeAnnotationsScanner())
		        .filterInputsBy(new FilterBuilder.Include(FilterBuilder.prefix("model"))));
				
				Set<Class<?>> all = reflections.getTypesAnnotatedWith(XStreamAlias.class);
				if(all == null || all.size() == 0) throw new IllegalStateException("no data model classes with annotation @XStreamAlias");
				log.info("found "+all.size()+" classes");
				
				for (Class<?> clazz : all) {
					converter.configureAliases(clazz);
				}
				
			}
		};
	}

	public Container load(String path) throws Exception {
		File file = new File(path);
		if(!file.exists())return null;
		
		return xmlStore.loadFile(file);
	}

	public Container save(File file, Container container) throws Exception {
		xmlStore.saveFile(file, container, false);
		return container;
	}

}
