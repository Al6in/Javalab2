import org.calculator.Calculator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class CalculatorTest {

    Calculator calculator;

    @Before
    public void before() {
        calculator = new Calculator();
    }

    @Test
    public void testSolve1() {
        calculator.input("1+3*4-22/2");
        Assert.assertEquals(calculator.solve(), 2.0, 0);
    }

    @Test
    public void testSolve2() {
        calculator.input("3*(33-2+3/1)-44+12/4");
        Assert.assertEquals(calculator.solve(), 61.0, 0);
    }

    @Test
    public void testSolve3() {
        calculator.input("sin(3)-tg(3)/234");
        Assert.assertEquals(calculator.solve(), 0.14, 0.01);
    }
}
