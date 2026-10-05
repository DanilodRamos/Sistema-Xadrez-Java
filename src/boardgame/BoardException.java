package boardgame;

public class BoardException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	 //construtor q recebe mensagem

	public BoardException(String msg) {
		super(msg);
	}
	
}
