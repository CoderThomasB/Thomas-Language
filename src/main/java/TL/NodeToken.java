package TL;

import java.util.HashSet;

public class NodeToken extends Token{
	public HashSet<NodeToken> Children = new HashSet<>();
	public NodeToken Parent = null;
	
	public NodeToken(String Body) {
		super(Body);
	}
	
	public NodeToken(char charBody) {
		super(charBody);
	}
}
