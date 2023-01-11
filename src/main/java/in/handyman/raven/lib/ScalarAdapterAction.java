package in.handyman.raven.lib;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.collect.Lists;
import in.handyman.raven.exception.HandymanException;
import in.handyman.raven.lambda.access.ResourceAccess;
import in.handyman.raven.lambda.action.ActionExecution;
import in.handyman.raven.lambda.action.IActionExecution;
import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import in.handyman.raven.lib.model.ScalarAdapter;
import in.handyman.raven.lib.model.Validator;
import in.handyman.raven.util.CommonQueryUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * Auto Generated By Raven
 */
@ActionExecution(
        actionName = "ScalarAdapter"
)
public class ScalarAdapterAction implements IActionExecution {
    public static final String NER = "ner";
    private final ActionExecutionAudit action;

    private final Logger log;

    private final ScalarAdapter scalarAdapter;
    private final Marker aMarker;


    public ScalarAdapterAction(final ActionExecutionAudit action, final Logger log,
                               final Object scalarAdapter) {
        this.scalarAdapter = (ScalarAdapter) scalarAdapter;
        this.action = action;
        this.log = log;
        this.aMarker = MarkerFactory.getMarker(" ScalarAdapter:" + this.scalarAdapter.getName());
    }

    @Override
    public void execute() throws Exception {
        try {
            log.info(aMarker, "scalar has started" + scalarAdapter.getName());

            final Jdbi jdbi = ResourceAccess.rdbmsJDBIConn(scalarAdapter.getResourceConn());
            final List<ValidatorConfigurationDetail> validatorConfigurationDetails = new ArrayList<>();

            jdbi.useTransaction(handle -> {
                final List<String> formattedQuery = CommonQueryUtil.getFormattedQuery(scalarAdapter.getResultSet());
                formattedQuery.forEach(sqlToExecute -> {
                    validatorConfigurationDetails.addAll(handle.createQuery(sqlToExecute).
                            mapToBean(ValidatorConfigurationDetail.class).stream().collect(Collectors.toList()));
                });
            });


            doProcess(jdbi, validatorConfigurationDetails);
            log.info(aMarker, "scalar has completed" + scalarAdapter.getName());
        } catch (Exception e) {
            action.getContext().put(scalarAdapter.getName().concat(".error"), "true");
            log.info(aMarker, "The Exception occurred ", e);
            throw new HandymanException("Failed to execute", e);
        }
    }

    private void doProcess(final Jdbi jdbi, final List<ValidatorConfigurationDetail> validatorConfigurationDetails) {
        final int parallelism;
        if (scalarAdapter.getForkBatchSize() != null) {
            parallelism = Integer.parseInt(scalarAdapter.getForkBatchSize());
        } else {
            parallelism = 1;
        }
        log.info(aMarker, "total records to process {}", validatorConfigurationDetails.size());
        try {
            final int batchSize = validatorConfigurationDetails.size() / parallelism;
            if (parallelism > 1 && batchSize > 0) {
                log.info(aMarker, "parallel processing has started" + scalarAdapter.getName());
                final List<ValidatorConfigurationDetail> nerValidatorConfigurationDetails = validatorConfigurationDetails.stream().filter(validatorConfigurationDetail -> Objects.equals(validatorConfigurationDetail.getAllowedAdapter(), "")).collect(Collectors.toList());

                validatorConfigurationDetails.removeAll(nerValidatorConfigurationDetails);

                final List<List<ValidatorConfigurationDetail>> partition = Lists.partition(validatorConfigurationDetails, batchSize);

                final CountDownLatch countDownLatch = new CountDownLatch(partition.size());
                final ExecutorService executorService = Executors.newFixedThreadPool(parallelism);

                partition.forEach(items -> executorService.submit(() -> {
                    try {
                        items.forEach(validatorConfigurationDetail -> {

                            doCompute(jdbi, validatorConfigurationDetail);

                        });
                        log.info(aMarker, "total records to processed {}", items.size());
                    } finally {
                        countDownLatch.countDown();
                        log.info(aMarker, " {} batch processed", countDownLatch.getCount());
                    }
                }));

                if (!nerValidatorConfigurationDetails.isEmpty()) {
                    doProcess(jdbi, nerValidatorConfigurationDetails);
                }

                countDownLatch.await();

            } else {
                log.info(aMarker, "sequential processing has started" + scalarAdapter.getName());

                validatorConfigurationDetails.forEach(validatorConfigurationDetail -> {

                    doCompute(jdbi, validatorConfigurationDetail);

                });
            }
        } catch (Exception e) {
            log.error(aMarker, "The Failure Response {} --> {}", scalarAdapter.getName(), e.getMessage(), e);
        }
    }

    private void doCompute(final Jdbi jdbi, final ValidatorConfigurationDetail result) {
        String inputValue = result.getInputValue();
        int wordScore = WordcountAction.getWordCount(inputValue,
                result.getWordLimit(), result.getWordThreshold());
        int charScore = CharactercountAction.getCharCount(inputValue,
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

        result.setConfidenceScore(confidenceScore);

        jdbi.useTransaction(handle -> {
            handle.createUpdate("INSERT INTO sor_transaction.docnet_result" +
                            "( file_ref_id, paper_no, group_id, sor_id, sor_item_id, sor_item_name, question, answer, created_user_id, tenant_id, created_on, confidence_score) " +
                            "VALUES( :fileRefId, :paperNo, :groupId, :sorId, :sorItemId, :sorKey, :question, :inputValue, :createdUserId, :tenentId, NOW(), :confidenceScore);")
                    .bindBean(result).execute();
        });
    }


    int computeAdapterScore(Validator inputDetail) {
        int confidenceScore = 0;
        switch (inputDetail.getAdapter()) {
            case "alpha":
                confidenceScore = AlphavalidatorAction.getAlphaScore(inputDetail);
                break;
            case "alphanumeric":
                confidenceScore = AlphanumericvalidatorAction.getAlphaNumericScore(inputDetail);
                break;
            case "numeric":
                confidenceScore = NumericvalidatorAction.getNumericScore(inputDetail);
                break;
            case NER:
                final String URI = action.getContext().get("copro.text-validation.url");
                confidenceScore = NervalidatorAction.getNerScore(inputDetail, URI);
                break;
            case "date":
                confidenceScore = DatevalidatorAction.getDateScore(inputDetail);
                break;
        }
        return confidenceScore;
    }

    @Override
    public boolean executeIf() throws Exception {
        return scalarAdapter.getCondition();
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ValidatorConfigurationDetail {
        private int sorId;
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
        private String fileRefId;
        private int paperNo;
        private String groupId;
        private int sorItemId;
        private String createdUserId;
        private String tenentId;
        private double confidenceScore;

        private String sorItemName;
    }
}
