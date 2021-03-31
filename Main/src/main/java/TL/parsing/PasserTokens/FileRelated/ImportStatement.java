package TL.parsing.PasserTokens.FileRelated;

import TL.Token;
import TL.TokenType;
import TL.parsing.PasserHelper;
import TL.parsing.PasserTokens.Exceptions.ParsingException;
import TL.parsing.PasserTokens.PasserTokenBasic;

import java.util.LinkedList;

public class ImportStatement extends PasserTokenBasic {
	public String Body;
	
	public ImportStatement(LinkedList<Token> Tokens, int TokenStartingPosition, int TokenEndingPosition, String Body) {
		super(Tokens, TokenStartingPosition, TokenEndingPosition);
		this.Body = Body;
	}
	
	public static ImportStatement ParsInnerBlock(LinkedList<Token> Tokens, int StartingPosition) throws ParsingException {
		int EndingPosition = PasserHelper.FindByTypeOrEndOfFile(Tokens, StartingPosition, TokenType.NextCommand);
		
		if (EndingPosition - StartingPosition != 2) {
			throw new ParsingException("Line is not the right size to be an import", Tokens, StartingPosition, EndingPosition);
		}
		
		if (!Tokens.get(StartingPosition).Body.equals("import")) {
			throw new ParsingException("Line is dose not start with import (%s)".formatted(Tokens.get(StartingPosition).Body), Tokens, StartingPosition, EndingPosition);
		}
		
		return new ImportStatement(
				Tokens,
				StartingPosition,
				EndingPosition,
				Tokens.get(StartingPosition + 1).Body
		);
	}
	
	@Override
	public String toString() {
		return "import " + Body;
	}
}
