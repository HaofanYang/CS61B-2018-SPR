import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    @Test
    public void testSth(){
        LinkedListDeque<Integer> student = new LinkedListDeque<>();
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
                Integer stu = student.removeLast();
                Integer exp = solution.removeLast();
                callsCollections += " removeLast()" + '\n';
                assertEquals(callsCollections, exp, stu);
            }
        }
    }
}
