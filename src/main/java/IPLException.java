public class IPLException extends RuntimeException {
    enum ExceptionType {
        CENSUS_FILE_PROBLEM, UNABLE_TO_PARSE,NO_CENSUS_DATA, INVALID_COUNTRY;
    }

    ExceptionType type;

    public IPLException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
   }
