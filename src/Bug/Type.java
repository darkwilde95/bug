package Bug;

public class Type {
  public static final int ID = 0;
  public static final int EXPR = 1;
  public static final int INT = 2;
  public static final int BOOL = 3;
  // public static final int ARRAY = 3;
  // public static final int MATRIX = 4;
  public static final int NONE = -1;

  public int type;
  public int auxType;
  public String val;
  public boolean global;
  
  public Type(int type) {
    this.auxType = -1;
    this.val = null;
    this.type = type;
    this.global = true;
  }

  public Type(int type, String val) {
    this.type = type;
    this.val = val;
    this.auxType = -1;
    this.global = true;
  }

  public Type(int type, int auxType, String val) {
    this.type = type;
    this.val = val;
    this.auxType = auxType;
    this.global = true;
  }
  
  public Type(int type, int auxType) {
    this.type = type;
    this.val = null;
    this.auxType = auxType;
    this.global = true;
  }
  
  public Type(int type, int auxType, String val, boolean global) {
    this.type = type;
    this.val = val;
    this.auxType = auxType;
    this.global = global;
  }

  public boolean isBoolean() {
    return this.type == Type.BOOL;
  }

  public boolean isInteger() {
    return this.type == Type.INT;
  }

  public boolean isId() {
    return this.type == Type.ID;
  }
   
  public boolean isExpr() {
    return this.type == Type.EXPR;
  }

  public String asBoolean() {
    Boolean v = Boolean.parseBoolean(this.val);
    return v ? "01H" : "00H";
  }

  public String asInteger() {
    int n = Integer.parseInt(this.val);
    String hex = Integer.toHexString(n).toUpperCase();
    String aux = String.format("%1$2sH",hex).replace(" ", "0");
    return aux;
  }
}
