package Network;

public enum StatusCode {
    INPUT(10),
    SENSITIVE_INPUT(11),
    SUCCESS(20),
    REDIRECT_TEMPORARY(30),
    REDIRECT_PERMANENT(31),
    TEMPORARY_FAILURE(40),
    SERVER_UNAVAILABLE(41),
    CGI_ERROR(42),
    PROXY_ERROR(43),
    SLOW_DOWN(44),
    PERMANENT_FAILURE(50),
    NOT_FOUND(51),
    GONE(52),
    PROXY_REQUEST_REFUSED(53),
    BAD_REQUEST(59),
    CLIENT_CERTIFICATE_REQUIRED(60),
    CLIENT_CERTIFICATE_UNAUTHORIZED(61),
    CLIENT_CERTIFICATE_INVALID(62);

    private int val;

    StatusCode(int val) {
        this.val = val;
    }

    public int getNum() {
        return this.val;
    }

    public static StatusCode fromCode(int x) {
        return switch (x) {
            case 10 -> INPUT;
            case 11 -> SENSITIVE_INPUT;
            case 20 -> SUCCESS;
            case 30 -> REDIRECT_TEMPORARY;
            case 31 -> REDIRECT_PERMANENT;
            case 40 -> TEMPORARY_FAILURE;
            case 41 -> SERVER_UNAVAILABLE;
            case 42 -> CGI_ERROR;
            case 43 -> PROXY_ERROR;
            case 44 -> SLOW_DOWN;
            case 50 -> PERMANENT_FAILURE;
            case 51 -> NOT_FOUND;
            case 52 -> GONE;
            case 53 -> PROXY_REQUEST_REFUSED;
            case 59 -> BAD_REQUEST;
            case 60 -> CLIENT_CERTIFICATE_REQUIRED;
            case 61 -> CLIENT_CERTIFICATE_UNAUTHORIZED;
            case 62 -> CLIENT_CERTIFICATE_INVALID;
            default -> BAD_REQUEST;
        };
    }
}
