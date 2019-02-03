/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bug;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author darkw
 */

public class Compiler extends bugBaseVisitor<Type>{

  private HashMap<String, Variable> SymbolsTable = new HashMap();
  private Stack<String> used = new Stack();
  private int while_num = 0;
  private int if_num = 0;
  private int for_num = 0;
  private int elif_num = 0;
  private boolean add_mult = false, add_div = false;
  private boolean add_gt = false, add_lt = false, add_ge = false, add_le = false;
  private boolean add_eq = false, add_neq = false;
  private PrintWriter outFile;
  private LinkedList<String> program;
  private int var_counter = 5;


  public Compiler() throws UnsupportedEncodingException, FileNotFoundException {
    outFile = new PrintWriter("programs/program.assembler", "UTF-8");
    program = new LinkedList();
  }

  private String asInteger(String val) {
    return toHex(val);
  }

  private String asBoolean(String val) {
    return val.equalsIgnoreCase("true") ? toHex(1) : toHex(0);
  }

  private String toHex(String i) {
    int n = Integer.parseInt(i);
    return toHex(n);
  }

  private String toHex(int i) {
    String hex = Integer.toHexString(i).toUpperCase();
    return String.format("%1$2sH",hex).replace(" ", "0");
  }

  private String toAddr(String id) {
    int addr = SymbolsTable.get(id).addr_i;
    String hex = Integer.toHexString(addr).toUpperCase();
    return String.format("%1$4sH",hex).replace(" ", "0");
    // return id;
  }

  private String addr_0(int addr) {
    return toHex(addr * 2);
  }

  private String addr_1(int addr) {
    return toHex(addr * 2 + 1);
  }

  private void secure_stack(String id) {
    this.secure_assign(id, Type.ID);
  }

  private void secure_assign(String id, int type) {
    int addr = 0;
    if (!SymbolsTable.containsKey(id)) {
      addr = var_counter++;
      SymbolsTable.put(id, new Variable(type, addr));
    } else {
      addr = SymbolsTable.get(id).addr_i;
      SymbolsTable.put(id, new Variable(type, addr));
    }
  }

  private void print(String instruction) {
    // System.out.println(instruction);
    program.add(instruction);
  }

  private void validate_global(String id) {
    if (!SymbolsTable.get(id).global) Errors.notDeclared(id);
  }

  private void validate_exists(String id) {
    if (!SymbolsTable.containsKey(id)) Errors.notDeclared(id);
  }

  private void validate_compatibility(int type1, int type2, String op) {
    if (type1 != type2) Errors.incompatibleTypes(type1, type2, op);
  }

  private void validate_type(int type, String id) {
    if (type != SymbolsTable.get(id).type) Errors.isNot(id, type);
  }

  @Override
  public Type visitIntegerType(bugParser.IntegerTypeContext ctx) {
    return new Type(Type.INT, asInteger(ctx.INT().getText()));
  }

  @Override
  public Type visitBooleanType(bugParser.BooleanTypeContext ctx) {
    return new Type(Type.BOOL, asBoolean(ctx.value.getText()));
  }

  @Override
  public Type visitArrayType(bugParser.ArrayTypeContext ctx) {

    return new Type(Type.ARRAY, 0);
  }

  @Override
  public Type visitIntegerId(bugParser.IntegerIdContext ctx) {
    return new Type(Type.ID, ctx.ID().getText());
  }

  @Override
  public Type visitBooleanId(bugParser.BooleanIdContext ctx) {
    return new Type(Type.ID, ctx.ID().getText());
  }

  @Override
  public Type visitArrayId(bugParser.ArrayIdContext ctx) {
    return new Type(Type.ID, ctx.ID().getText());
  }

  @Override
  public Type visitPrint(bugParser.PrintContext ctx) {
    Type op = this.visit(ctx.expression());
    if (op.isId()) {
      validate_exists(op.val);
      validate_global(op.val);
      print("LD A,(" + toAddr(op.val) + ")");
    } else if (!op.isExpr()){
      print("LD A," + op.val);
    }
    print("OUT");
    return null;
  }

  @Override
  public Type visitExprAssign(bugParser.ExprAssignContext ctx) {
    String left = ctx.ID().getText();
    Type right = this.visit(ctx.expression());

    // Agrega a la ST
    if (right.isExpr()) {
      secure_assign(left, right.auxType);
    } else if (right.isId()) {
      validate_exists(right.val);
      secure_assign(left, SymbolsTable.get(right.val).type);
    } else if (right.isArray()) {
      // secure_assign_arr(left, right.size);
    } else {
      secure_assign(left, right.type);
    }

    switch(right.type) {
      case Type.ID:
         print("LD ("+ toAddr(left) + "),(" + toAddr(right.val) + ")");
        break;

      case Type.INT:
      case Type.BOOL:
        print("LD ("+ toAddr(left) + "),"+ right.val);
        break;

      case Type.EXPR:
         print("LD (" + toAddr(left) + "),A");
        break;

      case Type.ARRAY:
        break;

      default:
        Errors.buildError();
        break;
    }
    return new Type(Type.ID, left);
  }

