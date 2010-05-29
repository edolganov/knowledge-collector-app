package knowledge.main.controller.tree;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import knowledge.command.tree.MoveNode;
import knowledge.core.controller.Controller;
import knowledge.core.controller.ControllerInfo;
import knowledge.event.persist.ChildAdded;
import knowledge.main.MainConst;
import knowledge.main.ui.MainWindow;
import knowledge.model.RootElementComparator;

import model.knowledge.Node;
import model.knowledge.RootElement;
import model.knowledge.Root;
import ru.chapaj.util.collection.ListUtil;
import ru.chapaj.util.event.annotation.EventListener;
import ru.chapaj.util.lang.ClassUtil;
import ru.chapaj.util.swing.tree.ExtendDefaultTreeModel;
import ru.chapaj.util.swing.tree.TreeNodeAdapter;

@ControllerInfo(target=MainWindow.class, dependence=TreeController.class)
public class SortTreeController extends Controller<MainWindow>{
	
	
	private Comparator<RootElement> nodeComparator = new RootElementComparator();
	
	MainWindow ui;
	
	@Override
	public void init(MainWindow ui_) {
		ui = ui_;
		
		ui.tree.addTreeNodeListener(new TreeNodeAdapter(){
			@Override
			public void onNodeMoveDownRequest() {
				moveNode(true);
			}
			
			@Override
			public void onNodeMoveUpRequest() {
				moveNode(false);
			}
			
			@Override
			public void afterDrop(DefaultMutableTreeNode tagretNode,
					DefaultMutableTreeNode draggedNode) {
				invoke(new MoveNode(tagretNode,draggedNode));
			}
		});
		
	}

	@EventListener(ChildAdded.class)
	public void sortNodes(ChildAdded added) {
		RootElement parent = added.getData().first;
		RootElement child = added.getData().second;
		//update model
		DefaultMutableTreeNode childInitNode = getCache().get(child, MainConst.tree_node.toString());
		TreePath childPath = new TreePath(childInitNode.getPath());
		TreePath selectedPath = ui.tree.isPathSelected(childPath)? childPath : null;
		Root root = child.getParent();
		List<RootElement> nodes = root.getNodes();
		Collections.sort(nodes, nodeComparator);
		
		getPersist().updateRoot(root);
		
		//update tree
		DefaultMutableTreeNode parentNode = getCache().get(parent, MainConst.tree_node.toString());

		for (int i = 0; i < nodes.size(); i++) {
			DefaultMutableTreeNode childNode = getCache().get(nodes.get(i), MainConst.tree_node.toString());
			if(parentNode.getChildAt(i) != childNode){
				parentNode.remove(childNode);
				parentNode.insert(childNode, i);
			}					
		}
		ui.tree.model().reload(parentNode);
		if(selectedPath != null) ui.tree.setSelectionPath(selectedPath);
	}

	private void moveNode(boolean down) {
		final DefaultMutableTreeNode node = ui.tree.getCurrentNode();
		if(node == null) return;
		final DefaultMutableTreeNode parent = (DefaultMutableTreeNode)node.getParent();
		if(parent == null || parent.getChildCount() == 1) return;
		Object ob = node.getUserObject();
		if(!(ob instanceof Node)) return;
		
		RootElement meta = (RootElement) ob;
		Root root = meta.getParent();
		List<RootElement> nodes = root.getNodes();
		
		int oldIndex = nodes.indexOf(meta);
		int minIndex = oldIndex;
		Class<? extends RootElement> nodeClass = meta.getClass();
		for(int i = oldIndex -1 ; i > -1; i--){
			minIndex = i;
			if(!ClassUtil.isValid(nodes.get(i).getClass(), nodeClass)){
				++minIndex;
				break;
			}
		}
		int maxIndex = oldIndex;
		for(int i = oldIndex +1 ; i < nodes.size(); i++){
			maxIndex = i;
			if(!ClassUtil.isValid(nodes.get(i).getClass(), nodeClass)){
				--maxIndex;
				break;
			}
		}
		if(minIndex == maxIndex) return;
		int newIndex = 0;
		if(down) newIndex = oldIndex + 1;
		else newIndex = oldIndex - 1;
		if(newIndex > maxIndex) newIndex = minIndex;
		if(newIndex < minIndex) newIndex = maxIndex;
		//System.out.println("oldIndex:"+oldIndex + " newIndex:" + newIndex);
		//update model
		ListUtil.move(nodes, meta, newIndex);
		
		final int newIndex_ = newIndex;
		
		getPersist().updateRoot(root);
		
		//update tree
		ExtendDefaultTreeModel model = ui.tree.model();
		model.removeNodeFromParent(node);
		model.insertNodeInto(node, parent, newIndex_);
		TreePath treePath = new TreePath(node.getPath());
		ui.tree.setSelectionPath(treePath);

	}
	

}
