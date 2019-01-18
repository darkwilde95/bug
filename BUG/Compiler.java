/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bug;

import java.util.HashMap;

/**
 *
 * @author darkw
 */
public class Compiler extends bugBaseVisitor<Type> {

  HashMap<String, Type> SymbolsTable = new HashMap();

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

  @Override
  public Type visitBugAssignation(bugParser.BugAssignationContext ctx) {

    String id = ctx.ID().getText();
    Type id_t = null;
    Type operand = this.visit(ctx.expression());
    
    if (SymbolsTable.containsKey(id)) {
      id_t = SymbolsTable.get(id);
    }
    // Verificar compatibilidad
    if (operand.type == Type.EXPR) {
      if (id_t != null && id_t.type != operand.auxType) {
        System.err.println("Incompatible Types!");
        System.exit(0);
      }
      SymbolsTable.put(id, new Type(operand.auxType, operand.val));
    } else if (operand.type == Type.ID) {
        if(!SymbolsTable.containsKey(operand.val)) {
          System.err.println("Variable '" + operand.val + "' was not declared");
          System.exit(0);
        }
        SymbolsTable.put(id, new Type(SymbolsTable.get(operand.val).type, operand.val));
    } else{
      if (id_t != null && id_t.type != operand.type) {
        System.err.println("Incompatible Types!");
        System.exit(0);
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
        System.out.println("LD ("+ id + "),"+ operand.val);
        break;

      case Type.EXPR:
        System.out.println("LD (" + id + "),A");
        break;

      case Type.NONE:
        System.err.println("Error");
        break;
    }

    return super.visitBugAssignation(ctx);
  }

  @Override
  public Type visitBugPrint(bugParser.BugPrintContext ctx) {
    return super.visitBugPrint(ctx); //To change body of generated methods, choose Tools | Templates.
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
    return super.visitParentExpr(ctx);
  }

  @Override
  public Type visitAndExpr(bugParser.AndExprContext ctx) {
    Type left = this.visit(ctx.expression(0));
    Type right = this.visit(ctx.expression(1));

    if (left.isBoolean() && right.isBoolean()) {
      System.out.println("AND");
    } else {
      System.err.println("Incompatible types!");
    }
    return super.visitAndExpr(ctx); //To change body of generated methods, choose Tools | Templates.
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
    Type left = this.visit(ctx.expression(0));
    Type right = this.visit(ctx.expression(1));

    if (left.isBoolean() && right.isBoolean()) {
      System.out.println("OR");
    } else {
      System.err.println("Incompatible types!");
    }
    return super.visitOrExpr(ctx); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Type visitCompExpr(bugParser.CompExprContext ctx) {
    return super.visitCompExpr(ctx); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Type visitTypeExpr(bugParser.TypeExprContext ctx) {
    return super.visitTypeExpr(ctx); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Type visitNegExpr(bugParser.NegExprContext ctx) {
    return super.visitNegExpr(ctx); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Type visitAddSubExpr(bugParser.AddSubExprContext ctx) {
    Type left = this.visit(ctx.expression(0));
    Type right = this.visit(ctx.expression(1));
    
    
    
    System.out.println("left: " + left.type + ", right: " + right.type);
    
    return new Type(Type.INT);
    
    //System.out.println(left.val + " " + left.type + " " + left.auxType);
    //System.out.println(right.val + " " + right.type + " " + right.auxType);

  }

  @Override
  public Type visitNotExpr(bugParser.NotExprContext ctx) {
    return super.visitNotExpr(ctx); //To change body of generated methods, choose Tools | Templates.
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
