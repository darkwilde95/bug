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
	 * Visit a parse tree produced by the {@code notExpr}
	 * labeled alternative in {@link bugParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotExpr(bugParser.NotExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code addSubExpr}
	 * labeled alternative in {@link bugParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddSubExpr(bugParser.AddSubExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code negExpr}
	 * labeled alternative in {@link bugParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNegExpr(bugParser.NegExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code typeExpr}
	 * labeled alternative in {@link bugParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeExpr(bugParser.TypeExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code compExpr}
	 * labeled alternative in {@link bugParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompExpr(bugParser.CompExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code orExpr}
	 * labeled alternative in {@link bugParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrExpr(bugParser.OrExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code multDivExpr}
	 * labeled alternative in {@link bugParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultDivExpr(bugParser.MultDivExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parentExpr}
	 * labeled alternative in {@link bugParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParentExpr(bugParser.ParentExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code eqNeqExpr}
	 * labeled alternative in {@link bugParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqNeqExpr(bugParser.EqNeqExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code andExpr}
	 * labeled alternative in {@link bugParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndExpr(bugParser.AndExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code numberType}
	 * labeled alternative in {@link bugParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumberType(bugParser.NumberTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boolType}
	 * labeled alternative in {@link bugParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolType(bugParser.BoolTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code identifier}
	 * labeled alternative in {@link bugParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(bugParser.IdentifierContext ctx);
	/**
	 * Visit a parse tree produced by the {@code bugPrint}
	 * labeled alternative in {@link bugParser#print}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBugPrint(bugParser.BugPrintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code bugAssignation}
	 * labeled alternative in {@link bugParser#assignation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBugAssignation(bugParser.BugAssignationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code bugIf}
	 * labeled alternative in {@link bugParser#b_if}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBugIf(bugParser.BugIfContext ctx);
	/**
	 * Visit a parse tree produced by the {@code bugWhile}
	 * labeled alternative in {@link bugParser#b_while}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBugWhile(bugParser.BugWhileContext ctx);
	/**
	 * Visit a parse tree produced by the {@code bugFor}
	 * labeled alternative in {@link bugParser#b_for}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBugFor(bugParser.BugForContext ctx);
}