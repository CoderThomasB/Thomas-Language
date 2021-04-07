package TL.parsing.PasserTokens.FileRelated.ClassRelated;

import TL.Token;
import TL.parsing.PasserTokens.Exceptions.MutelyParsingException;
import TL.parsing.PasserTokens.Exceptions.ParsingException;
import TL.parsing.PasserTokens.PasserTokenBasic;

import java.util.LinkedList;

public abstract class InnerClassPasser {
	public static PasserTokenBasic ParsInnerBlock(LinkedList<Token> Tokens, int StartingPosition) throws MutelyParsingException {
		MutelyParsingException TheMutelyParsingException = new MutelyParsingException();
		
		try {
			return PropertyDecoration.ParsInnerBlock(Tokens, StartingPosition);
		} catch (ParsingException E) {
			TheMutelyParsingException.add(E);
		}
		
		throw TheMutelyParsingException;
	}
}
