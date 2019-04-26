import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Scanner;

public class Polynomial {

    int[] poly;
    int degree;

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

            //test current path
//            Path path = FileSystems.getDefault().getPath(".");
//            System.out.println("FilePath is: " + path);
        }
    }

    public void print(){

        for(int i = 0; i < degree + 1; i++){
            System.out.println("Degree: " + i + ", Coeff: " + poly[i]);
        }
    }
}
