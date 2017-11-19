package mysys.app.biz.service.exception;


public class SystemException extends Exception {

    public SystemException() {
    }

    public SystemException(String msg) {
        super(msg);
    }

    public SystemException(Throwable th) {
        super(th);
    }

    public SystemException(String msg, Throwable th) {
        super(msg, th);
    }

    private static final long serialVersionUID = 4568765216598564123L;
}
