package TL.parsing.PasserTokens;

import TL.ErrorHandling;
import TL.Token;
import TL.TokenType;
import TL.parsing.PasserTokens.Exceptions.MutelyParsingException;
import TL.parsing.PasserTokens.Exceptions.ParsingException;

import java.util.ArrayList;
import java.util.LinkedList;

public class FunctionCallToken extends Statement {
	public String FunctionName;
	public LinkedList<Statement> Inputs;
	
	public FunctionCallToken(String FunctionName, LinkedList<Statement> Inputs) {
		this.FunctionName = FunctionName;
		this.Inputs = Inputs;
	}
	
	public static FunctionCallToken ParsInnerBlock(LinkedList<Token> Tokens, int StartingPosition, int EndingPosition) throws MutelyParsingException {
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
		FunctionCallToken token = new FunctionCallToken(Tokens.get(StartingPosition).Body, new LinkedList<>());
		
		token.Inputs = NoBracketsList.ParsInnerList(Tokens, StartingPosition + 2, EndingPosition - 1);
		
		return token;
	}
	
	@Override
	public String toString() {
		String StringInputs = "";
		
		for(Statement Input : Inputs){
			StringInputs += Input.toString() + ", ";
		}
		
		return "%s(%s)".formatted(FunctionName, StringInputs);
	}
}
