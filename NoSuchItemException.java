class NoSuchItemException extends Exception {

    public NoSuchItemException() {
        super();
    }

    public NoSuchItemException(String msg) {
        super(msg);
    }

    public NoSuchItemException(Throwable cause) {
        super(cause);
    }

    public NoSuchItemException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
