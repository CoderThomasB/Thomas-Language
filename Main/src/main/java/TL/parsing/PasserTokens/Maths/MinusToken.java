package TL.parsing.PasserTokens.Maths;

//import x86.*;

import TL.Token;
import TL.TokenType;
import TL.parsing.PasserTokens.Exceptions.MutelyParsingException;
import TL.parsing.PasserTokens.Exceptions.ParsingException;
import TL.parsing.PasserTokens.InCode.Statement;
import TL.parsing.PasserTokens.PasserTokenBasic;

import java.util.LinkedList;

import static TL.parsing.PasserTokens.Maths.MathsToken.FindPositionOfASymbol;

public class MinusToken extends PasserTokenBasic {
	public Object Input1;
	public Object Input2;
	
	public MinusToken(
			LinkedList<Token> Tokens,
			int TokenStartingPosition,
			int TokenEndingPosition,
			PasserTokenBasic Input1,
			PasserTokenBasic Input2
	) {
		super(Tokens, TokenStartingPosition, TokenEndingPosition);
		
		this.Input1 = Input1;
		this.Input2 = Input2;
	}
	
	public static TokenType getSymbol() {
		return TokenType.Minus;
	}
	
	public static MinusToken ParsInnerBlock(LinkedList<Token> Tokens, int StartingPosition, int EndingPosition) throws MutelyParsingException {
		try {
			int PlusPosition = FindPositionOfASymbol(Tokens, StartingPosition, EndingPosition, getSymbol());
			return new MinusToken(
					Tokens,
					StartingPosition,
					EndingPosition,
					Statement.ParsInnerBlock(Tokens, StartingPosition, PlusPosition),
					Statement.ParsInnerBlock(Tokens, PlusPosition + 1, EndingPosition)
			
			);
		} catch (ParsingException e) {
			throw new MutelyParsingException(e);
		}
	}
	
	@Override
	public String toString() {
		return MathsToken.toString('-', Input1, Input2);
	}
	
}
