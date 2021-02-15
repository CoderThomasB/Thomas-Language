package TL.parsing.PasserTokens;

import TL.Token;
import TL.TokenType;
import TL.parsing.PasserTokens.Exceptions.MutelyParsingException;

import java.util.LinkedList;

public abstract class NoBracketsList {
	public static LinkedList<Statement> ParsInnerList(LinkedList<Token> Tokens, int StartingPosition, int EndingPosition) throws MutelyParsingException {
		
		int ParsingPosition = StartingPosition;
		LinkedList<Statement> NewList = new LinkedList<>();
		while (ParsingPosition < EndingPosition){
			int ListSeparatorPosition = FindNextListSeparator(Tokens, ParsingPosition, EndingPosition);
			NewList.addLast(Statement.ParsInnerBlock(
					Tokens,
					ParsingPosition,
					ListSeparatorPosition
			));
			
			ParsingPosition = ListSeparatorPosition + 1;
		}
		
		return NewList;
	}
	
	public static int FindNextListSeparator(LinkedList<Token> Tokens, int StartingPosition, int EndingPosition){
		int ParsingPosition = StartingPosition;
		while (ParsingPosition < EndingPosition){
			if(Tokens.get(ParsingPosition).Type == TokenType.ListSeparator){
				return ParsingPosition;
			}
			ParsingPosition++;
		}
		return EndingPosition;
	}
}
