package nextstep.security.aspect;

import nextstep.security.authentication.Authentication;
import nextstep.security.authentication.ForbiddenException;
import nextstep.security.authorization.manager.AuthorizationManager;
import nextstep.security.context.SecurityContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class SecuredAspect {
    private AuthorizationManager authorizationManager;

    public SecuredAspect(AuthorizationManager authorizationManager) {
        this.authorizationManager = authorizationManager;
    }

    @Before("@annotation(Secured)")
    public void checkSecured(JoinPoint joinPoint) throws NoSuchMethodException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if ( !authorizationManager.check(authentication, joinPoint).authorized() ) {
            throw new ForbiddenException();
        }
    }
}
