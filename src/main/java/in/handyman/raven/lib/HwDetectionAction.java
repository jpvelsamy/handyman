package in.handyman.raven.lib;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import in.handyman.raven.exception.HandymanException;
import in.handyman.raven.lambda.access.ResourceAccess;
import in.handyman.raven.lambda.action.ActionExecution;
import in.handyman.raven.lambda.action.IActionExecution;
import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import in.handyman.raven.lib.model.HwDetection;
import in.handyman.raven.util.ExceptionUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import okhttp3.*;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.argument.Arguments;
import org.jdbi.v3.core.argument.NullArgument;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Types;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Auto Generated By Raven
 */
@ActionExecution(
        actionName = "HwDetection"
)
public class HwDetectionAction implements IActionExecution {
  private final ActionExecutionAudit action;

  private final Logger log;

  private static HwDetection hwDetection = new HwDetection();

  private final Marker aMarker;
  private static String httpClientTimeout = new String();

  public HwDetectionAction(final ActionExecutionAudit action, final Logger log,
                           final Object hwDetection) {
    this.hwDetection = (HwDetection) hwDetection;
    this.action = action;
    this.log = log;
    this.httpClientTimeout=action.getContext().get("okhttp.client.timeout");
    this.aMarker = MarkerFactory.getMarker(" HwDetection:"+this.hwDetection.getName());
  }

  @Override
  public void execute() throws Exception {

    try {
      final Jdbi jdbi = ResourceAccess.rdbmsJDBIConn(hwDetection.getResourceConn());
      jdbi.getConfig(Arguments.class).setUntypedNullArgument(new NullArgument(Types.NULL));

      log.info(aMarker, "Handwritten Classification Action for {} has been started", hwDetection.getName());
      final String insertQuery = "INSERT INTO paper_classification.paper_classification_result(created_on, created_user_id, last_updated_on, last_updated_user_id, tenant_id, origin_id, paper_no, template_id, model_id, document_type, status, stage, message, group_id, root_pipeline_id, confidence_score)" +
              "values(now(),?,now(),?,?,?,?,?,?,?,?,?,?,?,?,?)";
      final List<URL> urls = Optional.ofNullable(action.getContext().get("copro.hw-detection.url")).map(s -> Arrays.stream(s.split(",")).map(url -> {
        try {
          return new URL(url);
        } catch (MalformedURLException e) {
          log.error("Error in processing the URL {}", url, e);
          throw new HandymanException("Error in processing the URL", e, action);
        }
      }).collect(Collectors.toList())).orElse(Collections.emptyList());
      log.info("urls used in hw detection macro {}",urls);
      final CoproProcessor<HwClassificationInputTable, HwClassificationOutputTable> coproProcessor =
              new CoproProcessor<>(new LinkedBlockingQueue<>(),
                      HwClassificationOutputTable.class,
                      HwClassificationInputTable.class,
                      jdbi,log,
                      new HwClassificationInputTable(),urls,action);
      coproProcessor.startProducer(hwDetection.getQuerySet(),Integer.valueOf(action.getContext().get("read.batch.size")));
      log.info("hwdetection read batch size {} and queryset from macro {} ",Integer.valueOf(action.getContext().get("read.batch.size")),hwDetection.getQuerySet());
      Thread.sleep(1000);
      coproProcessor.startConsumer(insertQuery, Integer.valueOf(action.getContext().get("paper.classification.consumer.API.count")), Integer.valueOf(action.getContext().get("write.batch.size")), new HwClassificationConsumerProcess(log, aMarker, action));
      log.info(aMarker, " Handwritten Classification has been completed {}  ", hwDetection.getName());
    } catch (Exception e){
      action.getContext().put(hwDetection.getName() + ".isSuccessful", "false");
      log.error(aMarker, "Error at handwritten classification execute method {}", ExceptionUtil.toString(e));
      throw new HandymanException("Error at handwritten classification execute method ", e, action);
    }
  }

  @Override
  public boolean executeIf() throws Exception {
    return hwDetection.getCondition();
  }

  public static class HwClassificationConsumerProcess implements CoproProcessor.ConsumerProcess<HwClassificationInputTable,HwClassificationOutputTable> {
    private final Logger log;
    private final Marker aMarker;
    private final String STAGE="PAPER_CLASSIFICATION";
    private final ObjectMapper mapper = new ObjectMapper();
    private static final MediaType MediaTypeJSON = MediaType
            .parse("application/json; charset=utf-8");
    public final ActionExecutionAudit action;