  @Override
  public Type visitParentExpr_a(bugParser.ParentExpr_aContext ctx) {
    return this.visit(ctx.expression_a());
  }

  @Override
  public Type visitParentExpr_b(bugParser.ParentExpr_bContext ctx) {
    return this.visit(ctx.expression_b());
  }

  @Override
  public Type visitAddSubExpr_a(bugParser.AddSubExpr_aContext ctx) {
    boolean op = ctx.op.getType() == bugParser.PLUS;
    String op_s = ctx.op.getText();
    String id = null, op_l = null, op_r = null;
    Type left = null, right = null;
    left = this.visit(ctx.expression_a(0));
    if (left.isExpr() && ctx.getChild(2).getChildCount() > 1) {
      id = Integer.toString(used.size());
      used.add(id);
      this.secure_stack(id);
      print("LD (" + toAddr(id) + "),A");
    }
    right = this.visit(ctx.expression_a(1));
    if (right.isExpr() && !op) {
      id = Integer.toString(used.size());
      used.add(id);
      this.secure_stack(id);
      print("LD (" + toAddr(id) + "),A");
    }

    // EXPR en IZQ y DER
    if (left.isExpr() && right.isExpr()) {
      validate_compatibility(left.auxType, right.auxType, op_s);
      if (op) {
        op_l = used.pop();
        print("ADD A,(" + toAddr(op_l) + ")");
      } else {
        op_r = used.pop();
        op_l = used.pop();
        print("LD A,(" + toAddr(op_l) + ")");
        print("SUB (" + toAddr(op_r) + ")");
      }
    } else if (left.isExpr() || right.isExpr()) {

    // EXPR en izquierda
      if (left.isExpr()) {
        if (right.isId()) {
          validate_exists(right.val);
          validate_global(right.val);
          validate_type(Type.INT, right.val);
          validate_compatibility(left.auxType, SymbolsTable.get(right.val).type, op_s);
          print((op ? "ADD A,(" : "SUB (") + toAddr(right.val) + ")");
        } else {
          validate_compatibility(left.auxType, right.type, op_s);
          print((op ? "ADD A," : "SUB ") + right.val);
        }
      } else {

      // EXPR en derecha
        if (left.isId()) {
          validate_exists(left.val);
          validate_global(left.val);
          validate_type(Type.INT, left.val);
          validate_compatibility(SymbolsTable.get(left.val).type, right.auxType, op_s);
          if (op) {
            print("ADD A,(" + toAddr(left.val) + ")");
          } else {
            op_r = used.pop();
            print("LD A,(" + toAddr(left.val) + ")" );
            print("SUB (" + toAddr(op_r) + ")");
          }
        } else {
          validate_compatibility(left.type, right.auxType, op_s);
          if (op) {
            print("ADD A," + left.val);
          } else {
            op_r = used.pop();
            print("LD A," + left.val);
            print("SUB (" + toAddr(op_r) + ")");
          }
        }
      }
    } else {
      // HOJAS
      if (left.isInteger() && right.isInteger()) {
        print("LD A," + left.val);
        print((op ? "ADD A," : "SUB ") + right.val);

      } else if (left.isInteger() && right.isId()) {
        validate_exists(right.val);
        validate_global(right.val);
        validate_type(Type.INT, right.val);
        print("LD A," + left.val);
        print((op ? "ADD A,(" : "SUB (") + toAddr(right.val) + ")");

      } else if (left.isId() && right.isInteger()) {
        validate_exists(left.val);
        validate_global(left.val);
        validate_type(Type.INT, left.val);
        print("LD A,(" + toAddr(left.val) + ")");
        print((op ? "ADD A," : "SUB ") + right.val);

      } else if (left.isId() && right.isId()) {
        validate_exists(left.val);
        validate_global(left.val);
        validate_exists(right.val);
        validate_global(right.val);
        validate_type(Type.INT, left.val);
        validate_type(Type.INT, right.val);
        print("LD A,(" + toAddr(left.val) + ")");
        print((op ? "ADD A,(" : "SUB (") + toAddr(right.val) + ")");
      }
    }
    return new Type(Type.EXPR, Type.INT);
  }

