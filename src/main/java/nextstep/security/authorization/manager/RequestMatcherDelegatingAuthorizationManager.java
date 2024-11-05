package nextstep.security.authorization.manager;

import nextstep.security.authentication.Authentication;
import nextstep.security.authorization.AuthorizationDecision;
import nextstep.security.authorization.RequestMatcherEntry;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public class RequestMatcherDelegatingAuthorizationManager implements AuthorizationManager<HttpServletRequest> {
    private List<RequestMatcherEntry<AuthorizationManager>> mappings;


    public RequestMatcherDelegatingAuthorizationManager(List<RequestMatcherEntry<AuthorizationManager>> mappings) {
        this.mappings = mappings;
    }

    @Override
    public AuthorizationDecision check(Authentication authentication, HttpServletRequest request) {

        Optional<RequestMatcherEntry<AuthorizationManager>> any = mappings.stream().filter(v -> v.getMatcher().matches(request)).findAny();
        if(any.isPresent()) {
            return any.get().getManager().check(authentication, request);
        }
        return new AuthorizationDecision(false);
    }
}
