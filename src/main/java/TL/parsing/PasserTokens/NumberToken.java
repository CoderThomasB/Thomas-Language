package TL.parsing.PasserTokens;

import TL.Assembly.AssemblyBlock;
import TL.Assembly.x86.x86RegManger;
import TL.Assembly.x86.x86RegMemOrConst;
import TL.ErrorHandling;
import TL.parsing.PasserTokens.Exceptions.ParsingException;

public class NumberToken extends Value{
	public NumberToken(String Body) throws ParsingException {
		super(Body);
	}
	
	@Override
	public x86RegMemOrConst Get_x86RegOrMem() {
		return new x86RegMemOrConst(Integer.parseInt(Body));
	}
	
	@Override
	public void ValidateValue(String Body) throws ParsingException {
		try {
			Integer.parseInt(Body);
		}catch (Exception E){
			throw new ParsingException("Can not pares number", "", 0);
		}
	}
	
	@Override
	public x86RegMemOrConst GenerateAssemblyCode(AssemblyBlock TheAssemblyBlock, x86RegManger RegManger) {
		return new x86RegMemOrConst(Integer.parseInt(Body));
	}
}
