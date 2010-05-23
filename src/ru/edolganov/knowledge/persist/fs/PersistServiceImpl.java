package ru.edolganov.knowledge.persist.fs;

import java.util.List;
import java.util.Map;

import model.knowledge.Root;
import model.knowledge.RootElement;
import model.knowledge.role.Parent;
import model.tree.TreeSnapshot;
import model.tree.TreeSnapshotDir;
import ru.edolganov.knowledge.persist.PersistService;

public class PersistServiceImpl implements PersistService {

	@Override
	public void add(TreeSnapshot object, Map<String, Object> params) {
		// TODO Auto-generated method stub

	}

	@Override
	public void add(TreeSnapshotDir object) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean addChild(Parent parent, RootElement child) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addChild(Parent parent, RootElement child,
			Map<String, String> params) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void delete(RootElement node) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(TreeSnapshotDir parent, TreeSnapshot node) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(TreeSnapshotDir ob) {
		// TODO Auto-generated method stub

	}

	@Override
	public RootElement find(String rootUuid, String nodeUuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RootElement> getChildren(Parent parent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getExternalData(RootElement ob) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Root getRoot() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void merge(Root object) {
		// TODO Auto-generated method stub

	}

	@Override
	public void merge(Root root, boolean immediately) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(RootElement node, Map<String, String> params) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(TreeSnapshot snap) {
		// TODO Auto-generated method stub

	}

}
