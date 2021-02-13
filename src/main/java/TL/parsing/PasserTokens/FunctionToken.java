package TL.parsing.PasserTokens;

import TL.ErrorHandling;
import TL.Token;
import TL.TokenType;
import TL.parsing.PasserTokens.Exceptions.MutelyParsingException;
import TL.parsing.PasserTokens.Exceptions.ParsingException;

import java.util.ArrayList;
import java.util.LinkedList;

public class FunctionToken extends Statement{
	public String FunctionName;
	public ArrayList<Statement> Inputs;
	
	public FunctionToken(String FunctionName, ArrayList<Statement> Inputs){
		this.FunctionName = FunctionName;
		this.Inputs = Inputs;
	}
	
	public static FunctionToken ParsInnerBlock(LinkedList<Token> Tokens, int StartingPosition, int EndingPosition) throws MutelyParsingException {
		if(EndingPosition - StartingPosition < 3){
			throw new MutelyParsingException(new ParsingException("function is smaller than three tokens", ErrorHandling.CombineTokenBodies(Tokens, StartingPosition, EndingPosition), Tokens.get(StartingPosition).LineNumber));
		}
		if(Tokens.get(StartingPosition).Type != TokenType.Text){
			throw new MutelyParsingException(new ParsingException("function dose not have a valid name", ErrorHandling.CombineTokenBodies(Tokens, StartingPosition, EndingPosition), Tokens.get(StartingPosition).LineNumber));
		}
		if(Tokens.get(StartingPosition + 1).Type != TokenType.OpeningBracket){
			throw new MutelyParsingException(new ParsingException("function dose not have a valid opening bracket", ErrorHandling.CombineTokenBodies(Tokens, StartingPosition, EndingPosition), Tokens.get(StartingPosition).LineNumber));
		}
		if(Tokens.get(EndingPosition - 1).Type != TokenType.ClosingBracket){
			throw new MutelyParsingException(new ParsingException("function dose not have a valid closing bracket", ErrorHandling.CombineTokenBodies(Tokens, StartingPosition, EndingPosition), Tokens.get(StartingPosition).LineNumber));
		}
		return new FunctionToken(Tokens.get(StartingPosition).Body, new ArrayList<>());
	}
	
	@Override
	public String toString() {
		return FunctionName + "( Not Empty! )";
	}
}
