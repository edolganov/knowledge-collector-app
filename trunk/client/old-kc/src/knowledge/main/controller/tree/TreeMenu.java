package knowledge.main.controller.tree;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.tree.DefaultMutableTreeNode;

import knowledge.AppContext;
import knowledge.command.dialog.NewDir;
import knowledge.command.dialog.NewLink;
import knowledge.command.dialog.NewText;
import knowledge.command.link.AddNodeLinkCandidate;
import knowledge.command.link.CreateNodeLink;
import knowledge.command.tree.AddNode;
import knowledge.command.tree.AddTreeNode;
import knowledge.command.tree.DeleteCurrentTreeNode;
import knowledge.core.command.CommandService;
import knowledge.event.ui.HideActionPanel;
import knowledge.event.ui.NeedShowOrHideActionPanel;
import knowledge.event.ui.ShowActionPanel;
import knowledge.main.ui.tree.HasCellConst;

import model.knowledge.Dir;
import model.knowledge.Element;
import model.knowledge.TextData;
import ru.chapaj.util.event.annotation.EventListener;
import ru.chapaj.util.swing.IconHelper;
import ru.chapaj.util.swing.tree.ExtendTree;

public class TreeMenu extends JPopupMenu implements HasCellConst {
	
	ExtendTree tree;
	JMenuItem delete = new JMenuItem("delete",IconHelper.get("/images/kc/tree/menu/delete.png"));
	
	JMenuItem info = new JMenuItem();
	
	JMenu addMenu = new JMenu("add");
	JMenuItem dir = new JMenuItem("dir",IconHelper.get("/images/kc/tree/dir.png"));
	JMenuItem note = new JMenuItem("note",IconHelper.get("/images/kc/tree/note.png"));
	JMenuItem link = new JMenuItem("link",IconHelper.get("/images/kc/tree/netLink.png"));
	
	JMenu parent = new JMenu("parent ops");
	JMenuItem dirToParent = new JMenuItem("dir",IconHelper.get("/images/kc/tree/dir.png"));
	JMenuItem noteToParent = new JMenuItem("note",IconHelper.get("/images/kc/tree/note.png"));
	JMenuItem linkToParent = new JMenuItem("link",IconHelper.get("/images/kc/tree/netLink.png"));
	
	JMenuItem showHideInfo = new JMenuItem("");
	ImageIcon leftIcon = IconHelper.get("/images/kc/app/left.png");
	ImageIcon rightIcon = IconHelper.get("/images/kc/app/right.png");
	
	JMenuItem nodeLinkMenu = new JMenuItem("(temp) copy link");
	JMenuItem createNodeLink = new JMenuItem("(temp) add link");
	
	boolean showInfo = true;
	
	
	public TreeMenu(ExtendTree tree_, final AppContext appContext) {
		super();
		tree = tree_;
		
		appContext.getEventManager().addObjectMethodListeners(this);
		
		delete.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				appContext.getCommandService().invoke(new DeleteCurrentTreeNode());
			}
			
		});
		
		dir.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				CommandService commandService = appContext.getCommandService();
				Element dir = commandService.invoke(new NewDir());
				commandService.invoke(new AddNode(dir));
			}
			
		});
		
		note.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				CommandService commandService = appContext.getCommandService();
				Element dir = commandService.invoke(new NewText());
				commandService.invoke(new AddNode(dir));
			}
			
		});
		
		link.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				CommandService commandService = appContext.getCommandService();
				Element dir = commandService.invoke(new NewLink());
				commandService.invoke(new AddNode(dir));
			}
			
		});
		
		addMenu.add(dir);
		addMenu.add(note);
		addMenu.add(link);
		
		dirToParent.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Element parent = tree.getParentObject(tree.getCurrentNode(), Element.class);
				
				CommandService commandService = appContext.getCommandService();
				Element dir = commandService.invoke(new NewDir());
				commandService.invoke(new AddTreeNode(parent,dir));
			}
			
		});
		
		noteToParent.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {				
				Element parent = tree.getParentObject(tree.getCurrentNode(), Element.class);
				
				CommandService commandService = appContext.getCommandService();
				Element dir = commandService.invoke(new NewText());
				commandService.invoke(new AddTreeNode(parent,dir));
			}
			
		});
		
		linkToParent.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Element parent = tree.getParentObject(tree.getCurrentNode(), Element.class);
				
				CommandService commandService = appContext.getCommandService();
				Element dir = commandService.invoke(new NewLink());
				commandService.invoke(new AddTreeNode(parent,dir));
			}
			
		});
		
		parent.add(dirToParent);
		parent.add(noteToParent);
		parent.add(linkToParent);
		
		showHideInfo.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				appContext.getEventManager().fireEvent(this, new NeedShowOrHideActionPanel());
			}
			
		});
		
		
		nodeLinkMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				appContext.getCommandService().invoke(new AddNodeLinkCandidate());
				
			}
		});
		
		createNodeLink.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				appContext.getCommandService().invoke(new CreateNodeLink());
			}
		});
		
	}
	
	@EventListener(HideActionPanel.class)
	void hideInfo(){
		showInfo = false;
	}
	
	@EventListener(ShowActionPanel.class)
	void showInfo(){
		showInfo = true;
	}


	@Override
	public void show(Component invoker, int x, int y) {
		DefaultMutableTreeNode currentNode = tree.getCurrentNode();
		if(currentNode == null) return;
		
		
		Object ob = tree.getCurrentObject();
		if(Cell.BUTTONS == ob) return;
		
		removeAll();
		//info.setText(ob.toString());
		//add(info);
		
		if(ob instanceof Dir || ob instanceof TextData)addMenu.setEnabled(true);
		else addMenu.setEnabled(false);
		add(addMenu);
		
		if(currentNode.isRoot() 
				|| ((DefaultMutableTreeNode)currentNode.getParent()).isRoot())
			parent.setEnabled(false);
		else parent.setEnabled(true);
		add(parent);
		if(showInfo){
			showHideInfo.setText("hide info panel");
			showHideInfo.setIcon(rightIcon);
		}
		else {
			showHideInfo.setText("show info panel");
			showHideInfo.setIcon(leftIcon);
		}
		add(showHideInfo);
		addSeparator();
		add(nodeLinkMenu);
		add(createNodeLink);
		addSeparator();
		add(delete);
		
		
		
		super.show(invoker, x, y);
	}
	
	

}
