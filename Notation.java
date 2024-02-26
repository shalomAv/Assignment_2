/**
 * Class: CMSC204: 
 * Program: Assignment 2 -
 *  Summary of Description: converts infix to postfix, postfix to infix and
 * evaluates postfix notation
 * Instructor: Huseyin Aygun
 * Integrity Pledge: I pledge that I have completed the programming assignment
 * independently.
 * * Due Date: 02/25/2024
 * I have not copied the code from a student or any source.
 * 
 * @author Kodjo Avougla
 */

public class Notation {

    // empty constructor
    Notation() {
    }

    /**
     * A function to evaluate the total of postfix expressions
     * 
     * @param postfixExpr the postfix expression being evaluated
     * @return the total of the expression being evaluated
     * @throws InvalidNotationFormatException
     */
    public static double evaluatePostfixExpression(String postfixExpr) throws InvalidNotationFormatException {
        char c; // used to store current character on an iteration of the for loop
        double result; // delcare and initialize a variable for the result
        MyStack<String> s = new MyStack<>(50); // used to store operators and parathensis for logic

        // try catch for all logic happening in for loop
        try {
            // loop through all characters in the variable postfixExpr
            for (int i = 0; i < postfixExpr.length(); i++) {
                c = postfixExpr.charAt(i); // set c as the current character in the iteration

                // ignore space by continuing loop
                if (c == ' ') {
                    continue;
                }

                // if the character is a operand push it to the stack
                else if (isOperand(c)) {
                    s.push(Character.toString(c));
                }

                // a
                else if (isOperator(c)) {
                    if (s.size() < 2) {
                        // throw error if there is fewer than 2 values in the stack
                        // pass the postfix equation which is having errors for easier debugging
                        throw new InvalidNotationFormatException(postfixExpr);
                    }
                    String operand2 = s.pop(); // second operand
                    String operand1 = s.pop(); // first operand
                    double output = 0; // initialize a variable to store the calculation
                    try {
                        // convert the operands to doubles
                        double o1 = Double.valueOf(operand1);
                        double o2 = Double.valueOf(operand2);
                        // switch cases of the character c
                        switch (c) {
                            case '*':
                                output = o1 * o2;
                                break;
                            case '/':
                                // make sure operator 2 is not 0
                                if (o2 != 0) {
                                    output = o1 / o2;
                                    break;
                                }
                                System.out.println("Cannot divide by zero.");
                                return -999;
                            case '%':
                                // make sure operator 2 is not 0 and convert doubles to int as modulus does not
                                // work on double
                                if (o2 != 0) {
                                    output = (int) o1 % (int) o2;
                                    break;
                                }
                                System.out.println("Cannot modulus by zero.");
                                return -999;
                            case '+':
                                output = o1 + o2;
                                break;
                            case '-':
                                output = o1 - o2;
                                break;
                        }
                    } catch (NumberFormatException e) {
                        result = -999;
                    }

                    // convert the result to string and push it to the stack
                    s.push(Double.toString(output));
                }

            }

            if (s.size() != 1) {
                // throw error if there is more than 1 value in the stack
                // pass the postfix equation which is having errors for easier debugging
                result = -999;
                throw new InvalidNotationFormatException(postfixExpr);
            }
            result = Double.parseDouble(s.top());
        } catch (StackUnderflowException | StackOverflowException e) {
            throw new InvalidNotationFormatException(postfixExpr);
        }
        return result;
    }

