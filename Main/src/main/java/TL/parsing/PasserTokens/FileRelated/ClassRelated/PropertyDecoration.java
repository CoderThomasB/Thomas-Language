package TL.parsing.PasserTokens.FileRelated.ClassRelated;

import TL.Token;
import TL.TokenType;
import TL.parsing.PasserHelper;
import TL.parsing.PasserTokens.Exceptions.MutelyParsingException;
import TL.parsing.PasserTokens.Exceptions.ParsingException;
import TL.parsing.PasserTokens.FileRelated.ImportStatement;
import TL.parsing.PasserTokens.InCode.Statement;
import TL.parsing.PasserTokens.PasserTokenBasic;

import java.util.LinkedList;

public class PropertyDecoration extends PasserTokenBasic {
	public PropertyDecoration(LinkedList<Token> Tokens, int TokenStartingPosition, int TokenEndingPosition) {
		super(Tokens, TokenStartingPosition, TokenEndingPosition);
	}
	
	public static PropertyDecoration ParsInnerBlock(LinkedList<Token> Tokens, int StartingPosition) throws MutelyParsingException {
		int EndingPosition = PasserHelper.FindByTypeOrEndOfFile(Tokens, StartingPosition, TokenType.NextCommand);
		int EqualsPosition;
		
		try {
			EqualsPosition = PasserHelper.FindByTokenType(Tokens, StartingPosition, EndingPosition, TokenType.Equals);
		} catch (Exception e) {
			throw new MutelyParsingException(new ParsingException("Property decoration dose not contain a equals sine", Tokens, StartingPosition, EndingPosition));
		}
		
		Statement.ParsInnerBlock(Tokens, EqualsPosition + 1, EndingPosition);
		
		return new PropertyDecoration(
				Tokens,
				StartingPosition,
				EndingPosition
		);
	}
}
