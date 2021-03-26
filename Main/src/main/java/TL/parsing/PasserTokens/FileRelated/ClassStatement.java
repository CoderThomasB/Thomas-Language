package TL.parsing.PasserTokens.FileRelated;

import TL.ErrorHandling;
import TL.Token;
import TL.TokenType;
import TL.parsing.PasserHelper;
import TL.parsing.PasserTokens.Exceptions.ParsingException;
import TL.parsing.PasserTokens.PasserTokenBasic;

import java.util.LinkedList;

public class ClassStatement extends PasserTokenBasic {
	public ClassStatement(LinkedList<Token> Tokens, int TokenStartingPosition, int TokenEndingPosition) {
		super(Tokens, TokenStartingPosition, TokenEndingPosition);
	}
	
	public static ImportStatement ParsInnerBlock(LinkedList<Token> Tokens, int StartingPosition) throws ParsingException {
		int EndingPosition = PasserHelper.FindByTypeOrEndOfFile(Tokens, StartingPosition, TokenType.NextCommand);
		
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
}
