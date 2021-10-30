package in.handyman.raven.lym.action;

public interface IActionExecution {

    void execute() throws Exception;

    boolean executeIf() throws Exception;

}
