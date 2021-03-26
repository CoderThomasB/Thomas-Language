package TL.Assembly.x86;

import TL.Assembly.AssemblyBlock;

public interface x86Generatable {
	
	x86RegMemOrConst GenerateAssemblyCode(AssemblyBlock TheAssemblyBlock, x86RegManger RegManger);
}
