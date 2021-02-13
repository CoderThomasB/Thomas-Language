package TL.parsing.PasserTokens.Exceptions;

public class ParsingException extends Exception {
	public String ErrorTokenInText;
	public int LineNo;
	
	public ParsingException(String message, String ErrorTokenInText, int Line) {
		super(message);
		this.ErrorTokenInText = ErrorTokenInText;
		this.LineNo = Line;
	}
	
	public ParsingException() {
		super();
	}
}
