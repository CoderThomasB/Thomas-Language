package TL.Assembly.x86;

import java.util.Locale;

public class x86RegOrMem {
    public boolean IsMem;
    public long MemAddress;
    public String RegName;

    public x86RegOrMem(long MemAddress){
        set(MemAddress);
    }

    public x86RegOrMem(String RegName){
        set(RegName);
    }

    public void set(String RegName){
        this.IsMem = false;
        this.RegName = RegName.toUpperCase();
    }

    public void set(long MemAddress){
        this.IsMem = true;
        this.MemAddress = MemAddress;
    }

    @Override
    public String toString() {
        if (IsMem) {
            return "[%d]".formatted(MemAddress);
        } else {
            return RegName;
        }
    }
}
