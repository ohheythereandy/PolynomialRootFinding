import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Scanner;

public class Polynomial {

    int[] poly;
    int degree;

    public Polynomial(int degree){
        this.degree = degree ;
        poly = new int[this.degree + 1];
    }

    public Polynomial(String fileName) {
        //append file directory
        loadPolynomial("../assets/" + fileName);
        //System.out.println("/assets/" + fileName);
    }

    private void loadPolynomial(String fileName) {
        try{
          Scanner sc = new Scanner(new File(fileName));

          //set degree
          degree = sc.nextInt();
          poly = new int[degree + 1];
          for(int i = degree; i >= 0; i--){
              poly[i] = sc.nextInt();
          }
        }catch (FileNotFoundException e) {
            System.out.println("file not found");
        }
    }

    public Polynomial getDerivative(){

        int n = degree;
        Polynomial ret = new Polynomial(degree - 1);
        for(int i = degree; i > 0; i--){
            int coeff = poly[i];
            coeff = coeff * n;
            n = n - 1;
            ret.poly[i - 1] = coeff;
        }

        return ret;

    }

    public void print(){

        for(int i = 0; i < degree + 1; i++){
            System.out.println("Degree: " + i + ", Coeff: " + poly[i]);
        }
    }

    public float compute(float p1) {

        float ret = 0;

        for(int i = 0; i < poly.length; i++) {
            ret += Math.pow(p1, i) * poly[i];
        }

        return ret;
    }
}
