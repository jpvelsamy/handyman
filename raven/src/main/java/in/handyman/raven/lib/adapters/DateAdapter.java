package in.handyman.raven.lib.adapters;

import in.handyman.raven.exception.HandymanException;
import in.handyman.raven.lib.interfaces.AdapterInterface;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateAdapter implements AdapterInterface {

    @Override
    public boolean getDateValidationModel(String dateValue, int comparableYear, String[] dateFormats) throws Exception {
        {
            if (dateValue.trim().equals("")) {
                return false;
            }

            for (String format : dateFormats) {
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat(format);
                    dateFormat.setLenient(false);
                    Date date = dateFormat.parse(dateValue);
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(date);
                    int year = calendar.get(Calendar.YEAR);
                    boolean yearValidation = false;
                    if (Integer.toString(year).length() >= 4) {
                        yearValidation = year < comparableYear ? true : false;
                    }
                    return yearValidation;
                } catch (ParseException ex) {
                    //throw new HandymanException("Failed to execute", ex);
                }
            }
            return false;
        }
    }

    @Override
    public boolean getValidationModel(String sentence, String requiredFeature) throws Exception {
        return false;
    }

    @Override
    public int getThresoldScore(String sentence) throws Exception {
        return 0;
    }

}
