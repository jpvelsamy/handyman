package in.handyman.raven.lib;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import in.handyman.raven.lambda.access.ResourceAccess;
import in.handyman.raven.lambda.action.ActionExecution;
import in.handyman.raven.lambda.action.IActionExecution;
import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import in.handyman.raven.lib.model.*;

import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import in.handyman.raven.util.ExceptionUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import okhttp3.*;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.argument.Arguments;
import org.jdbi.v3.core.argument.NullArgument;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

/**
 * Auto Generated By Raven
 */
@ActionExecution(
        actionName = "NerAdapter"
)
public class NerAdapterAction implements IActionExecution {
    private final ActionExecutionAudit action;

    private final Logger log;

    private final NerAdapter nerAdapter;

    private final Marker aMarker;


    public NerAdapterAction(final ActionExecutionAudit action, final Logger log,
                            final Object nerAdapter) {
        this.nerAdapter = (NerAdapter) nerAdapter;
        this.action = action;
        this.log = log;
        this.aMarker = MarkerFactory.getMarker(" NerAdapter:" + this.nerAdapter.getName());

    }

    @Override
    public void execute() throws Exception {

        try {
            log.info(aMarker, "ner adapter Action has been started {}", nerAdapter);

            final Jdbi jdbi = ResourceAccess.rdbmsJDBIConn(nerAdapter.getResourceConn());
            jdbi.getConfig(Arguments.class).setUntypedNullArgument(new NullArgument(Types.NULL));
            // build insert prepare statement with output table columns
            final String insertQuery = "INSERT INTO " + nerAdapter.getResultTable() +
                    "(origin_id, paper_no, group_id, process_id, sor_id, sor_item_id, sor_item_name, question, answer, created_user_id, tenant_id, created_on, word_score, char_score, validator_score_allowed, validator_score_negative, confidence_score, validation_name, b_box,status,stage,message)" +
                    " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,?, ?, ?, ?, ?, ?,?,?,?);";
            log.info(aMarker, "ner adpater Insert query {}", insertQuery);

            //3. initiate copro processor and copro urls
            final List<URL> urls = Optional.ofNullable(action.getContext().get("copro.text-validation.url")).map(s -> Arrays.stream(s.split(",")).map(s1 -> {
                try {
                    return new URL(s1);
                } catch (MalformedURLException e) {
                    log.error("Error in processing the URL ", e);
                    throw new RuntimeException(e);
                }
            }).collect(Collectors.toList())).orElse(Collections.emptyList());
            log.info(aMarker, "ner adpater copro urls {}", urls);

            final CoproProcessor<NerInputTable, NerOutputTable> coproProcessor =
                    new CoproProcessor<>(new LinkedBlockingQueue<>(),
                            NerOutputTable.class,
                            NerInputTable.class,
                            jdbi, log,
                            new NerInputTable(), urls, action);

            log.info(aMarker, "ner adpater copro coproProcessor initialization  {}", coproProcessor);

            //4. call the method start producer from coproprocessor
            coproProcessor.startProducer(nerAdapter.getResultSet(), Integer.valueOf(action.getContext().get("read.batch.size")));
            log.info(aMarker, "ner adpater copro coproProcessor startProducer called read batch size {}", action.getContext().get("read.batch.size"));
            Thread.sleep(1000);
            coproProcessor.startConsumer(insertQuery, Integer.valueOf(action.getContext().get("consumer.API.count")), Integer.valueOf(action.getContext().get("write.batch.size")),
                    new NerAdapterConsumerProcess(log, aMarker, action));
            log.info(aMarker, "ner adpater copro coproProcessor startConsumer called consumer count {} write batch count {} ", Integer.valueOf(action.getContext().get("consumer.API.count")), Integer.valueOf(action.getContext().get("write.batch.size")));


        } catch (Exception ex) {
            log.error(aMarker, "error in execute method for ner adpater  ", ex);
        }
    }

