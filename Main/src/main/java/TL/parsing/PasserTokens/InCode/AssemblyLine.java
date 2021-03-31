package TL.parsing.PasserTokens.InCode;

import TL.Token;
import TL.TokenType;
import TL.parsing.PasserTokens.PasserTokenBasic;

import java.util.LinkedList;

public class AssemblyLine extends PasserTokenBasic {
	public String AssemblyCode;
	
	public AssemblyLine(LinkedList<Token> Tokens, int TokenStartingPosition, int TokenEndingPosition, Token AssemblyToken) {
		super(Tokens, TokenStartingPosition, TokenEndingPosition);
		setAssemblyCodeFormToken(AssemblyToken);
	}
	
	public void setAssemblyCodeFormToken(Token AssemblyToken) {
		if (AssemblyToken.Type != TokenType.Assembly) {
			throw new RuntimeException();
		}
		// removes the #asm
		this.AssemblyCode = AssemblyToken.Body.substring(4);
	}
	
	@Override
	public String toString() {
		return String.format("Assembly:\"%s\"", AssemblyCode);
	}
	
}
