
/**
 * Class: CMSC204: 
 * Program: Assignment 2 -
 *  Summary of Description: a Stack data structure Test
 * Instructor: Huseyin Aygun
 * Integrity Pledge: I pledge that I have completed the programming assignment
 * independently.
 * I have not copied the code from a student or any source.
 * * Due Date: 02/25/2024
 * @author Kodjo Avougla
 */
import java.util.ArrayList;

/**
 * Interface for a generic Stack data structure
 * 
 * @param <T> data type
 */
public class MyStack<T> implements StackInterface<T> {

    private int size = 0, top = -1;
    private int maxSize = 15;
    private T[] stack;

    /**
     * Provide two constructors
     * 1. takes in an int as the size of the stack
     * 2. default constructor - uses default as the size of the stack
     */
    @SuppressWarnings("unchecked")
    MyStack() {
        stack = (T[]) new Object[maxSize];
    }

    @SuppressWarnings("unchecked")
    MyStack(int maxSize) {
        this.maxSize = maxSize;
        stack = (T[]) new Object[maxSize];
    }

    /**
     * Determines if Stack is empty
     * 
     * @return true if Stack is empty, false if not
     */
    @Override
    public boolean isEmpty() {
        // if size is zero or top index is negative return true
        return size == 0 || top < 0;
    };

    /**
     * Determines if Stack is full
     * 
     * @return true if Stack is full, false if not
     */
    @Override
    public boolean isFull() {
        // if size is less than maxSize, queue has space.
        // if not the queue is full
        return !(size < maxSize);
    };

    /**
     * Deletes and returns the element at the top of the Stack
     * 
     * @return the element at the top of the Stack
     * @throws StackUnderflowException if stack is empty
     */
    @Override
    public T pop() throws StackUnderflowException {
        if (isEmpty()) {
            throw new StackUnderflowException();
        }
        top--; // decrement top
        size--; // decrement size
        // return the top value
        // there is no reason to change data in array as it will
        // be overwritten in the next call of push()
        return stack[top + 1];
    };

    /**
     * Returns the element at the top of the Stack, does not pop it off the Stack
     * 
     * @return the element at the top of the Stack
     * @throws StackUnderflowException if stack is empty
     */
    @Override
    public T top() throws StackUnderflowException {
        if (isEmpty()) {
            throw new StackUnderflowException(); // if empty throw exception
        }
        return stack[top]; // if not not empty return top of stack
    };

    /**
     * Number of elements in the Stack
     * 
     * @return the number of elements in the Stack
     */
    @Override
    public int size() {
        return size; // return current size of stack
    };

    /**
     * Adds an element to the top of the Stack
     * 
     * @param e the element to add to the top of the Stack
     * @return true if the add was successful, false if not
     * @throws StackOverflowException if stack is full
     */
    @Override
    public boolean push(T e) throws StackOverflowException {
        if (!isFull()) {
            top++; // increment top
            size++; // increment the size
            stack[top] = e; // add new entry to the top of the stack
            return true;
        } else {
            // throw exception if stack is full
            // e.toString() returns entry for debugging purposes
            throw new StackOverflowException(e.toString());
        }
    };

    /**
     * Returns the elements of the Stack in a string from bottom to top, the
     * beginning
     * of the String is the bottom of the stack
     * 
     * @return an string which represent the Objects in the Stack from bottom to top
     */
    @Override
    public String toString() {
        String result = ""; // delcare result string

        if (isEmpty()) {
            result = "No items in the stack."; // if is empty set result to "No items in the stack."
        } else {
            for (int i = 0; i < size; i++) {
                result += stack[i]; // append all items in stack from bottom to top
            }
        }

        return result; // return result
    };

    /**
     * Returns the string representation of the elements in the Stack, the beginning
     * of the
     * string is the bottom of the stack
     * Place the delimiter between all elements of the Stack
     * 
     * @return string representation of the Stack from bottom to top with elements
     *         separated with the delimiter
     */
    @Override
    public String toString(String delimiter) {
        String result = ""; // delcare result string

        if (isEmpty()) {
            result = "No items in the stack."; // if is empty set result to "No items in the stack."
        } else {
            for (int i = 0; i < size; i++) {
                if (i == size - 1) {
                    delimiter = ""; // reset delimiter on last iteration
                }
                result += stack[i] + delimiter; // append all items in stack from bottom to top
            }
        }

        return result; // return result
    };

    /**
     * Fills the Stack with the elements of the ArrayList, First element in the
     * ArrayList
     * is the first bottom element of the Stack
     * YOU MUST MAKE A COPY OF LIST AND ADD THOSE ELEMENTS TO THE STACK, if you use
     * the
     * list reference within your Stack, you will be allowing direct access to the
     * data of
     * your Stack causing a possible security breech.
     * 
     * @param list elements to be added to the Stack from bottom to top
     * @throws StackOverflowException if stack gets full
     */
    @Override
    public void fill(ArrayList<T> list) throws StackOverflowException {
        // create a copy of the arraylist list
        ArrayList<T> newList = new ArrayList<T>(list);

        // loop through all items in the cloned list
        for (int i = 0; i < newList.size(); i++) {
            // Check if the max stack size is reached:
            // If there is space in the stack add item to the stack,
            // else throw exception
            if (size < maxSize) {
                top++; // increment top
                size++; // increment size
                stack[top] = newList.get(i); // add the given entry to the top of the stack
            } else {
                // Throw exception when queue is full
                // newList.get(i).toString() is the string version of the variable being added
                // so debugging is easier.
                throw new StackOverflowException(newList.get(i).toString());
            }
        }

    };

}
