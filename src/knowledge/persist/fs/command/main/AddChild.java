package knowledge.persist.fs.command.main;

import knowledge.event.persist.ChildAdded;
import knowledge.persist.fs.command.Command;
import knowledge.persist.fs.command.main.check.CheckNoExist;
import knowledge.persist.fs.model.INodeManager;
import model.knowledge.Root;
import model.knowledge.RootElement;
import ru.chapaj.util.bean.Pair;

public class AddChild extends Command<Void>{
	
	RootElement parent;
	RootElement child;
	

	public AddChild(RootElement parent, RootElement child) {
		super();
		this.parent = parent;
		this.child = child;
	}



	@Override
	protected Void doAction() {
		if(parent == null || child == null) return null;
	
		//если у ребенка уже был родитель перемещаем ноду
		if(child.getParent() != null){
			invokeNext(new MoveChild(parent,child));
			return null;
		}
		//иначе добавляем созданную ноду родителю
		
		invokeNext(new CheckNoExist(parent,child));

		
		Root root = invokeNext(new GetNodeRoot(parent,true));
		if(root == null) return null;
		
		
		//добавляем в новый рут ноду
		root.getNodes().add(child);
		child.setParent(root);
		
		
		//сохраняем 
		invokeNext(new SaveRoot(root));
		fireEvent(new ChildAdded(new Pair<RootElement, RootElement>(parent, child)));
		
		return null;
		
	}
	

}
