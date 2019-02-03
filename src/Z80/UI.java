package Z80;

import java.io.IOException;
import Bug.Compiler;
import Bug.bugLexer;
import Bug.bugParser;
import java.io.FileInputStream;
import java.io.InputStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

class UI {
 private GUIJFrame f;
  public Processor z80;

   public UI(){
       this.f=new GUIJFrame();
   }
    public void getProgram(String file, int ch, int spd) throws InterruptedException, IOException {
    //this.f=new GUIJFrame();
    Runtime.getRuntime().gc();
    f.setVisible(true);
    InputStream is = System.in;
    if ( file!=null ) is = new FileInputStream(file);
    ANTLRInputStream input = new ANTLRInputStream(is);
    bugLexer lexer = new bugLexer(input);
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    bugParser parser = new bugParser(tokens);
    ParseTree tree = parser.program();
    Compiler eval = new Compiler();
    eval.visit(tree);
    eval = null;
    tree = null;
    parser = null;
    tokens = null;
    lexer = null;
    Memory mem = new Memory(this);
    Assembler a = new Assembler();
    a.assemble("programs/program.assembler");
    a = null;
    LinkerLoader l = new LinkerLoader();
    l.chargeProgram("programs/relocatableCode.txt", mem);
    l = null;
    updateMem(mem); //print mem in ui
    Runtime.getRuntime().gc();
    this.z80 = new Processor(mem, this, spd);
    if(ch==0){z80.runProgram();}
  }
  public void updateMem(Memory mem){
    String[] progmem = new String[mem.getSize()];
      for (int i = 0; i < mem.getSize(); i++) {
        //System.out.println("addr: " + i + "; MEM: " + mem.get(i) );
        progmem[i]= i+" | "+ mem.get(i);
    }
    f.printprog(progmem);
  }
  public void runInstruction() throws IOException{
      this.z80.runInstruction();
  }
  public void printLabel(String s){
      f.print(s);
  }
  public void printLabelOp(String s){
      f.print2(s);
  }
  public void printLabelOp2(String msg){
      f.printop2(msg);
  }
  public void printLabelReg(int reg,String msg){
      f.printreg(reg, msg);
  }
  public void printLabel16Reg(int reg, String msg){
      f.print16reg(reg,msg);
  }
  public void printLabelMem(int pos,String val){
      f.printmem(String.valueOf(pos),val);
  }
  public void printLabelFlag(int flag, boolean val){
      f.printflag(flag,val);
  }
  public void printOutput(String msg, int data){
      f.printoutput(msg,data);
  }
}
