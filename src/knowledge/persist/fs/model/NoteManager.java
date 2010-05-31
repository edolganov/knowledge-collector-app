package knowledge.persist.fs.model;

import model.knowledge.Note;
import model.knowledge.Root;

public class NoteManager extends AbstractNodeManager implements INodeManager<Note> {

	@Override
	public String getDirPath(Note node) {
		return getNodeDirPath(node);
	}

	@Override
	public void move(Root oldRoot, Note node) {
		// TODO Auto-generated method stub
		
	}

}
