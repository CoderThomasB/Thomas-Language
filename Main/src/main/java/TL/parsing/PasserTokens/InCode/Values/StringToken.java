package TL.parsing.PasserTokens.InCode.Values;

//import x86.x86RegManger;
//import x86.x86RegMemOrConst;

import TL.Token;
import TL.TokenType;
import TL.parsing.PasserTokens.Exceptions.ParsingException;
import TL.parsing.PasserTokens.PasserTokenBasic;

import java.util.LinkedList;

public class StringToken extends PasserTokenBasic {
	
	private String Body;
	
	public StringToken(LinkedList<Token> Tokens, int TokenStartingPosition, int TokenEndingPosition, String Body) {
		super(Tokens, TokenStartingPosition, TokenEndingPosition);
		this.Body = Body;
	}
	
	public static StringToken ParsInnerBlock(LinkedList<Token> Tokens, int StartingPosition, int EndingPosition) throws ParsingException {
		if (EndingPosition - StartingPosition != 1) {
			throw new ParsingException("Value is more than one Token in length.", Tokens, StartingPosition, EndingPosition);
		}
		
		if (Tokens.get(StartingPosition).Type != TokenType.String) {
			throw new ParsingException("Value is Not a String", Tokens, StartingPosition, EndingPosition);
		}
		
		return new StringToken(
				Tokens,
				StartingPosition,
				EndingPosition,
				
				Tokens.get(StartingPosition).Body
		);
	}

//	@Override
//	public x86RegMemOrConst Get_x86RegOrMem() {
//		throw new RuntimeException();
//	}

//	@Override
//	public void ValidateValue(String Body) {
//		return;
//	}

//	@Override
//	public x86RegMemOrConst GenerateAssemblyCode(AssemblyBlock TheAssemblyBlock, x86RegManger RegManger) {
//		throw new RuntimeException();
//	}
}
