package TL.parsing.PasserTokens.Maths;

import TL.Token;
import TL.TokenType;
import TL.parsing.PasserTokens.Exceptions.MutelyParsingException;
import TL.parsing.PasserTokens.Exceptions.ParsingException;
import TL.parsing.PasserTokens.PasserTokenBasic;

import java.text.MessageFormat;
import java.util.LinkedList;

public abstract class MathsToken {
	
	public static TokenType getSymbol() {
		throw new RuntimeException();
	}
	
	public static PasserTokenBasic ParsInnerBlock(LinkedList<Token> Tokens, int StartingPosition, int EndingPosition) throws MutelyParsingException {
		MutelyParsingException MyMutelyParsingException = new MutelyParsingException();
		
		try {
			return MinusToken.ParsInnerBlock(Tokens, StartingPosition, EndingPosition);
		} catch (MutelyParsingException E) {
			MyMutelyParsingException.add(E);
		}
		
		try {
			return PlusToken.ParsInnerBlock(Tokens, StartingPosition, EndingPosition);
		} catch (MutelyParsingException E) {
			MyMutelyParsingException.add(E);
		}
		
		throw MyMutelyParsingException;
	}
	
	public static int FindPositionOfASymbol(LinkedList<Token> Tokens, int StartingPosition, int EndingPosition, TokenType Symbol) throws ParsingException {
		for (int Position = StartingPosition; Position < EndingPosition; Position++) {
			if (Tokens.get(Position).Type == Symbol) {
				return Position;
			}
		}
		throw new ParsingException("Maths statement does not contain " + Symbol + " symbol.", Tokens, StartingPosition, EndingPosition);
	}
	
	public static String toString(char Symbol, Object Input1, Object Input2) {
		return MessageFormat.format("{0} {1} {2}", Input1.toString(), Symbol, Input2.toString());
	}
}
