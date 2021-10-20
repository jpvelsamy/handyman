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
	 * Enter a parse tree produced by {@link RavenParser#attribute}.
	 * @param ctx the parse tree
	 */
	void enterAttribute(RavenParser.AttributeContext ctx);
	/**
	 * Exit a parse tree produced by {@link RavenParser#attribute}.
	 * @param ctx the parse tree
	 */
	void exitAttribute(RavenParser.AttributeContext ctx);
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
	 * Enter a parse tree produced by {@link RavenParser#forkProcess}.
	 * @param ctx the parse tree
	 */
	void enterForkProcess(RavenParser.ForkProcessContext ctx);
	/**
	 * Exit a parse tree produced by {@link RavenParser#forkProcess}.
	 * @param ctx the parse tree
	 */
	void exitForkProcess(RavenParser.ForkProcessContext ctx);
	/**
	 * Enter a parse tree produced by {@link RavenParser#spawnProcess}.
	 * @param ctx the parse tree
	 */
	void enterSpawnProcess(RavenParser.SpawnProcessContext ctx);
	/**
	 * Exit a parse tree produced by {@link RavenParser#spawnProcess}.
	 * @param ctx the parse tree
	 */
	void exitSpawnProcess(RavenParser.SpawnProcessContext ctx);
	/**
	 * Enter a parse tree produced by {@link RavenParser#dogLeg}.
	 * @param ctx the parse tree
	 */
	void enterDogLeg(RavenParser.DogLegContext ctx);
	/**
	 * Exit a parse tree produced by {@link RavenParser#dogLeg}.
	 * @param ctx the parse tree
	 */
	void exitDogLeg(RavenParser.DogLegContext ctx);
	/**
	 * Enter a parse tree produced by {@link RavenParser#startProcess}.
	 * @param ctx the parse tree
	 */
	void enterStartProcess(RavenParser.StartProcessContext ctx);
	/**
	 * Exit a parse tree produced by {@link RavenParser#startProcess}.
	 * @param ctx the parse tree
	 */
	void exitStartProcess(RavenParser.StartProcessContext ctx);
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
	 * Enter a parse tree produced by {@link RavenParser#dropFile}.
	 * @param ctx the parse tree
	 */
	void enterDropFile(RavenParser.DropFileContext ctx);
	/**
	 * Exit a parse tree produced by {@link RavenParser#dropFile}.
	 * @param ctx the parse tree
	 */
	void exitDropFile(RavenParser.DropFileContext ctx);
	/**
	 * Enter a parse tree produced by {@link RavenParser#restApi}.
	 * @param ctx the parse tree
	 */
	void enterRestApi(RavenParser.RestApiContext ctx);
	/**
	 * Exit a parse tree produced by {@link RavenParser#restApi}.
	 * @param ctx the parse tree
	 */
	void exitRestApi(RavenParser.RestApiContext ctx);
	/**
	 * Enter a parse tree produced by {@link RavenParser#restPart}.
	 * @param ctx the parse tree
	 */
	void enterRestPart(RavenParser.RestPartContext ctx);
	/**
	 * Exit a parse tree produced by {@link RavenParser#restPart}.
	 * @param ctx the parse tree
	 */
	void exitRestPart(RavenParser.RestPartContext ctx);
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
	/**
	 * Enter a parse tree produced by {@link RavenParser#json}.
	 * @param ctx the parse tree
	 */
	void enterJson(RavenParser.JsonContext ctx);
	/**
	 * Exit a parse tree produced by {@link RavenParser#json}.
	 * @param ctx the parse tree
	 */
	void exitJson(RavenParser.JsonContext ctx);
	/**
	 * Enter a parse tree produced by {@link RavenParser#obj}.
	 * @param ctx the parse tree
	 */
	void enterObj(RavenParser.ObjContext ctx);
	/**
	 * Exit a parse tree produced by {@link RavenParser#obj}.
	 * @param ctx the parse tree
	 */
	void exitObj(RavenParser.ObjContext ctx);
	/**
	 * Enter a parse tree produced by {@link RavenParser#pair}.
	 * @param ctx the parse tree
	 */
	void enterPair(RavenParser.PairContext ctx);
	/**
	 * Exit a parse tree produced by {@link RavenParser#pair}.
	 * @param ctx the parse tree
	 */
	void exitPair(RavenParser.PairContext ctx);
	/**
	 * Enter a parse tree produced by {@link RavenParser#arr}.
	 * @param ctx the parse tree
	 */
	void enterArr(RavenParser.ArrContext ctx);
	/**
	 * Exit a parse tree produced by {@link RavenParser#arr}.
	 * @param ctx the parse tree
	 */
	void exitArr(RavenParser.ArrContext ctx);
	/**
	 * Enter a parse tree produced by {@link RavenParser#jValue}.
	 * @param ctx the parse tree
	 */
	void enterJValue(RavenParser.JValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link RavenParser#jValue}.
	 * @param ctx the parse tree
	 */
	void exitJValue(RavenParser.JValueContext ctx);
}