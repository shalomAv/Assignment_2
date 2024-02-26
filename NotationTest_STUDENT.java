
/**
 * Class: CMSC204: Huseyin Aygun
 * Program: Assignment 2 -
 * Instructor: 
 * Integrity Pledge: I pledge that I have completed the programming assignment
 * independently.
 * I have not copied the code from a student or any source.
 * 
 * @author Kodjo Avougla
 */
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

// This source was used to generate postfix from given postfix problems
// https://raj457036.github.io/Simple-Tools/prefixAndPostfixConvertor.html
public class NotationTest_STUDENT {
    String iExp1 = "3+5";
    String iExp2 = "((2+4)*6)/8";
    String iExp3 = "((7-3)*(8/4))+(6%2)";
    String iExp4 = "(2 + 3) * ((4 / 2) - 1)";

    String iExpErr = "1+)1-*5)(";

    String pExp1 = "35+";
    String pExp2 = "24+6*8/";
    String pExp3 = "73-84/*62%+";
    String pExp4 = "23+42/1-*";
    String pExpErr = "55+1+*112/0";

    double eval1 = 8;
    double eval2 = 4.5;
    double eval3 = 8;
    double eval4 = 5;

    @Test
    void testConvertInfixToPostfix() {
        try {
            assertEquals(Notation.convertInfixToPostfix(iExp1), pExp1);
            assertEquals(Notation.convertInfixToPostfix(iExp2), pExp2);
            assertEquals(Notation.convertInfixToPostfix(iExp3), pExp3);
            assertEquals(Notation.convertInfixToPostfix(iExp4), pExp4);

            Notation.convertInfixToPostfix(iExpErr);
            assertTrue(false, "This should have thrown an InvalidNotationFormatException");
        } catch (InvalidNotationFormatException e) {
            assertTrue(true, "This should have thrown an InvalidNotationFormatException");
        } catch (Exception e) {
            assertTrue(false, "This should have thrown an InvalidNotationFormatException");
        }

    }

    @Test
    void testConvertPostfixToInfix() {
        try {
            assertEquals(String.format("(%s)", iExp1), Notation.convertPostfixToInfix(pExp1));
            assertEquals(String.format("(%s)", iExp2), Notation.convertPostfixToInfix(pExp2));
            assertEquals(String.format("(%s)", iExp3), Notation.convertPostfixToInfix(pExp3));

            Notation.convertInfixToPostfix(pExpErr);
            assertTrue(false, "This should have thrown an InvalidNotationFormatException");
        } catch (InvalidNotationFormatException e) {
            assertTrue(true, "This should have thrown an InvalidNotationFormatException");
        } catch (Exception e) {
            assertTrue(false, "This should have thrown an InvalidNotationFormatException");
        }
    }

    @Test
    void testEvaluatePostfixExpression() {
        try {
            double calc1 = Notation.evaluatePostfixExpression(pExp1);
            double calc2 = Notation.evaluatePostfixExpression(pExp2);
            double calc3 = Notation.evaluatePostfixExpression(pExp3);
            double calc4 = Notation.evaluatePostfixExpression(pExp4);

            assertEquals(eval1, calc1, 0.01);
            assertEquals(eval2, calc2, 0.01);
            assertEquals(eval3, calc3, 0.01);
            assertEquals(eval4, calc4, 0.01);
        } catch (InvalidNotationFormatException e) {
            assertTrue(false, "An error occured while evaluating the expression.");
        } catch (Exception e) {
            assertTrue(false, "An exception that should not occur was thrown.");
        }
    }
}
