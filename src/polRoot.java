import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class polRoot {

    public static void main(String[] args) {

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
        else if(args.length == 4) { // standard Secant Method

        }
        else if(args.length == 5) { //max iteration for bisection or newt

            if(args[0].equals( "-newt" )){
                startNewtonsCustomIteration(args);
            } else {
                startBisectionCustomIteration(args);
            }
        }
        else if(args.length == 6) { // max iteration for secant

        }
        else {                      //too many arguments
            System.out.println("You've inputted too many arguments, please try again.");
        }

    }

    private static void startNewtonsCustomIteration(String[] args) {

        RootFinder finder = new RootFinder();

        int maxIterations = Integer.valueOf(args[2]);
        float point1 = Float.valueOf(args[3]);
        String fileName = args[4];
        finder.newtons(point1, maxIterations, Float.MIN_VALUE, Float.MIN_VALUE, fileName);
        outputToFile(fileName, finder);
    }

    private static void startNewtons(String[] args) {

        RootFinder finder = new RootFinder();
        float point1 = Float.valueOf(args[1]);
        String fileName = args[2];
        finder.newtons(point1, 10000, Float.MIN_VALUE, Float.MIN_VALUE, fileName);
        outputToFile(fileName, finder);
    }

    private static void startBisectionCustomIteration(String[] args) {
        RootFinder finder = new RootFinder();

        int maxIterations = Integer.valueOf(args[2]);
        float point1 = Float.valueOf(args[3]);
        float point2 = Float.valueOf(args[4]);
        String fileName = args[5];
        finder.bisection(point1, point2, maxIterations, Float.MIN_VALUE, fileName);
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

    private static void startBisection(String[] args) {

        RootFinder finder = new RootFinder();

        float point1 = Float.valueOf(args[0]);
        float point2 = Float.valueOf(args[1]);
        String fileName = args[2];
        finder.bisection(point1, point2, 10000, Float.MIN_VALUE, fileName);
        outputToFile(fileName, finder);
    }
}
