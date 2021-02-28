package TL.Assembly.x86;

public class x86AddIntrusion extends x86AssemblyIntrusion {
    public x86RegOrMem Input1andOutput;
    public x86RegOrMem Input2;

    public x86AddIntrusion(x86RegOrMem Input1andOutput, x86RegOrMem Input2) {
        this.Input1andOutput = Input1andOutput;
        this.Input2 = Input2;
    }

    @Override
    public String toString() {
        return super.toString("ADD %s,%s".formatted(Input1andOutput.toString(), Input2.toString()));
    }
}
