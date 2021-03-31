package TL.parsing.PasserTokens.InCode.Values;

import TL.Token;
import TL.TokenType;
import TL.parsing.PasserTokens.Exceptions.ParsingException;
import TL.parsing.PasserTokens.PasserTokenBasic;

import java.util.LinkedList;

public class StringToken extends PasserTokenBasic {
	
	@SuppressWarnings({"FieldCanBeLocal", "FieldMayBeFinal"})
	private String Body;
	
	public StringToken(LinkedList<Token> Tokens, int TokenStartingPosition, int TokenEndingPosition, String Body) {
		super(Tokens, TokenStartingPosition, TokenEndingPosition);
		this.Body = Body;
	}
	
	public static StringToken ParsInnerBlock(LinkedList<Token> Tokens, int StartingPosition, int EndingPosition) throws ParsingException {
		if (EndingPosition - StartingPosition != 1) {
			throw new ParsingException("Value is more than one Token in length.", Tokens, StartingPosition, EndingPosition);
		}
		
		if (Tokens.get(StartingPosition).Type != TokenType.String) {
			throw new ParsingException("Value is Not a String", Tokens, StartingPosition, EndingPosition);
		}
		
		return new StringToken(
				Tokens,
				StartingPosition,
				EndingPosition,
				
				Tokens.get(StartingPosition).Body
		);
	}
	
}
