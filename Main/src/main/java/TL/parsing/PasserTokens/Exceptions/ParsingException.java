package TL.parsing.PasserTokens.Exceptions;

import TL.ErrorHandling;
import TL.Token;

import java.text.MessageFormat;
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
	
	@Override
	public String toString() {
		
		String Code = ErrorHandling.CombineTokenBodies(
				this.Tokens,
				this.StartingPosition,
				this.EndingPosition);
		
		
		
		return MessageFormat.format("{0} ''{1}'' on line {2}",
				this.getMessage(),                                  // Message
				Code,                                               // InnerCode
				this.Tokens.get(this.StartingPosition).LineNumber); // LineNumber
		
	}
}
