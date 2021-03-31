package TL.Assembly.x86;

import TL.Assembly.AssemblyIntrusion;

public class x86AssemblyIntrusion extends AssemblyIntrusion {
	
	public String toString(String Thing) {
		return "\t%s".formatted(Thing);
	}
}
