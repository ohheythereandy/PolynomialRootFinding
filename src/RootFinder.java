public class RootFinder {

    public float approxRoot;
    public int iterations;
    public boolean outcome;

    public RootFinder(){

    }

    public void bisection(float p1, float p2, int maxIter, float epsilon, String fileName) {
        Polynomial poly = new Polynomial(fileName);

        float fPoint1 = poly.compute(p1);
        float fPoint2 = poly.compute(p2);

        if(fPoint1 * fPoint2 >= 0){
            System.out.println("Inadequate value for a and b.");
            outcome = false;
            approxRoot = -1;
            iterations = 0;
            return;
        }

        float error = p2 - p1;

        float fPoint3 = 0;
        for(int index = 1; index <= maxIter; index++){
            error /= 2;
            float p3 = p1 + error;
            fPoint3 = poly.compute(p3);

            if(Math.abs(error) < epsilon || fPoint3 == 0){
                System.out.println("Algorithm has converged after " + index  + " iterations at x = " + p3 + "!");
                outcome = true;
                approxRoot = fPoint3;
                iterations = index;
                return;
            }

            if(fPoint1 * fPoint3 < 0) {
                p2 = p3;
                fPoint2 = fPoint3;
            } else {
                p1 = p3;
                fPoint1 = fPoint3;
            }
        }

        System.out.println("Max iterations reached without convergence...");
        outcome = false;
        approxRoot = fPoint3;
        iterations = maxIter;
    }
}
