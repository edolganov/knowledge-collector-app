package knowledge.persist.fs.model;

import ru.chapaj.util.bean.Pair;
import model.knowledge.Container;
import model.knowledge.Element;

public interface INodeManager<T extends Element> {
	
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
	Pair<String, String> move(Container oldRoot, T node);

	/**
	 * Удалить все файлы связанные с нодой
	 * @param node
	 * @return deletedDirPath
	 */
	String delete(T node) throws Exception;


}
