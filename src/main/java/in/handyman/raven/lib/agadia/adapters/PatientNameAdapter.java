package in.handyman.raven.lib.agadia.adapters;

import in.handyman.raven.lib.adapters.CharacterCountAdapter;
import in.handyman.raven.lib.adapters.NameAdapter;
import in.handyman.raven.lib.adapters.WordCountAdapter;
import in.handyman.raven.lib.interfaces.AdapterInterface;
import in.handyman.raven.lib.interfaces.ScalarEvaluationInterface;
import in.handyman.raven.lib.model.AgadiaAdapter;

public class PatientNameAdapter implements ScalarEvaluationInterface {
    AdapterInterface wordCountAdapter = new WordCountAdapter();
    AdapterInterface charCountAdapter = new CharacterCountAdapter();
    AdapterInterface nameValidatorAdapter = new NameAdapter();

    @Override
    public int getConfidenceScore(String patientName, AgadiaAdapter adapter) throws Exception {
        int confidenceScore = 0;
        int wcLimit = adapter.getWordCountLimit();
        int wcThresold = adapter.getWordCountThreshold();
        int charLimit = adapter.getCharCountLimit();
        int charThreshold = adapter.getCharCountThreshold();
        int validatorThresold = adapter.getValidatorThreshold();
        String validatorFeature = adapter.getValidatorDetail();

        boolean nameValidator = nameValidatorAdapter.getValidationModel(patientName, validatorFeature);
        confidenceScore = nameValidator ? confidenceScore + validatorThresold : confidenceScore;

        int formatScore = 0;
        if (nameValidator) {
            String[] splitInput = patientName.split(" ");
            for (String name : splitInput) {
                int wordCount = wordCountAdapter.getThresholdScore(name);
                formatScore = wordCount <= wcLimit ? formatScore + wcThresold : formatScore;
                int charCount = charCountAdapter.getThresholdScore(name);
                formatScore = charCount <= charLimit ? formatScore + charThreshold : formatScore;
            }
            formatScore = formatScore / splitInput.length;
        }
        confidenceScore = confidenceScore + formatScore;
        return confidenceScore;
    }
}