    final OkHttpClient httpclient = new OkHttpClient.Builder()
            .connectTimeout(Long.parseLong(httpClientTimeout), TimeUnit.MINUTES)
            .writeTimeout(Long.parseLong(httpClientTimeout), TimeUnit.MINUTES)
            .readTimeout(Long.parseLong(httpClientTimeout), TimeUnit.MINUTES)
            .build();

    public HwClassificationConsumerProcess(final Logger log, final Marker aMarker, ActionExecutionAudit action) {
      this.log = log;
      this.aMarker = aMarker;
      this.action = action;
    }

    @Override
    public List<HwClassificationOutputTable> process(URL endpoint, HwClassificationInputTable entity) throws Exception {

      List<HwClassificationOutputTable> parentObj = new ArrayList<>();
      final ObjectNode objectNode = mapper.createObjectNode();
      String entityFilePath = entity.getFilePath();

      Long actionId=action.getActionId();
      final String PAPER_CLASSIFICATION_PROCESS="PAPER_CLASSIFICATION";
      long rootpipelineId= entity.getRootPipelineId();

      String modelPath = hwDetection.getModelPath();
      String directoryPath = hwDetection.getDirectoryPath();
      objectNode.put("inputFilePath", entityFilePath);
      objectNode.put("modelPath", modelPath);
      objectNode.put("outputDir", directoryPath);
      //added rootpipelineId,actionId and process anem in the request
      objectNode.put("rootPipelineId",rootpipelineId);
      objectNode.put("actionId",actionId);
      objectNode.put("process",PAPER_CLASSIFICATION_PROCESS);
      log.info(aMarker, "Request List {}", objectNode);
      log.info("request object node {}",objectNode);
      Request request = new Request.Builder().url(endpoint)
              .post(RequestBody.create(objectNode.toString(), MediaTypeJSON)).build();

      if(log.isInfoEnabled()) {
        log.info(aMarker, "Request has been build with the parameters \n coproUrl  {} ,inputFilePath : {} modelPath  {}  outputDir {} ", endpoint,entityFilePath,modelPath,directoryPath);
      }
      String createdUserId = entity.getCreatedUserId();
      String lastUpdatedUserId = entity.getLastUpdatedUserId();
      Long tenantId = entity.getTenantId();
      String originId = entity.getOriginId();
      Integer paperNo = entity.getPaperNo();
      String templateId = entity.getTemplateId();
      String modelId = entity.getModelId();
      Integer groupId = entity.getGroupId();
      try (Response response = httpclient.newCall(request).execute()){
        String responseBody = Objects.requireNonNull(response.body()).string();
        if (response.isSuccessful()) {
          log.info("copro api response body {}",responseBody);
          String documentStatus = Optional.ofNullable(mapper.readTree(responseBody).get("document_status")).map(JsonNode::asText).orElse(null);
          Long score = Optional.ofNullable(mapper.readTree(responseBody).get("confidence_score")).map(JsonNode::asLong).orElse(null);

          parentObj.add(HwClassificationOutputTable.builder()
                  .createdUserId(Optional.ofNullable(createdUserId).map(String::valueOf).orElse(null))
                  .lastUpdatedUserId(Optional.ofNullable(lastUpdatedUserId).map(String::valueOf).orElse(null))
                  .tenantId(Optional.ofNullable(tenantId).map(String::valueOf).map(Long::valueOf).orElse(null))
                  .originId(Optional.ofNullable(originId).map(String::valueOf).orElse(null))
                  .paperNo(Optional.ofNullable(paperNo).map(String::valueOf).map(Integer::parseInt).orElse(null))
                  .templateId(Optional.ofNullable(templateId).map(String::valueOf).orElse(null))
                  .modelId(Optional.ofNullable(modelId).map(String::valueOf).map(Integer::parseInt).orElse(null))
                  .groupId(Optional.ofNullable(groupId).map(String::valueOf).map(Integer::parseInt).orElse(null))
                  .documentType(documentStatus)
                  .confidenceScore(score)
                  .status("COMPLETED")
                  .stage(STAGE)
                  .message("Paper Classification Finished")
                  .groupId(entity.groupId)
                  .rootPipelineId(entity.rootPipelineId)
                  .build());
        }
        else {
          parentObj.add(HwClassificationOutputTable.builder()
                  .createdUserId(Optional.ofNullable(createdUserId).map(String::valueOf).orElse(null))
                  .lastUpdatedUserId(Optional.ofNullable(lastUpdatedUserId).map(String::valueOf).orElse(null))
                  .tenantId(Optional.ofNullable(tenantId).map(String::valueOf).map(Long::valueOf).orElse(null))
                  .originId(Optional.ofNullable(originId).map(String::valueOf).orElse(null))
                  .paperNo(Optional.ofNullable(paperNo).map(String::valueOf).map(Integer::parseInt).orElse(null))
                  .templateId(Optional.ofNullable(templateId).map(String::valueOf).orElse(null))
                  .modelId(Optional.ofNullable(modelId).map(String::valueOf).map(Integer::parseInt).orElse(null))
                  .groupId(Optional.ofNullable(groupId).map(String::valueOf).map(Integer::parseInt).orElse(null))
                  .status("FAILED")
                  .stage(STAGE)
                  .message(response.message())
                  .groupId(entity.groupId)
                  .rootPipelineId(entity.rootPipelineId)
                  .build());
          log.info(aMarker, "The Exception occurred in paper classification response");
        }
      } catch (Exception e) {
        parentObj.add(HwClassificationOutputTable.builder()
                .createdUserId(Optional.ofNullable(createdUserId).map(String::valueOf).orElse(null))
                .lastUpdatedUserId(Optional.ofNullable(lastUpdatedUserId).map(String::valueOf).orElse(null))
                .tenantId(Optional.ofNullable(tenantId).map(String::valueOf).map(Long::valueOf).orElse(null))
                .originId(Optional.ofNullable(originId).map(String::valueOf).orElse(null))
                .paperNo(Optional.ofNullable(paperNo).map(String::valueOf).map(Integer::parseInt).orElse(null))
                .templateId(Optional.ofNullable(templateId).map(String::valueOf).orElse(null))
                .modelId(Optional.ofNullable(modelId).map(String::valueOf).map(Integer::parseInt).orElse(null))
                .groupId(Optional.ofNullable(groupId).map(String::valueOf).map(Integer::parseInt).orElse(null))
                .status("FAILED")
                .stage(STAGE)
                .message(ExceptionUtil.toString(e))
                .groupId(entity.groupId)
                .rootPipelineId(entity.rootPipelineId)
                .build());
        log.error(aMarker, "The Exception occurred in paper classification request", e);
        HandymanException handymanException = new HandymanException(e);
        HandymanException.insertException("Paper classification (hw-detection) consumer failed for batch/group "+ groupId, handymanException, this.action);

      }
      return parentObj;
    }
  }