    public static class NerAdapterConsumerProcess implements CoproProcessor.ConsumerProcess<NerInputTable, NerOutputTable> {
        private final Logger log;
        private final Marker aMarker;
        private final NervalidatorAction nerAction;
        private final WordcountAction wordcountAction;
        private final CharactercountAction charactercountAction;
        private final AlphavalidatorAction alphaAction;
        private final NumericvalidatorAction numericAction;
        private final AlphanumericvalidatorAction alphaNumericAction;
        private final DatevalidatorAction dateAction;
        public final ActionExecutionAudit action;
        String URI;

        public NerAdapterConsumerProcess(Logger log, Marker aMarker, ActionExecutionAudit action) {
            this.log = log;
            this.aMarker = aMarker;
            this.action = action;
            this.nerAction = new NervalidatorAction(action, log, Nervalidator.builder().build());
            this.wordcountAction = new WordcountAction(action, log, Wordcount.builder().build());
            this.charactercountAction = new CharactercountAction(action, log, Charactercount.builder().build());
            this.alphaAction = new AlphavalidatorAction(action, log, Alphavalidator.builder().build());
            this.numericAction = new NumericvalidatorAction(action, log, Numericvalidator.builder().build());
            this.alphaNumericAction = new AlphanumericvalidatorAction(action, log, Alphanumericvalidator.builder().build());
            this.dateAction = new DatevalidatorAction(action, log, Datevalidator.builder().build());
        }

        @Override
        public List<NerOutputTable> process(URL endpoint, NerInputTable result) throws Exception {
            URI = String.valueOf(endpoint);
            log.info(aMarker, "coproProcessor consumer process started with endpoint {} and entity {}", endpoint, result);
            List<NerOutputTable> parentObj = new ArrayList<>();
            String inputValue = result.getInputValue();
            int wordScore = wordcountAction.getWordCount(inputValue,
                    result.getWordLimit(), result.getWordThreshold());
            int charScore = charactercountAction.getCharCount(inputValue,
                    result.getCharLimit(), result.getCharThreshold());
            Validator configurationDetails = Validator.builder()
                    .inputValue(inputValue)
                    .adapter(result.getAllowedAdapter())
                    .allowedSpecialChar(result.getAllowedCharacters())
                    .comparableChar(result.getComparableCharacters())
                    .threshold(result.getValidatorThreshold())
                    .build();

            int validatorScore = computeAdapterScore(configurationDetails);
            int validatorNegativeScore = 0;
            if (result.getRestrictedAdapterFlag() == 1 && validatorScore != 0) {
                configurationDetails.setAdapter(result.getRestrictedAdapter());
                validatorNegativeScore = computeAdapterScore(configurationDetails);
            }
            double confidenceScore = wordScore + charScore + validatorScore - validatorNegativeScore;
            AtomicInteger atomicInteger = new AtomicInteger();
            log.info(aMarker, "coproProcessor consumer confidence score  {}", confidenceScore);
            if (confidenceScore >= 0) {
                parentObj.add(
                        NerOutputTable
                                .builder()
                                .originId(Optional.ofNullable(result.getOriginId()).map(String::valueOf).orElse(null))
                                .groupId(result.getGroupId())
                                .sorId(result.sorId)
                                .tenantId(result.tenantId)
                                .processId(result.processId)
                                .paperNo(result.paperNo)
                                .sorItemId(result.sorItemId)
                                .sorItemName(result.sorItemName)
                                .question(result.question)
                                .answer(result.inputValue)
                                .createdUserId(result.createdUserId)
                                .createdOn(Timestamp.valueOf(LocalDateTime.now()))
                                .wordScore(wordScore)
                                .charScore(charScore)
                                .validatorScoreAllowed(validatorScore)
                                .validatorScoreNegative(validatorNegativeScore)
                                .confidenceScore(confidenceScore)
                                .validationName(result.allowedAdapter)
                                .bBox(result.bbox)
                                .status("COMPLETED")
                                .stage("SCALAR_VALIDATION")
                                .message("Ner validation macro completed")
                                .build());

            } else {
                parentObj.add(
                        NerOutputTable
                                .builder()
                                .originId(Optional.ofNullable(result.getOriginId()).map(String::valueOf).orElse(null))
                                .groupId(result.getGroupId())
                                .sorId(result.sorId)
                                .tenantId(result.tenantId)
                                .processId(result.processId)
                                .paperNo(result.paperNo)
                                .sorItemId(result.sorItemId)
                                .sorItemName(result.sorItemName)
                                .question(result.question)
                                .answer(result.inputValue)
                                .createdUserId(result.createdUserId)
                                .createdOn(Timestamp.valueOf(LocalDateTime.now()))
                                .wordScore(wordScore)
                                .charScore(charScore)
                                .validatorScoreAllowed(validatorScore)
                                .validatorScoreNegative(validatorNegativeScore)
                                .confidenceScore(confidenceScore)
                                .validationName(result.allowedAdapter)
                                .bBox(result.bbox)
                                .status("FAILED")
                                .stage("SCALAR_VALIDATION")
                                .message("Confidence Score is less than 0")
                                .build());
                log.error(aMarker, "The Exception occurred in confidence score validation by {} ", confidenceScore);
            }

            atomicInteger.set(0);
            log.info(aMarker, "coproProcessor consumer process with output entity {}", parentObj);
            return parentObj;
        }

