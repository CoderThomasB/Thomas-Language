package TL.Assembly.x86;

import org.junit.Test;

import static org.junit.Assert.*;

public class x86AddIntrusionTest {
    @Test
    public void x86AddIntrusionTest1() {
        x86AddIntrusion TheIntrusion = new x86AddIntrusion(
                new x86RegMemOrConst("Ax"),
                new x86RegMemOrConst("cX")
        );

        assertEquals("\tADD AX,CX", TheIntrusion.toString());

        assertEquals("AX", TheIntrusion.Input1andOutput.toString());
        assertEquals("AX", TheIntrusion.Input1andOutput.RegName);

        assertEquals("CX", TheIntrusion.Input2.toString());
        assertEquals("CX", TheIntrusion.Input2.RegName);
    }
}