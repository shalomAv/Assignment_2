public class QueueUnderflowException extends Exception {
    public QueueUnderflowException() {
        super("The queue is empty.");
    }

    public QueueUnderflowException(String errorMsg) {
        super(errorMsg + " as the queue is empty.");
    }
}
