package lab3.operator;

/**
 * Enum that contains implements of UnaryOperator
 * @author zhilinh
 *
 */
public enum UnaryOperatorImp implements UnaryOperator {
	ABS{
		
		/**
		 * toString method that returns the abs operator "abs".
		 */
		@Override
		public String toString() {
			return "abs";
		}
		
		/**
		 * Method that returns the absolute value of arg.
		 * 
		 * @param arg as the value.
		 * @return the absolute value.
		 */
		public double apply(double arg){
			double result = Math.abs(arg);
			return result;
		}
	},
	NEGATION{
		
		/**
		 * toString method that returns the negation operator "��".
		 */
		@Override
		public String toString() {
			return "neg";
		}
		
		/**
		 * Method that returns the value of arg with a contrary sign.
		 * 
		 * @param arg as the value.
		 * @return the negated value.
		 */
		public double apply(double arg){
			double result = - arg;
			return result;
		}
	};
}