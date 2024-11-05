package nextstep.security.authorization.manager;

import nextstep.security.authentication.Authentication;
import nextstep.security.authorization.AuthorizationDecision;

public class PermitAllAuthorizationManager implements AuthorizationManager<Object> {
    @Override
    public AuthorizationDecision check(Authentication authentication, Object object) {
        return new AuthorizationDecision(true);
    }
}
