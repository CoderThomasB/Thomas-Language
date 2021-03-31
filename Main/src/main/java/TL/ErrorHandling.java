package TL;

import java.util.LinkedList;
import java.util.List;

abstract public class ErrorHandling {
	public static String CombineTokenBodies(List<Token> Tokens, int StartingPosition, int EndingPosition) {
		LinkedList<String> Strings = new LinkedList<>();
		
		for (int Position = StartingPosition; Position < EndingPosition; Position++) {
			Strings.add(Tokens.get(Position).Body);
		}
		return String.join(" ", Strings);
	}
}
