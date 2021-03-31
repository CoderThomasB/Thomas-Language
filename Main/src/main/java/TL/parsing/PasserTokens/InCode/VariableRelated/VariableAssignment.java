package TL.parsing.PasserTokens.InCode.VariableRelated;

import TL.ErrorHandling;
import TL.Token;
import TL.TokenType;
import TL.parsing.PasserTokens.Exceptions.MutelyParsingException;
import TL.parsing.PasserTokens.Exceptions.ParsingException;
import TL.parsing.PasserTokens.InCode.Statement;
import TL.parsing.PasserTokens.PasserTokenBasic;

import java.util.LinkedList;

public class VariableAssignment extends PasserTokenBasic {
	public String VariableName;
	public Object Input;
	
	public VariableAssignment(LinkedList<Token> Tokens, int TokenStartingPosition, int TokenEndingPosition, String VariableName, Object Input){
		super(Tokens, TokenStartingPosition, TokenEndingPosition);
		this.VariableName = VariableName;
		this.Input = Input;
	}
	
	public static VariableAssignment ParsInnerBlock(LinkedList<Token> Tokens, int StartingPosition, int EndingPosition) throws MutelyParsingException {
		if(EndingPosition - StartingPosition < 3){
			throw new MutelyParsingException(new ParsingException("variable assignment is smaller than three tokens", Tokens, StartingPosition, EndingPosition));
		}
		if(Tokens.get(StartingPosition).Type != TokenType.Text){
			throw new MutelyParsingException(new ParsingException("variable token is not text", Tokens, StartingPosition, EndingPosition));
		}
		if(Tokens.get(StartingPosition + 1).Type != TokenType.Equals){
			throw new MutelyParsingException(new ParsingException("variable assignment dose contain a Equals simble", Tokens, StartingPosition, EndingPosition));
		}
		return new VariableAssignment(Tokens, StartingPosition, EndingPosition, Tokens.get(StartingPosition).Body, Statement.ParsInnerBlock(Tokens, StartingPosition + 2, EndingPosition));
	}
	
	@Override
	public String toString() {
		return "%s = %s".formatted(VariableName, Input.toString());
	}
}
