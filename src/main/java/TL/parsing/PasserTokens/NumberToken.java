package TL.parsing.PasserTokens;

import TL.ErrorHandling;
import TL.parsing.PasserTokens.Exceptions.ParsingException;

public class NumberToken extends Value{
	public NumberToken(String Body) throws ParsingException {
		super(Body);
	}
	
	@Override
	public void ValidateValue(String Body) throws ParsingException {
		try {
			Integer.parseInt(Body);
		}catch (Exception E){
			throw new ParsingException("Can not pares number", "", 0);
		}
	}
}
