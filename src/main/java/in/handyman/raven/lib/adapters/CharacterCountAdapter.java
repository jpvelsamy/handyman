package in.handyman.raven.lib.adapters;

import in.handyman.raven.lib.interfaces.AdapterInterface;

public class CharacterCountAdapter implements AdapterInterface {
    @Override
    public int getThresoldScore(String sentence) throws Exception {
        if (sentence == null || sentence.isEmpty()) {
            return 0;
        }
        return sentence.length();
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
