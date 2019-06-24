package lab3.guicalc;

//import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import lab3.operator.BinaryOperator;
import lab3.operator.BinaryOperatorImp;
import lab3.operator.UnaryOperator;
import lab3.operator.UnaryOperatorImp;

/**
 * Main program that runs the GUI Calculator
 */
public class Main {

    /**
     * Add BinaryOperators and UnaryOperators implements to the calculator.
     * 
     * @param args as input
     */
    public static void main(String[] args) {
        // Generating OperatorSet
        Set<BinaryOperator> binary= new LinkedHashSet<>();
        binary.add(BinaryOperatorImp.ADDITION);
        binary.add(BinaryOperatorImp.SUBTRACTION);
        binary.add(BinaryOperatorImp.MULTIPLICATION);
        binary.add(BinaryOperatorImp.DIVISION);
        binary.add(BinaryOperatorImp.EXPONENTIATION);
        
        Set<UnaryOperator> unary = new LinkedHashSet<>();
        unary.add(UnaryOperatorImp.NEGATION);
        unary.add(UnaryOperatorImp.ABS);

        // Run the calculator!
        new GuiCalculator(unary, binary);
    }
}
