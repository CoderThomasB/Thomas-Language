package TL.parsing.PasserTokens;

import TL.Token;
import TL.TokenType;

public class AssemblyLine extends Command {
	public String AssemblyCode;
	
	public AssemblyLine(Token AssemblyToken) {
		setAssemblyCodeFormToken(AssemblyToken);
	}
	
	public AssemblyLine() {
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
		return String.format("Assembly:'%s'", AssemblyCode);
	}
}
