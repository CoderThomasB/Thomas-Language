package TL.Assembly.x86;

public class x86MoveIntrusion extends x86AssemblyIntrusion {
	public x86RegMemOrConst Input;
	public x86RegMemOrConst Output;
	
	public x86MoveIntrusion(x86RegMemOrConst Output, x86RegMemOrConst Input) {
		this.Input = Input;
		this.Output = Output;
	}
	
	@Override
	public String toString() {
		return super.toString("MOV %s,%s".formatted(Output.toString(), Input.toString()));
	}
}