  @Override
  public Type visitAndExpr_b(bugParser.AndExpr_bContext ctx) {
    String op = null, id = null, op_s = "&&";
    Type left = null, right = null;
    left = this.visit(ctx.expression_b(0));
    if (left.isExpr() && ctx.getChild(2).getChildCount() > 1) {
      id = Integer.toString(used.size());
      used.add(id);
      this.secure_stack(id);
      print("LD (" + toAddr(id) + "),A");
    }
    right = this.visit(ctx.expression_b(1));

    // EXPR en IZQ y DER
    if (left.isExpr() && right.isExpr()) {
      validate_compatibility(left.auxType, right.auxType, op_s);
      op = used.pop();
      print("AND (" + toAddr(op) + ")");
    } else if (left.isExpr() || right.isExpr()) {

    // EXPR en izquierda
      if (left.isExpr()) {
        if (right.isId()) {
          validate_exists(right.val);
          validate_global(right.val);
          validate_type(Type.BOOL, right.val);
          validate_compatibility(left.auxType, SymbolsTable.get(right.val).type, op_s);
          print("AND (" + toAddr(right.val) + ")");
        } else {
          validate_compatibility(left.auxType, right.type, op_s);
          print("AND " + right.val);
        }
      } else {

      // EXPR en derecha
        if (left.isId()) {
          validate_exists(left.val);
          validate_global(left.val);
          validate_type(Type.BOOL, left.val);
          validate_compatibility(SymbolsTable.get(left.val).type, right.auxType, op_s);
          print("AND (" + toAddr(left.val) + ")");
        } else {
          validate_compatibility(left.type, right.auxType, op_s);
          print("AND " + left.val);
        }
      }
    } else {
      // HOJAS
      if (left.isBoolean() && right.isBoolean()) {
        print("LD A," + left.val);
        print("AND " + right.val);

      } else if (left.isBoolean() && right.isId()) {
        validate_exists(right.val);
        validate_global(right.val);
        validate_type(Type.BOOL, right.val);
        print("LD A," + left.val);
        print("AND (" + toAddr(right.val) + ")");

      } else if (left.isId() && right.isBoolean()) {
        validate_exists(left.val);
        validate_global(left.val);
        validate_type(Type.BOOL, left.val);
        print("LD A,(" + toAddr(left.val) + ")");
        print("AND " + right.val);

      } else if (left.isId() && right.isId()) {
        validate_exists(left.val);
        validate_global(left.val);
        validate_exists(right.val);
        validate_global(right.val);
        validate_type(Type.BOOL, left.val);
        validate_type(Type.BOOL, right.val);
        print("LD A,(" + toAddr(left.val) + ")");
        print("AND (" + toAddr(right.val) + ")");
      }
    }
    return new Type(Type.EXPR, Type.BOOL);
  }

  @Override
  public Type visitOrExpr_b(bugParser.OrExpr_bContext ctx) {
    String op = null, id = null, op_s = "||";
    Type left = null, right = null;
    left = this.visit(ctx.expression_b(0));
    if (left.isExpr() && ctx.getChild(2).getChildCount() > 1) {
      id = Integer.toString(used.size());
      used.add(id);
      this.secure_stack(id);
      print("LD (" + toAddr(id) + "),A");
    }
    right = this.visit(ctx.expression_b(1));

    // EXPR en IZQ y DER
    if (left.isExpr() && right.isExpr()) {
      validate_compatibility(left.auxType, right.auxType, op_s);
      op = used.pop();
      print("OR (" + toAddr(op) + ")");
    } else if (left.isExpr() || right.isExpr()) {

    // EXPR en izquierda
      if (left.isExpr()) {
        if (right.isId()) {
          validate_exists(right.val);
          validate_global(right.val);
          validate_type(Type.BOOL, right.val);
          validate_compatibility(left.auxType, SymbolsTable.get(right.val).type, op_s);
          print("OR (" + toAddr(right.val) + ")");
        } else {
          validate_compatibility(left.auxType, right.type, op_s);
          print("OR " + right.val);
        }
      } else {

      // EXPR en derecha
        if (left.isId()) {
          validate_exists(left.val);
          validate_global(left.val);
          validate_type(Type.BOOL, left.val);
          validate_compatibility(SymbolsTable.get(left.val).type, right.auxType, op_s);
          print("OR (" + toAddr(left.val) + ")");
        } else {
          validate_compatibility(left.type, right.auxType, op_s);
          print("OR " + left.val);
        }
      }
    } else {
      // HOJAS
      if (left.isBoolean() && right.isBoolean()) {
        print("LD A," + left.val);
        print("OR " + right.val);

      } else if (left.isBoolean() && right.isId()) {
        validate_exists(right.val);
        validate_global(right.val);
        validate_type(Type.BOOL, right.val);
        print("LD A," + left.val);
        print("OR (" + toAddr(right.val) + ")");

      } else if (left.isId() && right.isBoolean()) {
        validate_exists(left.val);
        validate_global(left.val);
        validate_type(Type.BOOL, left.val);
        print("LD A,(" + toAddr(left.val) + ")");
        print("OR " + right.val);

      } else if (left.isId() && right.isId()) {
        validate_exists(left.val);
        validate_global(left.val);
        validate_exists(right.val);
        validate_global(right.val);
        validate_type(Type.BOOL, left.val);
        validate_type(Type.BOOL, right.val);
        print("LD A,(" + toAddr(left.val) + ")");
        print("OR (" + toAddr(right.val) + ")");
      }
    }
    return new Type(Type.EXPR, Type.BOOL);
  }

