package in.handyman.raven.lib.adapters;

import in.handyman.raven.lib.interfaces.AdapterInterface;

import java.util.StringTokenizer;

public class WordCountAdapter implements AdapterInterface {
    @Override
    public int getThresoldScore(String sentence) throws Exception {
        if (sentence == null || sentence.isEmpty()) {
            return 0;
        }
        StringTokenizer tokens = new StringTokenizer(sentence);
        return tokens.countTokens();
    }

    @Override
    public boolean getValidationModel(String sentence, String requiredFeature) throws Exception {
        return false;
    }

    @Override
    public boolean getDateValidationModel(String sentence, int comparableYear, String[] dateFormats) throws Exception {
        return false;
    }
}
