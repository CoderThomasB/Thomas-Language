package TL.Assembly.x86;

import java.util.LinkedList;

public class x86RegManger {
	public LinkedList<String> InUsesRegs = new LinkedList<>();
	
	public void DescriedReg(String Reg) {
		InUsesRegs.remove(Reg);
	}
	
	public String GetUnused8BitReg() {
		if (
				!InUsesRegs.contains("AH") &&
						!InUsesRegs.contains("AX") &&
						!InUsesRegs.contains("EAX") &&
						!InUsesRegs.contains("RAX")
		) {
			return "AH";
		}
		if (
				!InUsesRegs.contains("AL") &&
						!InUsesRegs.contains("AX") &&
						!InUsesRegs.contains("EAX") &&
						!InUsesRegs.contains("RAX")
		) {
			return "AL";
		}
		
		throw new OutOfRegistersException();
	}
	
	public String GetUnused16BitReg() {
		if (
				!InUsesRegs.contains("AH") &&
						!InUsesRegs.contains("AL") &&
						!InUsesRegs.contains("AX") &&
						!InUsesRegs.contains("EAX") &&
						!InUsesRegs.contains("RAX")
		) {
			return "AX";
		}
		
		throw new OutOfRegistersException();
	}
	
	public String GetUnused32BitReg() {
		if (
				!InUsesRegs.contains("AH") &&
						!InUsesRegs.contains("AL") &&
						!InUsesRegs.contains("AX") &&
						!InUsesRegs.contains("EAX") &&
						!InUsesRegs.contains("RAX")
		) {
			return "EAX";
		}
		
		throw new OutOfRegistersException();
	}
	
	public String GetUnused64BitReg() {
		if (
				!InUsesRegs.contains("AH") &&
						!InUsesRegs.contains("AL") &&
						!InUsesRegs.contains("AX") &&
						!InUsesRegs.contains("EAX") &&
						!InUsesRegs.contains("RAX")
		) {
			return "RAX";
		}
		
		throw new OutOfRegistersException();
	}
}

class OutOfRegistersException extends RuntimeException {

}