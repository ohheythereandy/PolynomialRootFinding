public class RootFinder {

    public RootFinder(){

    }

    public void bisection(float p1, float p2, int maxIter, float epsilon, String fileName) {
        Polynomial poly = new Polynomial(fileName);
        poly.print();
    }
}
