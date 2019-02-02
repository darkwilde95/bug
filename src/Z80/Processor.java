package Z80;

import java.io.IOException;

public class Processor {
  // 8 bits
  private final int A = 0, B = 1, C = 2, D = 3, E = 4, H = 5, L = 6;
  private final int Ac = 7, Bc = 8, Cc = 9, Dc = 10, Ec = 11, Hc = 12, Lc = 13;

  // 16 bits
  private final int PC = 0, SP = 1, IX = 2, IY = 3;

  // Registros
  private int[] reg_8bit;
  private int[] reg_16bit;

  // Banderas
  private boolean[] flags;
  private final int carry = 0;
  private final int sig = 1;
  private final int zero = 2;
  private final int pv = 3;
  private final int aux_carry = 4;


  // Memoria
  private Memory mem;

  // Instruction register
  private IR ir;
  private boolean end;

  //Pins
  private final Chip chip;

  //UI
  private UI ui;
  
  //Execution speed
  private int SPD = 10;

  public Processor(Memory mem, UI ui, int speed) {
    this.end = false;
    this.flags = new boolean[8];
    this.mem = mem;
    this.reg_8bit = new int[14];
    this.reg_16bit =  new int[4];
    this.ir = new IR();
    for (int i = 0; i < 14; i++) {
      reg_8bit[i] = 0;
    }
    for (int i = 0; i < 4; i++) {
      reg_16bit[i] = 0;
    }
    this.chip = new Chip(ui);
    this.ui = ui;
    this.SPD = speed;
  }

  public void setReg_8bit(int reg, int val){
      this.reg_8bit[reg]=val;
      this.ui.printLabelReg(reg,String.valueOf(val));
  }

  public void setReg_16bit(int reg, int val){
      this.reg_16bit[reg]=val;
      this.ui.printLabel16Reg(reg,String.valueOf(val));
  }

  public void setMem(int pos, int val){
      this.mem.set(pos, val);
      ui.printLabelMem(pos, String.valueOf(val));
  }

  public void setFlags(int flag, boolean val){
      this.flags[flag]=val;
      this.ui.printLabelFlag(flag,val);
  }

  public void runProgram() throws IOException, InterruptedException {
    int i=0;

    while(!this.end) {
      this.fetch();
      this.execute();
      Thread.sleep(SPD);
      i++;
    }
  }
  
  public void runInstruction() throws IOException{      
    this.fetch();
    this.execute();
  }

  public void fetch() {
    int[] instruction = new int[5];
    instruction[0] = this.mem.get(this.reg_16bit[PC]);
    instruction[1] = this.mem.get(this.reg_16bit[PC]+1);
    instruction[2] = this.mem.get(this.reg_16bit[PC]+2);
    instruction[3] = this.mem.get(this.reg_16bit[PC]+3);
    instruction[4] = this.mem.get(this.reg_16bit[PC]+4);
    this.ir.decodeInstruction(instruction);
    this.setReg_16bit(PC, this.reg_16bit[PC]+5);
  }

  public void checkByteLimit() {

  }

  public void checkShortLimit() {

  }

  private int toDec(String address) {
    return Integer.parseInt(address, 16);
  }

