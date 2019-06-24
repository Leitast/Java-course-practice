
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import lab3.expression.AbsoluteValueExpression;
import lab3.expression.DerivativeExpression;
import lab3.expression.DifferenceExpression;
import lab3.expression.DivisionExpression;
import lab3.expression.ExponentiationExpression;
import lab3.expression.Expression;
import lab3.expression.NegationExpression;
import lab3.expression.NumberExpression;
import lab3.expression.ProductExpression;
import lab3.expression.SumExpression;
import lab3.expression.Variable;
import lab3.expression.NewtonsMethod;


public class CalcuTest {

	private Expression varexp;
	private Variable x = new Variable("x");
	private Expression data1;
	private Expression data2;
	private Expression temp;
	private Expression result;
	private Expression division;
	private Expression negation;
	private Expression absolute;
	private Expression exponent;
	private Expression sum;
	private Expression product;
	private Expression difference;

	/**
	 * called before each test. declare some Expressions
	 */
	@Before
	public void setUp() {
		data1 = new NumberExpression(2.0);
		data2 = new NumberExpression(3.0);
		temp = new ProductExpression(x, x);
		varexp = new DifferenceExpression(temp, data1);
		result = new DerivativeExpression(varexp, x);
	}

	@Test
	public void zeroTest() {
		double result = 0;
		result = NewtonsMethod.zero(varexp, x, 1, 0.0001);
		System.out.println(x);
		assertEquals(result, Math.sqrt(2), 0.0001);
	}

	@Test
	public void expressionText() {
		assertEquals(data1.toString(), "2.0");
		
		division = new DivisionExpression(data1, data1);
		assertEquals(division.toString(), "(2.0/2.0)");
		
		negation = new NegationExpression(data1);
		assertEquals(negation.toString(), "-(2.0)");
		
		absolute = new AbsoluteValueExpression(data1);
		assertEquals(absolute.toString(), "2.0");

		exponent = new ExponentiationExpression(data1, data2);
		assertEquals(exponent.eval()+"", "8.0");
		
		sum = new SumExpression(exponent, data1);
		assertEquals(sum.eval()+"", "10.0");

		product = new ProductExpression(data2, data1);
		assertEquals(product.toString(), "(3.0*2.0)");
		
		difference = new DifferenceExpression(data2, data1);
		assertEquals(difference.eval()+"", "1.0");
	}
}