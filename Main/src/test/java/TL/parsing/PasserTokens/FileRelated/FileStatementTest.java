package TL.parsing.PasserTokens.FileRelated;

import TL.Token;
import TL.TokenizerException;
import TL.parsing.PasserTokens.Exceptions.MutelyParsingException;
import TL.parsing.PasserTokens.Exceptions.ParsingException;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;

import static TL.Tokenizer.Tokenizes;

public class FileStatementTest {
	@Test
	public void TestFileStatementImport() throws TokenizerException, MutelyParsingException {
		LinkedList<Token> Tokens = Tokenizes(
				"""
						
						
						import TL.Types.Numbers.Int32
						
						
						"""
		);
		FileStatement Parsed = new FileStatement(Tokens, 0, Tokens.size());
		Parsed.ParsInnerBlock(Tokens, 0);
		
		Assert.assertEquals(1, Parsed.InnerFileItems.size());
		Assert.assertEquals(ImportStatement.class, Parsed.InnerFileItems.get(0).getClass());
		Assert.assertEquals("TL.Types.Numbers.Int32", ((ImportStatement) Parsed.InnerFileItems.get(0)).Body);
	}
	
	@Test
	public void TestFileStatementImportAndClass() throws TokenizerException, MutelyParsingException {
		LinkedList<Token> Tokens = Tokenizes(
				"""
						import TL.Numbers.Int32
						import TL.DataStructures.LikedList
						import TL.App.Application
						import TL.App.GUI.Window
						
						class MyApp extends Application{
							public LikedList<Window> Windows;
						}
						"""
		);
		System.out.println(Tokens);
		FileStatement Parsed = new FileStatement(Tokens, 0, Tokens.size());
		Parsed.ParsInnerBlock(Tokens, 0);
		System.out.println(Parsed);
	}
}