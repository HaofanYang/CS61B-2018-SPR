package synthesizer;
import java.util.Iterator;

//TODO: Make sure to make this class and all of its methods public
//TODO: Make sure to make this class extend AbstractBoundedQueue<t>
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T>{
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new Arra yRingBuffer with the given capacity.
     */
    @Override
    public Iterator<T> iterator(){
        return new RingIterator();
    }

    public ArrayRingBuffer(int capacity) {
        first = 0;
        last = 0;
        rb = (T[]) new Object[capacity];
        this.capacity = capacity; //capacity variable is inherited from AbstractBoundedQueue.
        this.fillCount = 0;
    }

    private int increment(int a){
        if (a == this.capacity - 1){
            a = 0;
        } else {
            a += 1;
        }
        return a;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    @Override
    public void enqueue(T x) {
        if (fillCount() == capacity()){
            throw new RuntimeException("Ring Buffer Overflow");
        }
        rb[last] = x;
        last = increment(last);
        this.fillCount += 1;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    @Override
    public T dequeue() {
        if (fillCount() == 0){
            throw new RuntimeException("Ring Buffer Underflow");
        }
        T output = rb[first];
        rb[first] = null;
        first = increment(first);
        this.fillCount -= 1;
        return output;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    @Override
    public T peek() {
        return rb[first];
    }


    // TODO: When you get to part 5, implement the needed code to support iteration.
    private class RingIterator implements Iterator<T>{
        private int current;

        public RingIterator(){
            current = first;
        }

        public boolean hasNext(){
            return current != last;
        }

        public T next(){
            return rb[current];
        }
    }
}
