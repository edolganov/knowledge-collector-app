package ru.edolganov.knowledge.main.controller.tree;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.event.CellEditorListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellEditor;
import javax.swing.tree.TreePath;

import model.knowledge.Dir;
import model.knowledge.Link;
import model.knowledge.TextData;

import ru.edolganov.knowledge.AppContext;
import ru.edolganov.knowledge.command.dialog.NewDir;
import ru.edolganov.knowledge.command.dialog.NewLink;
import ru.edolganov.knowledge.command.dialog.NewText;
import ru.edolganov.knowledge.command.tree.AddNode;
import ru.edolganov.knowledge.core.command.CommandService;
import ru.edolganov.knowledge.main.ui.tree.HasCellConst;
import ru.edolganov.knowledge.main.ui.tree.NodeButtonsPanelExtend;


public class MainCellEditor implements TreeCellEditor, HasCellConst {
	
	NodeButtonsPanelExtend nodeButtons = new NodeButtonsPanelExtend();
	/**
	 * устрение бага с тем, что при клике на ту же кнопку в другой ноде дерева,
	 * происходит потеря фокуса с нее и потеря клика.
	 * 
	 * С помощью этого флага явно вызываем первый клик на нужной кнопке.
	 */
	boolean isFirstClick;
	
	public MainCellEditor(final AppContext appContext) {
		
		nodeButtons.dirB.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e) {
				mayBeNeedClick(nodeButtons.dirB);
			}
		});
		nodeButtons.dirB.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				CommandService commandService = appContext.getCommandService();
				Dir dir = commandService.invoke(new NewDir());
				commandService.invoke(new AddNode(dir));
			}
			
		});
		
		
		nodeButtons.linkB.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e) {
				mayBeNeedClick(nodeButtons.linkB);
			}
		});
		nodeButtons.linkB.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				CommandService commandService = appContext.getCommandService();
				Link link = commandService.invoke(new NewLink());
				commandService.invoke(new AddNode(link));
			}
			
		});
		
		nodeButtons.noteB.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e) {
				mayBeNeedClick(nodeButtons.noteB);
			}
		});
		nodeButtons.noteB.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				CommandService commandService = appContext.getCommandService();
				TextData link = commandService.invoke(new NewText());
				commandService.invoke(new AddNode(link));
			}
			
		});
	}

	protected void mayBeNeedClick(JButton button) {
		if(isFirstClick) {
			button.requestFocus();
			button.doClick();
			isFirstClick = false;
		}
	}

	@Override
	public Component getTreeCellEditorComponent(JTree tree, Object value,
			boolean isSelected, boolean expanded, boolean leaf, int row) {
		Object obj = ((DefaultMutableTreeNode)value).getUserObject();
		isFirstClick = true;
		if(Cell.BUTTONS == obj){
			return nodeButtons;
		}
		return new JLabel("test");
	}

	@Override
	public void addCellEditorListener(CellEditorListener l) {
		//System.out.println("addCellEditorListener");
		
	}

	@Override
	public void cancelCellEditing() {
		//System.out.println("cancelCellEditing");
		
	}

	@Override
	public Object getCellEditorValue() {
		//System.out.println("getCellEditorValue");
		return null;
	}

	@Override
	public boolean isCellEditable(EventObject anEvent) {
		return needEdit(anEvent);
	}

	@Override
	public void removeCellEditorListener(CellEditorListener l) {
		//System.out.println("removeCellEditorListener");
		
	}

	@Override
	public boolean shouldSelectCell(EventObject anEvent) {
		return true;
	}

	@Override
	public boolean stopCellEditing() {
		//System.out.println("stopCellEditing");
		return false;
	}
	
	private boolean needEdit(EventObject anEvent){
		try {
			JTree tree = (JTree)anEvent.getSource();
			Point me = tree.getMousePosition();
			TreePath path = tree.getPathForLocation(me.x, me.y);
			DefaultMutableTreeNode node = (DefaultMutableTreeNode)path.getLastPathComponent();

			Object obj = node.getUserObject();
			if(obj == null) return false;
			if(obj instanceof Cell) return true;
			
			return false;
		} catch (RuntimeException e) {
			e.printStackTrace();
			return false;
		}
	}

}
