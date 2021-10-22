package in.handyman.raven.action;

public interface IActionExecution {

    void execute() throws Exception;

    boolean executeIf() throws Exception;

}