  @Override
  public Type visitNegExpr_a(bugParser.NegExpr_aContext ctx) {
    Type op = this.visit(ctx.expression_a());
    if (op.isInteger()) {
      print("LD A," + op.val);
    } else if (op.isId()) {
      validate_exists(op.val);
      validate_global(op.val);
      validate_type(Type.INT, op.val);
      print("LD A,(" + toAddr(op.val) + ")");
    }
    print("NEG");
    return new Type(Type.EXPR, Type.INT);
  }

  @Override
  public Type visitNotExpr_b(bugParser.NotExpr_bContext ctx) {
    Type op = this.visit(ctx.expression_b());
    if (op.isBoolean()) {
      print("LD A," + op.val);
    } else if (op.isId()) {
      validate_exists(op.val);
      validate_global(op.val);
      validate_type(Type.BOOL, op.val);
      print("LD A,(" + toAddr(op.val) + ")");
    }
    print("XOR 01H");
    return new Type(Type.EXPR, Type.BOOL);
  }

  @Override
  public Type visitCompExpr(bugParser.CompExprContext ctx) {
    int op = ctx.op.getType();
    String id = null, op_l = null, op_r = null, op_s = ctx.op.getText();
    Type left = null, right = null;
    left = this.visit(ctx.expression_a(0));
    if (left.isExpr() && ctx.getChild(2).getChildCount() > 1) {
      id = Integer.toString(used.size());
      used.add(id);
      this.secure_stack(id);
      print("LD (" + toAddr(id) + "),A");
    }
    right = this.visit(ctx.expression_a(1));

    // EXPR en IZQ y DER
    if (left.isExpr() && right.isExpr()) {
      validate_compatibility(left.auxType, right.auxType, op_s);
      op_l = used.pop();
      print("LD D,A");
      print("LD A,(" + toAddr(op_l) + ")");
    } else if (left.isExpr() || right.isExpr()) {

    // EXPR en izquierda
      if (left.isExpr()) {
        if (right.isId()) {
          validate_exists(right.val);
          validate_global(right.val);
          validate_type(Type.INT, right.val);
          validate_compatibility(left.auxType, SymbolsTable.get(right.val).type, op_s);
          print("LD D,(" + toAddr(right.val) + ")");
        } else {
          validate_compatibility(left.auxType, right.type, op_s);
          print("LD D," + right.val);
        }
      } else {

      // EXPR en derecha
        if (left.isId()) {
          validate_exists(left.val);
          validate_global(left.val);
          validate_type(Type.INT, left.val);
          validate_compatibility(SymbolsTable.get(left.val).type, right.auxType, op_s);
          print("LD D,A");
          print("LD A,(" + toAddr(left.val) + ")");
        } else {
          validate_compatibility(left.type, right.auxType, op_s);
          print("LD D,A");
          print("LD A," + left.val);
        }
      }
    } else {

    // HOJAS
      if (left.isInteger() && right.isInteger()) {
        print("LD A," + left.val);
        print("LD D," + right.val);

      } else if (left.isInteger() && right.isId()) {
        validate_exists(right.val);
        validate_global(right.val);
        validate_type(Type.INT, right.val);
        print("LD A," + left.val);
        print("LD D,(" + toAddr(right.val) + ")");

      } else if (left.isId() && right.isInteger()) {
        validate_exists(left.val);
        validate_global(left.val);
        validate_type(Type.INT, left.val);
        print("LD A,(" + toAddr(left.val) + ")");
        print("LD D," + right.val);

      } else if (left.isId() && right.isId()) {
        validate_exists(left.val);
        validate_global(left.val);
        validate_exists(right.val);
        validate_global(right.val);
        validate_type(Type.INT, left.val);
        validate_type(Type.INT, right.val);
        print("LD A,(" + toAddr(left.val) + ")");
        print("LD D,(" + toAddr(right.val) + ")");
      }
    }
    if (op == bugParser.GT) {
      add_gt = true;
      print("CALL GT");
    } else if (op == bugParser.LT) {
      add_lt = true;
      print("CALL LT");
    } else if (op == bugParser.GE) {
      add_ge = true;
      print("CALL GE");
    } else {
      add_le = true;
      print("CALL LE");
    }
    return new Type(Type.EXPR, Type.BOOL);
  }

