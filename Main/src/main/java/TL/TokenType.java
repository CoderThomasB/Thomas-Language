package TL;

public enum TokenType {
	Assembly, Text, Number, String, /*Comment,*/ NextCommand, ListSeparator,
	OpeningBracket, ClosingBracket,
	OpeningCurlyBracket, ClosingCurlyBracket,
	Plus, Minus, Multiply, Divide, /*Dot,*/ Equals,
	Unknown
}
