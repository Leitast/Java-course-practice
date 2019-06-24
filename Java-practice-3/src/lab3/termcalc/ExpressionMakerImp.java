package lab3.termcalc;

import lab3.expression.Expression;

import lab3.expression.AbsoluteValueExpression;
import lab3.expression.DifferenceExpression;
import lab3.expression.DivisionExpression;
import lab3.expression.ExponentiationExpression;
import lab3.expression.NegationExpression;
import lab3.expression.NumberExpression;
import lab3.expression.ProductExpression;
import lab3.expression.SumExpression;

/**
 * Class to implement ExpressionMaker and create a new Expression
 * @author zhilinh
 *
 */
public class ExpressionMakerImp implements ExpressionMaker{

	/**
	 * Method that returns a new Expression of the sum of addend1 and addend2.
	 */
	@Override
	public Expression sumExpression(Expression addend1, Expression addend2) {
		Expression sum=new SumExpression(addend1, addend2);
		return sum;
	}

	/**
	 * Method that returns a new Expression of the difference of op1 and op2.
	 */
	@Override

	public Expression differenceExpression(Expression op1, Expression op2) {
		Expression diff = new DifferenceExpression(op1, op2);
		return diff;
	}

	/**
	 * Method that returns a new Expression of the product of factor1 and factor2.
	 */
	@Override
	public Expression productExpression(Expression factor1, Expression factor2) {
		Expression product = new ProductExpression(factor1,factor2);
		return product;
	}

	/**
	 * Method that returns a new Expression of the division of dividend and divisor.
	 */
	@Override
	public Expression divisionExpression(Expression dividend, Expression divisor) {
		Expression division = new DivisionExpression(dividend,divisor);
		return division;
	}

	/**
	 * Method that returns a new Expression of the exponentiation of base and exponent.
	 */
	@Override
	public Expression exponentiationExpression(Expression base, Expression exponent) {
		Expression expon = new ExponentiationExpression(base,exponent);
		return expon;
	}

	/**
	 * Method that returns a new Expression of the the negated operand.
	 */
	@Override
	public Expression negationExpression(Expression operand) {
		Expression neg = new NegationExpression(operand);
		return neg;
	}

	/**
	 * Method that returns a new Expression of the absolute value Expression of value.
	 */
	@Override
	public Expression absoluteValueExpression(Expression value) {
		Expression abs = new AbsoluteValueExpression(value);
		return abs;
	}

	/**
	 * Method that returns a new Expression of the value.
	 */
	@Override
	public Expression numberExpression(double value) {
		Expression val = new NumberExpression(value);
		return val;
	}

}
