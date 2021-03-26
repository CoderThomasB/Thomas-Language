package TL.parsing.PasserTokens.InCode.Values;

//import x86.x86Generatable;
//import x86.x86RegMemOrConst;

import TL.Token;
import TL.parsing.PasserTokens.Exceptions.MutelyParsingException;
import TL.parsing.PasserTokens.Exceptions.ParsingException;
import TL.parsing.PasserTokens.InCode.VariableRelated.VariableToken;
import TL.parsing.PasserTokens.PasserTokenBasic;

import java.util.LinkedList;

public abstract class Value {
	
	public static PasserTokenBasic ParsInnerBlock(LinkedList<Token> Tokens, int StartingPosition, int EndingPosition) throws MutelyParsingException {
		MutelyParsingException TheMutelyParsingException = new MutelyParsingException();
		
		try {
			return NumberToken.ParsInnerBlock(Tokens, StartingPosition, EndingPosition);
		} catch (ParsingException E) {
			TheMutelyParsingException.add(E);
		}
		
		try {
			return StringToken.ParsInnerBlock(Tokens, StartingPosition, EndingPosition);
		} catch (ParsingException E) {
			TheMutelyParsingException.add(E);
		}
		
		try {
			return VariableToken.ParsInnerBlock(Tokens, StartingPosition, EndingPosition);
		} catch (ParsingException E) {
			TheMutelyParsingException.add(E);
		}
		
		throw TheMutelyParsingException;
	}
}
