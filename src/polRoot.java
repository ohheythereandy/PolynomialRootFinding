import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class polRoot {

    private static final int DEFAULT_MAXITER = 10000;
    private static final int HYBRID_ITERSWITCH = 10;
    /**
     * Main entry point of program.
     * @param args is the arguments passed in the command line
     */
    public static void main(String[] args) {

        parseInput(args);

    }

    /**
     * This method determines which method to use by checking for the presence of certain flags passed as arguments
     * to the main method.
     * @param args are the arguments passed in the command line
     */
    private static void parseInput(String[] args) {

        if(args.length < 3) {
            System.out.println("You are missing an argument");
        }
        else if(args.length == 3) {
            //either standard bisection, or newton's
            if(args[0].equals("-newt")){ //newton's
                startNewtons(args);
            } else {
                //supposed to be bisection
                startBisection(args);
            }
        }
        else if(args.length == 4) { // standard Secant Method or Hybrid

            if(args[0].equals("-hybrid")){
                startHybrid(args);
            }
            else{
                startSecant(args);
            }
        }
        else if(args.length == 5) { //max iteration for bisection or newt

            if(args[0].equals( "-newt" )){
                startNewtonsCustomIteration(args);
            } else {
                startBisectionCustomIteration(args);
            }
        }
        else if(args.length == 6) { // max iteration for secant or hybrid
            if (args[0].equals("-hybrid")) {
                startHybridCustomIteration(args);

            } else {
                startSecantCustomIteration(args);
            }
        }
        else {                      //too many arguments
            System.out.println("You've inputted too many arguments, please try again.");
        }
    }

    private static void startHybridCustomIteration(String[] args) {

        RootFinder finder = new RootFinder();

        int maxIterations = Integer.valueOf(args[2]);
        float point1 = Float.valueOf(args[3]);
        float point2 = Float.valueOf(args[4]);
        String fileName = args[5];

        System.out.println("Starting bisection method using point1 as: " + point1 + " and point2 as: " + point2);
        if(maxIterations < HYBRID_ITERSWITCH)
            finder.bisection(point1, point2, maxIterations, Float.MIN_VALUE, fileName, true);
        else{
            finder.bisection(point1, point2, HYBRID_ITERSWITCH, Float.MIN_VALUE, fileName, true);
            System.out.println("Finished running bisection for " + HYBRID_ITERSWITCH + " iterations.");
        }

        //check if converged. if so, output to file. otherwise, switch to hybrid
        if(finder.outcome = true)
            outputToFile(fileName, finder);

        finder.newtons(finder.lastPointUsed, maxIterations, Float.MIN_VALUE, Float.MIN_VALUE, fileName);
        outputToFile(fileName, finder);

    }


    /*
        This method uses bisection method for early iterations, then switches to using newton's method.
        The exact iterations that bisection will be used for is determined by the constant set.
     */
    private static void startHybrid(String[] args) {

        RootFinder finder = new RootFinder();

        float point1 = Float.valueOf(args[1]);
        float point2 = Float.valueOf(args[2]);
        String fileName = args[3];

        System.out.println("Starting bisection method using point1 as: " + point1 + " and point2 as: " + point2);
        finder.bisection(point1, point2, HYBRID_ITERSWITCH, Float.MIN_VALUE, fileName, true);
        System.out.println("Finished running bisection for " + HYBRID_ITERSWITCH + " iterations.");

        //check if converged. if so, output to file. otherwise, switch to hybrid
        if(finder.outcome = true)
            outputToFile(fileName, finder);

        finder.newtons(finder.lastPointUsed, DEFAULT_MAXITER, Float.MIN_VALUE, Float.MIN_VALUE, fileName);
        outputToFile(fileName, finder);

    }

    /*
        This method handles the secant method with default max iteration.
     */
    private static void startSecant(String[] args){
        RootFinder finder = new RootFinder();
        float point1 = Float.valueOf(args[1]);
        float point2 = Float.valueOf(args[2]);
        String fileName = args[3];
        finder.secant(point1, point2, DEFAULT_MAXITER, Float.MIN_VALUE, fileName);
        outputToFile(fileName, finder);

    }

    /*
        This method handles secant method with custom iteration, determined from command line.
     */
    private static void startSecantCustomIteration(String[] args){
        RootFinder finder = new RootFinder();
        int maxIterations = Integer.valueOf(args[2]);
        float point1 = Float.valueOf(args[3]);
        float point2 = Float.valueOf(args[4]);
        String fileName = args[5];
        finder.secant(point1, point2, maxIterations, Float.MIN_VALUE, fileName);
        outputToFile(fileName, finder);
    }

    /*
        This method handles newton's method using custom iteration, determined from command line.
     */
    private static void startNewtonsCustomIteration(String[] args) {

        RootFinder finder = new RootFinder();

        int maxIterations = Integer.valueOf(args[2]);
        float point1 = Float.valueOf(args[3]);
        String fileName = args[4];
        finder.newtons(point1, maxIterations, Float.MIN_VALUE, Float.MIN_VALUE, fileName);
        outputToFile(fileName, finder);
    }

      /*
        This method handles newton's method using default max iteration.
     */
    private static void startNewtons(String[] args) {

        RootFinder finder = new RootFinder();
        float point1 = Float.valueOf(args[1]);
        String fileName = args[2];
        finder.newtons(point1, 10000, Float.MIN_VALUE, Float.MIN_VALUE, fileName);
        outputToFile(fileName, finder);
    }

    /*
        This method handles bisection method with custom max iteration.
     */
    private static void startBisectionCustomIteration(String[] args) {
        RootFinder finder = new RootFinder();

        int maxIterations = Integer.valueOf(args[2]);
        float point1 = Float.valueOf(args[3]);
        float point2 = Float.valueOf(args[4]);
        String fileName = args[5];
        finder.bisection(point1, point2, maxIterations, Float.MIN_VALUE, fileName, false);
        outputToFile(fileName, finder);
    }

    private static void outputToFile(String fileName, RootFinder finder) {

        try {
            PrintWriter writer = new PrintWriter("../assets/" + fileName.substring(0, fileName.indexOf(".")) + ".sol");

            String outcome;
            if(finder.outcome == true) {
                outcome = "Success";
            } else {
                outcome = "Failure";
            }

            writer.write("Root: " + finder.approxRoot + " Iterations: " + finder.iterations + " Outcome:" + outcome);
            writer.close();

        } catch(FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }

    }

    /*
        Bisection method using default max iterations.
     */
    private static void startBisection(String[] args) {

        RootFinder finder = new RootFinder();

        float point1 = Float.valueOf(args[0]);
        float point2 = Float.valueOf(args[1]);
        String fileName = args[2];
        finder.bisection(point1, point2, 10000, Float.MIN_VALUE, fileName, false);
        outputToFile(fileName, finder);
    }
}
