package knowledge.persist.fs.command.main;

import knowledge.event.persist.ChildAdded;
import knowledge.persist.fs.command.Command;
import knowledge.persist.fs.command.main.check.CheckNoExist;
import knowledge.persist.fs.model.INodeManager;
import model.knowledge.Root;
import model.knowledge.RootElement;
import ru.chapaj.util.bean.Pair;

public class MoveChild extends Command<Void>{
	
	RootElement parent;
	RootElement child;

	public MoveChild(RootElement parent, RootElement child) {
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
		
		Root root = invokeNext(new GetNodeRoot(parent,true));
		if(root == null) return null;
		
		//удаляем из старого рута ноду
		Root oldRoot = child.getParent();
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
		
		fireEvent(new ChildAdded(new Pair<RootElement, RootElement>(parent, child)));
		return null;
	}

}
