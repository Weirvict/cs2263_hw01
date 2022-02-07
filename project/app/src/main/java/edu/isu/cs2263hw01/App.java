/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package edu.isu.cs2263hw01;

import org.apache.commons.cli.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

/**
 * Has options, accepts math equations using java
 * @author Victoria Weir
 */
public class App extends Object {
    /**
     * Simple method to process the expressions and return a result
     *
     * @param expr The input string from the user (assumed to be good input)
     * @return The value of the expression
     */
    public static int processExpression(String expr) {
        int result = 0;
        String[] components = expr.split(" ");
        List<String> operators = List.of("+", "-", "/", "*");
        String operator = "";
        for (String item : components) {
            if (!operators.contains(item)) {
                switch (operator) {
                    case "+" -> {
                        result += Integer.parseInt(item);
                    }
                    case "-" -> {
                        result -= Integer.parseInt(item);
                    }
                    case "/" -> {
                        result /= Integer.parseInt(item);
                    }
                    case "*" -> {
                        result *= Integer.parseInt(item);
                    }
                    default -> {
                        result = Integer.parseInt(item);
                    }
                }
            } else {
                operator = item;
            }
        }

        return result;
    }

    /**
     * Will print the options and help/usage display
     *
     * @param args the command line arguments
     * @throws org.apache.commons.cli.ParseException
     */
    public static void main(String[] args) throws ParseException {
        // Creating and defining parser
        CommandLineParser parser = new DefaultParser();
        //help formatter
        HelpFormatter formatter = new HelpFormatter();
        //output
        Output out = new ExpressionOutputter();


        //Option build
        Options options = new Options();
        options.addOption(OptionBuilder.withLongOpt("batch").withDescription("The batch file containing expressions to evaluate").hasArg().withArgName("file").create('b'));
        options.addOption(OptionBuilder.withLongOpt("help").withDescription("Print usage message").create('h'));
        options.addOption(OptionBuilder.withLongOpt("output").withDescription("Output file").hasArg().withArgName("file").create('o'));

        CommandLine cmd = parser.parse(options, args);


        Scanner scan = new Scanner(System.in);
        //ask user to put their input, either batch, help, or output
        System.out.println("Welcome to math evaluator! \n" +
                "Here are your options -batch, -help, -output \n" +
                "Please enter your choice: ");

        //Formatting the help/display if user chooses to do h
        if (cmd.hasOption("-h") || cmd.hasOption("-help") || cmd.hasOption("help")) {
            String footer = "Copyright (C) 2022";
            String header = "Evaluation of simple mathematical expressions\n\n";
            formatter.printHelp("eval", header, options, footer, true);
            System.exit(0);
        }
        //prints out the batch option
        else if (cmd.hasOption("-b") || cmd.hasOption("-batch") || cmd.hasOption("batch")) {
            //System.out.println("Batch works");
            String file = cmd.getOptionValue("batch");
            Path path = Paths.get(file);

            System.out.println("Batch value: " + path.getFileName().toString());
            if (cmd.hasOption("batch")) {
                String getFile = cmd.getOptionValue("batch");
                Path paths = Paths.get(getFile);
                if (Files.exists(paths)) {
                    path = paths;
                    //loop that will go through the equation
                    while (true) {
                        System.out.print("> ");
                        int result = Integer.parseInt(String.valueOf(path.iterator().hasNext()));
                        out.output(Integer.toString(result));
                    }
                } else {
                    System.out.println("The provided file for the batch input mode does not exist.");
                    System.exit(1);
                }
            }
            //prints out the output option
            else if (cmd.hasOption("-o") || cmd.hasOption("-output") || cmd.hasOption("output")) {
                //System.out.println("Output works");
                String fileOutput = cmd.getOptionValue("output");
                Path paths = Paths.get(fileOutput);
                System.out.println("Output value: " + paths.toString());
                //loop that will go through the equation
                while (true) {
                    System.out.print("> ");
                    int result = Integer.parseInt(String.valueOf(path.iterator().hasNext()));
                    out.output(Integer.toString(result));
                }
            } else{
                System.out.println("There has been an error. Please make sure it is typed in correctly!");
                System.exit(0);
            }
        }
    }
}
