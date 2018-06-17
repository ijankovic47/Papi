package konami.pes.exceptions;

public class NoSuchEntityException extends Exception{

	private static final long serialVersionUID = 7543088702755015544L;

	public NoSuchEntityException(String message) {
		super(message);
	}
}
