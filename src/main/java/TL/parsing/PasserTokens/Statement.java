package TL.parsing.PasserTokens;

import TL.parsing.PasserTokens.Exceptions.MutelyParsingException;
import TL.parsing.PasserTokens.Exceptions.ParsingException;
import TL.parsing.PasserTokens.Maths.MathsToken;
import TL.Token;

import java.util.LinkedList;

public abstract class Statement {
	public static Statement ParsInnerBlock(LinkedList<Token> Tokens, int StartingPosition, int EndingPosition) throws MutelyParsingException{
		MutelyParsingException TheMutelyParsingException = new MutelyParsingException();
		
		try {
			return Value.ParsInnerBlock(Tokens, StartingPosition, EndingPosition);
		} catch (MutelyParsingException E) {
			TheMutelyParsingException.add(E);
		}
		
		try {
			return FunctionToken.ParsInnerBlock(Tokens, StartingPosition, EndingPosition);
		} catch (MutelyParsingException E) {
			TheMutelyParsingException.add(E);
		}
		
		try {
			return MathsToken.ParsInnerBlock(Tokens, StartingPosition, EndingPosition);
		} catch (MutelyParsingException E) {
			TheMutelyParsingException.add(E);
		}
		
//		try {
//			return VariableAssignment.ParsInnerBlock(Tokens, StartingPosition, EndingPosition);
//		} catch (MutelyParsingException E) {
//			TheMutelyParsingException.add(E);
//		} catch (ParsingException e) {
//			TheMutelyParsingException.add(e);
//		}
		
		throw TheMutelyParsingException;
	}
}
