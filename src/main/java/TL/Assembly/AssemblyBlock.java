package TL.Assembly;

import java.util.LinkedList;

public class AssemblyBlock {
	public LinkedList<AssemblyIntrusion> Intrusions = new LinkedList<>();
	
	@Override
	public String toString() {
		String s = "";
		for (AssemblyIntrusion AI : Intrusions) {
			s += AI.toString();
		}
		return s;
	}
}
