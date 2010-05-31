package knowledge.persist.fs.exception;

public class RenameException extends RuntimeException {

	public RenameException() {
		super();
	}

	public RenameException(String message, Throwable cause) {
		super(message, cause);
	}

	public RenameException(String message) {
		super(message);
	}

	public RenameException(Throwable cause) {
		super(cause);
	}

}
