package TL.parsing;

import TL.Token;
import TL.TokenType;

import java.util.LinkedList;

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
	
	
	public static int FindByTokenType(LinkedList<Token> Tokens, int StartingPosition, TokenType Type) throws Exception {
		return FindByTokenType(Tokens, StartingPosition, Tokens.size(), Type);
	}
	
	public static int FindByTokenType(LinkedList<Token> Tokens, int StartingPosition, int EndingPosition, TokenType Type) throws Exception {
		int TokenPosition = StartingPosition;
		
		while (TokenPosition < EndingPosition) {
			if (Tokens.get(TokenPosition).Type == Type) {
				return TokenPosition;
			}
			TokenPosition++;
		}
		
		throw new Exception("Can not find Token");
	}
}
