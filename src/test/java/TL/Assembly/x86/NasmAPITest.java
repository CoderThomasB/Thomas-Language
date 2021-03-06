package TL.Assembly.x86;

import TL.Assembly.AssemblyBlock;
import org.junit.Test;

import java.io.IOException;

public class NasmAPITest {
	@Test
	public void NasmAPITest1( ) throws IOException, InterruptedException {
		AssemblyBlock TheAssemblyBlock = new AssemblyBlock( );
		TheAssemblyBlock.Intrusions.add(
				new x86MoveIntrusion(
						new x86RegMemOrConst( "AH" ),
						new x86RegMemOrConst( (long)10 ) ) );
		
		NasmAPI.ParesAssembly( TheAssemblyBlock );
	}
}