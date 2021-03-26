package TL.parsing.PasserTokens.InCode;

import TL.Token;
import TL.TokenizerException;
import TL.parsing.PasserTokens.Exceptions.ParsingException;
import TL.parsing.PasserTokens.InCode.Values.NumberToken;
import TL.parsing.PasserTokens.Maths.PlusToken;
import org.junit.Test;

import java.util.LinkedList;

import static TL.Tokenizer.Tokenizes;
import static org.junit.Assert.assertEquals;

public class ScopeTest {
	@Test
	public void TestScopeParsInnerBlockWithoutBracket() throws TokenizerException, ParsingException {
		LinkedList<Token> Tokens = Tokenizes(
				"""
						1 + 1
						stuff(a,b)
						"""
		);
		Scope Parsed = new Scope();
		Parsed.ParsInnerBlockWithoutBracket(Tokens, 0, Tokens.size());
		assertEquals(2, Parsed.Commands.size());
		
		assertEquals(PlusToken.class, Parsed.Commands.get(0).getClass());
		assertEquals(NumberToken.class, ((PlusToken) Parsed.Commands.get(0)).Input1.getClass());
		assertEquals(NumberToken.class, ((PlusToken) Parsed.Commands.get(0)).Input2.getClass());
		
		assertEquals(FunctionCallToken.class, Parsed.Commands.get(1).getClass());
		assertEquals("stuff", ((FunctionCallToken) Parsed.Commands.get(1)).FunctionName);
		assertEquals(2, ((FunctionCallToken) Parsed.Commands.get(1)).Inputs.size());
	}
	
	@Test
	public void TestScopeParsInnerBlockWithBracket1() throws TokenizerException, ParsingException {
		LinkedList<Token> Tokens = Tokenizes(
				"""
							{
							1 + 1
							stuff(a,b)
						}
						
						
						Other stuff that is in in the Scope and should not be parsed
							"""
		);
		Scope Parsed = new Scope();
		Parsed.ParsInnerBlockWithBracket(Tokens, 0);
		assertEquals(2, Parsed.Commands.size());
		
		assertEquals(PlusToken.class, Parsed.Commands.get(0).getClass());
		assertEquals(NumberToken.class, ((PlusToken) Parsed.Commands.get(0)).Input1.getClass());
		assertEquals(NumberToken.class, ((PlusToken) Parsed.Commands.get(0)).Input2.getClass());
		
		assertEquals(FunctionCallToken.class, Parsed.Commands.get(1).getClass());
		assertEquals("stuff", ((FunctionCallToken) Parsed.Commands.get(1)).FunctionName);
		assertEquals(2, ((FunctionCallToken) Parsed.Commands.get(1)).Inputs.size());
	}
	
	@Test
	public void TestScopeParsInnerBlockWithBracket2() throws TokenizerException, ParsingException {
		LinkedList<Token> Tokens = Tokenizes(
				"""
							{
							1 + 1
							stuff(a,b)
						}
						
						Other stuff that is in in the Scope and should not be parsed
							"""
		);
		System.out.println(Tokens.get(13));
		
		Scope Parsed = new Scope();
		Parsed.ParsInnerBlockWithBracket(Tokens, 0, 14);
		assertEquals(2, Parsed.Commands.size());
		
		assertEquals(PlusToken.class, Parsed.Commands.get(0).getClass());
		assertEquals(NumberToken.class, ((PlusToken) Parsed.Commands.get(0)).Input1.getClass());
		assertEquals(NumberToken.class, ((PlusToken) Parsed.Commands.get(0)).Input2.getClass());
		
		assertEquals(FunctionCallToken.class, Parsed.Commands.get(1).getClass());
		assertEquals("stuff", ((FunctionCallToken) Parsed.Commands.get(1)).FunctionName);
		assertEquals(2, ((FunctionCallToken) Parsed.Commands.get(1)).Inputs.size());
	}
	
	
}