  public void execute() throws IOException{
    int res = 0, addr = 0, aux = 0;
    String output = null;
    this.ui.printLabel(String.valueOf(this.ir.opcode));
    this.ui.printLabelOp(String.valueOf(this.ir.op1));
    this.ui.printLabelOp2(String.valueOf(this.ir.op2));

    switch (this.ir.opcode) {
      // add (with reg 8 bits) accum->op1
      case 0:
        res = ALU.add(this.reg_8bit[A], this.reg_8bit[this.ir.op2]);
        this.setReg_8bit(A,res);
        break;

      // add (with mem ind) accum
      case 1:
        addr = this.reg_8bit[this.ir.op1] << 8;
        addr |= this.reg_8bit[this.ir.op2];
        res = ALU.add(this.reg_8bit[A], this.mem.get(addr));
        this.setReg_8bit(A,res);
        break;

      // add (with mem dir) accum
      case 2:
        res = ALU.add(this.reg_8bit[A], this.mem.get(this.ir.op2));
        this.setReg_8bit(A,res);
        break;

      //add (with num 8 bits) accum
      case 3:
        res = ALU.add(this.reg_8bit[A], this.ir.op2);
        this.setReg_8bit(A,res);
        //this.reg_8bit[A] = res;
        break;

      // sub (with reg 8 bits) accum
      case 4:
        res = ALU.sub(this.reg_8bit[A], this.reg_8bit[this.ir.op2]);
        this.setReg_8bit(A,res);
        //this.reg_8bit[A] = res;
        break;

      // sub (with num 8 bits) accum
      case 5:
        addr = this.reg_8bit[this.ir.op1] << 8;
        addr |= this.reg_8bit[this.ir.op2];
        res = ALU.sub(this.reg_8bit[A], this.mem.get(addr));
        this.setReg_8bit(A,res);
        //this.reg_8bit[A] = res;
        break;

      // sub (with mem 8 bits) accum
      case 6:
        res = ALU.sub(this.reg_8bit[A], this.mem.get(this.ir.op2));
        this.setReg_8bit(A,res);
        break;

      // sub (with mem 8 bits) accum
      case 7:
        res = ALU.sub(this.reg_8bit[A], this.ir.op2);
        this.setReg_8bit(A,res);
        //this.reg_8bit[A] = res;
        break;

      // increment1 (with reg 8 bits)-> op2
      case 8:
        this.setReg_8bit(this.ir.op2, this.reg_8bit[this.ir.op2]+1);
        break;

      // increment1 (with reg 16 bits)
      case 9:
        this.setReg_16bit(this.ir.op2, this.reg_16bit[this.ir.op2]+1);
        break;

      // increment1 (with mem ind 8 bits)
      case 10:
        addr = this.reg_8bit[this.ir.op1] << 8;
        addr |= this.reg_8bit[this.ir.op2];
        this.setMem(addr, this.mem.get(addr)+1);
        res = this.mem.get(addr);
        break;

      // increment1 (with mem dir 8 bits)
      case 11:
        this.setMem(this.ir.op2, this.mem.get(this.ir.op2)+1);
        res = this.mem.get(this.ir.op2);
        break;

      // decrement1 (with reg 8 bits)
      case 12:
        this.setReg_8bit(this.ir.op2, this.reg_8bit[this.ir.op2]-1);
        break;

      // decrement1 (with reg 16 bits)
      case 13:
        this.setReg_16bit(this.ir.op2, this.reg_16bit[this.ir.op2]-1);
        break;

      // decrement1 (with mem ind 8 bits)
      case 14:
        addr = this.reg_8bit[this.ir.op1] << 8;
        addr |= this.reg_8bit[this.ir.op2];
        this.setMem(addr, this.mem.get(addr)-1);
        res = this.mem.get(addr);
        break;

      // decrement1 (with mem dir 8 bits)
      case 15:
        this.setMem(this.ir.op2, this.mem.get(this.ir.op2)-1);
        res = this.mem.get(this.ir.op2);
        break;

      // complement1 (only acc)
      case 16:
        res = ALU.complement1(this.reg_8bit[A]);
        this.reg_8bit[A] = res;
        break;

      // complement2 (only acc)
      case 17:
        res = ALU.complement2(this.reg_8bit[A]);
        this.setReg_8bit(A,res);
        //this.reg_8bit[A] = res;
        break;

      // load reg 8 - reg 8
      case 18:
        this.setReg_8bit(this.ir.op1, this.reg_8bit[this.ir.op2]);
        break;

      // load reg 8 - mem ind 8
      case 19:
        addr = this.reg_8bit[(0x00_00_ff_00 & this.ir.op2) >>> 8] << 8;
        addr |= this.reg_8bit[0x00_00_00_ff & this.ir.op2];
        this.setReg_8bit(this.ir.op1, this.mem.get(addr));
        break;

      //load (with reg 8 - mem dir)
      case 20:
        this.setReg_8bit(this.ir.op1, this.mem.get(this.ir.op2));
        break;

      //load (with reg 8 - num 8)
      case 21:
        this.setReg_8bit(this.ir.op1, this.ir.op2);
        break;

      //load (with reg 16 - reg 16)
      case 22:
        this.setReg_16bit(this.ir.op1, this.reg_16bit[this.ir.op2]);
        break;

      //load (with reg 16 - num )
      case 23:
        this.setReg_8bit(this.ir.op1, this.ir.op2);
        break;

      //load (with reg 16 - 2 reg 8 )
      case 24:
        addr = this.reg_8bit[(0x00_00_ff_00 & this.ir.op2) >>> 8] << 8;
        addr |= this.reg_8bit[0x00_00_00_ff & this.ir.op2];
        this.setReg_16bit(this.ir.op1, addr);
        break;

      // load (with 2 reg 8 - reg 16)
      case 25:
        aux = this.reg_16bit[this.ir.op2];
        addr = (0x00_00_ff_00 & this.ir.op1) >>> 8;
        this.setReg_8bit(addr, (0x00_00_ff_00 & aux >>> 8));
        addr = 0x00_00_00_ff & this.ir.op1;
        this.setReg_8bit(addr, 0x00_00_00_ff & aux);
        break;

      //load (with mem ind 8 - reg 8)
      case 26:
        addr = this.reg_8bit[(0x00_00_ff_00 & this.ir.op1) >>> 8] << 8;
        addr |= this.reg_8bit[0x00_00_00_ff & this.ir.op1];
        this.setMem(addr, this.reg_8bit[this.ir.op2]);
        break;

      // load (with mem ind 8 - mem ind 8)
      case 27:
        addr = this.reg_8bit[(0x00_00_ff_00 & this.ir.op1) >>> 8] << 8;
        addr |= this.reg_8bit[0x00_00_00_ff & this.ir.op1];
        aux = this.reg_8bit[(0x00_00_ff_00 & this.ir.op2) >>> 8] << 8;
        aux |= this.reg_8bit[0x00_00_00_ff & this.ir.op2];
        this.setMem(addr, this.mem.get(aux));
        break;

      //load (with mem ind 8 - mem dir 8)
      case 28:
        addr = this.reg_8bit[(0x00_00_ff_00 & this.ir.op1) >>> 8] << 8;
        addr |= this.reg_8bit[0x00_00_00_ff & this.ir.op1];
        this.setMem(addr, this.mem.get(this.ir.op2));
        break;

      // load (with mem ind 8 - num 8)
      case 29:
        addr = this.reg_8bit[(0x00_00_ff_00 & this.ir.op1) >>> 8] << 8;
        addr |= this.reg_8bit[0x00_00_00_ff & this.ir.op1];
        this.setMem(addr, this.ir.op2);
        break;

      //load (with mem dir 8 - reg 8)
      case 30:
        this.setMem(this.ir.op1,  this.reg_8bit[this.ir.op2]);
        break;

      // load (with mem dir 8 - mem ind 8)
      case 31:
        addr = this.reg_8bit[(0x00_00_ff_00 & this.ir.op2) >>> 8] << 8;
        addr |= this.reg_8bit[0x00_00_00_ff & this.ir.op2];
        this.setMem(this.ir.op1, this.mem.get(addr));
        break;

      //load (with mem dir 8 - mem dir 8)
      case 32:
        this.setMem(this.ir.op1, this.mem.get(this.ir.op2));
        break;

      // load (with mem dir 8 - num 8)
      case 33:
        this.setMem(this.ir.op1, this.ir.op2);
        break;

      //Input (only acc)
      case 34:
        this.setReg_8bit(A,this.chip.getDatabus());
        //this.reg_8bit[A] = this.chip.getDatabus();
        break;

      // Output (only acc)
      case 35:
        //output = Integer.toBinaryString(this.reg_8bit[A]);
        System.out.print("Bin: ");
        output =  String.format("%8s", Integer.toBinaryString(this.reg_8bit[A])).replace(' ', '0');
        this.chip.setDatabus(this.reg_8bit[A]);
        System.out.print(output + ", Dec: " + this.reg_8bit[A] + "\n");
        break;

      //  AND (with reg 8 bits) acc
      case 36:
        res = ALU.and(this.reg_8bit[A], this.reg_8bit[this.ir.op2]);
        this.reg_8bit[A] = res;
        break;

      // AND (with mem ind  8 bits) acc
      case 37:
        addr = this.reg_8bit[(0x00_00_ff_00 & this.ir.op2) >>> 8] << 8;
        addr |= this.reg_8bit[0x00_00_00_ff & this.ir.op2];
        res = ALU.and(this.reg_8bit[A], this.mem.get(addr));
        this.reg_8bit[A] = res;
        break;

      // AND (with mem dir  8 bits) acc
      case 38:
        res = ALU.and(this.reg_8bit[A], this.mem.get(this.ir.op2));
        this.reg_8bit[A] = res;
        break;

      // AND (with num 8 bits) acc
      case 39:
        res = ALU.and(this.reg_8bit[A], this.ir.op2);
        this.reg_8bit[A] = res;
        break;

      //  OR (with reg 8 bits) acc
      case 40:
        res = ALU.or(this.reg_8bit[A], this.reg_8bit[this.ir.op2]);
        this.reg_8bit[A] = res;
        break;

      // OR (with mem 8 bits) acc
      case 41:
        addr = this.reg_8bit[(0x00_00_ff_00 & this.ir.op2) >>> 8] << 8;
        addr |= this.reg_8bit[0x00_00_00_ff & this.ir.op2];
        res = ALU.or(this.reg_8bit[A], this.mem.get(addr));
        this.reg_8bit[A] = res;
        break;

      // OR (with mem 8 bits) acc
      case 42:
        res = ALU.or(this.reg_8bit[A], this.mem.get(this.ir.op2));
        this.reg_8bit[A] = res;
        break;

      // OR (with num 8 bits) acc
      case 43:
        res = ALU.or(this.reg_8bit[A], this.ir.op2);
        this.reg_8bit[A] = res;
        break;

      //  XOR (with reg 8 bits) acc
      case 44:
        res = ALU.xor(this.reg_8bit[A], this.reg_8bit[this.ir.op2]);
        this.reg_8bit[A] = res;
        break;

      // XOR (with mem 8 bits) acc
      case 45:
        addr = this.reg_8bit[(0x00_00_ff_00 & this.ir.op2) >>> 8] << 8;
        addr |= this.reg_8bit[0x00_00_00_ff & this.ir.op2];
        res = ALU.xor(this.reg_8bit[A], this.mem.get(addr));
        this.reg_8bit[A] = res;
        break;

      // XOR (with mem 8 bits) acc
      case 46:
        res = ALU.xor(this.reg_8bit[A], this.mem.get(this.ir.op2));
        this.reg_8bit[A] = res;
        break;

      // XOR (with num 8 bits) acc
      case 47:
        res = ALU.xor(this.reg_8bit[A], this.ir.op2);
        this.reg_8bit[A] = res;
        break;

      // comparation (with reg 8 bits) acc
      case 48:
        if (ALU.equal(this.reg_8bit[A], this.reg_8bit[this.ir.op2])) {
          setFlags(this.carry,false);
          setFlags(this.zero,true);
        } else if(ALU.lessThan(this.reg_8bit[A], this.reg_8bit[this.ir.op2])) {
          setFlags(this.carry,true);
          setFlags(this.zero,false);
        } else {
          setFlags(this.carry,false);
          setFlags(this.zero,false);
        }
        break;

      // comparation (with reg 8 bits) acc
      case 49:
        addr = this.reg_8bit[(0x00_00_ff_00 & this.ir.op2) >>> 8] << 8;
        addr |= this.reg_8bit[0x00_00_00_ff & this.ir.op2];
        if (ALU.equal(this.reg_8bit[A], this.mem.get(addr))) {
          setFlags(this.carry,false);
          setFlags(this.zero,true);
        }else if(ALU.lessThan(this.reg_8bit[A], this.mem.get(addr))) {
          setFlags(this.carry,true);
          setFlags(this.zero,false);
        } else {
          setFlags(this.carry,false);
          setFlags(this.zero,false);
        }
        break;

      // comparation (with reg 8 bits) acc
      case 50:
        if (ALU.equal(this.reg_8bit[A], this.mem.get(this.ir.op2))) {
          setFlags(this.carry,false);
          setFlags(this.zero,true);
        }else if(ALU.lessThan(this.reg_8bit[A], this.mem.get(this.ir.op2))) {
          setFlags(this.carry,true);
          setFlags(this.zero,false);
        } else {
          setFlags(this.carry,false);
          setFlags(this.zero,false);
        }
        break;

      // comparation (with reg 8 bits) acc
      case 51:
        if (ALU.equal(this.reg_8bit[A], this.ir.op2)) {
          setFlags(this.carry,false);
          setFlags(this.zero,true);
        }else if(ALU.lessThan(this.reg_8bit[A], this.ir.op2)) {
          setFlags(this.carry,true);
          setFlags(this.zero,false);
        } else {
          setFlags(this.carry,false);
          setFlags(this.zero,false);
        }
        break;

      // rigthRotation (with reg 8 bits)
      case 52:
        this.reg_8bit[this.ir.op2] = ALU.rigthRotation8(this.reg_8bit[this.ir.op2]);
        break;

      // rigthRotation (with reg 16 bits)
      case 53:
        this.reg_16bit[this.ir.op2] = ALU.rigthRotation16(this.reg_16bit[this.ir.op2]);
        break;

      // rigthRotation (with mem 8 bits)
      case 54:
        addr = this.reg_8bit[(0x00_00_ff_00 & this.ir.op2) >>> 8] << 8;
        addr |= this.reg_8bit[0x00_00_00_ff & this.ir.op2];
        this.setMem(addr, ALU.rigthRotation8(this.mem.get(addr)));
        break;

      // rigthRotation (with mem 8 bits)
      case 55:
        this.setMem(this.ir.op2, ALU.rigthRotation8(this.mem.get(this.ir.op2)));
        break;

      // leftRotation (with reg 8 bits)
      case 56:
        this.reg_8bit[this.ir.op2] = ALU.leftRotation8(this.reg_8bit[this.ir.op2]);
        break;

      // leftRotation (with reg 16 bits)
      case 57:
        this.reg_16bit[this.ir.op2] = ALU.leftRotation16(this.reg_16bit[this.ir.op2]);
        break;

      case 58:
        addr = this.reg_8bit[(0x00_00_ff_00 & this.ir.op2) >>> 8] << 8;
        addr |= this.reg_8bit[0x00_00_00_ff & this.ir.op2];
        this.setMem(addr, ALU.leftRotation8(this.mem.get(addr)));
        break;

      case 59:
        this.setMem(this.ir.op2, ALU.leftRotation8(this.mem.get(this.ir.op2)));
        break;

      // TODO: BIT SET RESET

      // jump C, mem ind
      case 69:
        if (this.flags[this.carry]) {
          addr = this.reg_8bit[this.ir.op1] << 8;
          addr |= this.reg_8bit[this.ir.op2];
          this.setReg_16bit(PC, addr);
        }
        break;

      // jump pos(is a memory address)
      case 70:
        if (this.flags[this.carry]) {
          this.setReg_16bit(PC, this.ir.op2);
        }
        break;

      // jump zero
      case 71:
        if (this.flags[this.zero]) {
          addr = this.reg_8bit[this.ir.op1] << 8;
          addr |= this.reg_8bit[this.ir.op2];
          this.setReg_16bit(PC, addr);
        }
        break;

      // jump zero(is a memory address)
      case 72:
        if (this.flags[this.zero]){
          this.setReg_16bit(PC, this.ir.op2);
        }
        break;

      // jump addr
      case 73:
        addr = this.reg_8bit[this.ir.op1] << 8;
        addr |= this.reg_8bit[this.ir.op2];
        this.setReg_16bit(PC, addr);
        break;

      // jump label
      case 74:
        this.setReg_16bit(PC, this.ir.op2);
        break;

      // halt
      case 75:
        this.end = true;
        System.out.println("Fin del programa");
        break;

      // end
      case 76:
        this.end = true;
        break;

      // org
      case 77:
        this.setReg_16bit(PC, this.ir.op2);
        break;

      // equ
      case 78:
        break;

      // call
      case 79:
        //TODO: validar SP
        this.setReg_16bit(SP, this.reg_16bit[PC]);
        this.setReg_16bit(PC, this.ir.op2);
        break;

      // ret
      case 80:
        this.setReg_16bit(PC, this.reg_16bit[SP]);
        this.setReg_16bit(SP, 0);
        break;
      
      // nop
      case 81:
        break;
    }
  }


