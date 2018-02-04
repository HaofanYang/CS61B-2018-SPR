public class ArrayDequeTest {
    public static void main(String[] args) {
        ArrayDeque<Integer> array = new ArrayDeque<>();
        int i = 0;
        for (; i <= 10000; i++) {
            if (i % 2 == 0) {
                array.addFirst(i);
            } else {
                array.addLast(i);
            }
        }
        int b = 0;
        for (; b <= 5000; b++) {
            if (b % 2 == 0) {
                array.removeFirst();
            }else{
                array.removeLast();
            }
        }

    }
}
