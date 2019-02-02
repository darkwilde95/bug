package Z80;

public class Memory {

  private int[] M;
  private final int SIZE = 65535;
//UI
  private UI ui;
  
  public Memory(UI ui) {
    this.M = new int[SIZE];
    this.ui = ui;
  }

  public int get(int addr) {
    return this.M[addr];
  }
  
  public int getSize(){
    return this.SIZE;
  }

  public void set(int addr, int value) {
    this.M[addr] = value;  
    this.ui.printLabelMem(addr,Integer.toString(value));
   // System.out.println("addr: " + addr + "; MEM: " + value );
  }
}
