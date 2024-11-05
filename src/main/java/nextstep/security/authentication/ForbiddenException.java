package nextstep.security.authentication;

public class ForbiddenException extends RuntimeException {
    public ForbiddenException(String message) {
        super(message);
    }
    public ForbiddenException(){
        super("Forbidden");
    }
}
