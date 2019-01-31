/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bug;

import java.util.HashMap;
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

  private void ensure(String id) {
    if (!SymbolsTable.containsKey(id)) {
      SymbolsTable.put(id, null);
    }
  }

  private void print(String instruction) {
     System.out.println(instruction);
  }

  @Override
  public Type visitB_for(bugParser.B_forContext ctx) {
    return super.visitB_for(ctx); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Type visitB_while(bugParser.B_whileContext ctx) {
    return super.visitB_while(ctx); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Type visitB_if(bugParser.B_ifContext ctx) {
    return super.visitB_if(ctx); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Type visitAssignation(bugParser.AssignationContext ctx) {
    String left = ctx.ID().getText();
    last = ctx.expression().getChild(2);
    Type right = this.visit(ctx.expression());


    if (right.type == Type.EXPR) {
      SymbolsTable.put(left, new Type(right.auxType));
    } else if (right.type == Type.ID) {
        if(!SymbolsTable.containsKey(right.val)) {
          Errors.notDeclared(right.val);
        }
        SymbolsTable.put(left, new Type(SymbolsTable.get(right.val).type));
    } else {
      SymbolsTable.put(left, new Type(right.type));
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

    return new Type(Type.NONE);
  }

  @Override
  public Type visitPrint(bugParser.PrintContext ctx) {
    return super.visitPrint(ctx); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Type visitMultDivExpr_a(bugParser.MultDivExpr_aContext ctx) {
    return super.visitMultDivExpr_a(ctx); //To change body of generated methods, choose Tools | Templates.
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
      this.ensure(id);
      print("LD (" + id + "),A");
    }
    right = this.visit(ctx.expression_a(1));
    if (right.type == Type.EXPR && !op) {
      id = Integer.toString(used.size());
      used.add(id);
      this.ensure(id);
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
          if (SymbolsTable.containsKey(right.val)) {
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
      System.out.println("LD A," + op.asInteger());
      System.out.println("NEG");
      return new Type(Type.EXPR, Type.INT, null);
    } else if (op.type == Type.ID) {
      if (SymbolsTable.containsKey(op.val)) {
        if (SymbolsTable.get(op.val).type == Type.INT) {
          System.out.println("LD A,(" + op.val + ")");
          System.out.println("NEG");
          return new Type(Type.EXPR, Type.INT, null);
        } else {
          Errors.isNot(Type.INT);
        }
      } else {
        Errors.notDeclared(op.val);
      }
    } else if (op.type == Type.EXPR) {
      if (op.auxType == Type.INT) {
        System.out.println("NEG");
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
    Type left = null, right = null;
    left = this.visit(ctx.expression_a(0));
    right = this.visit(ctx.expression_a(1));

    if (left.type == Type.INT && right.type == Type.INT) {
      System.out.println("LD A," + left.asInteger());
      switch(op) {
        case bugParser.GT:
          jp1 = jp_num++;
          jp2 = jp_num++;
          System.out.println("CP " + right.asInteger());
          System.out.println("JP C,JUMP_" + jp1); //1 -> 5
          System.out.println("JP Z,JUMP_" + jp1);  //2 -> 5
          System.out.println("LD A,01H");  // 3
          System.out.println("JP JUMP_" + jp2);  // 4 -> 6
          System.out.println("JUMP_" + jp1 + " LD A,00H");  //5
          System.out.println("JUMP_" + jp2 + " ADD A,00H");
          break;

        case bugParser.LT:
          jp1 = jp_num++;
          jp2 = jp_num++;
          System.out.println("CP " + right.asInteger());
          System.out.println("JP C,JUMP_" + jp1); //1 -> 4
          System.out.println("LD A,00H");  // 2
          System.out.println("JP JUMP_" + jp2);  // 3 -> 5
          System.out.println("JUMP_" + jp1 + " LD A,01H");  // 4
          System.out.println("JUMP_" + jp2 + " ADD A,00H"); // 5
          break;

        case bugParser.GE:
          jp1 = jp_num++;
          jp2 = jp_num++;
          System.out.println("LD B," + right.asInteger());
          System.out.println("DEC B");
          System.out.println("CP B");
          System.out.println("JP C,JUMP_" + jp1); //1 -> 5
          System.out.println("JP Z,JUMP_" + jp1);  //2 -> 5
          System.out.println("LD A,01H");  // 3
          System.out.println("JP JUMP_" + jp2);  // 4 -> 6
          System.out.println("JUMP_" + jp1 + " LD A,00H");  //5
          System.out.println("JUMP_" + jp2 + " ADD A,00H");
          break;

        case bugParser.LE:
          jp1 = jp_num++;
          jp2 = jp_num++;
          System.out.println("LD B," + right.asInteger());
          System.out.println("INC B");
          System.out.println("CP B");
          System.out.println("JP C,JUMP_" + jp1);
          System.out.println("LD A,00H");
          System.out.println("JP JUMP_" + jp2);
          System.out.println("JUMP_" + jp1 + " LD A,01H");
          System.out.println("JUMP_" + jp2 + " ADD A,00H");
          break;
      }
      return new Type(Type.EXPR, Type.BOOL, null);

    } else if (left.type == Type.INT && right.type == Type.ID) {
      if (SymbolsTable.containsKey(right.val)) {
        if (SymbolsTable.get(right.val).type == Type.INT) {
          System.out.println("LD A," + left.asInteger());
          switch(op) {
            case bugParser.GT:
              jp1 = jp_num++;
              jp2 = jp_num++;
              System.out.println("CP (" + right.val + ")");
              System.out.println("JP C,JUMP_" + jp1); //1 -> 5
              System.out.println("JP Z,JUMP_" + jp1);  //2 -> 5
              System.out.println("LD A,01H");  // 3
              System.out.println("JP JUMP_" + jp2);  // 4 -> 6
              System.out.println("JUMP_" + jp1 + " LD A,00H");  //5
              System.out.println("JUMP_" + jp2 + " ADD A,00H");
              break;

            case bugParser.LT:
              jp1 = jp_num++;
              jp2 = jp_num++;
              System.out.println("CP (" + right.val + ")");
              System.out.println("JP C,JUMP_" + jp1); //1 -> 4
              System.out.println("LD A,00H");  // 2
              System.out.println("JP JUMP_" + jp2);  // 3 -> 5
              System.out.println("JUMP_" + jp1 + " LD A,01H");  // 4
              System.out.println("JUMP_" + jp2 + " ADD A,00H"); // 5
              break;

            case bugParser.GE:
              jp1 = jp_num++;
              jp2 = jp_num++;
              System.out.println("LD B,(" + right.val + ")");
              System.out.println("DEC B");
              System.out.println("CP B");
              System.out.println("JP C,JUMP_" + jp1); //1 -> 5
              System.out.println("JP Z,JUMP_" + jp1);  //2 -> 5
              System.out.println("LD A,01H");  // 3
              System.out.println("JP JUMP_" + jp2);  // 4 -> 6
              System.out.println("JUMP_" + jp1 + " LD A,00H");  //5
              System.out.println("JUMP_" + jp2 + " ADD A,00H");
              break;

            case bugParser.LE:
              jp1 = jp_num++;
              jp2 = jp_num++;
              System.out.println("LD B,(" + right.val + ")");
              System.out.println("INC B");
              System.out.println("CP B");
              System.out.println("JP C,JUMP_" + jp1);
              System.out.println("LD A,00H");
              System.out.println("JP JUMP_" + jp2);
              System.out.println("JUMP_" + jp1 + " LD A,01H");
              System.out.println("JUMP_" + jp2 + " ADD A,00H");
              break;
          }

          return new Type(Type.BOOL, null);
        } else {
          Errors.incompatibleTypes(left.type, SymbolsTable.get(right.val).type, ctx.op.getText());
        }
      } else {
        Errors.notDeclared(right.val);
      }

    } else if (left.type == Type.ID && right.type == Type.INT) {
      if (SymbolsTable.containsKey(left.val)) {
        if (SymbolsTable.get(left.val).type == Type.INT) {
          System.out.println("LD A,(" + left.val + ")");
          switch(op) {
            case bugParser.GT:
              jp1 = jp_num++;
              jp2 = jp_num++;
              System.out.println("CP " + right.asInteger());
              System.out.println("JP C,JUMP_" + jp1); //1 -> 5
              System.out.println("JP Z,JUMP_" + jp1);  //2 -> 5
              System.out.println("LD A,01H");  // 3
              System.out.println("JP JUMP_" + jp2);  // 4 -> 6
              System.out.println("JUMP_" + jp1 + " LD A,00H");  //5
              System.out.println("JUMP_" + jp2 + " ADD A,00H");
              break;

            case bugParser.LT:
              jp1 = jp_num++;
              jp2 = jp_num++;
              System.out.println("CP " + right.asInteger());
              System.out.println("JP C,JUMP_" + jp1); //1 -> 4
              System.out.println("LD A,00H");  // 2
              System.out.println("JP JUMP_" + jp2);  // 3 -> 5
              System.out.println("JUMP_" + jp1 + " LD A,01H");  // 4
              System.out.println("JUMP_" + jp2 + " ADD A,00H"); // 5
              break;

            case bugParser.GE:
              jp1 = jp_num++;
              jp2 = jp_num++;
              System.out.println("LD B," + right.asInteger());
              System.out.println("DEC B");
              System.out.println("CP B");
              System.out.println("JP C,JUMP_" + jp1); //1 -> 5
              System.out.println("JP Z,JUMP_" + jp1);  //2 -> 5
              System.out.println("LD A,01H");  // 3
              System.out.println("JP JUMP_" + jp2);  // 4 -> 6
              System.out.println("JUMP_" + jp1 + " LD A,00H");  //5
              System.out.println("JUMP_" + jp2 + " ADD A,00H");
              break;

            case bugParser.LE:
              jp1 = jp_num++;
              jp2 = jp_num++;
              System.out.println("LD B," + right.asInteger());
              System.out.println("INC B");
              System.out.println("CP B");
              System.out.println("JP C,JUMP_" + jp1);
              System.out.println("LD A,00H");
              System.out.println("JP JUMP_" + jp2);
              System.out.println("JUMP_" + jp1 + " LD A,01H");
              System.out.println("JUMP_" + jp2 + " ADD A,00H");
              break;
          }

          return new Type(Type.BOOL, null);
        } else {
          Errors.incompatibleTypes(SymbolsTable.get(left.val).type, right.type, ctx.op.getText());
        }
      } else {
        Errors.notDeclared(left.val);
      }


    } else if (left.type == Type.ID && right.type == Type.ID) {
      if (SymbolsTable.containsKey(left.val)) {
        if (SymbolsTable.containsKey(right.val)) {
          if (SymbolsTable.get(left.val).type == SymbolsTable.get(right.val).type) {
            if (SymbolsTable.get(left.val).type == Type.INT) {
              System.out.println("LD A,(" + left.val + ")");
              switch(op) {
                case bugParser.GT:
                  jp1 = jp_num++;
                  jp2 = jp_num++;
                  System.out.println("CP (" + right.val + ")");
                  System.out.println("JP C,JUMP_" + jp1); //1 -> 5
                  System.out.println("JP Z,JUMP_" + jp1);  //2 -> 5
                  System.out.println("LD A,01H");  // 3
                  System.out.println("JP JUMP_" + jp2);  // 4 -> 6
                  System.out.println("JUMP_" + jp1 + " LD A,00H");  //5
                  System.out.println("JUMP_" + jp2 + " ADD A,00H");
                  break;

                case bugParser.LT:
                  jp1 = jp_num++;
                  jp2 = jp_num++;
                  System.out.println("CP (" + right.val + ")");
                  System.out.println("JP C,JUMP_" + jp1); //1 -> 4
                  System.out.println("LD A,00H");  // 2
                  System.out.println("JP JUMP_" + jp2);  // 3 -> 5
                  System.out.println("JUMP_" + jp1 + " LD A,01H");  // 4
                  System.out.println("JUMP_" + jp2 + " ADD A,00H"); // 5
                  break;

                case bugParser.GE:
                  jp1 = jp_num++;
                  jp2 = jp_num++;
                  System.out.println("LD B,(" + right.val + ")");
                  System.out.println("DEC B");
                  System.out.println("CP B");
                  System.out.println("JP C,JUMP_" + jp1); //1 -> 5
                  System.out.println("JP Z,JUMP_" + jp1);  //2 -> 5
                  System.out.println("LD A,01H");  // 3
                  System.out.println("JP JUMP_" + jp2);  // 4 -> 6
                  System.out.println("JUMP_" + jp1 + " LD A,00H");  //5
                  System.out.println("JUMP_" + jp2 + " ADD A,00H");
                  break;

                case bugParser.LE:
                  jp1 = jp_num++;
                  jp2 = jp_num++;
                  System.out.println("LD B,(" + right.val + ")");
                  System.out.println("INC B");
                  System.out.println("CP B");
                  System.out.println("JP C,JUMP_" + jp1);
                  System.out.println("LD A,00H");
                  System.out.println("JP JUMP_" + jp2);
                  System.out.println("JUMP_" + jp1 + " LD A,01H");
                  System.out.println("JUMP_" + jp2 + " ADD A,00H");
                  break;
              }

              return new Type(Type.BOOL, null);
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
      this.ensure(id);
      System.out.println("LD (" + id + "),A");
    }
    right = this.visit(ctx.expression_b(1));

    // EXPR en IZQ y DER
    if (left.type == Type.EXPR && right.type == Type.EXPR) {
      if (left.auxType == right.auxType) {
        if (left.auxType == Type.BOOL) {
          op = used.pop();
          System.out.println("AND (" + op + ")");
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
            if (SymbolsTable.get(right.val).type == left.auxType) {
              if (SymbolsTable.get(right.val).type == Type.BOOL) {
                System.out.println("AND (" + right.val + ")");
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
            System.out.println("AND " + right.asBoolean());
            return new Type(Type.EXPR, Type.BOOL, null);
          } else {
            Errors.incompatibleTypes(left.auxType, right.type, "&&");
          }
        }
      } else {

      // EXPR en derecha
        if (left.type == Type.ID) {
          if (SymbolsTable.containsKey(left.val)) {
            if (SymbolsTable.get(left.val).type == right.auxType) {
              if (SymbolsTable.get(left.val).type == Type.BOOL) {
                System.out.println("AND (" + left.val + ")");
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
            System.out.println("AND " + left.asBoolean());
            return new Type(Type.EXPR, Type.BOOL, null);
          } else {
            Errors.incompatibleTypes(SymbolsTable.get(left.val).type, right.auxType, "&&");
          }
        }
      }
    } else {
      // HOJAS
      if (left.type == Type.BOOL && right.type == Type.BOOL) {
        System.out.println("LD A," + left.asBoolean());
        System.out.println("AND " + right.asBoolean());
        return new Type(Type.EXPR, Type.BOOL, null);

      } else if (left.type == Type.BOOL && right.type == Type.ID) {
        if (SymbolsTable.containsKey(right.val)) {
          if (SymbolsTable.get(right.val).type == Type.BOOL) {
            System.out.println("LD A," + left.asBoolean());
            System.out.println("AND (" + right.val + ")");
            return new Type(Type.EXPR, Type.BOOL, null);
          } else {
            Errors.incompatibleTypes(left.type, SymbolsTable.get(right.val).type, "&&");
          }
        } else {
          Errors.notDeclared(right.val);
        }

      } else if (left.type == Type.ID && right.type == Type.BOOL) {
        if (SymbolsTable.containsKey(left.val)) {
          if (SymbolsTable.get(left.val).type == Type.BOOL) {
            System.out.println("LD A,(" + left.val + ")");
            System.out.println("AND " + right.asBoolean());
            return new Type(Type.EXPR, Type.BOOL, null);
          } else {
            Errors.incompatibleTypes(SymbolsTable.get(left.val).type, right.type, "&&");
          }
        } else {
          Errors.notDeclared(left.val);
        }

      } else if (left.type == Type.ID && right.type == Type.ID) {
        if (SymbolsTable.containsKey(left.val)) {
          if (SymbolsTable.containsKey(right.val)) {
            if (SymbolsTable.get(left.val).type == SymbolsTable.get(right.val).type) {
              if (SymbolsTable.get(left.val).type == Type.BOOL) {
                System.out.println("LD A,(" + left.val + ")");
                System.out.println("AND (" + right.val + ")");
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
      this.ensure(id);
      System.out.println("LD (" + id + "),A");
    }
    right = this.visit(ctx.expression_b(1));

    // EXPR en IZQ y DER
    if (left.type == Type.EXPR && right.type == Type.EXPR) {
      if (left.auxType == right.auxType) {
        if (left.auxType == Type.BOOL) {
          op = used.pop();
          System.out.println("OR (" + op + ")");
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
            if (SymbolsTable.get(right.val).type == left.auxType) {
              if (SymbolsTable.get(right.val).type == Type.BOOL) {
                System.out.println("OR (" + right.val + ")");
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
            System.out.println("OR " + right.asBoolean());
            return new Type(Type.EXPR, Type.BOOL, null);
          } else {
            Errors.incompatibleTypes(left.auxType, right.type, "&&");
          }
        }
      } else {

      // EXPR en derecha
        if (left.type == Type.ID) {
          if (SymbolsTable.containsKey(left.val)) {
            if (SymbolsTable.get(left.val).type == right.auxType) {
              if (SymbolsTable.get(left.val).type == Type.BOOL) {
                System.out.println("OR (" + left.val + ")");
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
            System.out.println("OR " + left.asBoolean());
            return new Type(Type.EXPR, Type.BOOL, null);
          } else {
            Errors.incompatibleTypes(SymbolsTable.get(left.val).type, right.auxType, "&&");
          }
        }
      }
    } else {
      // HOJAS
      if (left.type == Type.BOOL && right.type == Type.BOOL) {
        System.out.println("LD A," + left.asBoolean());
        System.out.println("OR " + right.asBoolean());
        return new Type(Type.EXPR, Type.BOOL, null);

      } else if (left.type == Type.BOOL && right.type == Type.ID) {
        if (SymbolsTable.containsKey(right.val)) {
          if (SymbolsTable.get(right.val).type == Type.BOOL) {
            System.out.println("LD A," + left.asBoolean());
            System.out.println("OR (" + right.val + ")");
            return new Type(Type.EXPR, Type.BOOL, null);
          } else {
            Errors.incompatibleTypes(left.type, SymbolsTable.get(right.val).type, "&&");
          }
        } else {
          Errors.notDeclared(right.val);
        }

      } else if (left.type == Type.ID && right.type == Type.BOOL) {
        if (SymbolsTable.containsKey(left.val)) {
          if (SymbolsTable.get(left.val).type == Type.BOOL) {
            System.out.println("LD A,(" + left.val + ")");
            System.out.println("OR " + right.asBoolean());
            return new Type(Type.EXPR, Type.BOOL, null);
          } else {
            Errors.incompatibleTypes(SymbolsTable.get(left.val).type, right.type, "&&");
          }
        } else {
          Errors.notDeclared(left.val);
        }

      } else if (left.type == Type.ID && right.type == Type.ID) {
        if (SymbolsTable.containsKey(left.val)) {
          if (SymbolsTable.containsKey(right.val)) {
            if (SymbolsTable.get(left.val).type == SymbolsTable.get(right.val).type) {
              if (SymbolsTable.get(left.val).type == Type.BOOL) {
                System.out.println("LD A,(" + left.val + ")");
                System.out.println("OR (" + right.val + ")");
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
    return super.visitEqNeqExpr_b(ctx); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Type visitBooleanId(bugParser.BooleanIdContext ctx) {
    return new Type(Type.ID, Type.BOOL, ctx.ID().getText());
  }

  @Override
  public Type visitEqNeqExpr_a(bugParser.EqNeqExpr_aContext ctx) {
    return super.visitEqNeqExpr_a(ctx); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Type visitNotExpr_b(bugParser.NotExpr_bContext ctx) {
    Type op = this.visit(ctx.expression_b());
    if (op.type == Type.INT) {
      Errors.isNot(Type.BOOL);
    } else if (op.type == Type.BOOL) {
      System.out.println("LD A," + op.asBoolean());
      System.out.println("XOR 01H");
      return new Type(Type.EXPR, Type.BOOL, null);
    } else if (op.type == Type.ID) {
      if (SymbolsTable.containsKey(op.val)) {
        if (SymbolsTable.get(op.val).type == Type.BOOL) {
          System.out.println("LD A,(" + op.val + ")");
          System.out.println("XOR 01H");
          return new Type(Type.EXPR, Type.BOOL, null);
        } else {
          Errors.isNot(Type.BOOL);
        }
      } else {
        Errors.notDeclared(op.val);
      }
    } else if (op.type == Type.EXPR) {
      if (op.auxType == Type.BOOL) {
        System.out.println("XOR 01H");
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
}
