package TL;

public class Token {
	public String Body;
	public TokenType Type;
	public int StringPosition;
	public int LineNumber;
	
	public Token(String Body, TokenType Type, int StringPosition, int LineNumber) {
		this.Body = Body;
		this.Type = Type;
		this.StringPosition = StringPosition;
		this.LineNumber = LineNumber;
	}
	
	public Token(char charBody, TokenType Type, int StringPosition, int LineNumber) {
		this.Body = String.valueOf(charBody);
		this.Type = Type;
		this.StringPosition = StringPosition;
		this.LineNumber = LineNumber;
	}
	
	@Override
	public String toString() {
		return String.format("%d %s:'%s'", StringPosition, Type, Body);
	}
}
