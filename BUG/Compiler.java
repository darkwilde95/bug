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
public class Compiler extends bugBaseVisitor<Type> {

  HashMap<String, Type> SymbolsTable = new HashMap();
  org.antlr.v4.runtime.tree.ParseTree last = null;
  Stack<String> used = new Stack();

  @Override
  public Type visitBugFor(bugParser.BugForContext ctx) {
    return super.visitBugFor(ctx); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Type visitBugWhile(bugParser.BugWhileContext ctx) {
    return super.visitBugWhile(ctx); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Type visitBugIf(bugParser.BugIfContext ctx) {
    return super.visitBugIf(ctx); //To change body of generated methods, choose Tools | Templates.
  }

  private void ensure(String id) {
    if (!SymbolsTable.containsKey(id)) {
      SymbolsTable.put(id, null);
    }
  }
  
  @Override
  public Type visitBugAssignation(bugParser.BugAssignationContext ctx) {

    String id = ctx.ID().getText();
    Type id_t = null;
    last = ctx.expression().getChild(2);
    Type operand = this.visit(ctx.expression());
    
    if (SymbolsTable.containsKey(id)) {
      id_t = SymbolsTable.get(id);
    }
    
    // Verificar compatibilidad
    if (operand.type == Type.EXPR) {
      if (id_t != null && id_t.type != operand.auxType) {
        System.err.println("Incompatible Types!");
        System.exit(-1);
      }
      SymbolsTable.put(id, new Type(operand.auxType, operand.val));
    } else if (operand.type == Type.ID) {
        if(!SymbolsTable.containsKey(operand.val)) {
          System.err.println("Variable '" + operand.val + "' was not declared");
          System.exit(-1);
        }
        SymbolsTable.put(id, new Type(SymbolsTable.get(operand.val).type, operand.val));
    } else{
      if (id_t != null && id_t.type != operand.type) {
        System.err.println("Incompatible Types!");
        System.exit(-1);
      }
      SymbolsTable.put(id, new Type(operand.type, operand.val));
    }

    switch(operand.type) {
      case Type.ID:
        System.out.println("LD ("+ id + "),(" + operand.val + ")");
        break;

      case Type.INT:
        System.out.println("LD ("+ id + "),"+ operand.asInteger());
        break;

      case Type.BOOL:
        System.out.println("LD ("+ id + "),"+ operand.asBoolean());
        break;

      case Type.EXPR:
        System.out.println("LD (" + id + "),A");
        break;

      case Type.NONE:
        System.err.println("Operation not supported");
        System.exit(-1);
        break;
    }

    return new Type(Type.NONE, null);
  }

  @Override
  public Type visitBugPrint(bugParser.BugPrintContext ctx) {
    return super.visitBugPrint(ctx);
  }

  @Override
  public Type visitIdentifier(bugParser.IdentifierContext ctx) {
    String id = ctx.ID().getText();
    return new Type(Type.ID, id);
  }

  @Override
  public Type visitBoolType(bugParser.BoolTypeContext ctx) {
    return new Type(Type.BOOL, ctx.value.getText());
  }

  @Override
  public Type visitNumberType(bugParser.NumberTypeContext ctx) {
    return new Type(Type.INT, ctx.INT().getText());
  }

  @Override
  public Type visitParentExpr(bugParser.ParentExprContext ctx) {
    return this.visit(ctx.expression());
  }

  @Override
  public Type visitAndExpr(bugParser.AndExprContext ctx) {
    String op = null, id = null;
    Type left = null, right = null;
    left = this.visit(ctx.expression(0));
    if (left.type == Type.EXPR && ctx.getChild(2).getChildCount() > 1) {
      id = Integer.toString(used.size());
      used.add(id);
      this.ensure(id);
      System.out.println("LD (" + id + "),A");
    }
    right = this.visit(ctx.expression(1));
    
    // EXPR en IZQ y DER
    if (left.type == Type.EXPR && right.type == Type.EXPR) {
      if (left.auxType == right.auxType) {
        if (left.auxType == Type.BOOL) {
          op = used.pop();
          System.out.println("AND (" + op + ")");
          return new Type(Type.EXPR, Type.BOOL, null);
        } else {
          System.err.println("Operation not result in 'boolean'");
          System.exit(-1);
        }
      } else {
        System.err.println("Incompatible types!");
        System.exit(-1);
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
                System.err.println("Variables are not 'boolean' types");
                System.exit(-1);
              }
            } else {
              System.err.println("Incompatible types!");
              System.exit(-1);
            }
          }
        } else {
          if (right.type == Type.BOOL) {
            System.out.println("AND " + right.asBoolean());           
            return new Type(Type.EXPR, Type.BOOL, null);
          } else {
            System.err.println("Incompatible types!");
            System.exit(-1);
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
                System.err.println("Variables are not 'boolean' types");
                System.exit(-1);
              }
            } else {
              System.err.println("Incompatible types!");
              System.exit(-1);
            }
          }
        } else {
          if (left.type == Type.BOOL) {
            System.out.println("AND " + left.asBoolean());            
            return new Type(Type.EXPR, Type.BOOL, null);
          } else {
            System.err.println("Incompatible types!");
            System.exit(-1);
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
            System.err.println("Incompatible types!");
            System.exit(-1);
          }
        } else {
          System.err.println("Variable '" + right.val + "' was not declared");
          System.exit(-1);
        }
        

      } else if (left.type == Type.ID && right.type == Type.BOOL) {
        if (SymbolsTable.containsKey(left.val)) {
          if (SymbolsTable.get(left.val).type == Type.BOOL) {
            System.out.println("LD A,(" + left.val + ")");
            System.out.println("AND " + right.asBoolean());
            return new Type(Type.EXPR, Type.BOOL, null);
          } else {
            System.err.println("Incompatible types!");
            System.exit(-1);
          }
        } else {
          System.err.println("Variable '" + left.val + "' was not declared");
          System.exit(-1);
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
                System.err.println("Variables are not 'boolean' type");
                System.exit(-1);
              }
            } else {
              System.err.println("Incompatible types!");
              System.exit(-1);
            }
          } else {
            System.err.println("Variable '" + right.val + "' was not declared");
            System.exit(-1);
          }
        } else {
          System.err.println("Variable '" + left.val + "' was not declared");
          System.exit(-1);
        }
      }
    }
    return new Type(Type.NONE, null);
  }

  @Override
  public Type visitEqNeqExpr(bugParser.EqNeqExprContext ctx) {
    Type left = this.visit(ctx.expression(0));
    Type right = this.visit(ctx.expression(1));

    if (left.type == right.type) {
      if (left.type == bugParser.INT) {
        // valores mierda no es ni tan int por que pueden ser id's
      } else {
        // convertir true false
      }
    } else {
      System.err.println("Incompatible types!");
    }
    return super.visitEqNeqExpr(ctx); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Type visitMultDivExpr(bugParser.MultDivExprContext ctx) {
    Type left = this.visit(ctx.expression(0));
    Type right = this.visit(ctx.expression(1));

    if (left.isInteger() && right.isInteger()) {
      if (ctx.op.getType() == bugParser.MULT) {
        System.out.println("MULT");
      } else {
        System.out.println("DIV");
      }
    } else {
      System.err.println("Incompatible types!");
    }
    return super.visitMultDivExpr(ctx); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Type visitOrExpr(bugParser.OrExprContext ctx) {
    String op = null, id = null;
    Type left = null, right = null;
    left = this.visit(ctx.expression(0));
    if (left.type == Type.EXPR && ctx.getChild(2).getChildCount() > 1) {
      id = Integer.toString(used.size());
      used.add(id);
      this.ensure(id);
      System.out.println("LD (" + id + "),A");
    }
    right = this.visit(ctx.expression(1));
    
    // EXPR en IZQ y DER
    if (left.type == Type.EXPR && right.type == Type.EXPR) {
      if (left.auxType == right.auxType) {
        if (left.auxType == Type.BOOL) {
          op = used.pop();
          System.out.println("OR (" + op + ")");
          return new Type(Type.EXPR, Type.BOOL, null);
        } else {
          System.err.println("Operation not result in 'boolean'");
          System.exit(-1);
        }
      } else {
        System.err.println("Incompatible types!");
        System.exit(-1);
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
                System.err.println("Variables are not 'boolean' types");
                System.exit(-1);
              }
            } else {
              System.err.println("Incompatible types!");
              System.exit(-1);
            }
          }
        } else {
          if (right.type == Type.BOOL) {
            System.out.println("OR " + right.asBoolean());           
            return new Type(Type.EXPR, Type.BOOL, null);
          } else {
            System.err.println("Incompatible types!");
            System.exit(-1);
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
                System.err.println("Variables are not 'boolean' types");
                System.exit(-1);
              }
            } else {
              System.err.println("Incompatible types!");
              System.exit(-1);
            }
          }
        } else {
          if (left.type == Type.BOOL) {
            System.out.println("OR " + left.asBoolean());            
            return new Type(Type.EXPR, Type.BOOL, null);
          } else {
            System.err.println("Incompatible types!");
            System.exit(-1);
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
            System.err.println("Incompatible types!");
            System.exit(-1);
          }
        } else {
          System.err.println("Variable '" + right.val + "' was not declared");
          System.exit(-1);
        }
        

      } else if (left.type == Type.ID && right.type == Type.BOOL) {
        if (SymbolsTable.containsKey(left.val)) {
          if (SymbolsTable.get(left.val).type == Type.BOOL) {
            System.out.println("LD A,(" + left.val + ")");
            System.out.println("OR " + right.asBoolean());
            return new Type(Type.EXPR, Type.BOOL, null);
          } else {
            System.err.println("Incompatible types!");
            System.exit(-1);
          }
        } else {
          System.err.println("Variable '" + left.val + "' was not declared");
          System.exit(-1);
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
                System.err.println("Variables are not 'boolean' type");
                System.exit(-1);
              }
            } else {
              System.err.println("Incompatible types!");
              System.exit(-1);
            }
          } else {
            System.err.println("Variable '" + right.val + "' was not declared");
            System.exit(-1);
          }
        } else {
          System.err.println("Variable '" + left.val + "' was not declared");
          System.exit(-1);
        }
      }
    }
    return new Type(Type.NONE, null);
  }

  @Override
  public Type visitCompExpr(bugParser.CompExprContext ctx) {
    return super.visitCompExpr(ctx); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Type visitTypeExpr(bugParser.TypeExprContext ctx) {
    return super.visitTypeExpr(ctx);
  }

  @Override
  public Type visitNegExpr(bugParser.NegExprContext ctx) {
    Type op = this.visit(ctx.expression());
    if (op.type == Type.BOOL) {
      System.err.println("Type is not 'int'");
      System.exit(-1);
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
          System.err.println("Incompatible Types!");
          System.exit(-1);
        }
      } else {
        System.err.println("Variable '" + op.val + "' was not declared");
        System.exit(-1);
      }
    } else if (op.type == Type.EXPR) {
      if (op.auxType == Type.INT) {
        System.out.println("NEG");
        return new Type(Type.EXPR, Type.INT, null);
      } else {
        System.err.println("Incompatible Types!");
        System.exit(-1);
      }
    }
    return new Type(Type.NONE, null);
  }

  @Override
  public Type visitAddSubExpr(bugParser.AddSubExprContext ctx) {
    boolean op = ctx.op.getType() == bugParser.PLUS;
    String id = null, op_l = null, op_r = null;
    Type left = null, right = null;
    left = this.visit(ctx.expression(0));
    if (left.type == Type.EXPR && ctx.getChild(2).getChildCount() > 1) {
      id = Integer.toString(used.size());
      used.add(id);
      this.ensure(id);
      System.out.println("LD (" + id + "),A");
    }
    right = this.visit(ctx.expression(1));
    if (right.type == Type.EXPR && !op) {
      id = Integer.toString(used.size());
      used.add(id);
      this.ensure(id);
      System.out.println("LD (" + id + "),A");
    }
    
    // EXPR en IZQ y DER
    if (left.type == Type.EXPR && right.type == Type.EXPR) {
      if (left.auxType == right.auxType) {
        if (left.auxType == Type.INT) {
          if (op) {
            op_l = used.pop();
            System.out.println("ADD A,(" + op_l + ")");
          } else {
            op_r = used.pop();
            op_l = used.pop();
            System.out.println("LD A,(" + op_l + ")");
            System.out.println("SUB (" + op_r + ")");
          }
          return new Type(Type.EXPR, Type.INT, null);
        } else {
          System.err.println("Operation not result in 'int'");
          System.exit(-1);
        }
      } else {
        System.err.println("Incompatible types!");
        System.exit(-1);
      }
    } else if (left.type == Type.EXPR || right.type == Type.EXPR) {
     
    // EXPR en izquierda
      if (left.type == Type.EXPR) {
        if (right.type == Type.ID) {
          if (SymbolsTable.containsKey(right.val)) {
            if (SymbolsTable.get(right.val).type == left.auxType) {
              if (SymbolsTable.get(right.val).type == Type.INT) {
                if (op) {
                  System.out.println("ADD A,(" + right.val + ")");
                } else {
                  System.out.println("SUB (" + right.val + ")");
                }
                return new Type(Type.EXPR, Type.INT, null);
              } else {
                System.err.println("Variables are not 'int' types");
                System.exit(-1);
              }
            } else {
              System.err.println("Incompatible types!");
              System.exit(-1);
            }
          }
        } else {
          if (right.type == Type.INT) {
            if (op) {
              System.out.println("ADD A," + right.asInteger());
            } else {
              System.out.println("SUB " + right.asInteger());
            }
            return new Type(Type.EXPR, Type.INT, null);
          } else {
            System.err.println("Incompatible types!");
            System.exit(-1);
          }
        }
      } else {
      
      // EXPR en derecha
        if (left.type == Type.ID) {
          if (SymbolsTable.containsKey(left.val)) {
            if (SymbolsTable.get(left.val).type == right.auxType) {
              if (SymbolsTable.get(left.val).type == Type.INT) {
                
                
                if (op) {
                  System.out.println("ADD A,(" + left.val + ")");
                } else {
                  op_r = used.pop();
                  System.out.println("LD A,(" + left.val + ")" );
                  System.out.println("SUB (" + op_r + ")");
                }
                return new Type(Type.EXPR, Type.INT, null);
                
                
              } else {
                System.err.println("Variables are not 'int' types");
                System.exit(-1);
              }
            } else {
              System.err.println("Incompatible types!");
              System.exit(-1);
            }
          }
        } else {
          if (left.type == Type.INT) {
            if (op) {
              System.out.println("ADD A," + left.asInteger());
            } else {
              op_r = used.pop();
              System.out.println("LD A," + left.asInteger());
              System.out.println("SUB (" + op_r + ")");
            }
            return new Type(Type.EXPR, Type.INT, null);
          } else {
            System.err.println("Incompatible types!");
            System.exit(-1);
          }
        }
      }
    } else {
      // HOJAS
      if (left.type == Type.INT && right.type == Type.INT) {
        System.out.println("LD A," + left.asInteger());
        if (op) {
          System.out.println("ADD A," + right.asInteger());
        } else {
          System.out.println("SUB " + right.asInteger());
        }
        return new Type(Type.EXPR, Type.INT, null);
        
        
      } else if (left.type == Type.INT && right.type == Type.ID) {
        if (SymbolsTable.containsKey(right.val)) {
          if (SymbolsTable.get(right.val).type == Type.INT) {
            System.out.println("LD A," + left.asInteger());
            if (op) {
              System.out.println("ADD A,(" + right.val + ")");
            } else {
              System.out.println("SUB (" + right.val + ")");
            }
            return new Type(Type.EXPR, Type.INT, null);
          } else {
            System.err.println("Incompatible types!");
            System.exit(-1);
          }
        } else {
          System.err.println("Variable '" + right.val + "' was not declared");
          System.exit(-1);
        }
        
        
      } else if (left.type == Type.ID && right.type == Type.INT) {
        if (SymbolsTable.containsKey(left.val)) {
          if (SymbolsTable.get(left.val).type == Type.INT) {
            System.out.println("LD A,(" + left.val + ")");
            if (op) {
              System.out.println("ADD A," + right.asInteger());
            } else {
              System.out.println("SUB " + right.asInteger());
            }
            return new Type(Type.EXPR, Type.INT, null);
          } else {
            System.err.println("Incompatible types!");
            System.exit(-1);
          }
        } else {
          System.err.println("Variable '" + left.val + "' was not declared");
          System.exit(-1);
        }
        
        
      } else if (left.type == Type.ID && right.type == Type.ID) {
        if (SymbolsTable.containsKey(left.val)) {
          if (SymbolsTable.containsKey(right.val)) {
            if (SymbolsTable.get(left.val).type == SymbolsTable.get(right.val).type) {
              if (SymbolsTable.get(left.val).type == Type.INT) {
                System.out.println("LD A,(" + left.val + ")");
                if (op) {
                  System.out.println("ADD A,(" + right.val + ")");
                } else {
                  System.out.println("SUB (" + right.val + ")");
                }
                return new Type(Type.EXPR, Type.INT, null);
              } else {
                System.err.println("Variables are not 'int' types");
                System.exit(-1);
              }
            } else {
              System.err.println("Incompatible types!");
              System.exit(-1);
            }
          } else {
            System.err.println("Variable '" + right.val + "' was not declared");
            System.exit(-1);
          }
        } else {
          System.err.println("Variable '" + left.val + "' was not declared");
          System.exit(-1);
        }
      }
    }
    return new Type(Type.NONE, null);
  }

  @Override
  public Type visitNotExpr(bugParser.NotExprContext ctx) {
    Type op = this.visit(ctx.expression());
    if (op.type == Type.INT) {
      System.err.println("Type is not 'boolean'");
      System.exit(-1);
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
          System.err.println("Incompatible types!");
          System.exit(-1);
        }
      } else {
        System.err.println("Variable '" + op.val + "' was not declared?");
        System.exit(-1);
      }
    } else if (op.type == Type.EXPR) {
      if (op.auxType == Type.BOOL) {
        System.out.println("XOR 01H");
        return new Type(Type.EXPR, Type.BOOL, null);
      } else {
        System.err.println("Incompatible Types!");
        System.exit(-1);
      }
    }
    return new Type(Type.NONE, null);
  }

  @Override
  public Type visitStatement(bugParser.StatementContext ctx) {
    return super.visitStatement(ctx); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Type visitBlock(bugParser.BlockContext ctx) {
    return super.visitBlock(ctx); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Type visitProgram(bugParser.ProgramContext ctx) {
    return super.visitProgram(ctx); //To change body of generated methods, choose Tools | Templates.
  }

}