  @Override
  public Type visitEqNeqExpr_b(bugParser.EqNeqExpr_bContext ctx) {
    boolean op = ctx.op.getType() == bugParser.EQ;
    String id = null, op_l = null, op_s = ctx.op.getText();
    Type left = null, right = null;
    left = this.visit(ctx.expression_b(0));
    if (left.isExpr() && ctx.getChild(2).getChildCount() > 1) {
      id = Integer.toString(used.size());
      used.add(id);
      this.secure_stack(id);
      print("LD (" + toAddr(id) + "),A");
    }
    right = this.visit(ctx.expression_b(1));

    // EXPR en IZQ y DER
    if (left.isExpr() && right.isExpr()) {
      validate_compatibility(left.auxType, right.auxType, op_s);
      op_l = used.pop();
      print("LD D,(" + toAddr(op_l) + ")");
    } else if (left.isExpr() || right.isExpr()) {

    // EXPR en izquierda
      if (left.isExpr()) {
        if (right.isId()) {
          validate_exists(right.val);
          validate_global(right.val);
          validate_type(Type.BOOL, right.val);
          validate_compatibility(left.auxType, SymbolsTable.get(right.val).type, op_s);
          print("LD D,(" + toAddr(right.val) + ")");
        } else {
          validate_compatibility(left.auxType, right.type, op_s);
          print("LD D," + right.val);
        }
      } else {

      // EXPR en derecha
        if (left.isId()) {
          validate_exists(left.val);
          validate_global(left.val);
          validate_type(Type.BOOL, left.val);
          validate_compatibility(SymbolsTable.get(left.val).type, right.auxType, op_s);
          print("LD D,(" + toAddr(left.val) + ")");
        } else {
          validate_compatibility(left.type, right.auxType, op_s);
          print("LD D," + left.val);
        }
      }
    } else {

      // HOJAS
      if (left.isBoolean() && right.isBoolean()) {
        print("LD A," + left.val);
        print("LD D," + right.val);
      } else if (left.isBoolean() && right.isId()) {
        validate_exists(right.val);
        validate_global(right.val);
        validate_type(Type.BOOL, right.val);
        print("LD A," + left.val);
        print("LD D,(" + toAddr(right.val) + ")");

      } else if (left.isId() && right.isBoolean()) {
        validate_exists(left.val);
        validate_global(left.val);
        validate_type(Type.BOOL, left.val);
        print("LD A,(" + toAddr(left.val) + ")");
        print("LD D," + right.val);

      } else if (left.isId() && right.isId()) {
        validate_exists(left.val);
        validate_global(left.val);
        validate_exists(right.val);
        validate_global(right.val);
        validate_type(Type.BOOL, left.val);
        validate_type(Type.BOOL, right.val);
        print("LD A,(" + toAddr(left.val) + ")");
        print("LD D,(" + toAddr(right.val) + ")");
      }
    }
    if (op) {
      add_eq = true;
      print("CALL EQ");
    } else {
      add_neq = true;
      print("CALL NEQ");
    }
    return new Type(Type.EXPR, Type.BOOL);
  }

  @Override
  public Type visitEqNeqExpr_a(bugParser.EqNeqExpr_aContext ctx) {
    boolean op = ctx.op.getType() == bugParser.EQ;
    String id = null, op_l = null, op_s = ctx.op.getText();
    Type left = null, right = null;
    left = this.visit(ctx.expression_a(0));
    if (left.isExpr() && ctx.getChild(2).getChildCount() > 1) {
      id = Integer.toString(used.size());
      used.add(id);
      this.secure_stack(id);
      print("LD (" + toAddr(id) + "),A");
    }
    right = this.visit(ctx.expression_a(1));

    // EXPR en IZQ y DER
    if (left.isExpr() && right.isExpr()) {
      validate_compatibility(left.auxType, right.auxType, op_s);
      op_l = used.pop();
      print("LD D,(" + toAddr(op_l) + ")");

    } else if (left.isExpr() || right.isExpr()) {

    // EXPR en izquierda
      if (left.isExpr()) {
        if (right.isId()) {
          validate_exists(right.val);
          validate_global(right.val);
          validate_type(Type.INT, right.val);
          validate_compatibility(left.auxType, SymbolsTable.get(right.val).type, op_s);
          print("LD D,(" + toAddr(right.val) + ")");
        } else {
          validate_compatibility(left.auxType, right.type, op_s);
          print("LD D," + right.val);
        }
      } else {

      // EXPR en derecha
        if (left.isId()) {
          validate_exists(left.val);
          validate_global(left.val);
          validate_type(Type.INT, left.val);
          validate_compatibility(SymbolsTable.get(left.val).type, right.auxType, op_s);
          print("LD D,(" + toAddr(left.val) + ")");
        } else {
          validate_compatibility(left.type, right.auxType, op_s);
          print("LD D," + left.val);
        }
      }
    } else {

      // HOJAS
      if (left.isInteger() && right.isInteger()) {
        print("LD A," + left.val);
        print("LD D," + right.val);
      } else if (left.isInteger() && right.isId()) {
        validate_exists(right.val);
        validate_global(right.val);
        validate_type(Type.INT, right.val);
        print("LD A," + left.val);
        print("LD D,(" + toAddr(right.val) + ")");

      } else if (left.isId() && right.isInteger()) {
        validate_exists(left.val);
        validate_global(left.val);
        validate_type(Type.INT, left.val);
        print("LD A,(" + toAddr(left.val) + ")");
        print("LD D," + right.val);

      } else if (left.isId() && right.isId()) {
        validate_exists(left.val);
        validate_global(left.val);
        validate_exists(right.val);
        validate_global(right.val);
        validate_compatibility(SymbolsTable.get(left.val).type, SymbolsTable.get(right.val).type, op_s);
        print("LD A,(" + toAddr(left.val) + ")");
        print("LD D,(" + toAddr(right.val) + ")");
      }
    }
    if (op) {
      add_eq = true;
      print("CALL EQ");
    } else {
      add_neq = true;
      print("CALL NEQ");
    }
    return new Type(Type.EXPR, Type.BOOL);
  }

