package knowledge.persist.fs.command.main;

import knowledge.persist.fs.command.Command;

public class GetRootFilePath extends Command<String>{
	
	String dirPath;

	public GetRootFilePath(String dirPath) {
		super();
		this.dirPath = dirPath;
	}

	@Override
	protected String doAction() {
		return dirPath+'/'+context.getConstants().ROOT_FILE_NAME;
	}
	
	

}
