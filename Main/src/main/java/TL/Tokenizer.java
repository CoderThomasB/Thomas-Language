package TL;

import java.util.LinkedList;

import static java.lang.Character.isDigit;
import static java.lang.Character.isWhitespace;

public abstract class Tokenizer {
	public static LinkedList<Token> Tokenizes(String input) throws TokenizerException {
		LinkedList<Token> Tokens = new LinkedList<>();
		int Position;
		int LineNumber = 1;

MainLoop:
		for (Position = 0; Position < input.length(); ) {
			
			if (input.startsWith("//", Position)) {
				
				int NextIndex = Position + 1;
				while (NextIndex < input.length()) {
					if (input.charAt(NextIndex) == '\n') {
						Position = NextIndex;
						continue MainLoop;
					}
					NextIndex++;
				}
				Position = NextIndex;
				//noinspection UnnecessaryLabelOnContinueStatement
				continue MainLoop;
			}
			
			if (input.charAt(Position) == '\n') {
				Tokens.add(new Token('\n', TokenType.NextCommand, Position, LineNumber));
				LineNumber++;
				Position++;
				continue;
			}
			
			if (isWhitespace(input.charAt(Position))) {
				Position++;
				continue;
			}
			
			Tokens.add(GetNextToken(input, Position, LineNumber));
			Position += Tokens.getLast().Body.length();
		}
		
		return Tokens;
	}
	
	private static Token GetNextToken(String input, int Position, int LineNumber) throws TokenizerException {
		
		int NextIndex = Position + 1;
		
		if (input.startsWith("#asm", Position)) {
			while (NextIndex < input.length()) {
				if (input.charAt(NextIndex) == '\n') {
					return new Token(input.substring(Position, NextIndex), TokenType.Assembly, Position, LineNumber);
				}
				NextIndex++;
			}
			return new Token(input.substring(Position, NextIndex), TokenType.Assembly, Position, LineNumber);
			
			//To-Do fix problem ware comment is on the end of the input
//			return new Token(input.substring(Position, input.indexOf("\n", Position)), TokenType.Assembly, Position, LineNumber);
		}
		
		if (input.charAt(Position) == '"') {
			while (NextIndex < input.length()) {
				if (input.charAt(NextIndex) == '"') {
					if (input.charAt(NextIndex - 1) == '\\') {
						NextIndex++;
						continue;
					}
					return new Token(input.substring(Position, NextIndex + 1), TokenType.String, Position, LineNumber);
				}
				NextIndex++;
			}
			
			throw new TokenizerException("Quotation marks are not closed");
		}
		
		TokenType CharTokenType = GetCharTokenType(input.charAt(Position));
		if (CharTokenType != TokenType.Unknown) {
			return new Token(input.charAt(Position), CharTokenType, Position, LineNumber);
		}
		
		if (isDigit(input.charAt(Position))) {
			while (NextIndex < input.length()) {
				if (!isDigit(input.charAt(NextIndex))) {
					break;
				}
				NextIndex++;
			}
			
			return new Token(input.substring(Position, NextIndex), TokenType.Number, Position, LineNumber);
		}
		
		
		while (NextIndex < input.length()) {
			CharTokenType = GetCharTokenType(input.charAt(NextIndex));
			if (CharTokenType != TokenType.Unknown) {
				return new Token(input.substring(Position, NextIndex), TokenType.Text, Position, LineNumber);
			}
			if (isWhitespace(input.charAt(NextIndex))) {
				return new Token(input.substring(Position, NextIndex), TokenType.Text, Position, LineNumber);
			}
			NextIndex++;
		}
		return new Token(input.substring(Position, NextIndex), TokenType.Text, Position, LineNumber);
	}
	
	private static TokenType GetCharTokenType(Character input) {
		return switch (input) {
			case '(' -> TokenType.OpeningBracket;
			case ')' -> TokenType.ClosingBracket;
			case '{' -> TokenType.OpeningCurlyBracket;
			case '}' -> TokenType.ClosingCurlyBracket;
			case '=' -> TokenType.Equals;
			case ';', '\n' -> TokenType.NextCommand;
			case '+' -> TokenType.Plus;
			case '-' -> TokenType.Minus;
			case '*' -> TokenType.Multiply;
			case '/' -> TokenType.Divide;
			case ',' -> TokenType.ListSeparator;
			default -> TokenType.Unknown;
		};
		
	}
}

