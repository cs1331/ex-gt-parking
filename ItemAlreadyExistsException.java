class ItemAlreadyExistsException extends Exception {

    public ItemAlreadyExistsException() {
        super();
    }

    public ItemAlreadyExistsException(String msg) {
        super(msg);
    }

    public ItemAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    public ItemAlreadyExistsException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
