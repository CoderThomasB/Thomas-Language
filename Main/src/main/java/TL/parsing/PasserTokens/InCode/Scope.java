package TL.parsing.PasserTokens.InCode;

import TL.Token;
import TL.TokenType;
import TL.parsing.PasserTokens.Exceptions.MutelyParsingException;
import TL.parsing.PasserTokens.Exceptions.ParsingException;
import TL.parsing.PasserTokens.PasserTokenBasic;

import java.util.LinkedList;

import static TL.parsing.PasserHelper.FindByTypeOrEndOfFile;

public class Scope {
	public LinkedList<PasserTokenBasic> Commands = new LinkedList<>();
	
	public static int FindEndOfScope(LinkedList<Token> Tokens, int StartingPosition) throws ParsingException {
		int EndingPosition = StartingPosition;
		
		while (EndingPosition < Tokens.size()) {
			if (Tokens.get(EndingPosition).Type == TokenType.ClosingCurlyBracket) {
				return EndingPosition;
			}
			EndingPosition++;
		}
		
		throw new ParsingException("Can not find end of scope.",
				Tokens, StartingPosition, EndingPosition
		);
	}
	
	public void ParsInnerBlockWithoutBracket(LinkedList<Token> Tokens, int StartingPosition, int EndingPosition) {
		while (StartingPosition < EndingPosition) {
			int CommandEndingPosition = FindByTypeOrEndOfFile(Tokens, StartingPosition, TokenType.NextCommand);
			ParesCommand(Tokens, StartingPosition, CommandEndingPosition);
			
			StartingPosition = CommandEndingPosition + 1;
		}
	}
	
	public void ParsInnerBlockWithBracket(LinkedList<Token> Tokens, int StartingPosition) throws ParsingException {
		if (Tokens.get(StartingPosition).Type != TokenType.OpeningCurlyBracket) {
			throw new ParsingException("Scope dose not start with OpeningCurlyBracket '{'.", Tokens, StartingPosition, StartingPosition + 1);
		}
		ParsInnerBlockWithoutBracket(
				Tokens,
				StartingPosition + 1,
				FindEndOfScope(Tokens, StartingPosition)
		);
	}
	
	public void ParsInnerBlockWithBracket(LinkedList<Token> Tokens, int StartingPosition, int EndingPosition) throws ParsingException {
		if (Tokens.get(StartingPosition).Type != TokenType.OpeningCurlyBracket) {
			throw new ParsingException("Scope dose not start with OpeningCurlyBracket '{'.",
					Tokens, StartingPosition, StartingPosition + 1
			);
		}
		if (Tokens.get(EndingPosition - 1).Type != TokenType.ClosingCurlyBracket) {
			throw new ParsingException("Scope dose not start with ClosingBracket '}'.",
					Tokens, StartingPosition, EndingPosition
			);
		}
		ParsInnerBlockWithoutBracket(
				Tokens,
				StartingPosition + 1,
				FindEndOfScope(Tokens, StartingPosition)
		);
	}
	
	public void ParesCommand(LinkedList<Token> Tokens, int StartingPosition, int EndingPosition) {
		if (EndingPosition - StartingPosition < 1) {
			return;
		}
		
		if (EndingPosition - StartingPosition == 1 && Tokens.get(StartingPosition).Type == TokenType.Assembly) {
			this.Commands.add(new AssemblyLine(Tokens, StartingPosition, EndingPosition, Tokens.get(StartingPosition)));
			return;
		}
		
		try {
			this.Commands.add(Command.ParsInnerBlock(Tokens, StartingPosition, EndingPosition));
		} catch (MutelyParsingException E) {
			E.printStackTrace();
			System.exit(1);
		}
	}
	
	@Override
	public String toString() {
		String string = "{\n";
		
		for (Object TheCommand : Commands) {
			string += TheCommand.toString() + "\n";
		}
		
		string += "}";
		
		return string;
	}
}
