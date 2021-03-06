package TL.parsing.PasserTokens;

import TL.Assembly.AssemblyBlock;
import TL.Assembly.x86.x86RegManger;
import TL.Assembly.x86.x86RegMemOrConst;
import TL.parsing.PasserTokens.Exceptions.ParsingException;

public class StringToken extends Value{
	
	public StringToken(String Body) throws ParsingException {
		super(Body);
	}
	
	@Override
	public x86RegMemOrConst Get_x86RegOrMem() {
		throw new RuntimeException();
	}
	
	@Override
	public void ValidateValue(String Body) {
		return;
	}
	
	@Override
	public x86RegMemOrConst GenerateAssemblyCode(AssemblyBlock TheAssemblyBlock, x86RegManger RegManger) {
		throw new RuntimeException();
	}
}
