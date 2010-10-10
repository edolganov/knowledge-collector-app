package ru.kc.model.knowledge;

import java.util.ArrayList;
import java.util.List;

import ru.kc.model.HavingUuid;
import ru.kc.model.knowledge.role.Parent;
import ru.kc.model.tree.TreeSnapshotRoot;


import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;


@XStreamAlias("container")
public class Container implements Parent, HavingUuid {
	
	// [15.08.2009] jenua.dolganov: все дети лежат в перемешку
	private List<Element> nodes;
	private String uuid;
	
	@XStreamOmitField
	private String dirPath;
	
	private TreeSnapshotRoot treeSnapshots;
	

	public List<Element> getNodes() {
		if(nodes == null) nodes = new ArrayList<Element>();
		return nodes;
	}

	public void setNodes(ArrayList<Element> nodes) {
		this.nodes = nodes;
	}
	
	


	public TreeSnapshotRoot getTreeSnapshots() {
		if(treeSnapshots == null) treeSnapshots = new TreeSnapshotRoot();
		return treeSnapshots;
	}

	public void setTreeSnapshots(TreeSnapshotRoot treeSnapshots) {
		this.treeSnapshots = treeSnapshots;
	}

	public String getDirPath() {
		return dirPath;
	}

	public void setDirPath(String dirPath) {
		this.dirPath = dirPath;
	}

	@Override
	public String getUuid() {
		return uuid;
	}
	
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Override
	public String toString() {
		return "Container [uuid=" + uuid + ", dirPath=" + dirPath
				+ ", treeSnapshots=" + treeSnapshots + ", nodes=" + nodes + "]";
	}



	
	
}
