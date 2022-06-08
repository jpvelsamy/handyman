package in.handyman.raven.lambda.action;

public interface IActionExecution {

    void execute() throws Exception;

    boolean executeIf() throws Exception;

}
