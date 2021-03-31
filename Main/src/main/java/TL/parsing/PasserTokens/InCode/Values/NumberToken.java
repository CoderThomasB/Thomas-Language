package TL.parsing.PasserTokens.InCode.Values;

//import x86.x86RegManger;
//import x86.x86RegMemOrConst;

import TL.ErrorHandling;
import TL.Token;
import TL.parsing.PasserTokens.Exceptions.ParsingException;
import TL.parsing.PasserTokens.PasserTokenBasic;

import java.util.LinkedList;

public class NumberToken extends PasserTokenBasic {
	private String Body;
	
	public NumberToken(LinkedList<Token> Tokens, int TokenStartingPosition, int TokenEndingPosition, String Body) {
		super(Tokens, TokenStartingPosition, TokenEndingPosition);
		this.Body = Body;
	}
	
	public static NumberToken ParsInnerBlock(LinkedList<Token> Tokens, int StartingPosition, int EndingPosition) throws ParsingException {
		if (EndingPosition - StartingPosition != 1) {
			throw new ParsingException("Value is more than one Token in length.", Tokens, StartingPosition, EndingPosition);
		}
		
		try {
			Integer.parseInt(Tokens.get(StartingPosition).Body);
			return new NumberToken(
					Tokens,
					StartingPosition,
					EndingPosition,
					
					Tokens.get(StartingPosition).Body
			);
		} catch (Exception E) {
			throw new ParsingException("Value is Not a Number", Tokens, StartingPosition, EndingPosition);
		}
	}

//	public NumberToken(String Body) throws ParsingException {
//		super(Body);
//	}

//	@Override
//	public x86RegMemOrConst Get_x86RegOrMem() {
//		return new x86RegMemOrConst(Integer.parseInt(Body));
//	}

//	@Override
//	public void ValidateValue(String Body) throws ParsingException {
//		try {
//			Integer.parseInt(Body);
//		} catch (Exception E) {
//			throw new ParsingException("Can not pares number", "", 0);
//		}
//	}

//	@Override
//	public x86RegMemOrConst GenerateAssemblyCode(AssemblyBlock TheAssemblyBlock, x86RegManger RegManger) {
//		return new x86RegMemOrConst(Integer.parseInt(Body));
//	}
}
