package TL.parsing.PasserTokens.InCode;

import TL.Token;
import TL.TokenizerException;
import TL.parsing.PasserTokens.InCode.Values.NumberToken;
import TL.parsing.PasserTokens.InCode.Values.StringToken;
import org.junit.Test;

import java.util.LinkedList;

import static TL.Tokenizer.Tokenizes;
import static org.junit.Assert.assertEquals;

public class FunctionCallTokenTest {
	@Test
	public void TestFunctionCallToken1() throws TokenizerException {
		LinkedList<Token> Tokens = Tokenizes(
				"""
						System.Terminal.print("Test %d",  21)
						"""
		);
		Scope Parsed = new Scope();
		Parsed.ParsInnerBlockWithoutBracket(Tokens, 0, Tokens.size());
		assertEquals(1, Parsed.Commands.size());
		
		assertEquals(FunctionCallToken.class, Parsed.Commands.get(0).getClass());
		assertEquals(2, ((FunctionCallToken) Parsed.Commands.get(0)).Inputs.size());
		assertEquals("System.Terminal.print", ((FunctionCallToken) Parsed.Commands.get(0)).FunctionName);
		assertEquals(StringToken.class, ((FunctionCallToken) Parsed.Commands.get(0)).Inputs.get(0).getClass());
		assertEquals(NumberToken.class, ((FunctionCallToken) Parsed.Commands.get(0)).Inputs.get(1).getClass());
	}
}