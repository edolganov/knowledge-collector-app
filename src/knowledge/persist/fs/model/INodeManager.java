package knowledge.persist.fs.model;

import ru.chapaj.util.bean.Pair;
import model.knowledge.Root;
import model.knowledge.RootElement;

public interface INodeManager<T extends RootElement> {
	
	/**
	 * Получить путь до папки содержащей файл 'data.xml' данного элемента
	 * @param node
	 * @return
	 */
	String getDirPath(T node);

	/**
	 * Переместить данные ноды из одной папки в другую
	 * @param oldRoot
	 * @param node
	 * @return [oldPath, newPath]
	 */
	Pair<String, String> move(Root oldRoot, T node);


}
