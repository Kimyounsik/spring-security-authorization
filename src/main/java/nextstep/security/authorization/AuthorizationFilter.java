package nextstep.security.authorization;

import nextstep.security.authentication.Authentication;
import nextstep.security.authorization.manager.RequestMatcherDelegatingAuthorizationManager;
import nextstep.security.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizationFilter extends GenericFilterBean {
    private final RequestMatcherDelegatingAuthorizationManager manager;

    public AuthorizationFilter(RequestMatcherDelegatingAuthorizationManager manager) {
        this.manager = manager;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(manager.check(authentication, (HttpServletRequest) servletRequest).authorized()){
            filterChain.doFilter(servletRequest, servletResponse);
        }
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
    }
}
