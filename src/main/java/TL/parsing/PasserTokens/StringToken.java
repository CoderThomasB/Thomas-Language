package TL.parsing.PasserTokens;

import TL.parsing.PasserTokens.Exceptions.ParsingException;

public class StringToken extends Value{
	
	public StringToken(String Body) throws ParsingException {
		super(Body);
	}
	
	@Override
	public void ValidateValue(String Body) {
		return;
	}
}
