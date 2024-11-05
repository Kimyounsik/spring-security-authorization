package nextstep.security.authorization;

import javax.servlet.http.HttpServletRequest;

public interface RequestMatcher {
    boolean matches(HttpServletRequest request);
}
