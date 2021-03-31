package TL.Assembly;

import java.util.LinkedList;

public class AssemblyBlock {
	public LinkedList<AssemblyIntrusion> Intrusions = new LinkedList<>();
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (AssemblyIntrusion AI : Intrusions) {
			s.append("%s\n".formatted(AI.toString()));
		}
		return s.toString();
	}
}
