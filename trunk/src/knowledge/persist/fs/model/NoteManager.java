package knowledge.persist.fs.model;

import model.knowledge.Note;

public class NoteManager extends AbstractNodeManager implements INodeManager<Note> {

	@Override
	public String getDirPath(Note node) {
		return getNodeDirPath(node);
	}

}
