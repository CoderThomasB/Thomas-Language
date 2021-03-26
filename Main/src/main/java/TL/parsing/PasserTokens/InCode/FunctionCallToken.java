package TL.parsing.PasserTokens.InCode;

import TL.ErrorHandling;
import TL.Token;
import TL.TokenType;
import TL.parsing.PasserTokens.Exceptions.MutelyParsingException;
import TL.parsing.PasserTokens.Exceptions.ParsingException;
import TL.parsing.PasserTokens.PasserTokenBasic;

import java.util.LinkedList;

public class FunctionCallToken extends PasserTokenBasic {
	public String FunctionName;
	public LinkedList<PasserTokenBasic> Inputs;
	
	public FunctionCallToken(LinkedList<Token> Tokens, int TokenStartingPosition, int TokenEndingPosition, String FunctionName, LinkedList<PasserTokenBasic> Inputs) {
		super(Tokens,TokenStartingPosition, TokenEndingPosition);
		this.FunctionName = FunctionName;
		this.Inputs = Inputs;
	}
	
	public static PasserTokenBasic ParsInnerBlock(LinkedList<Token> Tokens, int StartingPosition, int EndingPosition) throws MutelyParsingException {
		if (EndingPosition - StartingPosition < 3) {
			throw new MutelyParsingException(new ParsingException("function is smaller than three tokens", ErrorHandling.CombineTokenBodies(Tokens, StartingPosition, EndingPosition), Tokens.get(StartingPosition).LineNumber));
		}
		if (Tokens.get(StartingPosition).Type != TokenType.Text) {
			throw new MutelyParsingException(new ParsingException("function dose not have a valid name", ErrorHandling.CombineTokenBodies(Tokens, StartingPosition, EndingPosition), Tokens.get(StartingPosition).LineNumber));
		}
		if (Tokens.get(StartingPosition + 1).Type != TokenType.OpeningBracket) {
			throw new MutelyParsingException(new ParsingException("function dose not have a valid opening bracket", ErrorHandling.CombineTokenBodies(Tokens, StartingPosition, EndingPosition), Tokens.get(StartingPosition).LineNumber));
		}
		if (Tokens.get(EndingPosition - 1).Type != TokenType.ClosingBracket) {
			throw new MutelyParsingException(new ParsingException("function dose not have a valid closing bracket", ErrorHandling.CombineTokenBodies(Tokens, StartingPosition, EndingPosition), Tokens.get(StartingPosition).LineNumber));
		}
		FunctionCallToken Token = new FunctionCallToken(
				Tokens,
				StartingPosition,
				EndingPosition,
				
				Tokens.get(StartingPosition).Body,
				NoBracketsList.ParsInnerList(Tokens, StartingPosition + 2, EndingPosition - 1)
		);
		return Token;
	}
	
	@Override
	public String toString() {
		String StringInputs = "";
		
		for(Object Input : Inputs){
			StringInputs += Input.toString() + ", ";
		}
		
		return "%s(%s)".formatted(FunctionName, StringInputs);
	}
}
