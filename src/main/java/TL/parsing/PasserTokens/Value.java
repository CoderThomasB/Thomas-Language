package TL.parsing.PasserTokens;

import TL.ErrorHandling;
import TL.Token;
import TL.TokenType;
import TL.parsing.PasserTokens.Exceptions.MutelyParsingException;
import TL.parsing.PasserTokens.Exceptions.ParsingException;

import java.util.LinkedList;

public abstract class Value extends Statement {
	
	private String Body;
	
	public Value(String Body) throws ParsingException {
		ValidateValue(Body);
		this.Body = Body;
	}
	
	public static Statement ParsInnerBlock(LinkedList<Token> Tokens, int StartingPosition, int EndingPosition) throws MutelyParsingException {
		MutelyParsingException TheMutelyParsingException = new MutelyParsingException();
		
		if (EndingPosition - StartingPosition != 1) {
			TheMutelyParsingException.add(new ParsingException("Value is not one Token in length.", ErrorHandling.CombineTokenBodies(Tokens, StartingPosition, EndingPosition), Tokens.get(StartingPosition).LineNumber));
			throw TheMutelyParsingException;
		}
		if (Tokens.get(StartingPosition).Type == TokenType.String) {
			try {
				return new StringToken(Tokens.get(StartingPosition).Body);
			} catch (ParsingException E) {
				E.ErrorTokenInText = ErrorHandling.CombineTokenBodies(Tokens, StartingPosition, EndingPosition);
				E.LineNo = Tokens.get(StartingPosition).LineNumber;
				TheMutelyParsingException.add(E);
			}
		}
		if (Tokens.get(StartingPosition).Type == TokenType.Number) {
			try {
				return new NumberToken(Tokens.get(StartingPosition).Body);
			} catch (ParsingException E) {
				E.ErrorTokenInText = ErrorHandling.CombineTokenBodies(Tokens, StartingPosition, EndingPosition);
				E.LineNo = Tokens.get(StartingPosition).LineNumber;
				TheMutelyParsingException.add(E);
			}
		}
		
		if (Tokens.get(StartingPosition).Type == TokenType.Text) {
			try {
				return new VariableToken(Tokens.get(StartingPosition).Body);
			} catch (ParsingException E) {
				E.ErrorTokenInText = ErrorHandling.CombineTokenBodies(Tokens, StartingPosition, EndingPosition);
				E.LineNo = Tokens.get(StartingPosition).LineNumber;
				TheMutelyParsingException.add(E);
			}
		}
		
		if(TheMutelyParsingException.ParsingExceptions.size() <= 0){
			TheMutelyParsingException.add(new ParsingException("Not a value", ErrorHandling.CombineTokenBodies(Tokens, StartingPosition, EndingPosition), Tokens.get(StartingPosition).LineNumber));
		}
		
		throw TheMutelyParsingException;
	}
	
	public abstract void ValidateValue(String Body) throws ParsingException;
	
	@Override
	public String toString() {
		return Body;
	}
}
