package nextstep.security.authentication;

import java.util.List;
import java.util.Set;

public interface Authentication {

    Object getCredentials();

    Object getPrincipal();

    Set<String> getAuthorities();

    boolean isAuthenticated();
}
