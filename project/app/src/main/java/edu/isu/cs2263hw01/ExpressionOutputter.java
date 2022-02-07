package edu.isu.cs2263hw01;

/**
 * Class that will implement the output interface
 * @author Victoria Weir
 */

public class ExpressionOutputter implements Output{
    //V1.2.0
    public void output(String result) {
        System.out.printf("  -> %s\n", result);
    }
}