  //  opcode      op 1              op 2
  //  00000000 | 00000000 00000000| 00000000 00000000

  /*
0  add (with reg) accum
1  add (with mem ind) accum
2  add (with mem dir)
3  add (with num 8 bits) accum
4  sub (with reg) accum
5  sub (with mem ind) accum
6  sub (with mem dir)
7  sub (with num 8 bits) accum
8  increment1 (with reg 8 bits)
9  increment1 (with reg 16 bits)
10 increment1 (with mem ind 8 bits)
11 increment1 (with mem dir 8 bits)
12 decrement1 (with reg 8 bits)
13 decrement1 (with reg 16 bits)
14 decrement1 (with mem ind 8 bits)
15 decrement1 (with mem dir 8 bits)
16 complement1 (only acc)
17 complement2 (only acc)
18 load (with reg 8 - reg 8)
19 load (with reg 8 - mem ind) A, (HL) ind
20 load (with reg 8 - mem dir) A, (2016H) dir
21 load (with reg 8 - num 8)
22 load (with reg 16 - reg 16)
23 load (with reg 16 - num )
24 load (with reg 16 - 2 reg 8 )
25 load (with 2 reg 8 - reg 16)
26 load (with mem ind 8 - reg 8)
27 load (with mem ind 8 - mem ind 8)
28 load (with mem ind 8 - mem dir 8)
29 load (with mem ind 8 - num 8)
30 load (with mem dir 8 - reg 8)
31 load (with mem dir 8 - mem ind 8)
32 load (with mem dir 8 - mem dir 8)
33 load (with mem dir 8 - num 8)
34 Input (only acc)
35 Output (only acc)
36 AND (with reg 8 bits) acc
37 AND (with mem ind  8 bits) acc
38 AND (with mem dir  8 bits) acc
39 AND (with num 8 bits) acc
40 OR (with reg 8 bits) acc
41 OR (with mem ind) acc
42  OR (with mem dir) acc
43  OR (with num 8 bits) acc
44  XOR (with reg 8 bits) acc
45  XOR (with mem ind bits) acc
46  XOR (with mem dir bits) acc
47  XOR (with num 8 bits) acc
48  comparation (with reg 8 bits) acc
49  comparation (with mem ind 8 bits) acc
50  comparation (with mem dir 8 bits) acc
51  comparation (with num 8 bits) acc
52  rigthRotation (with reg 8 bits)
53  rigthRotation (with reg 16 bits)
54  rigthRotation (with mem ind 8 bits)
55  rigthRotation (with mem dir 8 bits)
56  leftRotation (with reg 8 bits)
57  leftRotation (with reg 16 bits)
58  leftRotation (with mem ind 8 bits)
59  leftRotation (with mem dir 8 bits)
60  checkBit (with reg 8 bits) modify z-flag
61  checkBit (with mem ind 8 bits) modify z-flag
62  checkBit (with mem dir 8 bits) modify z-flag
63  setBit (with reg 8 bits)
64  setBit (with mem ind 8 bits)
65  setBit (with mem dir 8 bits)
66  resetBit (with reg 8 bits)
67  resetBit (with mem ind 8 bits)
68  resetBit (with mem dir 8 bits)
69  jump C, mem ind (acc > 0)  JP C,(2050H) JP C,ENDIF JP C,(HL)
70  jump C, mem dir
70  jump C, label
71  jump Z, mem ind (acc == 0)
72  jump Z, mem dir
72  jump Z, label
73  jump mem ind
74  jump mem dir
74  jump label
75  halt
76  end
77  org
78  equ
79  call
80  ret
*/
}
