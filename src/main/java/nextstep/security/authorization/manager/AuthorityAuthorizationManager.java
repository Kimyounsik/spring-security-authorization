package nextstep.security.authorization.manager;

import nextstep.security.authentication.Authentication;
import nextstep.security.authorization.AuthorizationDecision;

import javax.servlet.http.HttpServletRequest;

public class AuthorityAuthorizationManager implements AuthorizationManager<HttpServletRequest> {
    private String role;
    public AuthorityAuthorizationManager(String role) {
        this.role = role;
    }

    @Override
    public AuthorizationDecision check(Authentication authentication, HttpServletRequest request) {
        if (authentication.getAuthorities().contains(role)) {
            return new AuthorizationDecision(true);
        }
        return new AuthorizationDecision(false);
    }
}
