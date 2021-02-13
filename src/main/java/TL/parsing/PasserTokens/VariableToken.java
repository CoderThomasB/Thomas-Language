package TL.parsing.PasserTokens;

import TL.parsing.PasserTokens.Exceptions.ParsingException;

public class VariableToken extends Value {
	public VariableToken(String Body) throws ParsingException {
		super(Body);
	}
	
	@Override
	public void ValidateValue(String Body) {
		return;
	}
}
