public class IPLException extends RuntimeException {
    enum ExceptionType {
        IPL_FILE_PROBLEM, NO_IPL_DATA;
    }

    ExceptionType type;

    public IPLException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
}
