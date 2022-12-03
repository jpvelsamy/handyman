package in.handyman.raven.lib.adapters;

import in.handyman.raven.lib.interfaces.AdapterInterface;
import org.apache.commons.lang3.StringUtils;

public class IdAdapter implements AdapterInterface {
    @Override
    public boolean getValidationModel(String memberId, String requiredFeature) throws Exception {
        memberId = validateSpecialCharacters(requiredFeature, memberId);
        boolean isAlpha = StringUtils.isAlpha(memberId);
        boolean isAlphaNumeric = StringUtils.isAlphanumeric(memberId);
        return isAlphaNumeric && !isAlpha;
    }

    String validateSpecialCharacters(String specialCharacters, String input) {
        for (int i = 0; i < specialCharacters.length(); i++) {
            if (input.contains(Character.toString(specialCharacters.charAt(i)))) {
                input = input.replace(Character.toString(specialCharacters.charAt(i)), "");
            }
        }
        return input;
    }

    @Override
    public int getThresoldScore(String sentence) throws Exception {
        return 0;
    }

    @Override
    public boolean getDateValidationModel(String sentence, int comparableYear, String[] dateFormats) throws Exception {
        return false;
    }
}
