package TL.Assembly.x86;

public class x86RegMemOrConst {
	public x86RegMemOrConstTypes Type;
	public long MemAddress;
	public long Const;
	public String RegName;
	
	public x86RegMemOrConst(long MemAddress) {
		set(MemAddress);
	}
	
	public x86RegMemOrConst(String RegName) {
		set(RegName);
	}
	
	public x86RegMemOrConst(int Const) {
		set(Const);
	}
	
	public void set(String RegName) {
		this.Type = x86RegMemOrConstTypes.Reg;
		this.RegName = RegName.toUpperCase();
	}
	
	public void set(long MemAddress) {
		this.Type = x86RegMemOrConstTypes.Mem;
		this.MemAddress = MemAddress;
	}
	
	public void set(int Const) {
		this.Type = x86RegMemOrConstTypes.Const;
		this.Const = Const;
	}
	
	@Override
	public String toString() {
		return switch (this.Type) {
			case Mem -> "[%d]".formatted(MemAddress);
			case Const -> "%d".formatted(Const);
			case Reg -> RegName;
		};
	}
}
