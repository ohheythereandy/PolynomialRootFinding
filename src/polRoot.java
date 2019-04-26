public class polRoot {

    public static void main(String[] args) {

        if(args.length < 3) {
            System.out.println("You are missing an argument");
        }
        else if(args.length == 3) {
            //either standard bisection, or newton's
            if(args[0] == "-newt"){ //newton's

            } else {
                //supposed to be bisection
                startBisection(args);
            }
        }
        else if(args.length == 4) { // standard Secant Method

        }
        else if(args.length == 5) { //max iteration for bisection or newt

            if(args[0] == "-newt"){

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

    private static void startBisectionCustomIteration(String[] args) {
        RootFinder finder = new RootFinder();

        int maxIterations = Integer.valueOf(args[2]);
        float point1 = Float.valueOf(args[3]);
        float point2 = Float.valueOf(args[4]);
        String fileName = args[5];
        finder.bisection(point1, point2, maxIterations, Float.MIN_VALUE, fileName);
    }

    private static void startBisection(String[] args) {

        RootFinder finder = new RootFinder();

        float point1 = Float.valueOf(args[0]);
        float point2 = Float.valueOf(args[1]);
        String fileName = args[2];
        finder.bisection(point1, point2, 100000, Float.MIN_VALUE, fileName);
    }
}