        int computeAdapterScore(Validator inputDetail) {
            int confidenceScore = 0;
            try {
                switch (inputDetail.getAdapter()) {
                    case "alpha":
                        confidenceScore = this.alphaAction.getAlphaScore(inputDetail);
                        break;
                    case "alphanumeric":
                        confidenceScore = this.alphaNumericAction.getAlphaNumericScore(inputDetail);
                        break;
                    case "numeric":
                        confidenceScore = this.numericAction.getNumericScore(inputDetail);
                        break;
                    case "ner":
                        confidenceScore = this.nerAction.getNerScore(inputDetail, URI);
                        break;
                    case "date":
                        confidenceScore = this.dateAction.getDateScore(inputDetail);
                        break;
                }
            } catch (Throwable t) {
                log.error(aMarker, "error adpater validation{}", inputDetail, t);
            }
            return confidenceScore;
        }
    }

    @Override
    public boolean executeIf() throws Exception {
        return nerAdapter.getCondition();
    }


    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class NerInputTable implements CoproProcessor.Entity {
        private int sorId;
        private String originId;
        private String processId;
        private String sorKey;
        private String question;
        private String inputValue;
        private String allowedAdapter;
        private String restrictedAdapter;
        private int wordLimit;
        private int wordThreshold;
        private int charLimit;
        private int charThreshold;
        private int validatorThreshold;
        private String allowedCharacters;
        private String comparableCharacters;
        private int restrictedAdapterFlag;
        private int paperNo;
        private Integer groupId;
        private String bbox;
        private int sorItemId;
        private String createdUserId;
        private String tenantId;
        private double wordScore;
        private double charScore;
        private double validatorScore;
        private double validatorNegativeScore;
        private double confidenceScore;
        private String sorItemName;
        private int weight;

        @Override
        public List<Object> getRowData() {
            return null;
        }
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class NerOutputTable implements CoproProcessor.Entity {
        private String originId;
        private int paperNo;
        private int groupId;
        private String processId;
        private int sorId;
        private int sorItemId;
        private String sorItemName;
        private String question;
        private String answer;
        private String createdUserId;
        private String tenantId;
        private Timestamp createdOn;
        private double wordScore;
        private double charScore;
        private double validatorScoreAllowed;
        private double validatorScoreNegative;
        private double confidenceScore;
        private String validationName;
        private String bBox;
        private String status;
        private String stage;
        private String message;

        @Override
        public List<Object> getRowData() {
            return Stream.of(this.originId, this.paperNo, this.groupId, this.processId, this.sorId, this.sorItemId, this.sorItemName,
                    this.question, this.answer, this.createdUserId, this.tenantId, this.createdOn, this.wordScore, this.charScore,
                    this.validatorScoreAllowed, this.validatorScoreNegative, this.confidenceScore, this.validationName, this.bBox,
                    this.status, this.stage, this.message
            ).collect(Collectors.toList());
        }

    }
}
