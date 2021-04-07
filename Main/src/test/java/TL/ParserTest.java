package TL;

import TL.parsing.PasserTokens.Exceptions.MutelyParsingException;
import TL.parsing.PasserTokens.FileRelated.FileStatement;
import TL.parsing.PasserTokens.InCode.Scope;
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
						// #asm\tMOV AH, [a]
						// #asm\tADD AH, 1
						// #asm\tMOV [a], AH
						"""
		);
		Scope Parsed = new Scope();
		Parsed.ParsInnerBlockWithoutBracket(Tokens, 0, Tokens.size());
		System.out.println(Tokens);
		System.out.println(Parsed);
	}

//	@Test
//	public void TestParser2() throws TokenizerException, IOException, InterruptedException {
//		LinkedList<Token> Tokens = Tokenizes(
//				"""
//                        #asmglobal _start
//                        #asmsection .text
//                        #asm_start:
//						1 + 100 + 10
//						"""
//		);
//		CodeBlock Parsed = new CodeBlock();
//		Parsed.ParsInnerBlock(Tokens, 0);
//		System.out.println(Tokens);
//		System.out.println(Parsed);
//
//		AssemblyBlock TheAssemblyBlock = Parsed.AssembleX86();
//		System.out.println("Assembly:");
//		System.out.println(TheAssemblyBlock);
//		File OutPutFile = NasmAPI.ParesAssembly(TheAssemblyBlock);
//
//		Process MyProgram = Runtime.getRuntime().exec(OutPutFile.getAbsolutePath());
//		MyProgram.waitFor();
//	}
	
	@Test
	public void TestParser3() throws TokenizerException, MutelyParsingException {
		LinkedList<Token> Tokens = Tokenizes(
				"""
						import TL.Types.Numbers.int32
						"""
		);
		FileStatement Parsed = new FileStatement(Tokens, 0, Tokens.size());
		Parsed.ParsInnerBlock(Tokens, 0);
		System.out.println(Tokens);
		System.out.println(Parsed);
	}
}