    /**
     * A function to convert postfix expressions to infix expressions
     * 
     * @param postfix the postfix expression being converted
     * @return the infix expression in string format
     * @throws InvalidNotationFormatException
     */
    public static String convertPostfixToInfix(String postfix) throws InvalidNotationFormatException {
        char c; // used to store current character on an iteration of the for loop
        String result = ""; // delcare and initialize a result string
        MyStack<String> s = new MyStack<>(50); // used to store operands for logic

        // try catch for all logic happening in for loop
        try {
            // loop through all characters in the variable postfix
            for (int i = 0; i < postfix.length(); i++) {
                c = postfix.charAt(i); // set c as the current character in the iteration

                // ignore space by continuing loop
                if (c == ' ') {
                    continue;
                }

                // if the character is a operand push it to the stack
                else if (isOperand(c)) {
                    s.push(Character.toString(c));
                }

                // if there is an operator concatenate the last to operators with
                // the current operand and parentheses
                else if (isOperator(c)) {
                    if (s.size() < 2) {
                        // throw error if there is fewer than 2 values in the stack
                        // pass the postfix equation which is having errors for easier debugging
                        throw new InvalidNotationFormatException(postfix);
                    }
                    String operand2 = s.pop(); // second operand
                    String operand1 = s.pop(); // first operand
                    // put operands and operators together and encapsulate string with parentheses
                    String str = String.format("(%s%c%s)", operand1, c, operand2);
                    s.push(str);
                }

            }

            if (s.size() != 1) {
                // throw error if there is more than 1 value in the stack
                // pass the postfix equation which is having errors for easier debugging
                result = "Invalid format for given expression.";
                throw new InvalidNotationFormatException(postfix);
            }
            result = s.top();
        } catch (StackUnderflowException | StackOverflowException e) {
            throw new InvalidNotationFormatException(postfix);
        }

        return result;
    }

    /**
     * A function to convert infix expressions to postfix expressions
     * 
     * @param infix the infix expression being converted
     * @return the postfix expression in string format
     * @throws InvalidNotationFormatException
     */
    public static String convertInfixToPostfix(String infix) throws InvalidNotationFormatException {

        char c; // used to store current character on an iteration of the for loop
        MyStack<Character> s = new MyStack<>(50); // used to store operators and parathensis for logic
        MyQueue<Character> q = new MyQueue<>(50); // used to store output

        // try catch for all logic happening in for loop
        try {
            // loop through all characters in the variable infix
            for (int i = 0; i < infix.length(); i++) {
                c = infix.charAt(i); // set c as the current character in the iteration
                // ignore space by continuing loop
                if (c == ' ') {
                    continue;
                }

                // add character c to queue if it is a digit
                else if (isOperand(c)) {
                    q.enqueue(c);
                }

                // check for paranthesis
                else if (c == '(') {
                    s.push(c);
                }

                // check for operators and remove them from the stack as necessary
                else if (isOperator(c)) {
                    // while the precedence of the operator at the top of the stack
                    while (getPrecedence(s.top()) >= getPrecedence(c) && !s.isEmpty()) {
                        // remove the operator at the top of the stack and add it to the queue output
                        q.enqueue(s.pop());
                    }
                    s.push(c);

                }

                // check for closing paranthesis
                else if (c == ')') {
                    // while the top is the stack is not a opening paranthesis and not empty:
                    while (s.top() != '(' && !s.isEmpty()) {
                        // remove the operator at the top of the stack and add it to the queue output
                        q.enqueue(s.pop());
                    }
                    if (s.top() != '(' && !s.isEmpty()) {
                        // throw error if there is no opening paranthesis at the top of the stack
                        // pass the infix equation which is having errors for easier debugging
                        throw new InvalidNotationFormatException(infix);
                    }
                    s.pop();
                }

            }
            // while there is operators in the stack, add them to the solution and remove
            // them from the stack
            while (!s.isEmpty()) {
                q.enqueue(s.pop());
            }
        } catch (StackUnderflowException | QueueOverflowException | StackOverflowException e) {
            throw new InvalidNotationFormatException(infix);
        }

        return q.toString();
    }

    /**
     * A simple function to check if a character is an operator or not
     * 
     * @param c the character being checked
     * @return true if the character is an operator, false otherwise
     */
    private static boolean isOperator(char c) {
        return (c == '+' || c == '-' || c == '*' || c == '/' || c == '%');
    }

    /**
     * A simple function to get precendence of an operator character
     * 
     * @param c the operator character being checked
     * @return 2 if c is *, / or %
     *         1 if c is +, -;
     *         -1 otherwise
     */
    private static int getPrecedence(char c) {
        // if c is *, / or % return 2
        // if c is +, - return 1
        // else return -1
        switch (c) {
            case '*':
            case '/':
            case '%':
                return 2;
            case '+':
            case '-':
                return 1;
        }
        return -1;
    }

    /**
     * A simple function to check if a character is an operand
     * 
     * @param c the character being checked
     * @return true if c is an operand, false otherwise
     */
    private static boolean isOperand(char c) {
        // if c is not an operator or opening/closing parentheses return true
        return !isOperator(c) && c != '(' && c != ')';
    }
}
