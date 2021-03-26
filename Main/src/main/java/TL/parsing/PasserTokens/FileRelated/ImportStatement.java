package TL.parsing.PasserTokens.FileRelated;

import TL.ErrorHandling;
import TL.Token;
import TL.TokenType;
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
		int EndingPosition = FindEndOfCommand(Tokens, StartingPosition);
		
		if (EndingPosition - StartingPosition <= 1) {
			throw new ParsingException("Line is too small to be an import", ErrorHandling.CombineTokenBodies(Tokens, StartingPosition, EndingPosition), Tokens.get(StartingPosition).LineNumber);
		}
		
		if (Tokens.get(StartingPosition).Body == "import") {
			throw new ParsingException("Line is dose not start with import", ErrorHandling.CombineTokenBodies(Tokens, StartingPosition, EndingPosition), Tokens.get(StartingPosition).LineNumber);
		}
		
		ImportStatement NewImportStatement = new ImportStatement(
				Tokens,
				StartingPosition,
				EndingPosition,
				Tokens.get(StartingPosition + 1).Body
		);
		return NewImportStatement;
	}
	
	public static int FindEndOfCommand(LinkedList<Token> Tokens, int StartingPosition) {
		int EndingPosition = StartingPosition;
		
		while (EndingPosition < Tokens.size()) {
			if (Tokens.get(EndingPosition).Type == TokenType.NextCommand) {
				break;
			}
			EndingPosition++;
		}
		
		return EndingPosition;
	}
	
	@Override
	public String toString() {
		return "import " + Body;
	}
}
