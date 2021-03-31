package TL.parsing.PasserTokens.Exceptions;

import TL.Token;

import java.util.LinkedList;

public class ParsingException extends Exception {
	public LinkedList<Token> Tokens;
	public int StartingPosition;
	public int EndingPosition;
	
	public ParsingException(String message, LinkedList<Token> Tokens, int StartingPosition, int EndingPosition) {
		super(message);
		this.Tokens = Tokens;
		this.StartingPosition = StartingPosition;
		this.EndingPosition = EndingPosition;
	}
	
	public ParsingException() {
		super();
	}
}
