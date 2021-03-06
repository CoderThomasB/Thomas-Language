package TL;

import TL.Assembly.AssemblyBlock;
import TL.Assembly.x86.NasmAPI;
import TL.Assembly.x86.x86RegManger;
import TL.Assembly.x86.x86ValueOutput;
import TL.parsing.PasserTokens.CodeBlock;
import TL.parsing.PasserTokens.Maths.MathsToken;
import TL.parsing.PasserTokens.Maths.PlusToken;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
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
	public void TestParser2() throws TokenizerException, IOException, InterruptedException {
		LinkedList<Token> Tokens = Tokenizes(
      """
			1 + 10 + 100
			"""
		);
		CodeBlock Parsed = new CodeBlock();
		AssemblyBlock TheAssemblyBlock = new AssemblyBlock();
		x86RegManger RegManger = new x86RegManger();
		Parsed.ParsInnerBlock(Tokens, 0);
		System.out.println(Tokens);
		System.out.println(Parsed);
		System.out.println(((x86ValueOutput)Parsed.Commands.get(0)).GenerateAssemblyCode(TheAssemblyBlock, RegManger));
		System.out.println("Asembly:");
		System.out.println(TheAssemblyBlock);
		File OutPutFile = NasmAPI.ParesAssembly(TheAssemblyBlock);
		
		Process MyProgram = Runtime.getRuntime( ).exec(OutPutFile.getAbsolutePath());
		MyProgram.waitFor( );
	}
}