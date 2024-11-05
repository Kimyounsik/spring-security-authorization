package nextstep.security.authorization;

public class AuthorizationDecision {
    private boolean decision;
    public AuthorizationDecision(boolean decision) {
        this.decision = decision;
    }

    public boolean authorized() {
        return this.decision;
    }
}
