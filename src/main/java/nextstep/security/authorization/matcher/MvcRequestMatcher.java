package nextstep.security.authorization.matcher;

import nextstep.security.authorization.RequestMatcher;
import org.springframework.http.HttpMethod;

import javax.servlet.http.HttpServletRequest;

public class MvcRequestMatcher implements RequestMatcher {
    private HttpMethod httpMethod;
    private String path;

    public MvcRequestMatcher(HttpMethod httpMethod, String path) {
        this.httpMethod = httpMethod;
        this.path = path;
    }

    public boolean matches(HttpServletRequest request) {
        if (!request.getRequestURI().matches(path)) {
            return false;
        }

        if (!request.getMethod().equals(httpMethod.name())) {
            return false;
        }

        return true;
    }
}
