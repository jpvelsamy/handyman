package in.handyman.raven.lib.adapters;

import in.handyman.raven.lib.interfaces.AdapterInterface;
import org.apache.commons.lang3.StringUtils;

public class AlphaNumericAdapter implements AdapterInterface {
    @Override
    public boolean getValidationModel(String input, String allowedCharacters) throws Exception {
        input = validateSpecialCharacters(allowedCharacters, input);
        boolean isAlphanumeric = StringUtils.isAlphanumeric(input);
        return isAlphanumeric;
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
