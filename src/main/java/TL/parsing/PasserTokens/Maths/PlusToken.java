package TL.parsing.PasserTokens.Maths;

import TL.parsing.PasserTokens.Exceptions.MutelyParsingException;
import TL.parsing.PasserTokens.Exceptions.ParsingException;
import TL.parsing.PasserTokens.Statement;
import TL.Token;
import TL.TokenType;

import java.text.MessageFormat;
import java.util.LinkedList;

public class PlusToken extends MathsToken{

	public static TokenType getSymbol() {
		return TokenType.Plus;
	}

	public static MathsToken ParsInnerBlock(LinkedList<Token> Tokens, int StartingPosition, int EndingPosition) throws MutelyParsingException {
		try {
			int PlusPosition = 0;
			PlusPosition = FindPositionOfASymbol(Tokens, StartingPosition, EndingPosition, getSymbol());
			PlusToken TheToken = new PlusToken();
			
			TheToken.Input1 = Statement.ParsInnerBlock(Tokens, StartingPosition, PlusPosition);
			TheToken.Input2 = Statement.ParsInnerBlock(Tokens, PlusPosition + 1, EndingPosition);
			
			return TheToken;
		} catch (ParsingException e) {
			throw new MutelyParsingException(e);
		}
	}
	
	@Override
	public String toString() {
		return super.toString('+');
	}
}
