// Generated from java-escape by ANTLR 4.11.1

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
	 * Enter a parse tree produced by {@link RavenParser#multitude}.
	 * @param ctx the parse tree
	 */
	void enterMultitude(RavenParser.MultitudeContext ctx);
	/**
	 * Exit a parse tree produced by {@link RavenParser#multitude}.
	 * @param ctx the parse tree
	 */
	void exitMultitude(RavenParser.MultitudeContext ctx);
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
	 * Enter a parse tree produced by {@link RavenParser#exportCsv}.
	 * @param ctx the parse tree
	 */
	void enterExportCsv(RavenParser.ExportCsvContext ctx);
	/**
	 * Exit a parse tree produced by {@link RavenParser#exportCsv}.
	 * @param ctx the parse tree
	 */
	void exitExportCsv(RavenParser.ExportCsvContext ctx);
	/**
	 * Enter a parse tree produced by {@link RavenParser#importCsvToDB}.
	 * @param ctx the parse tree
	 */
	void enterImportCsvToDB(RavenParser.ImportCsvToDBContext ctx);
	/**
	 * Exit a parse tree produced by {@link RavenParser#importCsvToDB}.
	 * @param ctx the parse tree
	 */
	void exitImportCsvToDB(RavenParser.ImportCsvToDBContext ctx);
	/**
	 * Enter a parse tree produced by {@link RavenParser#extractTAR}.
	 * @param ctx the parse tree
	 */
	void enterExtractTAR(RavenParser.ExtractTARContext ctx);
	/**
	 * Exit a parse tree produced by {@link RavenParser#extractTAR}.
	 * @param ctx the parse tree
	 */
	void exitExtractTAR(RavenParser.ExtractTARContext ctx);
	/**
	 * Enter a parse tree produced by {@link RavenParser#createTAR}.
	 * @param ctx the parse tree
	 */
	void enterCreateTAR(RavenParser.CreateTARContext ctx);
	/**
	 * Exit a parse tree produced by {@link RavenParser#createTAR}.
	 * @param ctx the parse tree
	 */
	void exitCreateTAR(RavenParser.CreateTARContext ctx);
	/**
	 * Enter a parse tree produced by {@link RavenParser#createDirectory}.
	 * @param ctx the parse tree
	 */
	void enterCreateDirectory(RavenParser.CreateDirectoryContext ctx);
	/**
	 * Exit a parse tree produced by {@link RavenParser#createDirectory}.
	 * @param ctx the parse tree
	 */
	void exitCreateDirectory(RavenParser.CreateDirectoryContext ctx);
	/**
	 * Enter a parse tree produced by {@link RavenParser#createFile}.
	 * @param ctx the parse tree
	 */
	void enterCreateFile(RavenParser.CreateFileContext ctx);
	/**
	 * Exit a parse tree produced by {@link RavenParser#createFile}.
	 * @param ctx the parse tree
	 */
	void exitCreateFile(RavenParser.CreateFileContext ctx);
	/**
	 * Enter a parse tree produced by {@link RavenParser#deleteFileDirectory}.
	 * @param ctx the parse tree
	 */
	void enterDeleteFileDirectory(RavenParser.DeleteFileDirectoryContext ctx);
	/**
	 * Exit a parse tree produced by {@link RavenParser#deleteFileDirectory}.
	 * @param ctx the parse tree
	 */
	void exitDeleteFileDirectory(RavenParser.DeleteFileDirectoryContext ctx);
	/**
	 * Enter a parse tree produced by {@link RavenParser#transferFileDirectory}.
	 * @param ctx the parse tree
	 */
	void enterTransferFileDirectory(RavenParser.TransferFileDirectoryContext ctx);
	/**
	 * Exit a parse tree produced by {@link RavenParser#transferFileDirectory}.
	 * @param ctx the parse tree
	 */
	void exitTransferFileDirectory(RavenParser.TransferFileDirectoryContext ctx);
	/**
	 * Enter a parse tree produced by {@link RavenParser#producerConsumerModel}.
	 * @param ctx the parse tree
	 */
	void enterProducerConsumerModel(RavenParser.ProducerConsumerModelContext ctx);
	/**
	 * Exit a parse tree produced by {@link RavenParser#producerConsumerModel}.
	 * @param ctx the parse tree
	 */
	void exitProducerConsumerModel(RavenParser.ProducerConsumerModelContext ctx);
	/**
	 * Enter a parse tree produced by {@link RavenParser#producer}.
	 * @param ctx the parse tree
	 */
	void enterProducer(RavenParser.ProducerContext ctx);
	/**
	 * Exit a parse tree produced by {@link RavenParser#producer}.
	 * @param ctx the parse tree
	 */
	void exitProducer(RavenParser.ProducerContext ctx);
	/**
	 * Enter a parse tree produced by {@link RavenParser#consumer}.
	 * @param ctx the parse tree
	 */
	void enterConsumer(RavenParser.ConsumerContext ctx);
	/**
	 * Exit a parse tree produced by {@link RavenParser#consumer}.
	 * @param ctx the parse tree
	 */
	void exitConsumer(RavenParser.ConsumerContext ctx);
	/**
	 * Enter a parse tree produced by {@link RavenParser#pushJson}.
	 * @param ctx the parse tree
	 */
	void enterPushJson(RavenParser.PushJsonContext ctx);
	/**
	 * Exit a parse tree produced by {@link RavenParser#pushJson}.
	 * @param ctx the parse tree
	 */
	void exitPushJson(RavenParser.PushJsonContext ctx);
	/**
	 * Enter a parse tree produced by {@link RavenParser#mapJsonContext}.
	 * @param ctx the parse tree
	 */
	void enterMapJsonContext(RavenParser.MapJsonContextContext ctx);
	/**
	 * Exit a parse tree produced by {@link RavenParser#mapJsonContext}.
	 * @param ctx the parse tree
	 */
	void exitMapJsonContext(RavenParser.MapJsonContextContext ctx);
	/**
	 * Enter a parse tree produced by {@link RavenParser#sharePoint}.
	 * @param ctx the parse tree
	 */
	void enterSharePoint(RavenParser.SharePointContext ctx);
	/**
	 * Exit a parse tree produced by {@link RavenParser#sharePoint}.
	 * @param ctx the parse tree
	 */
	void exitSharePoint(RavenParser.SharePointContext ctx);
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
	 * Enter a parse tree produced by {@link RavenParser#log}.
	 * @param ctx the parse tree
	 */
	void enterLog(RavenParser.LogContext ctx);
	/**
	 * Exit a parse tree produced by {@link RavenParser#log}.
	 * @param ctx the parse tree
	 */
	void exitLog(RavenParser.LogContext ctx);
	/**
	 * Enter a parse tree produced by {@link RavenParser#ravenVmException}.
	 * @param ctx the parse tree
	 */
	void enterRavenVmException(RavenParser.RavenVmExceptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link RavenParser#ravenVmException}.
	 * @param ctx the parse tree
	 */
	void exitRavenVmException(RavenParser.RavenVmExceptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link RavenParser#checksum}.
	 * @param ctx the parse tree
	 */
	void enterChecksum(RavenParser.ChecksumContext ctx);
	/**
	 * Exit a parse tree produced by {@link RavenParser#checksum}.
	 * @param ctx the parse tree
	 */
	void exitChecksum(RavenParser.ChecksumContext ctx);
	/**
	 * Enter a parse tree produced by {@link RavenParser#fileSize}.
	 * @param ctx the parse tree
	 */
	void enterFileSize(RavenParser.FileSizeContext ctx);
	/**
	 * Exit a parse tree produced by {@link RavenParser#fileSize}.
	 * @param ctx the parse tree
	 */
	void exitFileSize(RavenParser.FileSizeContext ctx);
	/**
	 * Enter a parse tree produced by {@link RavenParser#downloadAsset}.
	 * @param ctx the parse tree
	 */
	void enterDownloadAsset(RavenParser.DownloadAssetContext ctx);
	/**
	 * Exit a parse tree produced by {@link RavenParser#downloadAsset}.
	 * @param ctx the parse tree
	 */
	void exitDownloadAsset(RavenParser.DownloadAssetContext ctx);
	/**
	 * Enter a parse tree produced by {@link RavenParser#paperItemization}.
	 * @param ctx the parse tree
	 */
	void enterPaperItemization(RavenParser.PaperItemizationContext ctx);
	/**
	 * Exit a parse tree produced by {@link RavenParser#paperItemization}.
	 * @param ctx the parse tree
	 */
	void exitPaperItemization(RavenParser.PaperItemizationContext ctx);
	/**
	 * Enter a parse tree produced by {@link RavenParser#autoRotation}.
	 * @param ctx the parse tree
	 */
	void enterAutoRotation(RavenParser.AutoRotationContext ctx);
	/**
	 * Exit a parse tree produced by {@link RavenParser#autoRotation}.
	 * @param ctx the parse tree
	 */
	void exitAutoRotation(RavenParser.AutoRotationContext ctx);
	/**
	 * Enter a parse tree produced by {@link RavenParser#blankPageRemover}.
	 * @param ctx the parse tree
	 */
	void enterBlankPageRemover(RavenParser.BlankPageRemoverContext ctx);
	/**
	 * Exit a parse tree produced by {@link RavenParser#blankPageRemover}.
	 * @param ctx the parse tree
	 */
	void exitBlankPageRemover(RavenParser.BlankPageRemoverContext ctx);
	/**
	 * Enter a parse tree produced by {@link RavenParser#patientAttribution}.
	 * @param ctx the parse tree
	 */
	void enterPatientAttribution(RavenParser.PatientAttributionContext ctx);
	/**
	 * Exit a parse tree produced by {@link RavenParser#patientAttribution}.
	 * @param ctx the parse tree
	 */
	void exitPatientAttribution(RavenParser.PatientAttributionContext ctx);
	/**
	 * Enter a parse tree produced by {@link RavenParser#prescriberAttribution}.
	 * @param ctx the parse tree
	 */
	void enterPrescriberAttribution(RavenParser.PrescriberAttributionContext ctx);
	/**
	 * Exit a parse tree produced by {@link RavenParser#prescriberAttribution}.
	 * @param ctx the parse tree
	 */
	void exitPrescriberAttribution(RavenParser.PrescriberAttributionContext ctx);
	/**
	 * Enter a parse tree produced by {@link RavenParser#providerAttribution}.
	 * @param ctx the parse tree
	 */
	void enterProviderAttribution(RavenParser.ProviderAttributionContext ctx);
	/**
	 * Exit a parse tree produced by {@link RavenParser#providerAttribution}.
	 * @param ctx the parse tree
	 */
	void exitProviderAttribution(RavenParser.ProviderAttributionContext ctx);
	/**
	 * Enter a parse tree produced by {@link RavenParser#drugAttribution}.
	 * @param ctx the parse tree
	 */
	void enterDrugAttribution(RavenParser.DrugAttributionContext ctx);
	/**
	 * Exit a parse tree produced by {@link RavenParser#drugAttribution}.
	 * @param ctx the parse tree
	 */
	void exitDrugAttribution(RavenParser.DrugAttributionContext ctx);
	/**
	 * Enter a parse tree produced by {@link RavenParser#documentClassification}.
	 * @param ctx the parse tree
	 */
	void enterDocumentClassification(RavenParser.DocumentClassificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link RavenParser#documentClassification}.
	 * @param ctx the parse tree
	 */
	void exitDocumentClassification(RavenParser.DocumentClassificationContext ctx);
	/**
	 * Enter a parse tree produced by {@link RavenParser#qrAttribution}.
	 * @param ctx the parse tree
	 */
	void enterQrAttribution(RavenParser.QrAttributionContext ctx);
	/**
	 * Exit a parse tree produced by {@link RavenParser#qrAttribution}.
	 * @param ctx the parse tree
	 */
	void exitQrAttribution(RavenParser.QrAttributionContext ctx);
	/**
	 * Enter a parse tree produced by {@link RavenParser#resource}.
	 * @param ctx the parse tree
	 */
	void enterResource(RavenParser.ResourceContext ctx);
	/**
	 * Exit a parse tree produced by {@link RavenParser#resource}.
	 * @param ctx the parse tree
	 */
	void exitResource(RavenParser.ResourceContext ctx);
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