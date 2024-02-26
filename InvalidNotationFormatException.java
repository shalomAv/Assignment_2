public class InvalidNotationFormatException extends Exception {
    InvalidNotationFormatException() {
        super("Notation format is invalid");
    }

    InvalidNotationFormatException(String input) {
        super("Notation format for \"" + input + "\" is invalid");
    }
}
