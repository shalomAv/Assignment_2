public class StackOverflowException extends Exception {
    public StackOverflowException() {
        super("The stack is full. Please pop entries before more are added.");
    }

    public StackOverflowException(String entry) {
        super("\"" + entry
                + "\" was not added to the stack as the stack is full. Please pop entries before more are added.");
    }
}
