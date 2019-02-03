package Bug;

public class Variable {
  public int type;
  public int addr_i;
  public String addr_s;
  public boolean global;
  public int max_size;

  // INT / BOOLEAN
  public Variable(int type, int addr){
    this.type = type;
    this.setAddr(addr);
    this.global = true;
    this.max_size = 0;
  }

  // ARRAY
  public Variable(int type, int addr, int max_size){
    this.type = type;
    this.setAddr(addr);
    this.global = true;
    this.max_size = max_size;
  }

  public void setAddr(int addr) {
    this.addr_i = addr;
    this.addr_s = Integer.toHexString(addr).toUpperCase();
  }

  public boolean isBoolean() {
    return this.type == Type.BOOL;
  }

  public boolean isInteger() {
    return this.type == Type.INT;
  }

  public boolean isArray() {
    return this.type == Type.ARRAY;
  }
}
