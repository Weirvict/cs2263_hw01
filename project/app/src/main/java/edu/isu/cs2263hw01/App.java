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
public class App extends Object {
    /**
     * Will print the options and help/usage display
     * @param args the command line arguments
     * @throws org.apache.commons.cli.ParseException
     */
    public static void main(String[] args) throws ParseException{
        // Creating and defining parser
        CommandLineParser parser = new DefaultParser();

        //Option build
        Options options = new Options();
        options.addOption(OptionBuilder.withLongOpt("batch").withDescription("The batch file containing expressions to evaluate").hasArg().withArgName("file").create('b'));
        options.addOption(OptionBuilder.withLongOpt("help").withDescription("Print usage message").create('h'));
        options.addOption(OptionBuilder.withLongOpt("output").withDescription("Output file").hasArg().withArgName("file").create('o'));

        Scanner scan = new Scanner(System.in);
        //ask user to put their input, either batch, help, or output
        System.out.println("Welcome to math evaluator! \n" +
                "Here are your options -batch, -help, -output \n" +
                "Please enter your choice: ");
        //String line = scan.nextLine();


        //help formatter
        HelpFormatter formatter = new HelpFormatter();

//        try {
            //parse the options passed as command line arguments
            CommandLine cmd = parser.parse(options, args);

            //Formatting the help/display if user chooses to do h
            if (cmd.hasOption("-h") || cmd.hasOption("-help") || cmd.hasOption("help")) {
                String footer = "Copyright (C) 2022";
                String header = "Evaluation of simple mathematical expressions\n\n";
                formatter.printHelp("eval", header, options, footer, true);
                System.exit(0);
            }
            else if (cmd.hasOption("-b") || cmd.hasOption("-batch") || cmd.hasOption("batch")) {
                //System.out.println("Batch works");
                String file = cmd.getOptionValue("batch");
                Path path = Paths.get(file);
                System.out.println("Batch value: " + path.toString());
            }
            else if (cmd.hasOption("-o") || cmd.hasOption("-output") || cmd.hasOption("output")) {
                    //System.out.println("Output works");
                    String file = cmd.getOptionValue("output");
                    Path path = Paths.get(file);
                    System.out.println("Output value: " + path.toString());
                }else{
                System.out.println("There has been an error. Please make sure it is typed in correctly!");
                System.exit(0);
        }

    }
}