  @Override
  public Type visitMultDivExpr_a(bugParser.MultDivExpr_aContext ctx) {
    boolean op = ctx.op.getType() == bugParser.MULT;
    if (op) add_mult = true;
    if (!op) add_div = true;
    String id = null, op_l = null , op_s = ctx.op.getText();
    Type left = null, right = null;
    left = this.visit(ctx.expression_a(0));
    if (left.isExpr() && ctx.getChild(2).getChildCount() > 1) {
      id = Integer.toString(used.size());
      used.add(id);
      this.secure_stack(id);
      print("LD (" + toAddr(id) + "),A");
    }
    right = this.visit(ctx.expression_a(1));
    print("LD Ac,00H");

    // EXPR en IZQ y DER
    if (left.isExpr() && right.isExpr()) {
      validate_compatibility(left.auxType, right.auxType, op_s);
      op_l = used.pop();
      print("LD D,(" + toAddr(op_l) + ")");
      print("LD E,A");
    } else if (left.isExpr() || right.isExpr()) {

    // EXPR en izquierda
      if (left.isExpr()) {
        if (right.isId()) {
          validate_exists(right.val);
          validate_global(right.val);
          validate_type(Type.INT, right.val);
          validate_compatibility(left.auxType, SymbolsTable.get(right.val).type, op_s);
          print("LD D,A");
          print("LD E,(" + toAddr(right.val )+ ")");
        } else {
          print("LD D,A");
          print("LD E," + right.val);
        }
      } else {

      // EXPR en derecha
        if (left.isId()) {
          validate_exists(left.val);
          validate_global(left.val);
          validate_type(Type.INT, left.val);
          validate_compatibility( SymbolsTable.get(left.val).type, right.auxType, op_s);
          print("LD D,(" + toAddr(left.val) + ")");
          print("LD E,A");
        } else {
          print("LD D," + left.val);
          print("LD E,A");
        }
      }
    } else {

      // HOJAS
      if (left.isInteger() && right.isInteger()) {
        print("LD D," + left.val);
        print("LD E," + right.val);
      } else if (left.isInteger() && right.isId()) {
        validate_exists(right.val);
        validate_global(right.val);
        validate_type(Type.INT, right.val);
        print("LD D," + left.val);
        print("LD E,(" + toAddr(right.val) + ")");

      } else if (left.isId() && right.isInteger()) {
        validate_exists(left.val);
        validate_global(left.val);
        validate_type(Type.INT, left.val);
        print("LD D,(" + toAddr(left.val) + ")");
        print("LD E," + right.val);

      } else if (left.isId() && right.isId()) {
        validate_exists(left.val);
        validate_global(left.val);
        validate_exists(right.val);
        validate_global(right.val);
        validate_type(Type.INT, left.val);
        validate_type(Type.INT, right.val);
        print("LD D,(" + toAddr(left.val) + ")");
        print("LD E,(" + toAddr(right.val) + ")");
      }
    }
    print(op ? "CALL MULT" : "CALL DIV");
    print("LD A,Ac");
    return new Type(Type.EXPR, Type.INT);
  }

  @Override
  public Type visitB_while(bugParser.B_whileContext ctx) {
    int wh = while_num++;
    Type expr = null;
    if (ctx.expression_b().getChildCount() == 1) {
      expr = this.visit(ctx.expression_b());
      if (expr.isId()) {
        validate_exists(expr.val);
        validate_global(expr.val);
        validate_type(Type.BOOL, expr.val);
        print("WHILE_" + wh + " LD A,(" + expr.val + ")");
      } else {
        print("WHILE_" + wh + " LD A," + expr.val);
      }
    } else {
      print("WHILE_" + wh + " NOP");
      this.visit(ctx.expression_b());
    }

    print("CP 00H");
    print("JP Z,END_WHILE_" + wh);
    this.visit(ctx.block());
    print("JP WHILE_" + wh);
    print("END_WHILE_" + wh + " NOP");
    return null;
  }

