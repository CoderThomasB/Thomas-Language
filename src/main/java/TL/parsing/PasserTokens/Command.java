package TL.parsing.PasserTokens;

import TL.Token;
import TL.parsing.PasserTokens.Exceptions.MutelyParsingException;
import TL.parsing.PasserTokens.Exceptions.ParsingException;
import TL.parsing.PasserTokens.Maths.MathsToken;

import java.util.LinkedList;

public abstract class Command extends Statement{
	public static Statement ParsInnerBlock(LinkedList<Token> Tokens, int StartingPosition, int EndingPosition) throws MutelyParsingException{
		MutelyParsingException TheMutelyParsingException = new MutelyParsingException();
		
		try {
			return Statement.ParsInnerBlock(Tokens, StartingPosition, EndingPosition);
		} catch (MutelyParsingException E) {
			TheMutelyParsingException.add(E);
		}

		try {
			return VariableAssignment.ParsInnerBlock(Tokens, StartingPosition, EndingPosition);
		} catch (MutelyParsingException E) {
			TheMutelyParsingException.add(E);
		}
		
		throw TheMutelyParsingException;
	}
}
