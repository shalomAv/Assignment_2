
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
// import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class MyStackTest {
	public MyStack<String> stringS;
	public String a = "a", b = "b", c = "c", d = "d", e = "e", f = "f";
	public ArrayList<String> fill = new ArrayList<String>();

	// STUDENT: student tests will use the doubleS
	public MyStack<Double> doubleS;
	// STUDENT: add variables as needed for your student tests
	public Double d1 = 17.46, d2 = -15.81, d3 = 7.08, d4 = -9.55,
			d5 = 5.86, d6 = 6.75, d7 = 4.44, d8 = 6.42;

	public ArrayList<Double> fillDouble = new ArrayList<Double>();

	@BeforeEach
	public void setUp() throws Exception {
		stringS = new MyStack<String>(5);
		stringS.push(a);
		stringS.push(b);
		stringS.push(c);

		// STUDENT: add setup for doubleS for student tests
		doubleS = new MyStack<Double>(8);
		doubleS.push(d1);
		doubleS.push(d2);
		doubleS.push(d3);
		doubleS.push(d4);
		doubleS.push(d5);
	}

	@AfterEach
	public void tearDown() throws Exception {
		stringS = null;
		doubleS = null;
	}

	@Test
	public void testIsEmpty() throws StackUnderflowException {
		assertEquals(false, stringS.isEmpty());
		stringS.pop();
		stringS.pop();
		stringS.pop();
		assertEquals(true, stringS.isEmpty());
	}

	@Test
	public void testIsFull() throws StackOverflowException {
		assertEquals(false, stringS.isFull());
		stringS.push(d);
		stringS.push(e);
		assertEquals(true, stringS.isFull());
	}

	@Test
	public void testPop() {
		try {
			assertEquals(c, stringS.pop());
			assertEquals(b, stringS.pop());
			assertEquals(a, stringS.pop());
			// Queue is empty, next statement should cause QueueUnderFlowException
			stringS.pop();
			assertTrue(false, "This should have caused an StackUnderflowException");
		} catch (StackUnderflowException e) {
			assertTrue(true, "This should have caused an StackUnderflowException");
		} catch (Exception e) {
			assertTrue(false, "This should have caused an StackUnderflowException");
		}
	}

	@Test
	public void testPopStudent() {
		// STUDENT TEST:
		try {
			assertEquals(d5, doubleS.pop());
			assertEquals(d4, doubleS.pop());
			assertEquals(d3, doubleS.pop());
			assertEquals(d2, doubleS.pop());
			assertEquals(d1, doubleS.pop());
			// Queue is empty, next statement should cause QueueUnderFlowException
			doubleS.pop();
			assertTrue(false, "This should have caused an StackUnderflowException");
		} catch (StackUnderflowException e) {
			assertTrue(true, "This should have caused an StackUnderflowException");
		} catch (Exception e) {
			assertTrue(false, "This should have caused an StackUnderflowException");
		}
	}

	@Test
	public void testTop() throws StackUnderflowException, StackOverflowException {
		assertEquals(c, stringS.top());
		stringS.push(d);
		assertEquals(d, stringS.top());
		stringS.pop();
		stringS.pop();
		assertEquals(b, stringS.top());
	}

	@Test
	public void testSize() throws StackOverflowException, StackUnderflowException {
		assertEquals(3, stringS.size());
		stringS.push(d);
		assertEquals(4, stringS.size());
		stringS.pop();
		stringS.pop();
		assertEquals(2, stringS.size());
	}

	@Test
	public void testPush() {
		try {
			assertEquals(3, stringS.size());
			assertEquals(true, stringS.push(d));
			assertEquals(4, stringS.size());
			assertEquals(true, stringS.push(e));
			assertEquals(5, stringS.size());
			// Queue is full, next statement should cause QueueOverFlowException
			stringS.push(f);
			assertTrue(false, "This should have caused an StackOverflowException");
		} catch (StackOverflowException e) {
			assertTrue(true, "This should have caused an StackOverflowException");
		} catch (Exception e) {
			assertTrue(false, "This should have caused an StackOverflowException");
		}
	}

	@Test
	public void testPushStudent() {
		// STUDENT TEST:
		try {
			assertEquals(true, doubleS.push(d6));
			assertEquals(6, doubleS.size());
			assertEquals(true, doubleS.push(d7));
			assertEquals(7, doubleS.size());
			assertEquals(true, doubleS.push(d8));
			assertEquals(8, doubleS.size());
			// Queue is full, next statement should cause QueueOverFlowException
			doubleS.push(d8);
			assertTrue(false, "This should have caused an StackOverflowException");
		} catch (StackOverflowException e) {
			assertTrue(true, "This should have caused an StackOverflowException");
		} catch (Exception e) {
			assertTrue(false, "This should have caused an StackOverflowException");
		}
	}

	@Test
	public void testToString() throws StackOverflowException {
		assertEquals("abc", stringS.toString());
		stringS.push(d);
		assertEquals("abcd", stringS.toString());
		stringS.push(e);
		assertEquals("abcde", stringS.toString());
	}

	@Test
	public void testToStringStudent() throws StackOverflowException {
		// STUDENT TEST:
		String t1 = String.format("%.2f%.2f%.2f%.2f%.2f", d1, d2, d3, d4, d5);
		assertEquals(t1, doubleS.toString());

		doubleS.push(d6);
		doubleS.push(d7);
		String t2 = String.format("%s%.2f%.2f", t1, d6, d7);
		assertEquals(t2, doubleS.toString());
		doubleS.push(d8);
		String t3 = String.format("%s%.2f", t2, d8);
		assertEquals(t3, doubleS.toString());
	}

	@Test
	public void testToStringDelimiter() throws StackOverflowException {
		assertEquals("a%b%c", stringS.toString("%"));
		stringS.push(d);
		assertEquals("a&b&c&d", stringS.toString("&"));
		stringS.push(e);
		assertEquals("a/b/c/d/e", stringS.toString("/"));

		// STUDENT TEST:
		String t1 = String.format("%.2f_%.2f_%.2f_%.2f_%.2f", d1, d2, d3, d4, d5);
		assertEquals(t1, doubleS.toString("_"));
	}

	@Test
	public void testFill() throws StackOverflowException, StackUnderflowException {
		fill.add("apple");
		fill.add("banana");
		fill.add("carrot");
		// start with an empty queue
		stringS = new MyStack<String>(5);
		// fill with an ArrayList
		stringS.fill(fill);
		assertEquals(3, stringS.size());
		assertEquals("carrot", stringS.pop());
		assertEquals("banana", stringS.pop());
		assertEquals("apple", stringS.pop());

		// STUDENT TEST:
		fillDouble.add(d5);
		fillDouble.add(d6);
		fillDouble.add(d7);
		fillDouble.add(d8);
		// start with an empty queue
		doubleS = new MyStack<Double>(5);
		// fill with an ArrayList
		doubleS.fill(fillDouble);
		assertEquals(4, doubleS.size());
		assertEquals(d8, doubleS.pop());
		assertEquals(d7, doubleS.pop());
		assertEquals(d6, doubleS.pop());
		assertEquals(d5, doubleS.pop());
	}

}
