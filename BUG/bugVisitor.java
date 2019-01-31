package bug;
// Generated from .\bug.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link bugParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface bugVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link bugParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(bugParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link bugParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(bugParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link bugParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(bugParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link bugParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(bugParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code notExpr_b}
	 * labeled alternative in {@link bugParser#expression_b}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotExpr_b(bugParser.NotExpr_bContext ctx);
	/**
	 * Visit a parse tree produced by the {@code eqNeqExpr_a}
	 * labeled alternative in {@link bugParser#expression_b}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqNeqExpr_a(bugParser.EqNeqExpr_aContext ctx);
	/**
	 * Visit a parse tree produced by the {@code booleanId}
	 * labeled alternative in {@link bugParser#expression_b}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanId(bugParser.BooleanIdContext ctx);
	/**
	 * Visit a parse tree produced by the {@code eqNeqExpr_b}
	 * labeled alternative in {@link bugParser#expression_b}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqNeqExpr_b(bugParser.EqNeqExpr_bContext ctx);
	/**
	 * Visit a parse tree produced by the {@code booleanType}
	 * labeled alternative in {@link bugParser#expression_b}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanType(bugParser.BooleanTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code andExpr_b}
	 * labeled alternative in {@link bugParser#expression_b}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndExpr_b(bugParser.AndExpr_bContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parentExpr_b}
	 * labeled alternative in {@link bugParser#expression_b}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParentExpr_b(bugParser.ParentExpr_bContext ctx);
	/**
	 * Visit a parse tree produced by the {@code compExpr}
	 * labeled alternative in {@link bugParser#expression_b}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompExpr(bugParser.CompExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code orExpr_b}
	 * labeled alternative in {@link bugParser#expression_b}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrExpr_b(bugParser.OrExpr_bContext ctx);
	/**
	 * Visit a parse tree produced by the {@code integerId}
	 * labeled alternative in {@link bugParser#expression_a}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegerId(bugParser.IntegerIdContext ctx);
	/**
	 * Visit a parse tree produced by the {@code integerType}
	 * labeled alternative in {@link bugParser#expression_a}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegerType(bugParser.IntegerTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parentExpr_a}
	 * labeled alternative in {@link bugParser#expression_a}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParentExpr_a(bugParser.ParentExpr_aContext ctx);
	/**
	 * Visit a parse tree produced by the {@code negExpr_a}
	 * labeled alternative in {@link bugParser#expression_a}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNegExpr_a(bugParser.NegExpr_aContext ctx);
	/**
	 * Visit a parse tree produced by the {@code addSubExpr_a}
	 * labeled alternative in {@link bugParser#expression_a}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddSubExpr_a(bugParser.AddSubExpr_aContext ctx);
	/**
	 * Visit a parse tree produced by the {@code multDivExpr_a}
	 * labeled alternative in {@link bugParser#expression_a}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultDivExpr_a(bugParser.MultDivExpr_aContext ctx);
	/**
	 * Visit a parse tree produced by {@link bugParser#print}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrint(bugParser.PrintContext ctx);
	/**
	 * Visit a parse tree produced by {@link bugParser#assignation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignation(bugParser.AssignationContext ctx);
	/**
	 * Visit a parse tree produced by {@link bugParser#b_if}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitB_if(bugParser.B_ifContext ctx);
	/**
	 * Visit a parse tree produced by {@link bugParser#b_while}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitB_while(bugParser.B_whileContext ctx);
	/**
	 * Visit a parse tree produced by {@link bugParser#b_for}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitB_for(bugParser.B_forContext ctx);
}