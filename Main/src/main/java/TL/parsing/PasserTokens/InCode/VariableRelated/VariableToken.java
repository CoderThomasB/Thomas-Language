package TL.parsing.PasserTokens.InCode.VariableRelated;

//import x86.x86RegManger;
//import x86.x86RegMemOrConst;
import TL.ErrorHandling;
import TL.Token;
import TL.TokenType;
import TL.parsing.PasserTokens.Exceptions.ParsingException;
import TL.parsing.PasserTokens.InCode.FunctionCallToken;
import TL.parsing.PasserTokens.InCode.NoBracketsList;
import TL.parsing.PasserTokens.InCode.Values.StringToken;
import TL.parsing.PasserTokens.InCode.Values.Value;
import TL.parsing.PasserTokens.PasserTokenBasic;

import java.util.LinkedList;

public class VariableToken extends PasserTokenBasic {
	
	public VariableToken(LinkedList<Token> Tokens, int TokenStartingPosition, int TokenEndingPosition, String Name) {
		super(Tokens, TokenStartingPosition, TokenEndingPosition);
	}
	
	public static VariableToken ParsInnerBlock(LinkedList<Token> Tokens, int StartingPosition, int EndingPosition) throws ParsingException {
		if (EndingPosition - StartingPosition != 1) {
			throw new ParsingException("Variable is more than one Token in length.", ErrorHandling.CombineTokenBodies(Tokens, StartingPosition, EndingPosition), Tokens.get(StartingPosition).LineNumber);
		}
		
		if (Tokens.get(StartingPosition).Type != TokenType.Text) {
			throw new ParsingException("Variable is not a valid name", ErrorHandling.CombineTokenBodies(Tokens, StartingPosition, EndingPosition), Tokens.get(StartingPosition).LineNumber);
		}
		VariableToken Token = new VariableToken(
				Tokens,
				StartingPosition,
				EndingPosition,
				Tokens.get(StartingPosition).Body);
		
		return Token;
	}
}
