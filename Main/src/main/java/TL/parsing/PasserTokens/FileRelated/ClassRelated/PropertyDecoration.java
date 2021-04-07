package TL.parsing.PasserTokens.FileRelated.ClassRelated;

import TL.Token;
import TL.TokenType;
import TL.parsing.PasserHelper;
import TL.parsing.PasserTokens.Exceptions.ParsingException;
import TL.parsing.PasserTokens.PasserTokenBasic;
import TL.parsing.StatementVisibility;

import java.util.LinkedList;

public class PropertyDecoration extends PasserTokenBasic {
	public String Name = null;
	public StatementVisibility Visibility = null;
//	public PasserTokenBasic Type;
	
	public PropertyDecoration(LinkedList<Token> Tokens, int TokenStartingPosition, int TokenEndingPosition) {
		super(Tokens, TokenStartingPosition, TokenEndingPosition);
	}
	
	public static PropertyDecoration ParsInnerBlock(LinkedList<Token> Tokens, int StartingPosition) throws ParsingException {
		int EndingPosition = PasserHelper.FindByTypeOrEndOfFile(Tokens, StartingPosition, TokenType.NextCommand);
		
		int NameAndTagEndingPosition;
		
		try {
			NameAndTagEndingPosition = PasserHelper.FindByTokenType(Tokens, StartingPosition, EndingPosition, TokenType.Equals);
		} catch (Exception e) {
			NameAndTagEndingPosition = EndingPosition;
		}
		PropertyDecoration NewPropertyDecoration = new PropertyDecoration(
				Tokens,
				StartingPosition,
				EndingPosition
		);
		
		NewPropertyDecoration.ParsKeyWorlds(Tokens, StartingPosition, NameAndTagEndingPosition);
		return NewPropertyDecoration;
	}
	
	void ParsKeyWorlds(LinkedList<Token> Tokens, int StartingPosition, int EndingPosition) throws ParsingException {
		int NowPosition = EndingPosition - 1;

MainLoop:
		while (NowPosition >= StartingPosition) {
			if (this.Name == null) {
				this.Name = Tokens.get(NowPosition).Body;
				NowPosition--;
				continue;
			}
			if (this.Visibility == null) {
				// To-Do Loop threw all Visibility and find mashing one and set this.Visibility to it.
				for (StatementVisibility NowChecking : StatementVisibility.values()) {
					if (NowChecking.toString().equalsIgnoreCase(Tokens.get(NowPosition).Body)) {
						this.Visibility = NowChecking;
						NowPosition--;
						continue MainLoop;
					}
				}
			}
//			if (this.Type == null) {
//				this.Type = TypeStatement.ParsInnerBlock(Tokens, NowPosition, NowPosition+1);
//				NowPosition--;
//				continue;
//			}
			throw new ParsingException("Unknown property decoration keyword. Have you misspell something?", Tokens, NowPosition, NowPosition + 1);
		}
		
	}
}
