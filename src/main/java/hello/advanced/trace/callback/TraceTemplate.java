package hello.advanced.trace.callback;

import hello.advanced.logtrace.LogTrace;
import hello.advanced.trace.TraceStatus;

public class TraceTemplate {

    private final LogTrace trace;

    public TraceTemplate(LogTrace logTrace) {
        this.trace = logTrace;
    }

    public <T> T execute(String message, TraceCallback<T> callback) {
        TraceStatus status = null;

        try {
            status = trace.begin(message);

            //로직 호출
            T result = callback.call();

            trace.end(status);

            return result;
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
