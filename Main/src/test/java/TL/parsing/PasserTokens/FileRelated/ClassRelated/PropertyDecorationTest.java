package TL.parsing.PasserTokens.FileRelated.ClassRelated;

import TL.Token;
import TL.TokenizerException;
import TL.parsing.PasserTokens.Exceptions.MutelyParsingException;
import TL.parsing.PasserTokens.Exceptions.ParsingException;
import TL.parsing.PasserTokens.FileRelated.FileStatement;
import TL.parsing.StatementVisibility;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;

import static TL.Tokenizer.Tokenizes;

public class PropertyDecorationTest {
	@Test
	public void TestPropertyDecoration1() throws TokenizerException, ParsingException {
		LinkedList<Token> Tokens = Tokenizes(
				"""
						public SomeRandomName = 123;
						"""
		);
//		PropertyDecoration Parsed = new PropertyDecoration(Tokens, 0, Tokens.size());
//		Parsed.ParsInnerBlock(Tokens, 0);
		PropertyDecoration Parsed = PropertyDecoration.ParsInnerBlock(Tokens, 0);
		
		// ToDO Make the PropertyDecorationTest better
		Assert.assertEquals(Tokens,         Parsed.Tokens);
		Assert.assertEquals(0,     Parsed.TokenStartingPosition);
		Assert.assertEquals(4,  Parsed.TokenEndingPosition);
		
		Assert.assertEquals("SomeRandomName", Parsed.Name);
		Assert.assertEquals(StatementVisibility.Public, Parsed.Visibility);
	}
	
	@Test
	public void TestPropertyDecoration2() throws TokenizerException, ParsingException {
		LinkedList<Token> Tokens = Tokenizes(
				"""
						PriVaTe AbcXyz = 123;
						"""
		);
//		PropertyDecoration Parsed = new PropertyDecoration(Tokens, 0, Tokens.size());
//		Parsed.ParsInnerBlock(Tokens, 0);
		PropertyDecoration Parsed = PropertyDecoration.ParsInnerBlock(Tokens, 0);
		
		// ToDO Make the PropertyDecorationTest better
		Assert.assertEquals(Tokens,         Parsed.Tokens);
		Assert.assertEquals(0,     Parsed.TokenStartingPosition);
		Assert.assertEquals(4,  Parsed.TokenEndingPosition);
		
		Assert.assertEquals("AbcXyz", Parsed.Name);
		Assert.assertEquals(StatementVisibility.Private, Parsed.Visibility);
	}
}