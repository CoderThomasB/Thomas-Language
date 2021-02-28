package TL.Assembly.x86;

public class x86MoveIntrusion extends x86AssemblyIntrusion {
	public x86RegOrMem Input;
	public x86RegOrMem Output;
	
	public x86MoveIntrusion(x86RegOrMem Output, x86RegOrMem Input) {
		this.Input = Input;
		this.Output = Output;
	}
	
	@Override
	public String toString() {
		return super.toString("MOV %s,%s".formatted(Output.toString(), Input.toString()));
	}
}
