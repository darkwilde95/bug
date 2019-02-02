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

  private HashMap<String, Type> SymbolsTable = new HashMap();
  private org.antlr.v4.runtime.tree.ParseTree last = null;
  private Stack<String> used = new Stack();
  private int jp_num = 0;
  private int while_num = 0;
  private int if_num = 0;
  private int for_num = 0;
  private int elif_num = 0;
  private boolean add_mult = false, add_div = false;
  private PrintWriter outFile;
  private LinkedList<String> program;
  private int var_counter = 5;
  
  
  public Compiler() throws UnsupportedEncodingException, FileNotFoundException {
    outFile = new PrintWriter("programs/program.assembler", "UTF-8");
    program = new LinkedList();
  }
  
  private void ensure_global(String id) {
    if (!SymbolsTable.get(id).global) {
      Errors.notDeclared(id);
    }
  }

  private void ensure_stack(String id) {
    this.ensure_assign(id, Type.ID);
  }
  
  private void ensure_assign(String id, int t) {
    String addr = null;
    if (!SymbolsTable.containsKey(id)) {
      SymbolsTable.put(id, new Type(t, Integer.toHexString(var_counter++).toUpperCase()));
    } else {
      addr = SymbolsTable.get(id).val;
      SymbolsTable.put(id, new Type(t, addr));
    }
  }

  private void print(String instruction) {
    program.add(instruction);
  }

  @Override
  public Type visitB_for(bugParser.B_forContext ctx) {
    int for_n = for_num++, jp1 = 0, jp2 = 0;
    Type expr = null;
    Type var = this.visit(ctx.assignation());
    if (var.auxType == Type.BOOL) {
      Errors.isNot(Type.INT);
    }
    String id = var.val; 
    
    if (ctx.expression_a(0).getChildCount() == 1) {
      expr = this.visit(ctx.expression_a(0));

      if (expr.isId()) {
        if (SymbolsTable.containsKey(expr.val)) {
          if (SymbolsTable.get(expr.val).isInteger()) {
            print("FOR_" + for_n + " LD B,(" + expr.val + ")");
          } else {
            Errors.isNot(Type.INT);
          }
        } else {
          Errors.notDeclared(expr.val);
        }
      } else {
        print("FOR_" + for_n + " LD B," + expr.asInteger());
      }
    } else {
      print("FOR_" + for_n + " NOP");
      this.visit(ctx.expression_a(0));
      print("LD B,A");  
    }
    jp1 = jp_num++;
    jp2 = jp_num++;
    print("LD A,(" + id + ")");
    print("INC B");
    print("CP B");
    print("JP C,JUMP_" + jp1);
    print("LD A,00H");
    print("JP JUMP_" + jp2);
    print("JUMP_" + jp1 + " LD A,01H");
    print("JUMP_" + jp2 + " NOP");
    print("CP 00H");
    print("JP Z,END_FOR_" + for_n);
    
    this.visit(ctx.block());
    
    if (ctx.expression_a(1).getChildCount() == 1) {
      expr = this.visit(ctx.expression_a(1));
      if (expr.isId()) {
        if (SymbolsTable.containsKey(expr.val)) {
          if (SymbolsTable.get(expr.val).isInteger()) {
            print("LD A,(" + id + ")");
            print("ADD A,(" + expr.val + ")");
          } else {
            Errors.isNot(Type.INT);
          }
        } else {
          Errors.notDeclared(expr.val);
        }
      } else {
        print("LD A,(" + id + ")");
        print("ADD A," + expr.asInteger());
      }
    } else {
      print("ADD A,(" + id + ")");
    }
    print("LD (" + id + "),A");
    print("JP FOR_" + for_n);
    print("END_FOR_" + for_n + " NOP");
    
    var = SymbolsTable.get(id);
    var.global = false;
    SymbolsTable.put(id, var);   
    
    return null;
  }

  @Override
  public Type visitB_while(bugParser.B_whileContext ctx) {
    int wh = while_num++;
    Type expr = null;
    if (ctx.expression_b().getChildCount() == 1) {
      expr = this.visit(ctx.expression_b());

      if (expr.isId()) {
        if (SymbolsTable.containsKey(expr.val)) {
          ensure_global(expr.val);
          if (SymbolsTable.get(expr.val).isBoolean()) {
            print("WHILE_" + wh + " LD A,(" + expr.val + ")");
            print("CP 00H");
          } else {
            Errors.isNot(Type.BOOL);
          }
        } else {
          Errors.notDeclared(expr.val);
        }
      } else {
        print("WHILE_" + wh + " LD A," + expr.asBoolean());
        print("CP 00H");
      }
    } else {
      print("WHILE_" + wh + " NOP");
      this.visit(ctx.expression_b());
      print("CP 00H");
    }

    print("JP Z,END_WHILE_" + wh);
    this.visit(ctx.block());
    print("JP WHILE_" + wh);
    print("END_WHILE_" + wh + " NOP");
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
        if (SymbolsTable.containsKey(expr_aux.val)) {
          ensure_global(expr_aux.val);
          if (SymbolsTable.get(expr_aux.val).isBoolean()) {
            print("IF_" + if_n + " LD A,(" + expr_aux.val + ")");
            print("CP 00H");
          } else {
            Errors.isNot(Type.BOOL);
          }
        } else {
          Errors.notDeclared(expr_aux.val);
        }
      } else {
        print("IF_" + if_n + " LD A," + expr_aux.asBoolean());
        print("CP 00H");
      }
    } else {
      print("IF_" + if_n + " NOP");
      this.visit(ctx.expression_b(0));
      print("CP 00H");
    }

    if (expr.size() > 1) {
      elif_n = elif_num++;
      print("JP Z,ELIF_" + elif_n);
      this.visit(ctx.block(0));
      print("JP END_IF_" + if_n);

      for (int i = 1; i < expr.size()-1; i++) {

        if (ctx.expression_b(i).getChildCount() == 1) {
          expr_aux = this.visit(ctx.expression_b(i));
          if (expr_aux.isId()) {
            if (SymbolsTable.containsKey(expr_aux.val)) {
              ensure_global(expr_aux.val);
              if (SymbolsTable.get(expr_aux.val).isBoolean()) {
                print("ELIF_" + elif_n + " LD A,(" + expr_aux.val + ")");
                print("CP 00H");
              } else {
                Errors.isNot(Type.BOOL);
              }
            } else {
              Errors.notDeclared(expr_aux.val);
            }
          } else {
            print("ELIF_" + elif_n + " LD A," + expr_aux.asBoolean());
            print("CP 00H");
          }
        } else {
          print("ELIF_" + elif_n + " NOP");
          this.visit(ctx.expression_b(i));
          print("CP 00H");
        }

        elif_n = elif_num++;
        print("JP Z,ELIF_" + elif_n);
        this.visit(ctx.block(i));
        print("JP END_IF_" + if_n);
      }
      if (ctx.expression_b(expr.size()-1).getChildCount() == 1) {
          expr_aux = this.visit(ctx.expression_b(expr.size()-1));
          if (expr_aux.isId()) {
            if (SymbolsTable.containsKey(expr_aux.val)) {
              ensure_global(expr_aux.val);
              if (SymbolsTable.get(expr_aux.val).isBoolean()) {
                print("ELIF_" + elif_n + " LD A,(" + expr_aux.val + ")");
                print("CP 00H");
              } else {
                Errors.isNot(Type.BOOL);
              }
            } else {
              Errors.notDeclared(expr_aux.val);
            }
          } else {
            print("ELIF_" + elif_n + " LD A," + expr_aux.asBoolean());
            print("CP 00H");
          }
        } else {
          print("ELIF_" + elif_n + " NOP");
          this.visit(ctx.expression_b(expr.size()-1));
          print("CP 00H");
        }

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
  public Type visitAssignation(bugParser.AssignationContext ctx) {
    String left = ctx.ID().getText();
    last = ctx.expression().getChild(2);
    Type right = this.visit(ctx.expression());


    if (right.type == Type.EXPR) {
      ensure_assign(left, right.auxType);
    } else if (right.type == Type.ID) {
        if(!SymbolsTable.containsKey(right.val)) {
          Errors.notDeclared(right.val);
        }
        ensure_assign(left, SymbolsTable.get(right.val).type);
    } else {
      ensure_assign(left, right.type);
    }

    switch(right.type) {
      case Type.ID:
         print("LD ("+ left + "),(" + right.val + ")");
        break;

      case Type.INT:
         print("LD ("+ left + "),"+ right.asInteger());
        break;

      case Type.BOOL:
         print("LD ("+ left + "),"+ right.asBoolean());
        break;

      case Type.EXPR:
         print("LD (" + left + "),A");
        break;

      case Type.NONE:
        Errors.buildError();
        break;
    }

    return new Type(Type.ID, SymbolsTable.get(left).type, left);
  }

  @Override
  public Type visitPrint(bugParser.PrintContext ctx) {
    Type op = this.visit(ctx.expression());
    if (op.isId()) {
      if (SymbolsTable.containsKey(op.val)) {
        ensure_global(op.val);
        print("LD A,(" + op.val + ")");
      } else {
        Errors.notDeclared(op.val);
      }
    } else if (op.isInteger()) {
      print("LD A," + op.asInteger());
    } else if (op.isBoolean()) {
      print("LD A," + op.asBoolean());
    }
    print("OUT");
    return null;
  }

  @Override
  public Type visitMultDivExpr_a(bugParser.MultDivExpr_aContext ctx) {
    boolean op = ctx.op.getType() == bugParser.MULT;
    if (op) add_mult = true;
    if (!op) add_div = true;
    String id = null, op_l = null;
    Type left = null, right = null;
    left = this.visit(ctx.expression_a(0));
    if (left.type == Type.EXPR && ctx.getChild(2).getChildCount() > 1) {
      id = Integer.toString(used.size());
      used.add(id);
      this.ensure_stack(id);
      print("LD (" + id + "),A");
    }
    right = this.visit(ctx.expression_a(1));

    // EXPR en IZQ y DER
    if (left.type == Type.EXPR && right.type == Type.EXPR) {
      if (left.auxType == right.auxType) {
        if (left.auxType == Type.INT) {
          op_l = used.pop();
          print("LD Ac,00H");
          print("LD D,(" + op_l + ")");
          print("LD E,A");
          print(op ? "CALL MULT" : "CALL DIV");
          print("LD A,Ac");
          return new Type(Type.EXPR, Type.INT, null);
        } else {
          Errors.operationNotSupported(left.auxType, right.auxType, ctx.op.getText());
        }
      } else {
        Errors.incompatibleTypes(left.auxType, right.auxType, ctx.op.getText());
      }
    } else if (left.type == Type.EXPR || right.type == Type.EXPR) {

    // EXPR en izquierda
      if (left.type == Type.EXPR) {
        if (right.type == Type.ID) {
          if (SymbolsTable.containsKey(right.val)) {
            ensure_global(right.val);
            if (SymbolsTable.get(right.val).type == left.auxType) {
              if (SymbolsTable.get(right.val).type == Type.INT) {
                print("LD Ac,00H");
                print("LD D,A");
                print("LD E,(" + right.val + ")");
                print(op ? "CALL MULT" : "CALL DIV");
                print("LD A,Ac");
                return new Type(Type.EXPR, Type.INT, null);
              } else {
                Errors.areNot(Type.INT);
              }
            } else {
              Errors.incompatibleTypes(left.auxType, SymbolsTable.get(right.val).type, ctx.op.getText());
            }
          }
        } else {
          if (right.type == Type.INT) {
            print("LD Ac,00H");
            print("LD D,A");
            print("LD E," + right.asInteger());
            print(op ? "CALL MULT" : "CALL DIV");
            print("LD A,Ac");
            return new Type(Type.EXPR, Type.INT, null);
          } else {
            Errors.incompatibleTypes(left.auxType, right.type, ctx.op.getText());
          }
        }
      } else {

      // EXPR en derecha
        if (left.type == Type.ID) {
          if (SymbolsTable.containsKey(left.val)) {
            ensure_global(left.val);
            if (SymbolsTable.get(left.val).type == right.auxType) {
              if (SymbolsTable.get(left.val).type == Type.INT) {
                print("LD Ac,00H");
                print("LD D,(" + left.val + ")");
                print("LD E,A");
                print(op ? "CALL MULT" : "CALL DIV");
                print("LD A,Ac");
                return new Type(Type.EXPR, Type.INT, null);
              } else {
                Errors.areNot(Type.INT);
              }
            } else {
              Errors.incompatibleTypes(SymbolsTable.get(left.val).type, right.auxType, ctx.op.getText());
            }
          }
        } else {
          if (left.type == Type.INT) {
            print("LD Ac,00H");
            print("LD D," + left.asInteger());
            print("LD E,A");
            print(op ? "CALL MULT" : "CALL DIV");
            print("LD A,Ac");
            return new Type(Type.EXPR, Type.INT, null);
          } else {
            Errors.incompatibleTypes(left.type, right.auxType, ctx.op.getText());
          }
        }
      }
    } else {
      // HOJAS
      if (left.type == Type.INT && right.type == Type.INT) {
        print("LD Ac,00H");
        print("LD D," + left.asInteger());
        print("LD E," + right.asInteger());
        print(op ? "CALL MULT" : "CALL DIV");
        print("LD A,Ac");
        return new Type(Type.EXPR, Type.INT, null);

      } else if (left.type == Type.INT && right.type == Type.ID) {
        if (SymbolsTable.containsKey(right.val)) {
          ensure_global(right.val);
          if (SymbolsTable.get(right.val).type == Type.INT) {
            print("LD Ac,00H");
            print("LD D," + left.asInteger());
            print("LD E,(" + right.val + ")");
            print(op ? "CALL MULT" : "CALL DIV");
            print("LD A,Ac");
            return new Type(Type.EXPR, Type.INT, null);
          } else {
            Errors.incompatibleTypes(left.type, SymbolsTable.get(right.val).type, ctx.op.getText());
          }
        } else {
          Errors.notDeclared(right.val);
        }

      } else if (left.type == Type.ID && right.type == Type.INT) {
        if (SymbolsTable.containsKey(left.val)) {
          ensure_global(left.val);
          if (SymbolsTable.get(left.val).type == Type.INT) {
            print("LD Ac,00H");
            print("LD D,(" + left.val + ")");
            print("LD E," + right.asInteger());
            print(op ? "CALL MULT" : "CALL DIV");
            print("LD A,Ac");
            return new Type(Type.EXPR, Type.INT, null);
          } else {
            Errors.incompatibleTypes(SymbolsTable.get(left.val).type, right.type, ctx.op.getText());
          }
        } else {
          Errors.notDeclared(left.val);
        }

      } else if (left.type == Type.ID && right.type == Type.ID) {
        if (SymbolsTable.containsKey(left.val)) {
          ensure_global(left.val);
          if (SymbolsTable.containsKey(right.val)) {
            ensure_global(right.val);
            if (SymbolsTable.get(left.val).type == SymbolsTable.get(right.val).type) {
              if (SymbolsTable.get(left.val).type == Type.INT) {
                print("LD Ac,00H");
                print("LD D,(" + left.val + ")");
                print("LD E,(" + right.val + ")");
                print(op ? "CALL MULT" : "CALL DIV");
                print("LD A,Ac");
                return new Type(Type.EXPR, Type.INT, null);
              } else {
                Errors.areNot(Type.INT);
              }
            } else {
              Errors.incompatibleTypes(SymbolsTable.get(left.val).type, SymbolsTable.get(right.val).type, ctx.op.getText());
            }
          } else {
            Errors.notDeclared(right.val);
          }
        } else {
          Errors.notDeclared(left.val);
        }
      }
    }
    return new Type(Type.NONE);
  }

  @Override
  public Type visitAddSubExpr_a(bugParser.AddSubExpr_aContext ctx) {
    boolean op = ctx.op.getType() == bugParser.PLUS;
    String id = null, op_l = null, op_r = null;
    Type left = null, right = null;
    left = this.visit(ctx.expression_a(0));
    if (left.type == Type.EXPR && ctx.getChild(2).getChildCount() > 1) {
      id = Integer.toString(used.size());
      used.add(id);
      this.ensure_stack(id);
      print("LD (" + id + "),A");
    }
    right = this.visit(ctx.expression_a(1));
    if (right.type == Type.EXPR && !op) {
      id = Integer.toString(used.size());
      used.add(id);
      this.ensure_stack(id);
      print("LD (" + id + "),A");
    }

    // EXPR en IZQ y DER
    if (left.type == Type.EXPR && right.type == Type.EXPR) {
      if (left.auxType == right.auxType) {
        if (left.auxType == Type.INT) {
          if (op) {
            op_l = used.pop();
             print("ADD A,(" + op_l + ")");
          } else {
            op_r = used.pop();
            op_l = used.pop();
             print("LD A,(" + op_l + ")");
             print("SUB (" + op_r + ")");
          }
          return new Type(Type.EXPR, Type.INT, null);
        } else {
          Errors.operationNotSupported(left.auxType, right.auxType, ctx.op.getText());
        }
      } else {
        Errors.incompatibleTypes(left.auxType, right.auxType, ctx.op.getText());
      }
    } else if (left.type == Type.EXPR || right.type == Type.EXPR) {

    // EXPR en izquierda
      if (left.type == Type.EXPR) {
        if (right.type == Type.ID) {
          if (SymbolsTable.containsKey(right.val)) {
            ensure_global(right.val);
            if (SymbolsTable.get(right.val).type == left.auxType) {
              if (SymbolsTable.get(right.val).type == Type.INT) {
                if (op) {
                   print("ADD A,(" + right.val + ")");
                } else {
                   print("SUB (" + right.val + ")");
                }
                return new Type(Type.EXPR, Type.INT, null);
              } else {
                Errors.areNot(Type.INT);
              }
            } else {
              Errors.incompatibleTypes(left.auxType, SymbolsTable.get(right.val).type, ctx.op.getText());
            }
          }
        } else {
          if (right.type == Type.INT) {
            if (op) {
               print("ADD A," + right.asInteger());
            } else {
               print("SUB " + right.asInteger());
            }
            return new Type(Type.EXPR, Type.INT, null);
          } else {
            Errors.incompatibleTypes(left.auxType, right.type, ctx.op.getText());
          }
        }
      } else {

      // EXPR en derecha
        if (left.type == Type.ID) {
          if (SymbolsTable.containsKey(left.val)) {
            ensure_global(left.val);
            if (SymbolsTable.get(left.val).type == right.auxType) {
              if (SymbolsTable.get(left.val).type == Type.INT) {
                if (op) {
                   print("ADD A,(" + left.val + ")");
                } else {
                  op_r = used.pop();
                   print("LD A,(" + left.val + ")" );
                   print("SUB (" + op_r + ")");
                }
                return new Type(Type.EXPR, Type.INT, null);
              } else {
                Errors.areNot(Type.INT);
              }
            } else {
              Errors.incompatibleTypes(SymbolsTable.get(left.val).type, right.auxType, ctx.op.getText());
            }
          }
        } else {
          if (left.type == Type.INT) {
            if (op) {
               print("ADD A," + left.asInteger());
            } else {
              op_r = used.pop();
               print("LD A," + left.asInteger());
               print("SUB (" + op_r + ")");
            }
            return new Type(Type.EXPR, Type.INT, null);
          } else {
            Errors.incompatibleTypes(left.type, right.auxType, ctx.op.getText());
          }
        }
      }
    } else {
      // HOJAS
      if (left.type == Type.INT && right.type == Type.INT) {
         print("LD A," + left.asInteger());
        if (op) {
           print("ADD A," + right.asInteger());
        } else {
           print("SUB " + right.asInteger());
        }
        return new Type(Type.EXPR, Type.INT, null);


      } else if (left.type == Type.INT && right.type == Type.ID) {
        if (SymbolsTable.containsKey(right.val)) {
          ensure_global(right.val);
          if (SymbolsTable.get(right.val).type == Type.INT) {
             print("LD A," + left.asInteger());
            if (op) {
               print("ADD A,(" + right.val + ")");
            } else {
               print("SUB (" + right.val + ")");
            }
            return new Type(Type.EXPR, Type.INT, null);
          } else {
            Errors.incompatibleTypes(left.type, SymbolsTable.get(right.val).type, ctx.op.getText());
          }
        } else {
          Errors.notDeclared(right.val);
        }


      } else if (left.type == Type.ID && right.type == Type.INT) {
        if (SymbolsTable.containsKey(left.val)) {
          ensure_global(left.val);
          if (SymbolsTable.get(left.val).type == Type.INT) {
             print("LD A,(" + left.val + ")");
            if (op) {
               print("ADD A," + right.asInteger());
            } else {
               print("SUB " + right.asInteger());
            }
            return new Type(Type.EXPR, Type.INT, null);
          } else {
            Errors.incompatibleTypes(SymbolsTable.get(left.val).type, right.type, ctx.op.getText());
          }
        } else {
          Errors.notDeclared(left.val);
        }


      } else if (left.type == Type.ID && right.type == Type.ID) {
        if (SymbolsTable.containsKey(left.val)) {
          ensure_global(left.val);
          if (SymbolsTable.containsKey(right.val)) {
            ensure_global(right.val);
            if (SymbolsTable.get(left.val).type == SymbolsTable.get(right.val).type) {
              if (SymbolsTable.get(left.val).type == Type.INT) {
                 print("LD A,(" + left.val + ")");
                if (op) {
                   print("ADD A,(" + right.val + ")");
                } else {
                   print("SUB (" + right.val + ")");
                }
                return new Type(Type.EXPR, Type.INT, null);
              } else {
                Errors.areNot(Type.INT);
              }
            } else {
              Errors.incompatibleTypes(SymbolsTable.get(left.val).type, SymbolsTable.get(right.val).type, ctx.op.getText());
            }
          } else {
            Errors.notDeclared(right.val);
          }
        } else {
          Errors.notDeclared(left.val);
        }
      }
    }
    return new Type(Type.NONE);
  }

  @Override
  public Type visitNegExpr_a(bugParser.NegExpr_aContext ctx) {
    Type op = this.visit(ctx.expression_a());
    if (op.type == Type.BOOL) {
      Errors.isNot(Type.INT);
    } else if (op.type == Type.INT) {
      print("LD A," + op.asInteger());
      print("NEG");
      return new Type(Type.EXPR, Type.INT, null);
    } else if (op.type == Type.ID) {
      if (SymbolsTable.containsKey(op.val)) {
        ensure_global(op.val);
        if (SymbolsTable.get(op.val).type == Type.INT) {
          print("LD A,(" + op.val + ")");
          print("NEG");
          return new Type(Type.EXPR, Type.INT, null);
        } else {
          Errors.isNot(Type.INT);
        }
      } else {
        Errors.notDeclared(op.val);
      }
    } else if (op.type == Type.EXPR) {
      if (op.auxType == Type.INT) {
        print("NEG");
        return new Type(Type.EXPR, Type.INT, null);
      } else {
        Errors.isNot(Type.INT);
      }
    }
    return new Type(Type.NONE);
  }

  @Override
  public Type visitIntegerType(bugParser.IntegerTypeContext ctx) {
    return new Type(Type.INT, ctx.INT().getText());
  }

  @Override
  public Type visitIntegerId(bugParser.IntegerIdContext ctx) {
    return new Type(Type.ID, Type.INT, ctx.ID().getText());
  }

  @Override
  public Type visitCompExpr(bugParser.CompExprContext ctx) {
    int op = ctx.op.getType();
    int jp1 = 0, jp2 = 0;
    String id = null, op_l = null, op_r = null;
    Type left = null, right = null;
    left = this.visit(ctx.expression_a(0));
    if (left.type == Type.EXPR && ctx.getChild(2).getChildCount() > 1) {
      id = Integer.toString(used.size());
      used.add(id);
      this.ensure_stack(id);
      print("LD (" + id + "),A");
    }

    right = this.visit(ctx.expression_a(1));
    if (right.type == Type.EXPR && (op == bugParser.GT || op == bugParser.LT)) {
      id = Integer.toString(used.size());
      used.add(id);
      this.ensure_stack(id);
      print("LD (" + id + "),A");
    }

    // EXPR en IZQ y DER
    if (left.type == Type.EXPR && right.type == Type.EXPR) {
      if (left.auxType == right.auxType) {
        if (left.auxType == Type.INT) {
          switch(op) {
            case bugParser.GT:
              op_r = used.pop();
              op_l = used.pop();
              jp1 = jp_num++;
              jp2 = jp_num++;
              print("LD A,(" + op_l + ")");
              print("CP (" + op_r + ")");
              print("JP C,JUMP_" + jp1); //1 -> 5
              print("JP Z,JUMP_" + jp1);  //2 -> 5
              print("LD A,01H");  // 3
              print("JP JUMP_" + jp2);  // 4 -> 6
              print("JUMP_" + jp1 + " LD A,00H");  //5
              print("JUMP_" + jp2 + " NOP");
              break;

            case bugParser.LT:
              op_r = used.pop();
              op_l = used.pop();
              jp1 = jp_num++;
              jp2 = jp_num++;
              print("LD A,(" + op_l + ")");
              print("CP (" + op_r + ")");
              print("JP C,JUMP_" + jp1); //1 -> 4
              print("LD A,00H");  // 2
              print("JP JUMP_" + jp2);  // 3 -> 5
              print("JUMP_" + jp1 + " LD A,01H");  // 4
              print("JUMP_" + jp2 + " NOP"); // 5
              break;

            case bugParser.GE:
              jp1 = jp_num++;
              jp2 = jp_num++;
              op_l = used.pop();
              print("LD B,A");
              print("DEC B");
              print("LD A,(" + op_l + ")");
              print("CP B");
              print("JP C,JUMP_" + jp1); //1 -> 5
              print("JP Z,JUMP_" + jp1);  //2 -> 5
              print("LD A,01H");  // 3
              print("JP JUMP_" + jp2);  // 4 -> 6
              print("JUMP_" + jp1 + " LD A,00H");  //5
              print("JUMP_" + jp2 + " NOP");
              break;

            case bugParser.LE:
              jp1 = jp_num++;
              jp2 = jp_num++;
              op_l = used.pop();
              print("LD B,A");
              print("INC B");
              print("LD A,(" + op_l + ")");
              print("CP B");
              print("JP C,JUMP_" + jp1);
              print("LD A,00H");
              print("JP JUMP_" + jp2);
              print("JUMP_" + jp1 + " LD A,01H");
              print("JUMP_" + jp2 + " NOP");
              break;
          }

          return new Type(Type.EXPR, Type.BOOL);
        } else {
          Errors.operationNotSupported(left.auxType, right.auxType, ctx.op.getText());
        }
      } else {
        Errors.incompatibleTypes(left.auxType, right.auxType, ctx.op.getText());
      }
    } else if (left.type == Type.EXPR || right.type == Type.EXPR) {

    // EXPR en izquierda
      if (left.type == Type.EXPR) {
        if (right.type == Type.ID) {
          if (SymbolsTable.containsKey(right.val)) {
            ensure_global(right.val);
            if (SymbolsTable.get(right.val).type == left.auxType) {
              if (SymbolsTable.get(right.val).type == Type.INT) {
                switch(op) {
                  case bugParser.GT:
                    jp1 = jp_num++;
                    jp2 = jp_num++;
                    print("CP (" + right.val + ")");
                    print("JP C,JUMP_" + jp1); //1 -> 5
                    print("JP Z,JUMP_" + jp1);  //2 -> 5
                    print("LD A,01H");  // 3
                    print("JP JUMP_" + jp2);  // 4 -> 6
                    print("JUMP_" + jp1 + " LD A,00H");  //5
                    print("JUMP_" + jp2 + " NOP");
                    break;

                  case bugParser.LT:
                    jp1 = jp_num++;
                    jp2 = jp_num++;
                    print("CP (" + right.val + ")");
                    print("JP C,JUMP_" + jp1); //1 -> 4
                    print("LD A,00H");  // 2
                    print("JP JUMP_" + jp2);  // 3 -> 5
                    print("JUMP_" + jp1 + " LD A,01H");  // 4
                    print("JUMP_" + jp2 + " NOP"); // 5
                    break;

                  case bugParser.GE:
                    jp1 = jp_num++;
                    jp2 = jp_num++;
                    print("LD B,(" + right.val + ")");
                    print("DEC B");
                    print("CP B");
                    print("JP C,JUMP_" + jp1); //1 -> 5
                    print("JP Z,JUMP_" + jp1);  //2 -> 5
                    print("LD A,01H");  // 3
                    print("JP JUMP_" + jp2);  // 4 -> 6
                    print("JUMP_" + jp1 + " LD A,00H");  //5
                    print("JUMP_" + jp2 + " NOP");
                    break;

                  case bugParser.LE:
                    jp1 = jp_num++;
                    jp2 = jp_num++;
                    print("LD B,(" + right.val + ")");
                    print("INC B");
                    print("CP B");
                    print("JP C,JUMP_" + jp1);
                    print("LD A,00H");
                    print("JP JUMP_" + jp2);
                    print("JUMP_" + jp1 + " LD A,01H");
                    print("JUMP_" + jp2 + " NOP");
                    break;
                }

                return new Type(Type.EXPR, Type.BOOL);
              } else {
                Errors.areNot(Type.INT);
              }
            } else {
              Errors.incompatibleTypes(left.auxType, SymbolsTable.get(right.val).type, ctx.op.getText());
            }
          }
        } else {
          if (right.type == Type.INT) {
            switch(op) {
              case bugParser.GT:
                jp1 = jp_num++;
                jp2 = jp_num++;
                print("CP " + right.asInteger());
                print("JP C,JUMP_" + jp1); //1 -> 5
                print("JP Z,JUMP_" + jp1);  //2 -> 5
                print("LD A,01H");  // 3
                print("JP JUMP_" + jp2);  // 4 -> 6
                print("JUMP_" + jp1 + " LD A,00H");  //5
                print("JUMP_" + jp2 + " NOP");
                break;

              case bugParser.LT:
                jp1 = jp_num++;
                jp2 = jp_num++;
                print("CP " + right.asInteger());
                print("JP C,JUMP_" + jp1); //1 -> 4
                print("LD A,00H");  // 2
                print("JP JUMP_" + jp2);  // 3 -> 5
                print("JUMP_" + jp1 + " LD A,01H");  // 4
                print("JUMP_" + jp2 + " NOP"); // 5
                break;

              case bugParser.GE:
                jp1 = jp_num++;
                jp2 = jp_num++;
                print("LD B," + right.asInteger());
                print("DEC B");
                print("CP B");
                print("JP C,JUMP_" + jp1); //1 -> 5
                print("JP Z,JUMP_" + jp1);  //2 -> 5
                print("LD A,01H");  // 3
                print("JP JUMP_" + jp2);  // 4 -> 6
                print("JUMP_" + jp1 + " LD A,00H");  //5
                print("JUMP_" + jp2 + " NOP");
                break;

              case bugParser.LE:
                jp1 = jp_num++;
                jp2 = jp_num++;
                print("LD B," + right.asInteger());
                print("INC B");
                print("CP B");
                print("JP C,JUMP_" + jp1);
                print("LD A,00H");
                print("JP JUMP_" + jp2);
                print("JUMP_" + jp1 + " LD A,01H");
                print("JUMP_" + jp2 + " NOP");
                break;
            }

            return new Type(Type.EXPR, Type.BOOL);
          } else {
            Errors.incompatibleTypes(left.auxType, right.type, ctx.op.getText());
          }
        }
      } else {

      // EXPR en derecha
        if (left.type == Type.ID) {
          if (SymbolsTable.containsKey(left.val)) {
            ensure_global(left.val);
            if (SymbolsTable.get(left.val).type == right.auxType) {
              if (SymbolsTable.get(left.val).type == Type.INT) {
                switch(op) {
                  case bugParser.GT:
                    op_r = used.pop();
                    jp1 = jp_num++;
                    jp2 = jp_num++;
                    print("LD A,(" + left.val + ")");
                    print("CP (" + op_r + ")");
                    print("JP C,JUMP_" + jp1); //1 -> 5
                    print("JP Z,JUMP_" + jp1);  //2 -> 5
                    print("LD A,01H");  // 3
                    print("JP JUMP_" + jp2);  // 4 -> 6
                    print("JUMP_" + jp1 + " LD A,00H");  //5
                    print("JUMP_" + jp2 + " NOP");
                    break;

                  case bugParser.LT:
                    op_r = used.pop();
                    jp1 = jp_num++;
                    jp2 = jp_num++;
                    print("LD A,(" + left.val + ")");
                    print("CP (" + op_r + ")");
                    print("JP C,JUMP_" + jp1); //1 -> 4
                    print("LD A,00H");  // 2
                    print("JP JUMP_" + jp2);  // 3 -> 5
                    print("JUMP_" + jp1 + " LD A,01H");  // 4
                    print("JUMP_" + jp2 + " NOP"); // 5
                    break;

                  case bugParser.GE:
                    jp1 = jp_num++;
                    jp2 = jp_num++;
                    print("LD B,A");
                    print("DEC B");
                    print("LD A,(" + left.val + ")");
                    print("CP B");
                    print("JP C,JUMP_" + jp1); //1 -> 5
                    print("JP Z,JUMP_" + jp1);  //2 -> 5
                    print("LD A,01H");  // 3
                    print("JP JUMP_" + jp2);  // 4 -> 6
                    print("JUMP_" + jp1 + " LD A,00H");  //5
                    print("JUMP_" + jp2 + " NOP");
                    break;

                  case bugParser.LE:
                    jp1 = jp_num++;
                    jp2 = jp_num++;
                    print("LD B,A");
                    print("INC B");
                    print("LD A,(" + left.val + ")");
                    print("CP B");
                    print("JP C,JUMP_" + jp1);
                    print("LD A,00H");
                    print("JP JUMP_" + jp2);
                    print("JUMP_" + jp1 + " LD A,01H");
                    print("JUMP_" + jp2 + " NOP");
                    break;
                }

                return new Type(Type.EXPR, Type.BOOL);
              } else {
                Errors.areNot(Type.INT);
              }
            } else {
              Errors.incompatibleTypes(SymbolsTable.get(left.val).type, right.auxType, ctx.op.getText());
            }
          }
        } else {
          if (left.type == Type.INT) {
            switch(op) {
              case bugParser.GT:
                op_r = used.pop();
                jp1 = jp_num++;
                jp2 = jp_num++;
                print("LD A," + left.asInteger());
                print("CP (" + op_r + ")");
                print("JP C,JUMP_" + jp1); //1 -> 5
                print("JP Z,JUMP_" + jp1);  //2 -> 5
                print("LD A,01H");  // 3
                print("JP JUMP_" + jp2);  // 4 -> 6
                print("JUMP_" + jp1 + " LD A,00H");  //5
                print("JUMP_" + jp2 + " NOP");
                break;

              case bugParser.LT:
                op_r = used.pop();
                jp1 = jp_num++;
                jp2 = jp_num++;
                print("LD A," + left.asInteger());
                print("CP (" + op_r + ")");
                print("JP C,JUMP_" + jp1); //1 -> 4
                print("LD A,00H");  // 2
                print("JP JUMP_" + jp2);  // 3 -> 5
                print("JUMP_" + jp1 + " LD A,01H");  // 4
                print("JUMP_" + jp2 + " NOP"); // 5
                break;

              case bugParser.GE:
                jp1 = jp_num++;
                jp2 = jp_num++;
                print("LD B,A");
                print("DEC B");
                print("LD A," + left.asInteger());
                print("CP B");
                print("JP C,JUMP_" + jp1); //1 -> 5
                print("JP Z,JUMP_" + jp1);  //2 -> 5
                print("LD A,01H");  // 3
                print("JP JUMP_" + jp2);  // 4 -> 6
                print("JUMP_" + jp1 + " LD A,00H");  //5
                print("JUMP_" + jp2 + " NOP");
                break;

              case bugParser.LE:
                jp1 = jp_num++;
                jp2 = jp_num++;
                print("LD B,A");
                print("INC B");
                print("LD A," + left.asInteger());
                print("CP B");
                print("JP C,JUMP_" + jp1);
                print("LD A,00H");
                print("JP JUMP_" + jp2);
                print("JUMP_" + jp1 + " LD A,01H");
                print("JUMP_" + jp2 + " NOP");
                break;
            }
            return new Type(Type.EXPR, Type.BOOL);
          } else {
            Errors.incompatibleTypes(left.type, right.auxType, ctx.op.getText());
          }
        }
      }
    } else {

    // HOJAS
      if (left.type == Type.INT && right.type == Type.INT) {
        print("LD A," + left.asInteger());
        switch(op) {
          case bugParser.GT:
            jp1 = jp_num++;
            jp2 = jp_num++;
            print("CP " + right.asInteger());
            print("JP C,JUMP_" + jp1); //1 -> 5
            print("JP Z,JUMP_" + jp1);  //2 -> 5
            print("LD A,01H");  // 3
            print("JP JUMP_" + jp2);  // 4 -> 6
            print("JUMP_" + jp1 + " LD A,00H");  //5
            print("JUMP_" + jp2 + " NOP");
            break;

          case bugParser.LT:
            jp1 = jp_num++;
            jp2 = jp_num++;
            print("CP " + right.asInteger());
            print("JP C,JUMP_" + jp1); //1 -> 4
            print("LD A,00H");  // 2
            print("JP JUMP_" + jp2);  // 3 -> 5
            print("JUMP_" + jp1 + " LD A,01H");  // 4
            print("JUMP_" + jp2 + " NOP"); // 5
            break;

          case bugParser.GE:
            jp1 = jp_num++;
            jp2 = jp_num++;
            print("LD B," + right.asInteger());
            print("DEC B");
            print("CP B");
            print("JP C,JUMP_" + jp1); //1 -> 5
            print("JP Z,JUMP_" + jp1);  //2 -> 5
            print("LD A,01H");  // 3
            print("JP JUMP_" + jp2);  // 4 -> 6
            print("JUMP_" + jp1 + " LD A,00H");  //5
            print("JUMP_" + jp2 + " NOP");
            break;

          case bugParser.LE:
            jp1 = jp_num++;
            jp2 = jp_num++;
            print("LD B," + right.asInteger());
            print("INC B");
            print("CP B");
            print("JP C,JUMP_" + jp1);
            print("LD A,00H");
            print("JP JUMP_" + jp2);
            print("JUMP_" + jp1 + " LD A,01H");
            print("JUMP_" + jp2 + " NOP");
            break;
        }
        return new Type(Type.EXPR, Type.BOOL);

      } else if (left.type == Type.INT && right.type == Type.ID) {
        if (SymbolsTable.containsKey(right.val)) {
          ensure_global(right.val);
          if (SymbolsTable.get(right.val).type == Type.INT) {
            print("LD A," + left.asInteger());
            switch(op) {
              case bugParser.GT:
                jp1 = jp_num++;
                jp2 = jp_num++;
                print("CP (" + right.val + ")");
                print("JP C,JUMP_" + jp1); //1 -> 5
                print("JP Z,JUMP_" + jp1);  //2 -> 5
                print("LD A,01H");  // 3
                print("JP JUMP_" + jp2);  // 4 -> 6
                print("JUMP_" + jp1 + " LD A,00H");  //5
                print("JUMP_" + jp2 + " NOP");
                break;

              case bugParser.LT:
                jp1 = jp_num++;
                jp2 = jp_num++;
                print("CP (" + right.val + ")");
                print("JP C,JUMP_" + jp1); //1 -> 4
                print("LD A,00H");  // 2
                print("JP JUMP_" + jp2);  // 3 -> 5
                print("JUMP_" + jp1 + " LD A,01H");  // 4
                print("JUMP_" + jp2 + " NOP"); // 5
                break;

              case bugParser.GE:
                jp1 = jp_num++;
                jp2 = jp_num++;
                print("LD B,(" + right.val + ")");
                print("DEC B");
                print("CP B");
                print("JP C,JUMP_" + jp1); //1 -> 5
                print("JP Z,JUMP_" + jp1);  //2 -> 5
                print("LD A,01H");  // 3
                print("JP JUMP_" + jp2);  // 4 -> 6
                print("JUMP_" + jp1 + " LD A,00H");  //5
                print("JUMP_" + jp2 + " NOP");
                break;

              case bugParser.LE:
                jp1 = jp_num++;
                jp2 = jp_num++;
                print("LD B,(" + right.val + ")");
                print("INC B");
                print("CP B");
                print("JP C,JUMP_" + jp1);
                print("LD A,00H");
                print("JP JUMP_" + jp2);
                print("JUMP_" + jp1 + " LD A,01H");
                print("JUMP_" + jp2 + " NOP");
                break;
            }

            return new Type(Type.EXPR, Type.BOOL);
          } else {
            Errors.incompatibleTypes(left.type, SymbolsTable.get(right.val).type, ctx.op.getText());
          }
        } else {
          Errors.notDeclared(right.val);
        }

      } else if (left.type == Type.ID && right.type == Type.INT) {
        if (SymbolsTable.containsKey(left.val)) {
          ensure_global(left.val);
          if (SymbolsTable.get(left.val).type == Type.INT) {
            print("LD A,(" + left.val + ")");
            switch(op) {
              case bugParser.GT:
                jp1 = jp_num++;
                jp2 = jp_num++;
                print("CP " + right.asInteger());
                print("JP C,JUMP_" + jp1); //1 -> 5
                print("JP Z,JUMP_" + jp1);  //2 -> 5
                print("LD A,01H");  // 3
                print("JP JUMP_" + jp2);  // 4 -> 6
                print("JUMP_" + jp1 + " LD A,00H");  //5
                print("JUMP_" + jp2 + " NOP");
                break;

              case bugParser.LT:
                jp1 = jp_num++;
                jp2 = jp_num++;
                print("CP " + right.asInteger());
                print("JP C,JUMP_" + jp1); //1 -> 4
                print("LD A,00H");  // 2
                print("JP JUMP_" + jp2);  // 3 -> 5
                print("JUMP_" + jp1 + " LD A,01H");  // 4
                print("JUMP_" + jp2 + " NOP"); // 5
                break;

              case bugParser.GE:
                jp1 = jp_num++;
                jp2 = jp_num++;
                print("LD B," + right.asInteger());
                print("DEC B");
                print("CP B");
                print("JP C,JUMP_" + jp1); //1 -> 5
                print("JP Z,JUMP_" + jp1);  //2 -> 5
                print("LD A,01H");  // 3
                print("JP JUMP_" + jp2);  // 4 -> 6
                print("JUMP_" + jp1 + " LD A,00H");  //5
                print("JUMP_" + jp2 + " NOP");
                break;

              case bugParser.LE:
                jp1 = jp_num++;
                jp2 = jp_num++;
                print("LD B," + right.asInteger());
                print("INC B");
                print("CP B");
                print("JP C,JUMP_" + jp1);
                print("LD A,00H");
                print("JP JUMP_" + jp2);
                print("JUMP_" + jp1 + " LD A,01H");
                print("JUMP_" + jp2 + " NOP");
                break;
            }

            return new Type(Type.EXPR, Type.BOOL);
          } else {
            Errors.incompatibleTypes(SymbolsTable.get(left.val).type, right.type, ctx.op.getText());
          }
        } else {
          Errors.notDeclared(left.val);
        }

      } else if (left.type == Type.ID && right.type == Type.ID) {
        if (SymbolsTable.containsKey(left.val)) {
          ensure_global(left.val);
          if (SymbolsTable.containsKey(right.val)) {
            ensure_global(right.val);
            if (SymbolsTable.get(left.val).type == SymbolsTable.get(right.val).type) {
              if (SymbolsTable.get(left.val).type == Type.INT) {
                print("LD A,(" + left.val + ")");
                switch(op) {
                  case bugParser.GT:
                    jp1 = jp_num++;
                    jp2 = jp_num++;
                    print("CP (" + right.val + ")");
                    print("JP C,JUMP_" + jp1); //1 -> 5
                    print("JP Z,JUMP_" + jp1);  //2 -> 5
                    print("LD A,01H");  // 3
                    print("JP JUMP_" + jp2);  // 4 -> 6
                    print("JUMP_" + jp1 + " LD A,00H");  //5
                    print("JUMP_" + jp2 + " NOP");
                    break;

                  case bugParser.LT:
                    jp1 = jp_num++;
                    jp2 = jp_num++;
                    print("CP (" + right.val + ")");
                    print("JP C,JUMP_" + jp1); //1 -> 4
                    print("LD A,00H");  // 2
                    print("JP JUMP_" + jp2);  // 3 -> 5
                    print("JUMP_" + jp1 + " LD A,01H");  // 4
                    print("JUMP_" + jp2 + " NOP"); // 5
                    break;

                  case bugParser.GE:
                    jp1 = jp_num++;
                    jp2 = jp_num++;
                    print("LD B,(" + right.val + ")");
                    print("DEC B");
                    print("CP B");
                    print("JP C,JUMP_" + jp1); //1 -> 5
                    print("JP Z,JUMP_" + jp1);  //2 -> 5
                    print("LD A,01H");  // 3
                    print("JP JUMP_" + jp2);  // 4 -> 6
                    print("JUMP_" + jp1 + " LD A,00H");  //5
                    print("JUMP_" + jp2 + " NOP");
                    break;

                  case bugParser.LE:
                    jp1 = jp_num++;
                    jp2 = jp_num++;
                    print("LD B,(" + right.val + ")");
                    print("INC B");
                    print("CP B");
                    print("JP C,JUMP_" + jp1);
                    print("LD A,00H");
                    print("JP JUMP_" + jp2);
                    print("JUMP_" + jp1 + " LD A,01H");
                    print("JUMP_" + jp2 + " NOP");
                    break;
                }

                return new Type(Type.EXPR, Type.BOOL);
              } else {
                Errors.areNot(Type.INT);
              }
            } else {
              Errors.incompatibleTypes(SymbolsTable.get(left.val).type, SymbolsTable.get(right.val).type, ctx.op.getText());
            }
          } else {
            Errors.notDeclared(right.val);
          }
        } else {
          Errors.notDeclared(left.val);
        }
      }
    }
    return new Type(Type.NONE);
  }

  @Override
  public Type visitAndExpr_b(bugParser.AndExpr_bContext ctx) {
    String op = null, id = null;
    Type left = null, right = null;
    left = this.visit(ctx.expression_b(0));
    if (left.type == Type.EXPR && ctx.getChild(2).getChildCount() > 1) {
      id = Integer.toString(used.size());
      used.add(id);
      this.ensure_stack(id);
      print("LD (" + id + "),A");
    }
    right = this.visit(ctx.expression_b(1));

    // EXPR en IZQ y DER
    if (left.type == Type.EXPR && right.type == Type.EXPR) {
      if (left.auxType == right.auxType) {
        if (left.auxType == Type.BOOL) {
          op = used.pop();
          print("AND (" + op + ")");
          return new Type(Type.EXPR, Type.BOOL, null);
        } else {
          Errors.operationNotSupported(left.auxType, right.auxType, "&&");
        }
      } else {
        Errors.incompatibleTypes(left.auxType, right.auxType, "&&");
      }
    } else if (left.type == Type.EXPR || right.type == Type.EXPR) {

    // EXPR en izquierda
      if (left.type == Type.EXPR) {
        if (right.type == Type.ID) {
          if (SymbolsTable.containsKey(right.val)) {
            ensure_global(right.val);
            if (SymbolsTable.get(right.val).type == left.auxType) {
              if (SymbolsTable.get(right.val).type == Type.BOOL) {
                print("AND (" + right.val + ")");
                return new Type(Type.EXPR, Type.BOOL, null);
              } else {
                Errors.areNot(Type.BOOL);
              }
            } else {
              Errors.incompatibleTypes(left.auxType, SymbolsTable.get(right.val).type, "&&");
            }
          }
        } else {
          if (right.type == Type.BOOL) {
            print("AND " + right.asBoolean());
            return new Type(Type.EXPR, Type.BOOL, null);
          } else {
            Errors.incompatibleTypes(left.auxType, right.type, "&&");
          }
        }
      } else {

      // EXPR en derecha
        if (left.type == Type.ID) {
          if (SymbolsTable.containsKey(left.val)) {
            ensure_global(left.val);
            if (SymbolsTable.get(left.val).type == right.auxType) {
              if (SymbolsTable.get(left.val).type == Type.BOOL) {
                print("AND (" + left.val + ")");
                return new Type(Type.EXPR, Type.BOOL, null);
              } else {
                Errors.areNot(Type.BOOL);
              }
            } else {
              Errors.incompatibleTypes(SymbolsTable.get(left.val).type, right.auxType, "&&");
            }
          }
        } else {
          if (left.type == Type.BOOL) {
            print("AND " + left.asBoolean());
            return new Type(Type.EXPR, Type.BOOL, null);
          } else {
            Errors.incompatibleTypes(SymbolsTable.get(left.val).type, right.auxType, "&&");
          }
        }
      }
    } else {
      // HOJAS
      if (left.type == Type.BOOL && right.type == Type.BOOL) {
        print("LD A," + left.asBoolean());
        print("AND " + right.asBoolean());
        return new Type(Type.EXPR, Type.BOOL, null);

      } else if (left.type == Type.BOOL && right.type == Type.ID) {
        if (SymbolsTable.containsKey(right.val)) {
          ensure_global(right.val);
          if (SymbolsTable.get(right.val).type == Type.BOOL) {
            print("LD A," + left.asBoolean());
            print("AND (" + right.val + ")");
            return new Type(Type.EXPR, Type.BOOL, null);
          } else {
            Errors.incompatibleTypes(left.type, SymbolsTable.get(right.val).type, "&&");
          }
        } else {
          Errors.notDeclared(right.val);
        }

      } else if (left.type == Type.ID && right.type == Type.BOOL) {
        if (SymbolsTable.containsKey(left.val)) {
          ensure_global(left.val);
          if (SymbolsTable.get(left.val).type == Type.BOOL) {
            print("LD A,(" + left.val + ")");
            print("AND " + right.asBoolean());
            return new Type(Type.EXPR, Type.BOOL, null);
          } else {
            Errors.incompatibleTypes(SymbolsTable.get(left.val).type, right.type, "&&");
          }
        } else {
          Errors.notDeclared(left.val);
        }

      } else if (left.type == Type.ID && right.type == Type.ID) {
        if (SymbolsTable.containsKey(left.val)) {
          ensure_global(left.val);
          if (SymbolsTable.containsKey(right.val)) {
            ensure_global(right.val);
            if (SymbolsTable.get(left.val).type == SymbolsTable.get(right.val).type) {
              if (SymbolsTable.get(left.val).type == Type.BOOL) {
                print("LD A,(" + left.val + ")");
                print("AND (" + right.val + ")");
                return new Type(Type.EXPR, Type.BOOL, null);
              } else {
                Errors.areNot(Type.BOOL);
              }
            } else {
              Errors.incompatibleTypes(SymbolsTable.get(left.val).type, SymbolsTable.get(right.val).type, "&&");
            }
          } else {
            Errors.notDeclared(right.val);
          }
        } else {
          Errors.notDeclared(left.val);
        }
      }
    }
    return new Type(Type.NONE);
  }

  @Override
  public Type visitOrExpr_b(bugParser.OrExpr_bContext ctx) {
    String op = null, id = null;
    Type left = null, right = null;
    left = this.visit(ctx.expression_b(0));
    if (left.type == Type.EXPR && ctx.getChild(2).getChildCount() > 1) {
      id = Integer.toString(used.size());
      used.add(id);
      this.ensure_stack(id);
      print("LD (" + id + "),A");
    }
    right = this.visit(ctx.expression_b(1));

    // EXPR en IZQ y DER
    if (left.type == Type.EXPR && right.type == Type.EXPR) {
      if (left.auxType == right.auxType) {
        if (left.auxType == Type.BOOL) {
          op = used.pop();
          print("OR (" + op + ")");
          return new Type(Type.EXPR, Type.BOOL, null);
        } else {
          Errors.operationNotSupported(left.auxType, right.auxType, "&&");
        }
      } else {
        Errors.incompatibleTypes(left.auxType, right.auxType, "&&");
      }
    } else if (left.type == Type.EXPR || right.type == Type.EXPR) {

    // EXPR en izquierda
      if (left.type == Type.EXPR) {
        if (right.type == Type.ID) {
          if (SymbolsTable.containsKey(right.val)) {
            ensure_global(right.val);
            if (SymbolsTable.get(right.val).type == left.auxType) {
              if (SymbolsTable.get(right.val).type == Type.BOOL) {
                print("OR (" + right.val + ")");
                return new Type(Type.EXPR, Type.BOOL, null);
              } else {
                Errors.areNot(Type.BOOL);
              }
            } else {
              Errors.incompatibleTypes(left.auxType, SymbolsTable.get(right.val).type, "&&");
            }
          }
        } else {
          if (right.type == Type.BOOL) {
            print("OR " + right.asBoolean());
            return new Type(Type.EXPR, Type.BOOL, null);
          } else {
            Errors.incompatibleTypes(left.auxType, right.type, "&&");
          }
        }
      } else {

      // EXPR en derecha
        if (left.type == Type.ID) {
          if (SymbolsTable.containsKey(left.val)) {
            ensure_global(left.val);
            if (SymbolsTable.get(left.val).type == right.auxType) {
              if (SymbolsTable.get(left.val).type == Type.BOOL) {
                print("OR (" + left.val + ")");
                return new Type(Type.EXPR, Type.BOOL, null);
              } else {
                Errors.areNot(Type.BOOL);
              }
            } else {
              Errors.incompatibleTypes(SymbolsTable.get(left.val).type, right.auxType, "&&");
            }
          }
        } else {
          if (left.type == Type.BOOL) {
            print("OR " + left.asBoolean());
            return new Type(Type.EXPR, Type.BOOL, null);
          } else {
            Errors.incompatibleTypes(SymbolsTable.get(left.val).type, right.auxType, "&&");
          }
        }
      }
    } else {
      // HOJAS
      if (left.type == Type.BOOL && right.type == Type.BOOL) {
        print("LD A," + left.asBoolean());
        print("OR " + right.asBoolean());
        return new Type(Type.EXPR, Type.BOOL, null);

      } else if (left.type == Type.BOOL && right.type == Type.ID) {
        if (SymbolsTable.containsKey(right.val)) {
          ensure_global(right.val);
          if (SymbolsTable.get(right.val).type == Type.BOOL) {
            print("LD A," + left.asBoolean());
            print("OR (" + right.val + ")");
            return new Type(Type.EXPR, Type.BOOL, null);
          } else {
            Errors.incompatibleTypes(left.type, SymbolsTable.get(right.val).type, "&&");
          }
        } else {
          Errors.notDeclared(right.val);
        }

      } else if (left.type == Type.ID && right.type == Type.BOOL) {
        if (SymbolsTable.containsKey(left.val)) {
          ensure_global(left.val);
          if (SymbolsTable.get(left.val).type == Type.BOOL) {
            print("LD A,(" + left.val + ")");
            print("OR " + right.asBoolean());
            return new Type(Type.EXPR, Type.BOOL, null);
          } else {
            Errors.incompatibleTypes(SymbolsTable.get(left.val).type, right.type, "&&");
          }
        } else {
          Errors.notDeclared(left.val);
        }

      } else if (left.type == Type.ID && right.type == Type.ID) {
        if (SymbolsTable.containsKey(left.val)) {
          ensure_global(left.val);
          if (SymbolsTable.containsKey(right.val)) {
            ensure_global(right.val);
            if (SymbolsTable.get(left.val).type == SymbolsTable.get(right.val).type) {
              if (SymbolsTable.get(left.val).type == Type.BOOL) {
                print("LD A,(" + left.val + ")");
                print("OR (" + right.val + ")");
                return new Type(Type.EXPR, Type.BOOL, null);
              } else {
                Errors.areNot(Type.BOOL);
              }
            } else {
              Errors.incompatibleTypes(SymbolsTable.get(left.val).type, SymbolsTable.get(right.val).type, "&&");
            }
          } else {
            Errors.notDeclared(right.val);
          }
        } else {
          Errors.notDeclared(left.val);
        }
      }
    }
    return new Type(Type.NONE);
  }

  @Override
  public Type visitBooleanType(bugParser.BooleanTypeContext ctx) {
    return new Type(Type.BOOL, ctx.value.getText());
  }

  @Override
  public Type visitEqNeqExpr_b(bugParser.EqNeqExpr_bContext ctx) {
    boolean op = ctx.op.getType() == bugParser.EQ;
    int jp1 = 0, jp2 = 0;
    String id = null, op_l = null;
    Type left = null, right = null;
    left = this.visit(ctx.expression_b(0));
    if (left.type == Type.EXPR && ctx.getChild(2).getChildCount() > 1) {
      id = Integer.toString(used.size());
      used.add(id);
      this.ensure_stack(id);
      print("LD (" + id + "),A");
    }
    right = this.visit(ctx.expression_b(1));

    // EXPR en IZQ y DER
    if (left.type == Type.EXPR && right.type == Type.EXPR) {
      if (left.auxType == right.auxType) {
        if (left.auxType == Type.BOOL) {
          op_l = used.pop();
          jp1 = jp_num++;
          jp2 = jp_num++;
          print("CP (" + op_l + ")");
          print("JP Z,JUMP_" + jp1);
          print(op ? "LD A,00H" : "LD A,01H");
          print("JP JUMP_" + jp2);
          print(op ? "JUMP_" + jp1 + " LD A,01H" : "JUMP_" + jp1 + " LD A,00H");
          print("JUMP_" + jp2 + " NOP");
          return new Type(Type.EXPR, Type.BOOL);
        } else {
          Errors.operationNotSupported(left.auxType, right.auxType, ctx.op.getText());
        }
      } else {
        Errors.incompatibleTypes(left.auxType, right.auxType, ctx.op.getText());
      }
    } else if (left.type == Type.EXPR || right.type == Type.EXPR) {

    // EXPR en izquierda
      if (left.type == Type.EXPR) {
        if (right.type == Type.ID) {
          if (SymbolsTable.containsKey(right.val)) {
            ensure_global(right.val);
            if (SymbolsTable.get(right.val).type == left.auxType) {
              if (SymbolsTable.get(right.val).type == Type.BOOL) {
                jp1 = jp_num++;
                jp2 = jp_num++;
                print("CP (" + right.val + ")");
                print("JP Z,JUMP_" + jp1);
                print(op ? "LD A,00H" : "LD A,01H");
                print("JP JUMP_" + jp2);
                print(op ? "JUMP_" + jp1 + " LD A,01H" : "JUMP_" + jp1 + " LD A,00H");
                print("JUMP_" + jp2 + " NOP");
                return new Type(Type.EXPR, Type.BOOL);
              } else {
                Errors.areNot(Type.INT);
              }
            } else {
              Errors.incompatibleTypes(left.auxType, SymbolsTable.get(right.val).type, ctx.op.getText());
            }
          }
        } else {
          if (right.type == Type.BOOL) {
            jp1 = jp_num++;
            jp2 = jp_num++;
            print("CP " + right.asBoolean());
            print("JP Z,JUMP_" + jp1);
            print(op ? "LD A,00H" : "LD A,01H");
            print("JP JUMP_" + jp2);
            print(op ? "JUMP_" + jp1 + " LD A,01H" : "JUMP_" + jp1 + " LD A,00H");
            print("JUMP_" + jp2 + " NOP");
            return new Type(Type.EXPR, Type.BOOL);
          } else {
            Errors.incompatibleTypes(left.auxType, right.type, ctx.op.getText());
          }
        }
      } else {

      // EXPR en derecha
        if (left.type == Type.ID) {
          if (SymbolsTable.containsKey(left.val)) {
            ensure_global(left.val);
            if (SymbolsTable.get(left.val).type == right.auxType) {
              if (SymbolsTable.get(left.val).type == Type.BOOL) {
                jp1 = jp_num++;
                jp2 = jp_num++;
                print("CP (" + left.val + ")");
                print("JP Z,JUMP_" + jp1);
                print(op ? "LD A,00H" : "LD A,01H");
                print("JP JUMP_" + jp2);
                print(op ? "JUMP_" + jp1 + " LD A,01H" : "JUMP_" + jp1 + " LD A,00H");
                print("JUMP_" + jp2 + " NOP");
                return new Type(Type.EXPR, Type.BOOL);
              } else {
                Errors.areNot(Type.INT);
              }
            } else {
              Errors.incompatibleTypes(SymbolsTable.get(left.val).type, right.auxType, ctx.op.getText());
            }
          }
        } else {
          if (left.type == Type.BOOL) {
            jp1 = jp_num++;
            jp2 = jp_num++;
            print("CP " + left.asBoolean());
            print("JP Z,JUMP_" + jp1);
            print(op ? "LD A,00H" : "LD A,01H");
            print("JP JUMP_" + jp2);
            print(op ? "JUMP_" + jp1 + " LD A,01H" : "JUMP_" + jp1 + " LD A,00H");
            print("JUMP_" + jp2 + " NOP");
            return new Type(Type.EXPR, Type.BOOL);
          } else {
            Errors.incompatibleTypes(left.type, right.auxType, ctx.op.getText());
          }
        }
      }
    } else {
      // HOJAS
      if (left.type == Type.BOOL && right.type == Type.BOOL) {
        jp1 = jp_num++;
        jp2 = jp_num++;
        print("LD A," + left.asBoolean());
        print("CP " + right.asBoolean());
        print("JP Z,JUMP_" + jp1);
        print(op ? "LD A,00H" : "LD A,01H");
        print("JP JUMP_" + jp2);
        print(op ? "JUMP_" + jp1 + " LD A,01H" : "JUMP_" + jp1 + " LD A,00H");
        print("JUMP_" + jp2 + " NOP");
        return new Type(Type.EXPR, Type.BOOL);

      } else if (left.type == Type.BOOL && right.type == Type.ID) {
        if (SymbolsTable.containsKey(right.val)) {
          ensure_global(right.val);
          if (SymbolsTable.get(right.val).type == Type.BOOL) {
            jp1 = jp_num++;
            jp2 = jp_num++;
            print("LD A," + left.asBoolean());
            print("CP (" + right.val + ")");
            print("JP Z,JUMP_" + jp1);
            print(op ? "LD A,00H" : "LD A,01H");
            print("JP JUMP_" + jp2);
            print(op ? "JUMP_" + jp1 + " LD A,01H" : "JUMP_" + jp1 + " LD A,00H");
            print("JUMP_" + jp2 + " NOP");

            return new Type(Type.EXPR, Type.BOOL);
          } else {
            Errors.incompatibleTypes(left.type, SymbolsTable.get(right.val).type, ctx.op.getText());
          }
        } else {
          Errors.notDeclared(right.val);
        }

      } else if (left.type == Type.ID && right.type == Type.BOOL) {
        if (SymbolsTable.containsKey(left.val)) {
          ensure_global(left.val);
          if (SymbolsTable.get(left.val).type == Type.BOOL) {
            jp1 = jp_num++;
            jp2 = jp_num++;
            print("LD A,(" + left.val + ")");
            print("CP " + right.asBoolean());
            print("JP Z,JUMP_" + jp1);
            print(op ? "LD A,00H" : "LD A,01H");
            print("JP JUMP_" + jp2);
            print(op ? "JUMP_" + jp1 + " LD A,01H" : "JUMP_" + jp1 + " LD A,00H");
            print("JUMP_" + jp2 + " NOP");
            return new Type(Type.EXPR, Type.BOOL);
          } else {
            Errors.incompatibleTypes(SymbolsTable.get(left.val).type, right.type, ctx.op.getText());
          }
        } else {
          Errors.notDeclared(left.val);
        }


      } else if (left.type == Type.ID && right.type == Type.ID) {
        if (SymbolsTable.containsKey(left.val)) {
          ensure_global(left.val);
          if (SymbolsTable.containsKey(right.val)) {
            ensure_global(right.val);
            if (SymbolsTable.get(left.val).type == SymbolsTable.get(right.val).type) {
              if (SymbolsTable.get(left.val).type == Type.BOOL) {
                jp1 = jp_num++;
                jp2 = jp_num++;
                print("LD A,(" + left.val + ")");
                print("CP (" + right.val + ")");
                print("JP Z,JUMP_" + jp1);
                print(op ? "LD A,00H" : "LD A,01H");
                print("JP JUMP_" + jp2);
                print(op ? "JUMP_" + jp1 + " LD A,01H" : "JUMP_" + jp1 + " LD A,00H");
                print("JUMP_" + jp2 + " NOP");

                return new Type(Type.EXPR, Type.BOOL);
              } else {
                Errors.areNot(Type.BOOL);
              }
            } else {
              Errors.incompatibleTypes(SymbolsTable.get(left.val).type, SymbolsTable.get(right.val).type, ctx.op.getText());
            }
          } else {
            Errors.notDeclared(right.val);
          }
        } else {
          Errors.notDeclared(left.val);
        }
      }
    }
    return new Type(Type.NONE);
  }

  @Override
  public Type visitEqNeqExpr_a(bugParser.EqNeqExpr_aContext ctx) {
    boolean op = ctx.op.getType() == bugParser.EQ;
    int jp1 = 0, jp2 = 0;
    String id = null, op_l = null;
    Type left = null, right = null;
    left = this.visit(ctx.expression_a(0));
    if (left.type == Type.EXPR && ctx.getChild(2).getChildCount() > 1) {
      id = Integer.toString(used.size());
      used.add(id);
      this.ensure_stack(id);
      print("LD (" + id + "),A");
    }
    right = this.visit(ctx.expression_a(1));

    // EXPR en IZQ y DER
    if (left.type == Type.EXPR && right.type == Type.EXPR) {
      if (left.auxType == right.auxType) {
        if (left.auxType == Type.INT) {
          op_l = used.pop();
          jp1 = jp_num++;
          jp2 = jp_num++;
          print("CP (" + op_l + ")");
          print("JP Z,JUMP_" + jp1);
          print(op ? "LD A,00H" : "LD A,01H");
          print("JP JUMP_" + jp2);
          print(op ? "JUMP_" + jp1 + " LD A,01H" : "JUMP_" + jp1 + " LD A,00H");
          print("JUMP_" + jp2 + " NOP");
          return new Type(Type.EXPR, Type.BOOL);
        } else {
          Errors.operationNotSupported(left.auxType, right.auxType, ctx.op.getText());
        }
      } else {
        Errors.incompatibleTypes(left.auxType, right.auxType, ctx.op.getText());
      }
    } else if (left.type == Type.EXPR || right.type == Type.EXPR) {

    // EXPR en izquierda
      if (left.type == Type.EXPR) {
        if (right.type == Type.ID) {
          if (SymbolsTable.containsKey(right.val)) {
            ensure_global(right.val);
            if (SymbolsTable.get(right.val).type == left.auxType) {
              if (SymbolsTable.get(right.val).type == Type.INT) {
                jp1 = jp_num++;
                jp2 = jp_num++;
                print("CP (" + right.val + ")");
                print("JP Z,JUMP_" + jp1);
                print(op ? "LD A,00H" : "LD A,01H");
                print("JP JUMP_" + jp2);
                print(op ? "JUMP_" + jp1 + " LD A,01H" : "JUMP_" + jp1 + " LD A,00H");
                print("JUMP_" + jp2 + " NOP");
                return new Type(Type.EXPR, Type.BOOL);
              } else {
                Errors.areNot(Type.INT);
              }
            } else {
              Errors.incompatibleTypes(left.auxType, SymbolsTable.get(right.val).type, ctx.op.getText());
            }
          }
        } else {
          if (right.type == Type.INT) {
            jp1 = jp_num++;
            jp2 = jp_num++;
            print("CP " + right.asInteger());
            print("JP Z,JUMP_" + jp1);
            print(op ? "LD A,00H" : "LD A,01H");
            print("JP JUMP_" + jp2);
            print(op ? "JUMP_" + jp1 + " LD A,01H" : "JUMP_" + jp1 + " LD A,00H");
            print("JUMP_" + jp2 + " NOP");
            return new Type(Type.EXPR, Type.BOOL);
          } else {
            Errors.incompatibleTypes(left.auxType, right.type, ctx.op.getText());
          }
        }
      } else {

      // EXPR en derecha
        if (left.type == Type.ID) {
          if (SymbolsTable.containsKey(left.val)) {
            ensure_global(left.val);
            if (SymbolsTable.get(left.val).type == right.auxType) {
              if (SymbolsTable.get(left.val).type == Type.INT) {
                jp1 = jp_num++;
                jp2 = jp_num++;
                print("CP (" + left.val + ")");
                print("JP Z,JUMP_" + jp1);
                print(op ? "LD A,00H" : "LD A,01H");
                print("JP JUMP_" + jp2);
                print(op ? "JUMP_" + jp1 + " LD A,01H" : "JUMP_" + jp1 + " LD A,00H");
                print("JUMP_" + jp2 + " NOP");
                return new Type(Type.EXPR, Type.BOOL);
              } else {
                Errors.areNot(Type.INT);
              }
            } else {
              Errors.incompatibleTypes(SymbolsTable.get(left.val).type, right.auxType, ctx.op.getText());
            }
          }
        } else {
          if (left.type == Type.INT) {
            jp1 = jp_num++;
            jp2 = jp_num++;
            print("CP " + left.asInteger());
            print("JP Z,JUMP_" + jp1);
            print(op ? "LD A,00H" : "LD A,01H");
            print("JP JUMP_" + jp2);
            print(op ? "JUMP_" + jp1 + " LD A,01H" : "JUMP_" + jp1 + " LD A,00H");
            print("JUMP_" + jp2 + " NOP");
            return new Type(Type.EXPR, Type.BOOL);
          } else {
            Errors.incompatibleTypes(left.type, right.auxType, ctx.op.getText());
          }
        }
      }
    } else {
      // HOJAS
      if (left.type == Type.INT && right.type == Type.INT) {
        jp1 = jp_num++;
        jp2 = jp_num++;
        print("LD A," + left.asInteger());
        print("CP " + right.asInteger());
        print("JP Z,JUMP_" + jp1);
        print(op ? "LD A,00H" : "LD A,01H");
        print("JP JUMP_" + jp2);
        print(op ? "JUMP_" + jp1 + " LD A,01H" : "JUMP_" + jp1 + " LD A,00H");
        print("JUMP_" + jp2 + " NOP");
        return new Type(Type.EXPR, Type.BOOL);

      } else if (left.type == Type.INT && right.type == Type.ID) {
        if (SymbolsTable.containsKey(right.val)) {
          ensure_global(right.val);
          if (SymbolsTable.get(right.val).type == Type.INT) {
            jp1 = jp_num++;
            jp2 = jp_num++;
            print("LD A," + left.asInteger());
            print("CP (" + right.val + ")");
            print("JP Z,JUMP_" + jp1);
            print(op ? "LD A,00H" : "LD A,01H");
            print("JP JUMP_" + jp2);
            print(op ? "JUMP_" + jp1 + " LD A,01H" : "JUMP_" + jp1 + " LD A,00H");
            print("JUMP_" + jp2 + " NOP");

            return new Type(Type.EXPR, Type.BOOL);
          } else {
            Errors.incompatibleTypes(left.type, SymbolsTable.get(right.val).type, ctx.op.getText());
          }
        } else {
          Errors.notDeclared(right.val);
        }

      } else if (left.type == Type.ID && right.type == Type.INT) {
        if (SymbolsTable.containsKey(left.val)) {
          ensure_global(left.val);
          if (SymbolsTable.get(left.val).type == Type.INT) {
            jp1 = jp_num++;
            jp2 = jp_num++;
            print("LD A,(" + left.val + ")");
            print("CP " + right.asInteger());
            print("JP Z,JUMP_" + jp1);
            print(op ? "LD A,00H" : "LD A,01H");
            print("JP JUMP_" + jp2);
            print(op ? "JUMP_" + jp1 + " LD A,01H" : "JUMP_" + jp1 + " LD A,00H");
            print("JUMP_" + jp2 + " NOP");
            return new Type(Type.EXPR, Type.BOOL);
          } else {
            Errors.incompatibleTypes(SymbolsTable.get(left.val).type, right.type, ctx.op.getText());
          }
        } else {
          Errors.notDeclared(left.val);
        }


      } else if (left.type == Type.ID && right.type == Type.ID) {
        if (SymbolsTable.containsKey(left.val)) {
          ensure_global(left.val);
          if (SymbolsTable.containsKey(right.val)) {
            ensure_global(right.val);
            if (SymbolsTable.get(left.val).type == SymbolsTable.get(right.val).type) {
              jp1 = jp_num++;
              jp2 = jp_num++;
              print("LD A,(" + left.val + ")");
              print("CP (" + right.val + ")");
              print("JP Z,JUMP_" + jp1);
              print(op ? "LD A,00H" : "LD A,01H");
              print("JP JUMP_" + jp2);
              print(op ? "JUMP_" + jp1 + " LD A,01H" : "JUMP_" + jp1 + " LD A,00H");
              print("JUMP_" + jp2 + " NOP");

              return new Type(Type.EXPR, Type.BOOL);
            } else {
              Errors.incompatibleTypes(SymbolsTable.get(left.val).type, SymbolsTable.get(right.val).type, ctx.op.getText());
            }
          } else {
            Errors.notDeclared(right.val);
          }
        } else {
          Errors.notDeclared(left.val);
        }
      }
    }
    return new Type(Type.NONE);
  }

  @Override
  public Type visitBooleanId(bugParser.BooleanIdContext ctx) {
    return new Type(Type.ID, Type.BOOL, ctx.ID().getText());
  }

  @Override
  public Type visitNotExpr_b(bugParser.NotExpr_bContext ctx) {
    Type op = this.visit(ctx.expression_b());
    if (op.type == Type.INT) {
      Errors.isNot(Type.BOOL);
    } else if (op.type == Type.BOOL) {
      print("LD A," + op.asBoolean());
      print("XOR 01H");
      return new Type(Type.EXPR, Type.BOOL, null);
    } else if (op.type == Type.ID) {
      if (SymbolsTable.containsKey(op.val)) {
        ensure_global(op.val);
        if (SymbolsTable.get(op.val).type == Type.BOOL) {
          print("LD A,(" + op.val + ")");
          print("XOR 01H");
          return new Type(Type.EXPR, Type.BOOL, null);
        } else {
          Errors.isNot(Type.BOOL);
        }
      } else {
        Errors.notDeclared(op.val);
      }
    } else if (op.type == Type.EXPR) {
      if (op.auxType == Type.BOOL) {
        print("XOR 01H");
        return new Type(Type.EXPR, Type.BOOL, null);
      } else {
        Errors.isNot(Type.BOOL);
      }
    }
    return new Type(Type.NONE);
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
    int num_vars = SymbolsTable.size() + 5;
    String hex = Integer.toHexString(num_vars).toUpperCase();
    String aux = String.format("%1$4sH",hex).replace(" ", "0");
    String id_aux = null;
    StringBuilder sb = new StringBuilder();
    program.addFirst("ORG " +  aux);
    for (String instruction : program) {      
      if (instruction.contains("(")) {
        sb.delete(0, sb.length());
        // Primera variable
        sb.append(instruction.substring(0, instruction.indexOf('(')+1));
        id_aux = instruction.substring(instruction.indexOf('(')+1, instruction.indexOf(')'));
        sb.append(String.format("%1$4sH)",SymbolsTable.get(id_aux).val).replace(" ", "0"));
        
        // Posible segunda variable
        if (instruction.indexOf('(', instruction.indexOf(')')+1) > 0) {
          sb.append(",(");
          id_aux = instruction.substring(
            instruction.indexOf('(', instruction.indexOf(')')+1)+1,
            instruction.indexOf(')', instruction.indexOf(')')+1)             
          );
          sb.append(String.format("%1$4sH)",SymbolsTable.get(id_aux).val).replace(" ", "0"));
          
        // Resto de la instruccion si no hay segunda variable
        } else {
          sb.append(instruction.substring(instruction.indexOf(')')+1));
        }
        outFile.println(sb.toString());
      } else {
        outFile.println(instruction);
      }
    }
    outFile.println("END");
    outFile.close();
    return null;
  }
  
}
