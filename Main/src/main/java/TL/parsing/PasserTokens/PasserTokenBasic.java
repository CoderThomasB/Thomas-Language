package TL.parsing.PasserTokens;

import TL.Token;

import java.util.LinkedList;

public class PasserTokenBasic {
	public LinkedList<Token> Tokens;
	public int TokenStartingPosition;
	public int TokenEndingPosition;
	public PasserTokenBasic(LinkedList<Token> Tokens,
	                        int TokenStartingPosition,
	                        int TokenEndingPosition) {
		this.Tokens = Tokens;
		this.TokenEndingPosition = TokenEndingPosition;
		this.TokenStartingPosition = TokenStartingPosition;
	}
}