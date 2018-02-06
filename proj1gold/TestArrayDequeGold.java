import static org.junit.Assert.*;
import org.junit.Test;
import java.io.*;
import java.util.*;

public class TestArrayDequeGold {
    /** @Test
    public void testSth() {
        ArrayDequeSolution<Integer> solution = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> student = new StudentArrayDeque<>();
        String[] collections = new String[10];
        for (int i = 0; i <= 10; i++) {
            long ref = StdRandom.uniform(2);
            if (ref == 1) {
                student.addFirst(i);
                solution.addFirst(i);
            }else if(ref == 0){
                student.addLast(i);
                solution.addLast(i);
            }
        }
        for (int i = 0; i <= 10; i++){
            Integer exp = 0;
            Integer stu = 1;
            long ref = StdRandom.uniform(2);
            if (ref == 1){
                stu = student.removeFirst();
                exp = solution.removeFirst();
                collections[i] = "remove"
            } else if (ref == 0){
                stu = student.removeLast();
                exp = solution.removeLast();
            }
            assertEquals("error",
                    exp, stu);
        }
    }
    */
    @Test
    public void testSth(){
        StudentArrayDeque<Integer> student = new StudentArrayDeque<>();
        int capacity = 100;
        ArrayDequeSolution<Integer> solution = new ArrayDequeSolution<>();
        String callsCollections = "sh \n";
        for (int i = 0; i < capacity; i++){
            int randomNumber = StdRandom.uniform(2);
            if (randomNumber == 0){
                student.addFirst(i);
                solution.addFirst(i);
                callsCollections += " addFirst(" + i + ")" + '\n';
            } else if (randomNumber == 1){
                student.addLast(i);
                solution.addLast(i);
                callsCollections += " addLast(" + i + ")" + '\n';
            }
        }
        for (int i =0; i < capacity; i++){
            int randomNumber = StdRandom.uniform(2);
            if (randomNumber == 0){
                Integer stu = student.removeFirst();
                Integer exp = solution.removeFirst();
                callsCollections += " removeFirst()" + '\n';
                assertEquals(callsCollections, exp, stu);
            }
            if (randomNumber == 1){
                Integer stu = student.removeFirst();
                Integer exp = solution.removeFirst();
                callsCollections += " removeFirst()" + '\n';
                assertEquals(callsCollections, exp, stu);
            }
        }

    }
}
