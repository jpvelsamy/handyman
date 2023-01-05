package in.handyman.raven.lib.interfaces;

public interface AdapterInterface {
    int getThresoldScore(String sentence) throws Exception;

    boolean getValidationModel(String sentence,String requiredFeature) throws Exception;
    boolean getDateValidationModel(String sentence,int comparableYear,String[] dateFormats) throws Exception;

}
