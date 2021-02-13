package TL.parsing;

import TL.Token;
import TL.parsing.PasserTokens.CodeBlock;

import java.util.LinkedList;

public class Parser {
	public static CodeBlock Pars(LinkedList<Token> Tokens){
		CodeBlock MainCodeBlock = new CodeBlock();
		
		MainCodeBlock.ParsInnerBlock(Tokens, 0);
		
		return MainCodeBlock;
	}
}
