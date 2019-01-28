package in.handyman.rest;


public interface RestClient {
    
    String post(String path, String payload);
    
    String get(String path);
    
    void createAuthToken();

}
