
/**
 * Class: CMSC204: 
 * Program: Assignment 2 -
 *  Summary of Description: a queue Data structure Test
 * Instructor: Huseyin Aygun
 * Integrity Pledge: I pledge that I have completed the programming assignment
 * independently.
 * * Due Date: 02/25/2024
 * I have not copied the code from a student or any source.
 * 
 * @author Kodjo Avougla
 */
import java.util.ArrayList;

/**
 * Class of a Queue data structure
 * 
 * @param <T> data type
 */
public class MyQueue<T> implements QueueInterface<T> {
    /**
     * variable meanings:
     * front: represents the front index.
     * I have hard coded front to always be 0 as
     * my code will move all items in the stack back 1 when there is a dequeue.
     * back: represents the rear index.
     * size: the current size of the queue.
     * queue: represents the queue.
     * maxSize: represents the max queue size. A default value is initialized if the
     * user does not provide a size.
     */
    private final int front = 0;
    private int maxSize = 15;
    private int back = 0, size = 0;
    private T[] queue;

    /**
     * provide two constructors
     * 1. takes an int as the size of the queue
     * 2. default constructor - uses a default as the size of the queue
     * 
     */
    @SuppressWarnings("unchecked")
    MyQueue() {
        queue = (T[]) new Object[maxSize];
    }

    @SuppressWarnings("unchecked")
    MyQueue(int maxSize) {
        this.maxSize = maxSize;
        queue = (T[]) new Object[maxSize];
    }

    /**
     * Determines if Queue is empty
     * 
     * @return true if Queue is empty, false if not
     */
    @Override
    public boolean isEmpty() {
        return size == 0; // if size is 0, queue is empty
    };

    /**
     * Determines of the Queue is Full
     * 
     * @return true if Queue is full, false if not
     */
    public boolean isFull() {
        // if size is less than maxSize, queue has space.
        // if not the queue is full
        return !(size < maxSize);
    };

    /**
     * Deletes and returns the element at the front of the Queue
     * 
     * @return the element at the front of the Queue
     * @throws QueueUnderflowException if queue is empty
     */
    @Override
    public T dequeue() throws QueueUnderflowException {
        if (isEmpty()) {
            throw new QueueUnderflowException("Cannot dequeue");
        } else {
            T frontEntry = queue[front]; // store front entry
            // move each item back one, overwriting the front entry in this.queue
            for (int i = front; i < size - 1; i++) {
                queue[i] = queue[i + 1];
            }
            back--; // since an item is being remove, the back must go back one
            size--; // size is reduced by one for the same reasoning
            queue[back] = null; // set last item in list to null
            return frontEntry; // return front entry
        }
    };

    /**
     * Returns number of elements in the Queue
     * 
     * @return the number of elements in the Queue
     */
    @Override
    public int size() {
        return size;
    };

    /**
     * Adds an element to the end of the Queue
     * 
     * @param e the element to add to the end of the Queue
     * @return true if the add was successful
     * @throws QueueOverflowException if queue is full
     */
    @Override
    public boolean enqueue(T e) throws QueueOverflowException {
        // Check if the max queue size is reached:
        // If there is space in the queue add item to queue,
        // else throw exception
        if (!isFull()) {
            queue[back] = e; // add the given entry to the end of the queue
            back++; // increase the queue rear index by 1
            size++; // increase the size of the queue as a new item is being added
            return true;
        } else {
            // Throw exception when queue is full
            // e.toString() is the string version of the variable being added so debugging
            // is easier.
            throw new QueueOverflowException(e.toString());
        }
    };

    /**
     * Returns the string representation of the elements in the Queue,
     * the beginning of the string is the front of the queue
     * 
     * @return string representation of the Queue with elements
     */
    @Override
    public String toString() {
        String result = ""; // delcare result string

        if (isEmpty()) {
            result = "No items in the queue."; // if is empty set result to "No items in the queue."
        } else {
            for (int i = front; i < size; i++) {
                result += queue[i]; // append all items in queue from front to back
            }
        }

        return result; // return result
    };

    /**
     * Returns the string representation of the elements in the Queue, the beginning
     * of the string is the front of the queue
     * Place the delimiter between all elements of the Queue
     * 
     * @return string representation of the Queue with elements separated with the
     *         delimiter
     */
    @Override
    public String toString(String delimiter) {
        String result = ""; // delcare result string

        if (isEmpty()) {
            result = "No items in the queue."; // if is empty set result to "No items in the queue."
        } else {
            for (int i = front; i < size; i++) {
                if (i == size - 1) {
                    delimiter = ""; // if last item in queue, set delimiter to empty string
                }
                result += queue[i] + delimiter; // append all items in queue from front to back
            }
        }
        return result; // return result
    };

    /**
     * Fills the Queue with the elements of the ArrayList, First element in the
     * ArrayList
     * is the first element in the Queue
     * YOU MUST MAKE A COPY OF LIST AND ADD THOSE ELEMENTS TO THE QUEUE, if you use
     * the
     * list reference within your Queue, you will be allowing direct access to the
     * data of
     * your Queue causing a possible security breech.
     * 
     * @param list elements to be added to the Queue
     * @throws QueueOverflowException if queue is full
     * 
     */
    @Override
    public void fill(ArrayList<T> list) throws QueueOverflowException {
        // create a copy of the arraylist list
        ArrayList<T> newList = new ArrayList<T>(list);

        // loop through all items in the cloned list
        for (int i = 0; i < newList.size(); i++) {
            // Check if the max queue size is reached:
            // If there is space in the queue add item to queue,
            // else throw exception
            if (!isFull()) {
                queue[back] = newList.get(i); // add the given entry to the end of the queue
                back++; // increase the queue rear index by 1
                size++; // increase the size of the queue as a new item is being added
            } else {
                // Throw exception when queue is full
                // newList.get(i).toString() is the string version of the variable being added
                // so debugging is easier.
                throw new QueueOverflowException(newList.get(i).toString());
            }
        }

    };

}
