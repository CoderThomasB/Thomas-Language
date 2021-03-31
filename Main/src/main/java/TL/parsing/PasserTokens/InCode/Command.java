package TL.parsing.PasserTokens.InCode;

import TL.Token;
import TL.parsing.PasserTokens.Exceptions.MutelyParsingException;
import TL.parsing.PasserTokens.InCode.VariableRelated.VariableAssignment;
import TL.parsing.PasserTokens.PasserTokenBasic;

import java.util.LinkedList;

public abstract class Command {
	public static PasserTokenBasic ParsInnerBlock(LinkedList<Token> Tokens, int StartingPosition, int EndingPosition) throws MutelyParsingException {
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
