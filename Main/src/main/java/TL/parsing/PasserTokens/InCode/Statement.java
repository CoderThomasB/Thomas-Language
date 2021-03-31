package TL.parsing.PasserTokens.InCode;

import TL.Token;
import TL.parsing.PasserTokens.Exceptions.MutelyParsingException;
import TL.parsing.PasserTokens.InCode.Values.Value;
import TL.parsing.PasserTokens.Maths.MathsToken;
import TL.parsing.PasserTokens.PasserTokenBasic;

import java.util.LinkedList;

public abstract class Statement {
	
	public static PasserTokenBasic ParsInnerBlock(LinkedList<Token> Tokens, int StartingPosition, int EndingPosition) throws MutelyParsingException {
		MutelyParsingException TheMutelyParsingException = new MutelyParsingException();
		
		try {
			return Value.ParsInnerBlock(Tokens, StartingPosition, EndingPosition);
		} catch (MutelyParsingException E) {
			TheMutelyParsingException.add(E);
		}
		
		try {
			return FunctionCallToken.ParsInnerBlock(Tokens, StartingPosition, EndingPosition);
		} catch (MutelyParsingException E) {
			TheMutelyParsingException.add(E);
		}
		
		try {
			return MathsToken.ParsInnerBlock(Tokens, StartingPosition, EndingPosition);
		} catch (MutelyParsingException E) {
			TheMutelyParsingException.add(E);
		}
		
		throw TheMutelyParsingException;
	}
}
