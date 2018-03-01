package byog.Core.RandomWorld;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;


public class TestSingleColumn {

    /**
    public static void main(String[] args) {
        Random rd = new Random();
        SingleColumn col = new SingleColumn(30, rd);
        System.out.println(col);
        SingleColumn col2 = new SingleColumn(col);
        System.out.println(col2);
        SingleColumn col3 = new SingleColumn(col2);
        System.out.println(col3);
    }
     */
    public static void main(String[] args) {
        Random rd = new Random();
        SingleColumn col = new SingleColumn(30, rd);
        System.out.println(col);
        col.randomContract(0, 30);
        System.out.println(col);
    }
}