  @AllArgsConstructor
  @NoArgsConstructor
  @Data
  @Builder
  public static class HwClassificationInputTable implements CoproProcessor.Entity{
    private String createdUserId;
    private String lastUpdatedUserId;
    private Long tenantId;
    private Double modelScore;
    private String originId;
    private Integer paperNo;
    private Integer groupId;
    private String templateId;
    private String modelId;
    private String filePath;
    private Long rootPipelineId;


    @Override
    public List<Object> getRowData() {
      return null;
    }
  }


  @AllArgsConstructor
  @NoArgsConstructor
  @Data
  @Builder
  public static class HwClassificationOutputTable implements CoproProcessor.Entity{

    private String createdUserId;
    private String lastUpdatedUserId;
    private Long tenantId;
    private String originId;
    private Integer groupId;
    private Integer paperNo;
    private String templateId;
    private Integer modelId;
    private String documentType;
    private Long confidenceScore;
    private String status;
    private String stage;
    private String message;
    private Long rootPipelineId;

    @Override
    public List<Object> getRowData() {
      return Stream.of(this.createdUserId,this.lastUpdatedUserId, this.tenantId,
              this.originId, this.paperNo, this.templateId, this.modelId,
              this.documentType, this.status, this.stage, this.message, this.groupId,this.rootPipelineId, this.confidenceScore).collect(Collectors.toList());

    }
  }

}