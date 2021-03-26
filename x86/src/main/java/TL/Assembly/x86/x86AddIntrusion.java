package TL.Assembly.x86;

public class x86AddIntrusion extends x86AssemblyIntrusion {
    public x86RegMemOrConst Input1andOutput;
    public x86RegMemOrConst Input2;

    public x86AddIntrusion(x86RegMemOrConst Input1andOutput, x86RegMemOrConst Input2) {
        this.Input1andOutput = Input1andOutput;
        this.Input2 = Input2;
    }
    
    @Override
    public String toString() {
        return super.toString("ADD %s,%s".formatted(Input1andOutput.toString(), Input2.toString()));
    }
}