  @Override
  public Type visitB_for(bugParser.B_forContext ctx) {
    int for_n = for_num++;
    Type expr = null;
    Type var = this.visit(ctx.assignation());
    String id = var.val;
    validate_type(Type.INT, var.val);

    if (ctx.expression_a(0).getChildCount() == 1) {
      expr = this.visit(ctx.expression_a(0));
      if (expr.isId()) {
        validate_exists(expr.val);
        validate_type(Type.INT, expr.val);
        print("FOR_" + for_n + " LD D,(" + toAddr(expr.val) + ")");
      } else {
        print("FOR_" + for_n + " LD D," + expr.val);
      }
    } else {
      print("FOR_" + for_n + " NOP");
      this.visit(ctx.expression_a(0));
      print("LD D,A");
    }
    add_le = true;
    print("LD A,(" + toAddr(id) + ")");
    print("CALL LE");
    print("CP 00H");
    print("JP Z,END_FOR_" + for_n);
    this.visit(ctx.block());

    if (ctx.expression_a(1).getChildCount() == 1) {
      expr = this.visit(ctx.expression_a(1));
      print("LD A,(" + toAddr(id) + ")");
      if (expr.isId()) {
        validate_exists(expr.val);
        validate_type(Type.INT, expr.val);
        print("ADD A,(" + toAddr(expr.val) + ")");
      } else {
        print("ADD A," + expr.val);
      }
    } else {
      this.visit(ctx.expression_a(1));
      print("ADD A,(" + toAddr(id) + ")");
    }
    print("LD (" + toAddr(id) + "),A");
    print("JP FOR_" + for_n);
    print("END_FOR_" + for_n + " NOP");
    Variable var2 = SymbolsTable.get(id);
    var2.global = false;
    SymbolsTable.put(id, var2);
    return null;
  }

  @Override
  public Type visitB_if(bugParser.B_ifContext ctx) {
    int if_n = if_num++;
    int elif_n = 0;
    List<bugParser.Expression_bContext> expr = ctx.expression_b();
    List<bugParser.BlockContext> blocks = ctx.block();
    boolean els = ctx.ELSE() != null;
    Type expr_aux = null;

    if (ctx.expression_b(0).getChildCount() == 1) {
      expr_aux = this.visit(ctx.expression_b(0));
      if (expr_aux.isId()) {
        validate_exists(expr_aux.val);
        validate_global(expr_aux.val);
        validate_type(Type.BOOL, expr_aux.val);
        print("IF_" + if_n + " LD A,(" + toAddr(expr_aux.val) + ")");
      } else {
        print("IF_" + if_n + " LD A," + expr_aux.val);
      }
    } else {
      print("IF_" + if_n + " NOP");
      this.visit(ctx.expression_b(0));
    }
    print("CP 00H");

    if (expr.size() > 1) {
      elif_n = elif_num++;
      print("JP Z,ELIF_" + elif_n);
      this.visit(ctx.block(0));
      print("JP END_IF_" + if_n);

      for (int i = 1; i < expr.size()-1; i++) {
        if (ctx.expression_b(i).getChildCount() == 1) {
          expr_aux = this.visit(ctx.expression_b(i));
          if (expr_aux.isId()) {
            validate_exists(expr_aux.val);
            validate_global(expr_aux.val);
            validate_type(Type.BOOL, expr_aux.val);
            print("ELIF_" + elif_n + " LD A,(" + toAddr(expr_aux.val) + ")");
          } else {
            print("ELIF_" + elif_n + " LD A," + expr_aux.val);
          }
        } else {
          print("ELIF_" + elif_n + " NOP");
          this.visit(ctx.expression_b(i));
        }
        print("CP 00H");

        elif_n = elif_num++;
        print("JP Z,ELIF_" + elif_n);
        this.visit(ctx.block(i));
        print("JP END_IF_" + if_n);
      }

      if (ctx.expression_b(expr.size()-1).getChildCount() == 1) {
        expr_aux = this.visit(ctx.expression_b(expr.size()-1));
        if (expr_aux.isId()) {
          validate_exists(expr_aux.val);
          validate_global(expr_aux.val);
          validate_type(Type.BOOL, expr_aux.val);
          print("ELIF_" + elif_n + " LD A,(" + toAddr(expr_aux.val) + ")");
        } else {
          print("ELIF_" + elif_n + " LD A," + expr_aux.val);
        }
      } else {
        print("ELIF_" + elif_n + " NOP");
        this.visit(ctx.expression_b(expr.size()-1));
      }
      print("CP 00H");

      if (els) {
        print("JP Z,ELSE_" + if_n);
        this.visit(ctx.block(blocks.size()-2));
        print("JP END_IF_" + if_n);
        print("ELSE_" + if_n + " NOP");
        this.visit(ctx.block(blocks.size()-1));
      } else {
        print("JP Z,END_IF_" + if_n);
        this.visit(ctx.block(blocks.size()-1));
      }
    } else {
      if (els) {
        print("JP Z,ELSE_" + if_n);
        this.visit(ctx.block(0));
        print("JP END_IF_" + if_n);
        print("ELSE_" + if_n + " NOP");
        this.visit(ctx.block(1));
      } else {
        print("JP Z,END_IF_" + if_n);
        this.visit(ctx.block(0));
      }
    }
    print("END_IF_" + if_n + " NOP");

    return null;
  }

