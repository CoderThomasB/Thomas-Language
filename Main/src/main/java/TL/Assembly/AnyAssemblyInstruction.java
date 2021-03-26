package TL.Assembly;

public class AnyAssemblyInstruction extends AssemblyIntrusion{
	public String Body;
	
	public AnyAssemblyInstruction(String Body) {
		this.Body = Body;
	}
	
	@Override
	public String toString() {
		return Body;
	}
}
