package TL.parsing.PasserTokens.Exceptions;

import TL.ErrorHandling;

import java.text.MessageFormat;
import java.util.LinkedList;

public class MutelyParsingException extends Exception {
	public LinkedList<ParsingException> ParsingExceptions = new LinkedList<>();
	
	public MutelyParsingException() {
		super();
	}
	
	public MutelyParsingException(ParsingException Other) {
		super();
		add(Other);
	}
	
	public MutelyParsingException CombinedParsingExceptions(MutelyParsingException Other) {
		ParsingExceptions.addAll(Other.ParsingExceptions);
		return this;
	}
	
	public void add(MutelyParsingException Other) {
		ParsingExceptions.addAll(Other.ParsingExceptions);
	}
	
	public void add(ParsingException Other) {
		ParsingExceptions.add(Other);
	}
	
	@Override
	public String toString() {
		String SmallestCodeText = null;
		String message = null;
		int Line = 0;
		
		for (ParsingException TheParsingException : ParsingExceptions) {
			String NewSmallestCodeText = ErrorHandling.CombineTokenBodies(
					TheParsingException.Tokens,
					TheParsingException.StartingPosition,
					TheParsingException.EndingPosition);
			int NewLineNumber = TheParsingException.Tokens.get(TheParsingException.StartingPosition).LineNumber;
			
			if (SmallestCodeText == null) {
				SmallestCodeText = NewSmallestCodeText;
				message = TheParsingException.getMessage();
				Line = NewLineNumber;
				continue;
			}
			
			if (NewSmallestCodeText.length() < SmallestCodeText.length()) {
				SmallestCodeText = NewSmallestCodeText;
				message = TheParsingException.getMessage();
				Line = NewLineNumber;
				//noinspection UnnecessaryContinue
				continue;
			}
		}
		
		return MessageFormat.format("{0} ''{1}'' on line {2}",
				message,
				SmallestCodeText,
				Line);
	}
}
