package Bug;

public class Type {
  public static final int ID = 0;
  public static final int EXPR = 1;
  public static final int INT = 2;
  public static final int BOOL = 3;
  public static final int ARRAY = 4;
  public static final int NONE = -1;

  public int type;
  public int auxType;
  public String val;
  public int size;

  // NONE Type
  public Type() {
    this.type = Type.NONE;
    this.val = null;
    this.auxType = -1;
    this.size = 0;
  }

  // ID, INT, BOOLEAN
  public Type(int type, String val) {
    this.type = type;
    this.val = val;
    this.auxType = -1;
    this.size = 0;
  }

  // EXPR y ARRAY
  public Type(int type, int param) {
    if (type == Type.EXPR) {
      this.type = Type.EXPR;
      this.val = null;
      this.auxType = param;
      this.size = 0;
    } else {
      this.type = Type.ARRAY;
      this.val = null;
      this.auxType = -1;
      this.size = param;
    }
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

  public boolean isArray() {
    return this.type == Type.ARRAY;
  }

  //
  // public String sizeAsInteger() {
  //   String hex = Integer.toHexString(this.size).toUpperCase();
  //   String aux = String.format("%1$2sH",hex).replace(" ", "0");
  //   return aux;
  // }
}
