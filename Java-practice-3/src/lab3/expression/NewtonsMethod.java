package lab3.expression;

/**
 * Class to find a zero of a function using Newton's method
 * @author zhilinh
 *
 */
public class NewtonsMethod {

	/**
	* Returns a zero of the specified function using Newton's method with
	* approxZero as the initial estimate.
	*
	* @param fn the function whose zero is to be found
	* @param x the independent variable of the function
	* @param approxZero initial approximation for the zero of the function
	* @param tolerance how close to zero f(the returned value) has to be
	* @return a value x for which f(x) is "close to zero" (<= tolerance)
	*/
	public static double zero(Expression fn, Variable x, double approxZero, double tolerance) {
		
		// Continue the loop to find the zero when the difference between the value of
		// variable x and approxZero is larger than tolerance.
		double temp = 0;
		Expression dfndx = new DerivativeExpression(fn,x);
		x.store(approxZero);
		do {
			temp = x.eval() - fn.eval() / dfndx.eval();
			x.store(temp);
		} while (Math.abs(fn.eval()) > tolerance);
		return x.eval();
	}

}
