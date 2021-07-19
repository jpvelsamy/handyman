// Generated from Raven.g4 by ANTLR 4.9.2

package in.handyman.raven.compiler;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link RavenParser}.
 */
public interface RavenListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link RavenParser#process}.
	 * @param ctx the parse tree
	 */
	void enterProcess(RavenParser.ProcessContext ctx);
	/**
	 * Exit a parse tree produced by {@link RavenParser#process}.
	 * @param ctx the parse tree
	 */
	void exitProcess(RavenParser.ProcessContext ctx);
	/**
	 * Enter a parse tree produced by {@link RavenParser#tryClause}.
	 * @param ctx the parse tree
	 */
	void enterTryClause(RavenParser.TryClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link RavenParser#tryClause}.
	 * @param ctx the parse tree
	 */
	void exitTryClause(RavenParser.TryClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link RavenParser#finallyClause}.
	 * @param ctx the parse tree
	 */
	void enterFinallyClause(RavenParser.FinallyClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link RavenParser#finallyClause}.
	 * @param ctx the parse tree
	 */
	void exitFinallyClause(RavenParser.FinallyClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link RavenParser#catchClause}.
	 * @param ctx the parse tree
	 */
	void enterCatchClause(RavenParser.CatchClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link RavenParser#catchClause}.
	 * @param ctx the parse tree
	 */
	void exitCatchClause(RavenParser.CatchClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link RavenParser#action}.
	 * @param ctx the parse tree
	 */
	void enterAction(RavenParser.ActionContext ctx);
	/**
	 * Exit a parse tree produced by {@link RavenParser#action}.
	 * @param ctx the parse tree
	 */
	void exitAction(RavenParser.ActionContext ctx);
	/**
	 * Enter a parse tree produced by {@link RavenParser#copyData}.
	 * @param ctx the parse tree
	 */
	void enterCopyData(RavenParser.CopyDataContext ctx);
	/**
	 * Exit a parse tree produced by {@link RavenParser#copyData}.
	 * @param ctx the parse tree
	 */
	void exitCopyData(RavenParser.CopyDataContext ctx);
	/**
	 * Enter a parse tree produced by {@link RavenParser#transform}.
	 * @param ctx the parse tree
	 */
	void enterTransform(RavenParser.TransformContext ctx);
	/**
	 * Exit a parse tree produced by {@link RavenParser#transform}.
	 * @param ctx the parse tree
	 */
	void exitTransform(RavenParser.TransformContext ctx);
	/**
	 * Enter a parse tree produced by {@link RavenParser#loadCsv}.
	 * @param ctx the parse tree
	 */
	void enterLoadCsv(RavenParser.LoadCsvContext ctx);
	/**
	 * Exit a parse tree produced by {@link RavenParser#loadCsv}.
	 * @param ctx the parse tree
	 */
	void exitLoadCsv(RavenParser.LoadCsvContext ctx);
	/**
	 * Enter a parse tree produced by {@link RavenParser#abort}.
	 * @param ctx the parse tree
	 */
	void enterAbort(RavenParser.AbortContext ctx);
	/**
	 * Exit a parse tree produced by {@link RavenParser#abort}.
	 * @param ctx the parse tree
	 */
	void exitAbort(RavenParser.AbortContext ctx);
	/**
	 * Enter a parse tree produced by {@link RavenParser#callProcess}.
	 * @param ctx the parse tree
	 */
	void enterCallProcess(RavenParser.CallProcessContext ctx);
	/**
	 * Exit a parse tree produced by {@link RavenParser#callProcess}.
	 * @param ctx the parse tree
	 */
	void exitCallProcess(RavenParser.CallProcessContext ctx);
	/**
	 * Enter a parse tree produced by {@link RavenParser#assign}.
	 * @param ctx the parse tree
	 */
	void enterAssign(RavenParser.AssignContext ctx);
	/**
	 * Exit a parse tree produced by {@link RavenParser#assign}.
	 * @param ctx the parse tree
	 */
	void exitAssign(RavenParser.AssignContext ctx);
	/**
	 * Enter a parse tree produced by {@link RavenParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(RavenParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link RavenParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(RavenParser.ExpressionContext ctx);
}