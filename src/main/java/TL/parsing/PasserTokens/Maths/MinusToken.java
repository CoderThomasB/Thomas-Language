package TL.parsing.PasserTokens.Maths;

import TL.Assembly.AssemblyBlock;
import TL.Assembly.x86.*;
import TL.Token;
import TL.TokenType;
import TL.parsing.PasserTokens.Exceptions.MutelyParsingException;
import TL.parsing.PasserTokens.Exceptions.ParsingException;
import TL.parsing.PasserTokens.NumberToken;
import TL.parsing.PasserTokens.Statement;

import java.util.LinkedList;

public class MinusToken extends MathsToken {
	public static TokenType getSymbol() {
		return TokenType.Minus;
	}
	
	public static MathsToken ParsInnerBlock(LinkedList<Token> Tokens, int StartingPosition, int EndingPosition) throws MutelyParsingException {
		try {
			int PlusPosition = 0;
			PlusPosition = FindPositionOfASymbol(Tokens, StartingPosition, EndingPosition, getSymbol());
			MinusToken TheToken = new MinusToken();
			
			TheToken.Input1 = Statement.ParsInnerBlock(Tokens, StartingPosition, PlusPosition);
			TheToken.Input2 = Statement.ParsInnerBlock(Tokens, PlusPosition + 1, EndingPosition);
			
			return TheToken;
		} catch (ParsingException e) {
			throw new MutelyParsingException(e);
		}
	}
	
	@Override
	public String toString() {
		return super.toString('-');
	}
	
	@Override
	public x86RegMemOrConst GenerateAssemblyCode(AssemblyBlock TheAssemblyBlock, x86RegManger RegManger) {
		throw new RuntimeException();
	}
}
