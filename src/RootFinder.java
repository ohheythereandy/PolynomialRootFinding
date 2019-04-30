public class RootFinder {

    public float approxRoot;
    public int iterations;
    public boolean outcome;
    public float lastPointUsed;

    public RootFinder(){

    }

    public void secant(float point1, float point2, int maxIter, float epsilon, String fileName) {
        Polynomial poly = new Polynomial(fileName);


        float polyOfPoint1 = poly.compute(point1);
        float polyOfPoint2 = poly.compute(point2);

        if(Math.abs(polyOfPoint1) > Math.abs(polyOfPoint2)){
            float temp = point1;
            point1 = point2;
            point2 = temp;
            temp = polyOfPoint1;
            polyOfPoint1 = polyOfPoint2;
            polyOfPoint2 = temp;
        }

        for(int iter = 1; iter <= maxIter; iter++) {

            if(Math.abs(polyOfPoint1) > Math.abs(polyOfPoint2)){
                float temp = point1;
                point1 = point2;
                point2 = temp;
                temp = polyOfPoint1;
                polyOfPoint1 = polyOfPoint2;
                polyOfPoint2 = temp;
            }

            //check if dividng by 0
            if((point2 == point1 && polyOfPoint2 == polyOfPoint1)){
                break;
            }
            float div = (point2 - point1) / (polyOfPoint2 - polyOfPoint1);
            point2 = point1;
            polyOfPoint2 = polyOfPoint1;
            div = div * polyOfPoint1;


            if(Math.abs(div) < epsilon ) {
                System.out.println("Algorithm has converged after " + iter + "iterations! At x=" + point1 + ".");
                outcome = true;
                approxRoot = polyOfPoint1;
                iterations = iter;
                return;
            }

            point1 = point1 - div;
            polyOfPoint1 = poly.compute(point1);
            //System.out.println("point1: " + point1 + " F(p1) = " + polyOfPoint1);

        }

        System.out.println("Max number of iterations reached!");
        outcome = false;
        approxRoot = polyOfPoint1;
        iterations = maxIter;
    }



    public void newtons(float x, int maxIter, float epsilon, float delta, String fileName){
        Polynomial poly = new Polynomial(fileName);
        Polynomial polyDeriv = poly.getDerivative();

        float y = poly.compute(x);

        float diff = 0;
        for(int index = 1; index <= maxIter; index++) {

            float yDeriv = polyDeriv.compute(x);

            if(Math.abs(yDeriv) < delta){
                System.out.println("Small slope!");
                outcome = false;
                approxRoot = -1;
                iterations = 1;
                return;
            }

            diff = y / yDeriv;
            x = x - diff;
            y = poly.compute(x);

            if(Math.abs(y) < epsilon){
                System.out.println("Algorithm has converged after " + index + " iterations at " + x + "!");
                outcome = true;
                approxRoot = y;
                iterations = index;
                return;
            }

            //System.out.println("p1: " + x + "F(p1): " + y);
        }

        System.out.println("Max iterations reached without convergence.");
        outcome = false;
        approxRoot = y;
        iterations = maxIter;
    }

    public void bisection(float p1, float p2, int maxIter, float epsilon, String fileName, boolean isHybrid) {
        Polynomial poly = new Polynomial(fileName);

        float fPoint1 = poly.compute(p1);
        float fPoint2 = poly.compute(p2);
        float p3 = 0;

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
            p3 = p1 + error;
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

        if(isHybrid)
            System.out.println("Switching to newton's using point " + p3);
        else
            System.out.println("Max iterations reached without convergence...");
        outcome = false;
        approxRoot = fPoint3;
        iterations = maxIter;
        lastPointUsed = p3;
    }
}
