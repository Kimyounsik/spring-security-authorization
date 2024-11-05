package nextstep.security.authorization.manager;

import nextstep.security.aspect.Secured;
import nextstep.security.authentication.Authentication;
import nextstep.security.authorization.AuthorizationDecision;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

public class SecuredAuthorizationManager implements AuthorizationManager<JoinPoint> {
    @Override
    public AuthorizationDecision check(Authentication authentication, JoinPoint joinPoint) {
        Method method = null;
        try {
            method = getMethodFromJoinPoint(joinPoint);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        String secured = method.getAnnotation(Secured.class).value();
        if (authentication == null || !authentication.isAuthenticated()) {
            return new AuthorizationDecision(false);
        }

        if (authentication.getAuthorities().contains(secured)) {
            return new AuthorizationDecision(true);
        }

        return new AuthorizationDecision(false);
    }

    private Method getMethodFromJoinPoint(JoinPoint joinPoint) throws NoSuchMethodException {
        Class<?> targetClass = joinPoint.getTarget().getClass();
        String methodName = joinPoint.getSignature().getName();
        Class<?>[] parameterTypes = ((MethodSignature) joinPoint.getSignature()).getParameterTypes();

        return targetClass.getMethod(methodName, parameterTypes);
    }

}
