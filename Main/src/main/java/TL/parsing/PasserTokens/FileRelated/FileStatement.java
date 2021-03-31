package TL.parsing.PasserTokens.FileRelated;

import TL.Token;
import TL.TokenType;
import TL.parsing.PasserHelper;
import TL.parsing.PasserTokens.Exceptions.MutelyParsingException;
import TL.parsing.PasserTokens.PasserTokenBasic;

import java.util.LinkedList;

public class FileStatement extends PasserTokenBasic {
	public LinkedList<PasserTokenBasic> InnerFileItems = new LinkedList<>();
	
	public FileStatement(LinkedList<Token> Tokens, int TokenStartingPosition, int TokenEndingPosition) {
		super(Tokens, TokenStartingPosition, TokenEndingPosition);
	}
	
	public void ParsInnerBlock(LinkedList<Token> Tokens, int Position) throws MutelyParsingException {
		while (Position < Tokens.size() - 1) {
			if (PasserHelper.FindByTypeOrEndOfFile(Tokens, Position, TokenType.NextCommand) - Position <= 1) {
				Position++;
				continue;
			}
			
			PasserTokenBasic NewToken = FileItem.ParsInnerBlock(Tokens, Position);
			InnerFileItems.add(NewToken);
			Position = NewToken.TokenEndingPosition;
		}
	}
	
	
	@Override
	public String toString() {
		String string = "File:\n";
		
		for (PasserTokenBasic TheFileItem : InnerFileItems) {
			string += TheFileItem.toString() + "\n";
		}
		
		return string;
	}
}