  @Override
  public Type visitProgram(bugParser.ProgramContext ctx) {
    super.visitProgram(ctx);
    print("HALT");
    if (add_mult) {
      print("MULT LD A,E");
      print("CP 00H");
      print("JP Z,END_MULT");
      print("LD A,Ac");
      print("ADD A,D");
      print("LD Ac,A");
      print("DEC E");
      print("JP MULT");
      print("END_MULT RET");
    }
    if (add_div) {
      print("DIV LD A,D");
      print("DIV2 CP E");
      print("JP C,END_DIV");
      print("SUB E");
      print("INC Ac");
      print("JP DIV2");
      print("END_DIV RET");
    }
    if (add_gt) {
      print("GT CP D");
      print("JP C,FALSE_GT");
      print("JP Z,FALSE_GT");
      print("LD A,01H");
      print("JP END_GT");
      print("FALSE_GT LD A,00H");
      print("END_GT RET");
    }

    if (add_lt) {
      print("LT CP D");
      print("JP C,TRUE_LT");
      print("LD A,00H");
      print("JP END_LT");
      print("TRUE_LT LD A,01H");
      print("END_LT RET");
    }

    if (add_ge) {
      print("GE DEC D");
      print("CP D");
      print("JP C,FALSE_GE");
      print("JP Z,FALSE_GE");
      print("LD A,01H");
      print("JP END_GE");
      print("FALSE_GE LD A,00H");
      print("END_GE RET");
    }

    if (add_le) {
      print("LE INC D");
      print("CP D");
      print("JP C,TRUE_LE");
      print("LD A,00H");
      print("JP END_LE");
      print("TRUE_LE LD A,01H");
      print("END_LE RET");
    }

    if (add_eq) {
      print("EQ CP D");
      print("JP Z,TRUE_EQ");
      print("LD A,00H");
      print("JP END_EQ");
      print("TRUE_EQ LD A,01H");
      print("END_EQ RET");
    }

    if (add_neq) {
      print("NEQ CP D");
      print("JP Z,FALSE_NEQ");
      print("LD A,01H");
      print("JP END_NEQ");
      print("FALSE_NEQ LD A,00H");
      print("END_NEQ RET");
    }

    int num_vars = SymbolsTable.size() + 5;
    String hex = Integer.toHexString(num_vars).toUpperCase();
    String aux = String.format("%1$4sH",hex).replace(" ", "0");
    String id_aux = null;
    program.addFirst("ORG " +  aux);
    for (String instruction : program) {
      outFile.println(instruction);
    }
    outFile.println("END");
    outFile.close();
    // for (String instruction : program) {
    //   if (instruction.contains("(")) {
    //     sb.delete(0, sb.length());
    //     // Primera variable
    //     sb.append(instruction.substring(0, instruction.indexOf('(')+1));
    //     id_aux = instruction.substring(instruction.indexOf('(')+1, instruction.indexOf(')'));
    //     sb.append(String.format("%1$4sH)",SymbolsTable.get(id_aux).val).replace(" ", "0"));
    //
    //     // Posible segunda variable
    //     if (instruction.indexOf('(', instruction.indexOf(')')+1) > 0) {
    //       sb.append(",(");
    //       id_aux = instruction.substring(
    //         instruction.indexOf('(', instruction.indexOf(')')+1)+1,
    //         instruction.indexOf(')', instruction.indexOf(')')+1)
    //       );
    //       sb.append(String.format("%1$4sH)",SymbolsTable.get(id_aux).val).replace(" ", "0"));
    //
    //     // Resto de la instruccion si no hay segunda variable
    //     } else {
    //       sb.append(instruction.substring(instruction.indexOf(')')+1));
    //     }
    //     outFile.println(sb.toString());
    //   } else {
    //     outFile.println(instruction);
    //   }
    // }

    return null;
  }
}
