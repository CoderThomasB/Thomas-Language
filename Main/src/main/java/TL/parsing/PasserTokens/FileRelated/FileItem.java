package TL.parsing.PasserTokens.FileRelated;

import TL.Token;
import TL.parsing.PasserTokens.Exceptions.MutelyParsingException;
import TL.parsing.PasserTokens.Exceptions.ParsingException;
import TL.parsing.PasserTokens.FileRelated.ClassRelated.ClassStatement;
import TL.parsing.PasserTokens.PasserTokenBasic;

import java.util.LinkedList;

abstract public class FileItem {
	public static PasserTokenBasic ParsInnerBlock(LinkedList<Token> Tokens, int StartingPosition) throws MutelyParsingException {
		MutelyParsingException TheMutelyParsingException = new MutelyParsingException();
		
		try {
			return ImportStatement.ParsInnerBlock(Tokens, StartingPosition);
		} catch (ParsingException E) {
			TheMutelyParsingException.add(E);
		}
		
		try {
			return ClassStatement.ParsInnerBlock(Tokens, StartingPosition);
		} catch (ParsingException E) {
			TheMutelyParsingException.add(E);
		}
		
		throw TheMutelyParsingException;
	}
}
