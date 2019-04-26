public class polRoot {

    public static void main(String[] args) {

        if(args.length > 3) {
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

    }

    private static void startBisection(String[] args) {

        RootFinder finder = new RootFinder();

        int point1 = Integer.valueOf(args[0]);
        int point2 = Integer.valueOf(args[1]);
        String fileName = args[2];
        finder.bisection(point1, point2, fileName);
    }
}
