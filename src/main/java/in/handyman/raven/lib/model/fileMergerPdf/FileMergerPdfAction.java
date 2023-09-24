package in.handyman.raven.lib.model.fileMergerPdf;


import com.fasterxml.jackson.databind.ObjectMapper;
import in.handyman.raven.exception.HandymanException;
import in.handyman.raven.lambda.access.ResourceAccess;
import in.handyman.raven.lambda.action.ActionExecution;
import in.handyman.raven.lambda.action.IActionExecution;
import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import in.handyman.raven.lib.CoproProcessor;
import in.handyman.raven.lib.model.FileMergerPdf;
import okhttp3.MediaType;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.argument.Arguments;
import org.jdbi.v3.core.argument.NullArgument;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

/**
 * Auto Generated By Raven
 */
@ActionExecution(
        actionName = "FileMerger"

)


public class FileMergerPdfAction implements IActionExecution {
    private static final MediaType MediaTypeJSON = MediaType.parse("application/json; charset=utf-8");
    private final ActionExecutionAudit action;
    private final Logger log;
    private final FileMergerPdf fileMergerPdf;
    private final Marker aMarker;
    private final ObjectMapper mapper = new ObjectMapper();
    private final String URI;
    private final String FILE_MERGER = "FILE_MERGER";
    private final Integer writeBatchSize = 1000;

    public FileMergerPdfAction(final ActionExecutionAudit action, final Logger log,
                            final Object fileMergerPdf) {
        this.fileMergerPdf = (FileMergerPdf) fileMergerPdf;
        this.action = action;
        this.log = log;
        this.aMarker = MarkerFactory.getMarker(" FileMerger:" + this.fileMergerPdf.getName());
        this.URI = action.getContext().get("copro.file-merger.url");
    }



    @Override
    public void execute(){
        try {
            log.info(aMarker, "file mergerAction has been started {}",fileMergerPdf);

            final Jdbi jdbi = ResourceAccess.rdbmsJDBIConn(fileMergerPdf.getResourceConn());
            jdbi.getConfig(Arguments.class).setUntypedNullArgument(new NullArgument(Types.NULL));
            final String outputDir =fileMergerPdf.getOutputDir();
            log.info(aMarker, "file mergerAction output directory {}",outputDir);
            //5. build insert prepare statement with output table columns
            final String insertQuery = "INSERT INTO file_merger.file_merger_result(origin_id,group_id,processed_file_path,paper_no,status,stage,message,created_on,root_pipeline_id,model_name,model_version,tenant_id,template_id,file_id) " +
                    " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";




            log.info(aMarker, "file mergerInsert query {}", insertQuery);

            //3. initiate copro processor and copro urls
            final List<URL> urls = Optional.ofNullable(action.getContext().get("copro.file-merger.url")).map(s -> Arrays.stream(s.split(",")).map(s1 -> {
                try {
                    return new URL(s1);
                } catch (MalformedURLException e) {
                    log.error("Error in processing the URL ", e);
                    throw new HandymanException("Error in processing the URL", e, action);
                }
            }).collect(Collectors.toList())).orElse(Collections.emptyList());
            log.info(aMarker, "file mergercopro urls {}", urls);

            final CoproProcessor<FileMergerpdfInputEntity, FileMergerpdfOutputEntity> coproProcessor =
                    new CoproProcessor<>(new LinkedBlockingQueue<>(),
                            FileMergerpdfOutputEntity.class,
                            FileMergerpdfInputEntity.class,
                            jdbi, log,
                            new FileMergerpdfInputEntity(), urls, action);

            log.info(aMarker, "file mergercopro coproProcessor initialization  {}", coproProcessor);

            //4. call the method start producer from coproprocessor
            coproProcessor.startProducer(fileMergerPdf.getQuerySet(), Integer.valueOf(action.getContext().get("read.batch.size")));
            log.info(aMarker, "file mergercopro coproProcessor startProducer called read batch size {}",action.getContext().get("read.batch.size"));
            Thread.sleep(1000);
            coproProcessor.startConsumer(insertQuery, Integer.valueOf(action.getContext().get("file.merger.consumer.API.count")), Integer.valueOf(action.getContext().get("write.batch.size")),  new FileMergerPdfConsumerProcess(log,aMarker,action,fileMergerPdf));
            log.info(aMarker, "file mergercopro coproProcessor startConsumer called consumer count {} write batch count {} ",Integer.valueOf(action.getContext().get("file.merger.consumer.API.count")),Integer.valueOf(action.getContext().get("write.batch.size")));
            System.out.print(Integer.valueOf(action.getContext().get("file.merger.consumer.API.count")));
            System.out.print(Integer.valueOf(action.getContext().get("write.batch.size")));
            System.out.print("action ended");
        }catch(Exception ex){
            log.error(aMarker,"error in execute method for file merger ",ex);
            throw new HandymanException("error in execute method for file merger", ex, action);
        }

    }

    @Override
    public boolean executeIf() throws Exception {
        return fileMergerPdf.getCondition();
    }

}
