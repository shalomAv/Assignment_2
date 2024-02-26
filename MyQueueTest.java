
/**
 * Class: CMSC204: 
 * Program: Assignment 2 -
 *  Summary of Description: a queue data structure Test
 * Instructor: Huseyin Aygun
 * Integrity Pledge: I pledge that I have completed the programming assignment
 * independently.
 * I have not copied the code from a student or any source.
 * * Due Date: 02/25/2024
 * @author Kodjo Avougla
 */

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class MyQueueTest {
	public MyQueue<String> stringQ;
	public String a = "a", b = "b", c = "c", d = "d", e = "e", f = "f";
	public ArrayList<String> fill = new ArrayList<String>();

	// STUDENT: student tests will use the doubleQ
	public MyQueue<Double> doubleQ;

	// STUDENT: add variables as needed for your student tests
	public Double d1 = 17.46, d2 = -15.81, d3 = 7.08, d4 = -9.55,
			d5 = 5.86, d6 = 6.75, d7 = 4.44, d8 = 6.42;

	public ArrayList<Double> fillDouble = new ArrayList<Double>();

	@BeforeEach
	public void setUp() throws Exception {
		stringQ = new MyQueue<String>(5);
		stringQ.enqueue(a);
		stringQ.enqueue(b);
		stringQ.enqueue(c);

		// STUDENT: add setup for doubleQ for student tests
		doubleQ = new MyQueue<Double>(8);
		doubleQ.enqueue(d1);
		doubleQ.enqueue(d2);
		doubleQ.enqueue(d3);
		doubleQ.enqueue(d4);
		doubleQ.enqueue(d5);
	}

	@AfterEach
	public void tearDown() throws Exception {
		stringQ = null;
		doubleQ = null;
	}

	@Test
	public void testIsEmpty() throws QueueUnderflowException {
		assertEquals(false, stringQ.isEmpty());
		stringQ.dequeue();
		stringQ.dequeue();
		stringQ.dequeue();
		assertEquals(true, stringQ.isEmpty());

		// STUDENT TEST:
		assertEquals(false, doubleQ.isEmpty());
		doubleQ.dequeue();
		doubleQ.dequeue();
		doubleQ.dequeue();
		assertEquals(false, doubleQ.isEmpty());
		doubleQ.dequeue();
		doubleQ.dequeue();
		assertEquals(true, doubleQ.isEmpty());
	}

	@Test
	public void testDequeue() {
		try {
			assertEquals(a, stringQ.dequeue());
			assertEquals(b, stringQ.dequeue());
			assertEquals(c, stringQ.dequeue());
			// Queue is empty, next statement should cause QueueUnderFlowException
			stringQ.dequeue();
			assertTrue(false, "This should have caused an QueueUnderflowException");
		} catch (QueueUnderflowException e) {
			assertTrue(true, "This should have caused an QueueUnderflowException");
		} catch (Exception e) {
			assertTrue(false, "This should have caused an QueueUnderflowException");
		}
	}

	@Test
	public void testDequeueStudent() {
		// STUDENT TEST:
		try {
			assertEquals(d1, doubleQ.dequeue());
			assertEquals(d2, doubleQ.dequeue());
			assertEquals(d3, doubleQ.dequeue());
			assertEquals(d4, doubleQ.dequeue());
			assertEquals(d5, doubleQ.dequeue());
			// Queue is empty, next statement should cause QueueUnderFlowException
			doubleQ.dequeue();
			assertTrue(false, "This should have caused an QueueUnderflowException");
		} catch (QueueUnderflowException e) {
			assertTrue(true, "This should have caused an QueueUnderflowException");
		} catch (Exception e) {
			assertTrue(false, "This should have caused an QueueUnderflowException");
		}
	}

	@Test
	public void testSize() throws QueueOverflowException, QueueUnderflowException {
		assertEquals(3, stringQ.size());
		stringQ.enqueue(d);
		assertEquals(4, stringQ.size());
		stringQ.dequeue();
		stringQ.dequeue();
		assertEquals(2, stringQ.size());
	}

	@Test
	public void testEnqueue() {
		try {
			assertEquals(3, stringQ.size());
			assertEquals(true, stringQ.enqueue(d));
			assertEquals(4, stringQ.size());
			assertEquals(true, stringQ.enqueue(e));
			assertEquals(5, stringQ.size());
			// Queue is full, next statement should cause QueueOverFlowException
			stringQ.enqueue(f);
			assertTrue(false, "This should have caused an QueueOverflowException");
		} catch (QueueOverflowException e) {
			assertTrue(true, "This should have caused an QueueOverflowException");
		} catch (Exception e) {
			assertTrue(false, "This should have caused an QueueOverflowException");
		}
	}

	@Test
	public void testEnqueueStudent() {
		// STUDENT TEST:
		try {
			assertEquals(true, doubleQ.enqueue(d6));
			assertEquals(6, doubleQ.size());
			assertEquals(true, doubleQ.enqueue(d7));
			assertEquals(7, doubleQ.size());
			assertEquals(true, doubleQ.enqueue(d8));
			assertEquals(8, doubleQ.size());
			// Queue is full, next statement should cause QueueOverFlowException
			doubleQ.enqueue(d8);
			assertTrue(false, "This should have caused an QueueOverflowException");
		} catch (QueueOverflowException e) {
			assertTrue(true, "This should have caused an QueueOverflowException");
		} catch (Exception e) {
			assertTrue(false, "This should have caused an QueueOverflowException");
		}
	}

	@Test
	public void testIsFull() throws QueueOverflowException {
		assertEquals(false, stringQ.isFull());
		stringQ.enqueue(d);
		stringQ.enqueue(e);
		assertEquals(true, stringQ.isFull());
	}

	@Test
	public void testToString() throws QueueOverflowException {
		assertEquals("abc", stringQ.toString());
		stringQ.enqueue(d);
		assertEquals("abcd", stringQ.toString());
		stringQ.enqueue(e);
		assertEquals("abcde", stringQ.toString());
	}

	@Test
	public void testToStringStudent() throws QueueOverflowException {
		// STUDENT TEST:
		String t1 = String.format("%.2f%.2f%.2f%.2f%.2f", d1, d2, d3, d4, d5);
		assertEquals(t1, doubleQ.toString());

		doubleQ.enqueue(d6);
		doubleQ.enqueue(d7);
		String t2 = String.format("%s%.2f%.2f", t1, d6, d7);
		assertEquals(t2, doubleQ.toString());
		doubleQ.enqueue(d8);
		String t3 = String.format("%s%.2f", t2, d8);
		assertEquals(t3, doubleQ.toString());
	}

	@Test
	public void testToStringDelimiter() throws QueueOverflowException {
		assertEquals("a%b%c", stringQ.toString("%"));
		stringQ.enqueue(d);
		assertEquals("a&b&c&d", stringQ.toString("&"));
		stringQ.enqueue(e);
		assertEquals("a/b/c/d/e", stringQ.toString("/"));

		// STUDENT TEST:
		String t1 = String.format("%.2f-%.2f-%.2f-%.2f-%.2f", d1, d2, d3, d4, d5);
		assertEquals(t1, doubleQ.toString("-"));

	}

	@Test
	public void testFill() throws QueueOverflowException, QueueUnderflowException {
		fill.add("apple");
		fill.add("banana");
		fill.add("carrot");
		// start with an empty queue
		stringQ = new MyQueue<String>(5);
		// fill with an ArrayList
		stringQ.fill(fill);
		assertEquals(3, stringQ.size());
		assertEquals("apple", stringQ.dequeue());
		assertEquals("banana", stringQ.dequeue());
		assertEquals("carrot", stringQ.dequeue());

		// STUDENT TEST:
		fillDouble.add(d5);
		fillDouble.add(d6);
		fillDouble.add(d7);
		fillDouble.add(d8);
		// start with an empty queue
		doubleQ = new MyQueue<Double>(5);
		// fill with an ArrayList
		doubleQ.fill(fillDouble);
		assertEquals(4, doubleQ.size());
		assertEquals(d5, doubleQ.dequeue());
		assertEquals(d6, doubleQ.dequeue());
		assertEquals(d7, doubleQ.dequeue());
		assertEquals(d8, doubleQ.dequeue());
	}

}
