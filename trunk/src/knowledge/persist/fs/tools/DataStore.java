package knowledge.persist.fs.tools;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import model.knowledge.Root;
import ru.chapaj.util.lang.PackageExplorer;
import ru.chapaj.util.store.XmlStore;
import ru.chapaj.util.xml.ObjectToXMLConverter;

public class DataStore extends XmlStore<Root> {

	@Override
	protected void config(final ObjectToXMLConverter<Root> converter) {
		PackageExplorer.find("model", new PackageExplorer.Callback(){

			@Override
			public void found(Class<?> clazz) {
				if(clazz.getAnnotation(XStreamAlias.class) != null){
					//System.out.println(clazz);
					converter.configureAliases(clazz);
				}
			}
			
		});
//		converter.configureAliases(
//				NodeMeta.class,
//				Root.class,
//				Dir.class,
//				LocalLink.class,
//				NetworkLink.class,
//				Note.class,
//				Image.class,
//				TreeSnapshotDir.class,
//				TreeSnapshotRoot.class,
//				TreeSnapshot.class,
//				TreeLink.class,
//				Tag.class
//				);
		
	}
}
