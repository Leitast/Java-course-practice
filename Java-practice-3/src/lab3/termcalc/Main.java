package lab3.termcalc;

import java.util.Scanner;
import lab3.expression.Expression;

/**
 * Main entry point for the command line calculator
 */
public class Main {
    /**
     * @param args program arguments
     */
    public static void main(String[] args) {
        ExpressionMaker myexpressionMaker = new  ExpressionMakerImp();
        TerminalCalculator mycalculator = new TerminalCalculator(myexpressionMaker);

        try (Scanner sc = new Scanner(System.in)) {
            do {
                System.out.print("Enter an expression: ");
                String myexpression = sc.nextLine();
                try {
                    Expression expression = mycalculator.run(myexpression);
                    System.out.printf("Result: %s%n", expression.eval());
                } catch (Exception e) {
                    System.out.println("Input format not accepted. Please try again." );
                }
            } while(true);
        }
    }
}
