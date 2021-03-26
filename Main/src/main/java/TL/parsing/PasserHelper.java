package TL.parsing;

import TL.Token;
import TL.TokenType;

import java.util.LinkedList;
import java.util.function.Function;

public class PasserHelper {
	public static int FindByTypeOrEndOfFile(LinkedList<Token> Tokens, int StartingPosition, TokenType Type) {
		int EndingPosition = StartingPosition;
		
		while (EndingPosition < Tokens.size()) {
			if (Tokens.get(EndingPosition).Type == Type) {
				break;
			}
			EndingPosition++;
		}
		
		return EndingPosition;
	}
}
