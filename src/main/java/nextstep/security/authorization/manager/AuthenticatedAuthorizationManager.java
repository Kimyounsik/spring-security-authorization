package nextstep.security.authorization.manager;

import nextstep.security.authentication.Authentication;
import nextstep.security.authorization.AuthorizationDecision;

public class AuthenticatedAuthorizationManager implements AuthorizationManager {
    @Override
    public AuthorizationDecision check(Authentication authentication, Object object) {
        if (authentication.isAuthenticated()) {
            return new AuthorizationDecision(true);
        }
        return new AuthorizationDecision(false);
    }
}
