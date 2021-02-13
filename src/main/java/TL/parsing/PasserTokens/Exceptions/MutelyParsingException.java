package TL.parsing.PasserTokens.Exceptions;

import TL.Token;

import java.text.MessageFormat;
import java.util.LinkedList;

public class MutelyParsingException extends Exception {
	public LinkedList<ParsingException> ParsingExceptions = new LinkedList<>();
	
	public MutelyParsingException(){
		super();
	}
	
	public MutelyParsingException(ParsingException Other){
		super();
		add(Other);
	}
	
	public MutelyParsingException CombinedParsingExceptions(MutelyParsingException Other){
		ParsingExceptions.addAll(Other.ParsingExceptions);
		return this;
	}
	
	public void add(MutelyParsingException Other){
		ParsingExceptions.addAll(Other.ParsingExceptions);
	}
	public void add(ParsingException Other){
		ParsingExceptions.add(Other);
	}
	
	@Override
	public String toString() {
		String SmallestCodeText = null;
		String message = null;
		int Line = 0;

		for(ParsingException TheParsingException : ParsingExceptions){
			if(SmallestCodeText == null){
				SmallestCodeText = TheParsingException.ErrorTokenInText;
				message = TheParsingException.getMessage();
				Line = TheParsingException.LineNo;
				continue;
			}

			if(TheParsingException.ErrorTokenInText.length() < SmallestCodeText.length()){
				SmallestCodeText = TheParsingException.ErrorTokenInText;
				message = TheParsingException.getMessage();
				Line = TheParsingException.LineNo;
				continue;
			}
		}

		return MessageFormat.format("\n{0} ''{1}'' on line {2}\n", message, SmallestCodeText, Line);
		
//		String Output = "Error:";
//		for(ParsingException TheParsingException : ParsingExceptions){
//			Output += MessageFormat.format("\n{0} ''{1}''", TheParsingException.getMessage(), TheParsingException.ErrorTokenInText);
////			Output += '\n' + TheParsingException.getStackTrace()[0].toString();
//		}
//
//		return Output + "\n";
	}
}
