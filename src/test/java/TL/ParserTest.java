package TL;

import TL.parsing.PasserTokens.CodeBlock;
import org.junit.Test;

import java.util.LinkedList;

import static TL.Tokenizer.Tokenizes;

public class ParserTest {
//	@Test
	public void TestParser() throws TokenizerException {
		LinkedList<Token> Tokens = Tokenizes(
				"""
						a = 1 + 2 - 3;
						b = a + 1;
						c = a + b
						// Stuff dose stuff!
						stuff(a, b, c,1 + 2,3)
						foo(1,foo("Text"),)
						#asm\tMOV AH, [a]
						#asm\tADD AH, 1
						#asm\tMOV [a], AH
						"""
		);
		CodeBlock Parsed = new CodeBlock();
		Parsed.ParsInnerBlock(Tokens, 0);
		System.out.println(Tokens);
		System.out.println(Parsed);
	}
	
	@Test
	public void TestParser2() throws TokenizerException {
		LinkedList<Token> Tokens = Tokenizes(
      """
			a = 1 + 2
			"""
		);
		CodeBlock Parsed = new CodeBlock();
		Parsed.ParsInnerBlock(Tokens, 0);
		System.out.println(Tokens);
		System.out.println(Parsed);
	}
}