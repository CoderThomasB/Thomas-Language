package TL.parsing.PasserTokens.Maths;

import TL.Assembly.AssemblyBlock;
import TL.Assembly.AssemblyIntrusion;
import TL.Assembly.x86.*;
import TL.Token;
import TL.TokenType;
import TL.parsing.PasserTokens.Exceptions.MutelyParsingException;
import TL.parsing.PasserTokens.Exceptions.ParsingException;
import TL.parsing.PasserTokens.NumberToken;
import TL.parsing.PasserTokens.Statement;

import java.util.LinkedList;

public class PlusToken extends MathsToken {
	
	@Override
	public x86RegMemOrConst GenerateAssemblyCode(AssemblyBlock TheAssemblyBlock, x86RegManger RegManger) {
		x86RegMemOrConst Input1x86Value = ((x86ValueOutput) this.Input1).GenerateAssemblyCode(TheAssemblyBlock, RegManger);
		x86RegMemOrConst Input2x86Value = ((x86ValueOutput) this.Input2).GenerateAssemblyCode(TheAssemblyBlock, RegManger);
		x86AddIntrusion AssembleCommand = null;
		
		if (Input1x86Value.Type == x86RegMemOrConstTypes.Const && Input2x86Value.Type == x86RegMemOrConstTypes.Const) {
			x86RegMemOrConst Input1andOutputReg = new x86RegMemOrConst(RegManger.GetUnused8BitReg());
			
			TheAssemblyBlock.Intrusions.add(
					new x86MoveIntrusion(
							Input1andOutputReg,
							Input1x86Value
					)
			);
			
			AssembleCommand = new x86AddIntrusion(
					Input1andOutputReg,
					Input2x86Value
			);
			TheAssemblyBlock.Intrusions.add(AssembleCommand);
			return AssembleCommand.Input1andOutput;
		}
		
		if (Input1x86Value.Type == x86RegMemOrConstTypes.Reg) {
			AssembleCommand = new x86AddIntrusion(
					Input1x86Value,
					Input2x86Value
			);
			TheAssemblyBlock.Intrusions.add(AssembleCommand);
			return AssembleCommand.Input1andOutput;
		}
		
		if (Input2x86Value.Type == x86RegMemOrConstTypes.Reg) { // Flipped inputs
			AssembleCommand = new x86AddIntrusion(
					Input2x86Value,
					Input1x86Value
			);
			TheAssemblyBlock.Intrusions.add(AssembleCommand);
			return AssembleCommand.Input1andOutput;
		}
		
		if(AssembleCommand == null){
			throw new RuntimeException();
		}
		
		TheAssemblyBlock.Intrusions.add(AssembleCommand);
		return AssembleCommand.Input1andOutput;
		
	}
	
	public static TokenType getSymbol() {
		return TokenType.Plus;
	}
	
	public static MathsToken ParsInnerBlock(LinkedList<Token> Tokens, int StartingPosition, int EndingPosition) throws MutelyParsingException {
		try {
			int PlusPosition;
			PlusPosition = FindPositionOfASymbol(Tokens, StartingPosition, EndingPosition, getSymbol());
			PlusToken TheToken = new PlusToken();
			
			TheToken.Input1 = Statement.ParsInnerBlock(Tokens, StartingPosition, PlusPosition);
			TheToken.Input2 = Statement.ParsInnerBlock(Tokens, PlusPosition + 1, EndingPosition);
			
			return TheToken;
		} catch (ParsingException e) {
			throw new MutelyParsingException(e);
		}
	}
	
	@Override
	public String toString() {
		return super.toString('+');
	}
}
