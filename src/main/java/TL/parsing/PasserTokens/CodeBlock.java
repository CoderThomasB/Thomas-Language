package TL.parsing.PasserTokens;

import TL.ErrorHandling;
import TL.parsing.PasserTokens.Exceptions.MutelyParsingException;
import TL.parsing.PasserTokens.Exceptions.ParsingException;
import TL.Token;
import TL.TokenType;

import java.util.LinkedList;

public class CodeBlock {
	public LinkedList<Statement> Commands = new LinkedList<>();
	
	public void ParsInnerBlock(LinkedList<Token> Tokens, int Position) {
		while (Position < Tokens.size()){
			int EndingPosition = FindEndOfCommand(Tokens, Position);
			ParesCommand(Tokens, Position, FindEndOfCommand(Tokens, Position));
			
			Position = EndingPosition + 1;
			int b = 1;
			int a = (b = 1);
		}
	}
	
	public void ParesCommand(LinkedList<Token> Tokens, int StartingPosition, int EndingPosition) {
		if(EndingPosition - StartingPosition < 1){
			return;
		}
		
		if(EndingPosition - StartingPosition == 1 && Tokens.get(StartingPosition).Type == TokenType.Assembly){
			this.Commands.add(new AssemblyLine(Tokens.get(StartingPosition)));
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
			System.err.println(E);
			System.exit(1);
		}
	}
	
	public static int FindEndOfCommand(LinkedList<Token> Tokens, int StartingPosition){
		int EndingPosition = StartingPosition;
		
		while (EndingPosition < Tokens.size()) {
			if(Tokens.get(EndingPosition).Type == TokenType.NextCommand){
				break;
			}
			EndingPosition++;
		}
		
		return EndingPosition;
	}
	
	@Override
	public String toString() {
		return Commands.toString();
	}
}
