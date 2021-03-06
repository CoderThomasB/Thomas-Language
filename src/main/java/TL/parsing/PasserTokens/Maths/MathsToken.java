package TL.parsing.PasserTokens.Maths;

import TL.Assembly.x86.x86ValueOutput;
import TL.ErrorHandling;
import TL.parsing.PasserTokens.Exceptions.MutelyParsingException;
import TL.parsing.PasserTokens.Exceptions.ParsingException;
import TL.parsing.PasserTokens.Statement;
import TL.Token;
import TL.TokenType;

import java.text.MessageFormat;
import java.util.LinkedList;

public abstract class MathsToken extends Statement  implements x86ValueOutput {
	public Object Input1;
	public Object Input2;
	
	public static TokenType getSymbol() {
		throw new RuntimeException();
	}
	
//		public static MathsToken ParsInnerBlock(LinkedList<Token> Tokens, int StartingPosition, int EndingPosition, MathsToken TheMathsToken) throws ParsingException {
//		int PlusPosition = FindPositionOfASymbol(Tokens, StartingPosition, EndingPosition, TheMathsToken.getSymbol());
//
//		Output.Input1 = Statement.ParsInnerBlock(Tokens, StartingPosition, PlusPosition);
//		Output.Input2 = Statement.ParsInnerBlock(Tokens, PlusPosition + 1, EndingPosition);
//
//		return Output;
//	}

	public static MathsToken ParsInnerBlock(LinkedList<Token> Tokens, int StartingPosition, int EndingPosition) throws MutelyParsingException {
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
		throw new ParsingException("Maths statement does not contain " + Symbol + " symbol.", ErrorHandling.CombineTokenBodies(Tokens, StartingPosition, EndingPosition), Tokens.get(StartingPosition).LineNumber);
	}
	
	public String toString(char Symbol) {
		return MessageFormat.format("{0} {1} {2}", Input1.toString(), Symbol, Input2.toString());
	}
}
