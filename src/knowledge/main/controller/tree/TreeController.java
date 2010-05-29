package knowledge.main.controller.tree;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import knowledge.core.controller.Controller;
import knowledge.core.controller.ControllerInfo;
import knowledge.event.persist.ChildAdded;
import knowledge.event.persist.NodeDeleted;
import knowledge.event.persist.NodeUpdated;
import knowledge.main.MainConst;
import knowledge.main.ui.MainWindow;
import knowledge.main.ui.tree.HasCellConst;
import knowledge.main.ui.tree.MainCellRender;

import model.knowledge.Dir;
import model.knowledge.NodeLink;
import model.knowledge.RootElement;
import ru.chapaj.util.event.annotation.EventListener;
import ru.chapaj.util.swing.tree.ExtendTree;
import ru.chapaj.util.swing.tree.TreeNodeAdapter;
import ru.chapaj.util.swing.tree.ExtendTree.SelectModel;

@ControllerInfo(target=MainWindow.class)
public class TreeController extends Controller<MainWindow> implements HasCellConst{

	ExtendTree tree;
	JTextField path;
	
	DefaultMutableTreeNode treeRoot;
	TreeMenu treeMenu;
//	DefaultMutableTreeNode lastDirNode;
//	DefaultMutableTreeNode buttons = new DefaultMutableTreeNode(Cell.BUTTONS);
	
	
	@Override
	public void init(final MainWindow ui) {
		tree = ui.tree;
		path = ui.path;
		path.setEditable(false);
		//path.setBackground(Color.WHITE);
		treeMenu = new TreeMenu(tree, appContext);
		tree.init(
				ExtendTree.createTreeModel(null), 
				true, 
				new MainCellRender(), 
				SelectModel.SINGLE,
				treeMenu);
		

		
		treeRoot = tree.getRootNode();
		treeRoot.setUserObject("root");
		tree.setRootVisible(false);
		
		tree.setCellEditor(new MainCellEditor(getAppContext()));
		tree.setEditable(true);
		
		tree.addTreeNodeListener(new TreeNodeAdapter(){
			
			@Override
			public void onNodeSelect(DefaultMutableTreeNode node) {
				setPathInfo(node);
			}
			
		});
		
		path.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultMutableTreeNode curNode = tree.getCurrentNode();
				if(curNode != null){
					DefaultMutableTreeNode parent = (DefaultMutableTreeNode)curNode.getParent();
					if(parent != null) tree.scrollPathToVisible(new TreePath(parent.getPath()));
					tree.scrollPathToVisible(new TreePath(curNode.getPath()));
				}
			}
		});
		
		fillTree();
	}
	
	@EventListener(ChildAdded.class)
	void onNodeAdded(ChildAdded added){
		RootElement parent = added.getData().first;
		RootElement child = added.getData().second;
		
		DefaultMutableTreeNode parentNode = getCache().get(parent, MainConst.tree_node.toString());
		DefaultMutableTreeNode childNode = getCache().get(child, MainConst.tree_node.toString());
		if(childNode != null){
			tree.moveNode(childNode, parentNode);
		} else {
			DefaultMutableTreeNode createTreeNode = createTreeNode(child);
			tree.addChild(parentNode, createTreeNode);
			tree.setSelectionPath(createTreeNode);
			tree.requestFocus();
		}
	}
	
	@EventListener(NodeDeleted.class)
	void onDeleted(NodeDeleted deleted){
		DefaultMutableTreeNode treeNode = getCache().get(deleted.getData(), MainConst.tree_node.toString());
		DefaultMutableTreeNode parent = (DefaultMutableTreeNode)treeNode.getParent();
		tree.model().removeNodeFromParent(treeNode);
		if(parent != null && !parent.isRoot()){
			tree.setSelectionPath(parent);
			tree.requestFocus();
		}
	}
	
	@EventListener(NodeUpdated.class)
	void onUpdated(NodeUpdated updated){
		RootElement node = updated.getData();
		DefaultMutableTreeNode treeNode = getCache().get(node, MainConst.tree_node.toString());
		treeNode.setUserObject(node);
		tree.model().reload(treeNode);
	}


	protected void setPathInfo(DefaultMutableTreeNode node) {
		StringBuilder sb = new StringBuilder();
		if(node != null){
			TreeNode[] path = node.getPath();
			if(path.length > 2){
				if(path.length == 3){
					Object ob = ((DefaultMutableTreeNode)path[1]).getUserObject();
					if(ob instanceof Dir){
						sb.append('/').append(path[2].toString());
					}
				}
				else {
					int last = path.length - 1;
					for (int i = 2; i < last ; i++) {
						sb.append('/').append(path[i].toString());
					}
					sb.append('/').append(path[last].toString());
				}
			}
		}
		String pathString = sb.toString();
		path.setText(pathString);
		path.setCaretPosition(0);
		
	}


	static class QS {
		List<RootElement> list;
		DefaultMutableTreeNode node;
		public QS(List<RootElement> list, DefaultMutableTreeNode node) {
			super();
			this.list = list;
			this.node = node;
		}
	}
	
	private void fillTree() {
		treeRoot.removeAllChildren();
		//long time = System.currentTimeMillis();
		
		LinkedList<QS> q = new LinkedList<QS>();
		q.addLast(new QS(getPersist().getRoot().getNodes(),treeRoot));
		while(!q.isEmpty()){
			QS s = q.removeFirst();
			DefaultMutableTreeNode node = s.node;
//			Object ob = node.getUserObject();
//			if(ob instanceof Dir){
//				node.add(new DefaultMutableTreeNode(Cell.BUTTONS));
//			}
			for(RootElement meta : s.list){
				DefaultMutableTreeNode chNode = createTreeNode(meta);
				node.add(chNode);
				q.addLast(new QS(getPersist().getChildren(meta),chNode));
			}
		}
		//System.out.println("tree filled after "+ ((System.currentTimeMillis() - time) / 1000.) + " sec");
		
		tree.expandPath(treeRoot);
		tree.updateUI();
		
	}

	private DefaultMutableTreeNode createTreeNode(RootElement meta) {
		
		Object treeData = null;
		RootElement nodeCache = null;
		if(meta instanceof NodeLink){
			NodeLink nodeLink = (NodeLink) meta;
			RootElement node = getPersist().find(nodeLink.getNodeRootUuid(), nodeLink.getNodeUuid());
			if(node == null){
				treeData = nodeLink;
				nodeCache = nodeLink;
			}
			else {
				treeData = node;//new TreeLink(nodeLink,node);
				nodeCache = nodeLink;
			}
		}
		else {
			treeData = meta;
			nodeCache = meta;
		}
		
		DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode(treeData);
		getCache().put(nodeCache,MainConst.tree_node.toString(), treeNode);
		return treeNode;
	}
	
	
}



//public void deleteCurrentNode() {
//NodeMeta node = getCurMeta();
//if(node == null) return;
//dao.delete(node);
//}
//
//public void updateCurrentNode(Map<String, String> params) {
//NodeMeta node = getCurMeta();
//if(node == null) return;
//dao.update(node,params);
//}

///**
//* пришел к пониманию что {@link Actions} лучше для этого подходят
//* @param node
//*/
//@Deprecated
//public void addNode(NodeMeta node){
//if(node == null) return;
//DefaultMutableTreeNode currentNode = tree.getCurrentNode();
//if(currentNode == null) return;
//
//Object userObject = currentNode.getUserObject();
//if(userObject == null) return;
//
//if(Cell.BUTTONS == userObject){
//	NodeMeta parent = tree.getParentObject(currentNode, NodeMeta.class);
//	if(parent == null) return;
//	dao.addChild(parent, node);
//}
//else if (userObject instanceof NodeMeta) {
//	dao.addChild((NodeMeta)userObject, node);
//}
//}
