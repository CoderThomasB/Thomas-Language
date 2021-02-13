package TL.parsing.PasserTokens;

import TL.ErrorHandling;
import TL.Token;
import TL.TokenType;
import TL.parsing.PasserTokens.Exceptions.MutelyParsingException;
import TL.parsing.PasserTokens.Exceptions.ParsingException;

import java.util.LinkedList;

public class VariableAssignment extends Statement {
	public String VariableName;
	public Statement Input;
	
	public VariableAssignment(String VariableName, Statement Input){
		this.VariableName = VariableName;
		this.Input = Input;
	}
	
	public static VariableAssignment ParsInnerBlock(LinkedList<Token> Tokens, int StartingPosition, int EndingPosition) throws MutelyParsingException {
		if(EndingPosition - StartingPosition < 3){
			throw new MutelyParsingException(new ParsingException("variable assignment is smaller than three tokens", ErrorHandling.CombineTokenBodies(Tokens, StartingPosition, EndingPosition), Tokens.get(StartingPosition).LineNumber));
		}
		if(Tokens.get(StartingPosition).Type != TokenType.Text){
			throw new MutelyParsingException(new ParsingException("variable token is not text", ErrorHandling.CombineTokenBodies(Tokens, StartingPosition, EndingPosition), Tokens.get(StartingPosition).LineNumber));
		}
		if(Tokens.get(StartingPosition + 1).Type != TokenType.Equals){
			throw new MutelyParsingException(new ParsingException("variable assignment dose contain a Equals simble", ErrorHandling.CombineTokenBodies(Tokens, StartingPosition, EndingPosition), Tokens.get(StartingPosition).LineNumber));
		}
		return new VariableAssignment(Tokens.get(StartingPosition).Body, Statement.ParsInnerBlock(Tokens, StartingPosition + 2, EndingPosition));
	}
	
	@Override
	public String toString() {
		return "%s = %s".formatted(VariableName, Input.toString());
	}
}
