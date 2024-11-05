package nextstep.security.authorization;

public class RequestMatcherEntry<T> {
    private RequestMatcher matcher;
    private T manager;

    public RequestMatcherEntry (RequestMatcher matcher, T manager) {
        this.matcher = matcher;
        this.manager = manager;
    }

    public RequestMatcher getMatcher() {
        return matcher;
    }

    public T getManager() {
        return manager;
    }
}
