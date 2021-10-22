package in.handyman.raven.process;

public interface Handler<I, C> {
    void process(C context, I input);
}
