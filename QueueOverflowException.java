public class QueueOverflowException extends Exception {
    public QueueOverflowException() {
        super("The queue is full. Please dequeue entries from the queue before more are added.");
    }

    public QueueOverflowException(String entry) {
        super("\"" + entry
                + "\" was not added to the queue as the max queue limit is reached. Please dequeue entries from the queue before more are added.");
    }
}
