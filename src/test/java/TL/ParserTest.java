package TL;

import TL.parsing.Parser;
import TL.parsing.PasserTokens.CodeBlock;
import org.junit.Test;

import java.util.LinkedList;

import static TL.Tokenizer.Tokenizes;

public class ParserTest {
	@Test
	public void TestParser() throws TokenizerException {
		LinkedList<Token> Tokens = Tokenizes(
				"""
						a = 1 + 2 - 3
						b = a + 1
						c = a + b
						stuff(a, b, c)
						"""
		);
		CodeBlock Parsed = Parser.Pars(Tokens);
		System.out.println(Tokens);
		System.out.println(Parsed);
	}
}