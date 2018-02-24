package synthesizer;
import java.util.Iterator;

public interface BoundedQueue<T> extends Iterable<T>{
    Iterator iterator();
    int capacity();

    int fillCount();

    void enqueue(T x); //add on item T at the end

    T dequeue(); // remove the first item.

    T peek(); //return the first item in the queue.

    default boolean isEmpty() {
        return fillCount() == 0;
    }

    default boolean isFull() {
        return capacity() == fillCount();
    }


}
