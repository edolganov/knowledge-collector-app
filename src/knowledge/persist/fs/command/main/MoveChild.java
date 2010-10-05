package knowledge.persist.fs.command.main;

import knowledge.event.persist.ChildAdded;
import knowledge.persist.fs.command.Command;
import knowledge.persist.fs.command.main.check.CheckNoExist;
import knowledge.persist.fs.model.INodeManager;
import model.knowledge.Container;
import model.knowledge.Element;
import ru.chapaj.util.bean.Pair;

public class MoveChild extends Command<Void>{
	
	Element parent;
	Element child;

	public MoveChild(Element parent, Element child) {
		super();
		this.parent = parent;
		this.child = child;
	}



	@SuppressWarnings("unchecked")
	@Override
	protected Void doAction() {
		if(parent == null || child == null) return null;
		if(child.getParent() == null) return null;
		
		invokeNext(new CheckNoExist(parent,child));
		
		Container root = invokeNext(new GetNodeRoot(parent,true));
		if(root == null) return null;
		
		//удаляем из старого рута ноду
		Container oldRoot = child.getParent();
		oldRoot.getNodes().remove(child);
		
		//добавляем в новый рут ноду
		root.getNodes().add(child);
		child.setParent(root);
		

		INodeManager nodeManager = context.getNodeManagerMap().get(child.getClass());
		if(nodeManager != null){
			Pair<String, String> result = nodeManager.move(oldRoot,child);
			if(result != null){
				context.getRootCache().renameAllRoots(result.first, result.second);
			}
		}

		invokeNext(new SaveRoot(oldRoot));
		invokeNext(new SaveRoot(root));
		
		fireEvent(new ChildAdded(new Pair<Element, Element>(parent, child)));
		return null;
	}

}
