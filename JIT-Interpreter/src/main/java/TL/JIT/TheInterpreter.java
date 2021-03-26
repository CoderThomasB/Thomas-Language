package TL.JIT;

import TL.parsing.PasserTokens.InCode.AssemblyLine;
import TL.parsing.PasserTokens.InCode.Statement;

public abstract class TheInterpreter {
	public static void InterpretCommand(Statement TheCommand) {
		if (
				AssemblyLine.class.equals(TheCommand.getClass())
		) {
			throw new RuntimeException("Can not Interpret Assembly" + TheCommand.toString());
		}
		
		if (
				AssemblyLine.class.equals(TheCommand.getClass())
		) {
			throw new RuntimeException("Can not Interpret Assembly" + TheCommand.toString());
		}
		
		throw new RuntimeException("Unknown Command/Statement: " + TheCommand.toString());
	}
}
