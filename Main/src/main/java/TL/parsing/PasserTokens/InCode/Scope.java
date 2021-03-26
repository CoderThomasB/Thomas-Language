package TL.parsing.PasserTokens.InCode;

//import x86.x86Generatable;
//import x86.x86RegManger;
import TL.ErrorHandling;
import TL.parsing.PasserTokens.Exceptions.MutelyParsingException;
import TL.Token;
import TL.TokenType;
import TL.parsing.PasserTokens.Exceptions.ParsingException;
import TL.parsing.PasserTokens.PasserTokenBasic;

import java.util.LinkedList;

import static TL.parsing.PasserHelper.FindByTypeOrEndOfFile;

public class Scope {
	public LinkedList<PasserTokenBasic> Commands = new LinkedList<>();
	
	public void ParsInnerBlockWithoutBracket(LinkedList<Token> Tokens, int StartingPosition, int EndingPosition) {
		while (StartingPosition < EndingPosition) {
			int CommandEndingPosition = FindByTypeOrEndOfFile(Tokens, StartingPosition, TokenType.NextCommand);
			ParesCommand(Tokens, StartingPosition, CommandEndingPosition);
			
			StartingPosition = CommandEndingPosition + 1;
		}
	}
	
	public void ParsInnerBlockWithBracket(LinkedList<Token> Tokens, int StartingPosition) throws ParsingException {
		if(Tokens.get(StartingPosition).Type != TokenType.OpeningCurlyBracket){
			throw new ParsingException("Scope dose not start with OpeningCurlyBracket '{'.",
					ErrorHandling.CombineTokenBodies(Tokens,  StartingPosition, Tokens.size()),
					Tokens.get(StartingPosition).LineNumber
			);
		}
		ParsInnerBlockWithoutBracket(
				Tokens,
				StartingPosition + 1,
				FindEndOfScope(Tokens, StartingPosition)
		);
	}
	
	public void ParsInnerBlockWithBracket(LinkedList<Token> Tokens, int StartingPosition, int EndingPosition) throws ParsingException {
		if(Tokens.get(StartingPosition).Type != TokenType.OpeningCurlyBracket){
			throw new ParsingException("Scope dose not start with OpeningCurlyBracket '{'.",
					ErrorHandling.CombineTokenBodies(Tokens,  StartingPosition, EndingPosition),
					Tokens.get(StartingPosition).LineNumber
			);
		}
		if(Tokens.get(EndingPosition - 1).Type != TokenType.ClosingCurlyBracket){
			throw new ParsingException("Scope dose not start with ClosingBracket '}'.",
					ErrorHandling.CombineTokenBodies(Tokens,  StartingPosition, EndingPosition),
					Tokens.get(StartingPosition).LineNumber
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
			// ToDo update this to use the new MutelyParsingException!
//			throw new RuntimeException(
//					"Unknown command '%s' on Line %d Charter %d".
//							formatted(
//									ErrorHandling.CombineTokenBodies(Tokens, StartingPosition, EndingPosition),
//									Tokens.get(StartingPosition).LineNumber,
//									Tokens.get(StartingPosition).StringPosition
//							));
//			System.err.println(E.toString());
			E.printStackTrace();
			System.exit(1);
		}
	}
	
	public static int FindEndOfScope(LinkedList<Token> Tokens, int StartingPosition) throws ParsingException {
		int EndingPosition = StartingPosition;
		
		while (EndingPosition < Tokens.size()) {
			if (Tokens.get(EndingPosition).Type == TokenType.ClosingCurlyBracket) {
				return  EndingPosition;
			}
			EndingPosition++;
		}
		
		throw new ParsingException("Can not find end of scope.",
				ErrorHandling.CombineTokenBodies(Tokens,  StartingPosition, Tokens.size()),
				Tokens.get(StartingPosition).LineNumber
		);
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
