package ru.edolganov.knowledge.main.controller.tree;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.tree.DefaultMutableTreeNode;

import model.knowledge.Link;
import model.knowledge.Node;
import model.knowledge.RootElement;

import ru.chapaj.util.os.BareBonesBrowserLaunch;
import ru.chapaj.util.os.win.WinUtil;
import ru.chapaj.util.swing.tree.TreeUtil;
import ru.edolganov.knowledge.command.tree.MoveNode;
import ru.edolganov.knowledge.core.controller.Controller;
import ru.edolganov.knowledge.core.controller.ControllerInfo;
import ru.edolganov.knowledge.event.ui.ShowOnTop;
import ru.edolganov.knowledge.main.ui.MainWindow;

@ControllerInfo(target=MainWindow.class,dependence=TreeController.class)
public class ClickController extends Controller<MainWindow> implements ClipboardOwner{
	
	DefaultMutableTreeNode movedNode;

	@Override
	public void init(final MainWindow ui) {
		ui.tree.addMouseListener(new MouseAdapter(){
			
			//long firstClick = -1;
			//long secondClick = -1;
			
			@Override
			public void mouseClicked(MouseEvent e) {
				//System.out.println(e.getClickCount());
				if (e.getClickCount() == 2 && TreeUtil.isOnSelectedElement(ui.tree, e.getX(), e.getY())) {
					//handle double click. 
					doClickAction(ui);
				}
			}
			
			
		});
		
		ui.tree.addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_C && e.getModifiersEx() == KeyEvent.CTRL_DOWN_MASK){
					//System.out.println("ctrl+c");
					movedNode = null;
					Node meta = ui.tree.getCurrentObject(Node.class);
					if(meta != null && meta.getName() != null){
						Clipboard clipboard = ClickController.this.getAppContext().getToolkit().getSystemClipboard();
					    clipboard.setContents( new StringSelection(meta.getName()), ClickController.this );
					}
				}
				else if (e.getKeyCode() == KeyEvent.VK_X && e.getModifiersEx() == KeyEvent.CTRL_DOWN_MASK){
					//System.out.println("ctrl+x");
					DefaultMutableTreeNode currentNode = ui.tree.getCurrentNode();
					if(currentNode != null) movedNode = currentNode;
					
					Node meta = ui.tree.getCurrentObject(Node.class);
					if(meta != null && meta.getName() != null){
						Clipboard clipboard = ClickController.this.getAppContext().getToolkit().getSystemClipboard();
					    clipboard.setContents( new StringSelection(meta.getName()), ClickController.this );
					}
					
				}
				else if (e.getKeyCode() == KeyEvent.VK_V && e.getModifiersEx() == KeyEvent.CTRL_DOWN_MASK){
					//System.out.println("ctrl+v");
					DefaultMutableTreeNode currentNode = ui.tree.getCurrentNode();
					if(currentNode != null && movedNode != null){
						invoke(new MoveNode(currentNode, movedNode));
					}
					
				}
				else if(e.getKeyChar() == KeyEvent.VK_ENTER){
					
					if(e.getModifiersEx() == KeyEvent.SHIFT_DOWN_MASK){
						//on top - on
						fireEvent(new ShowOnTop(true));

						//after time sec - off
						final Timer timer = new Timer(true);
						timer.schedule(new TimerTask() {
							
							@Override
							public void run() {
								fireEvent(new ShowOnTop(true));
								timer.cancel();
							}
						}, 2000);
					}
					
					doClickAction(ui);
				}
			}
		});
		
	}
	
	private void doClickAction(final MainWindow ui) {
		RootElement node = ui.tree.getCurrentObject(Node.class);
		if(node instanceof Link){
			try{
				String query = ((Link)node).getUrl();
				//it's url
				if(query.startsWith("http") || query.startsWith("www")){
					BareBonesBrowserLaunch.openURL(query);
				}
				//it's local path
				else WinUtil.openFile(query);
			}catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public void lostOwnership(Clipboard clipboard, Transferable contents) {
		
	}

}
