import com.sun.tools.javac.comp.Todo;
import com.sun.xml.internal.bind.v2.TODO;

public class LinkedListDeque<Item> implements Deque<Item> {
    private class ItemNode{
        public Item current;
        public ItemNode prev;
        public ItemNode next;

        public ItemNode(ItemNode p, Item c, ItemNode n){
            current = c;
            prev = p;
            next = n;
        }
    }

    private int size;
    private ItemNode sentinel;
    private Item getRecursvieHelper(ItemNode n, int i){
        if (i == 0){
            return n.current;
        }
        return getRecursvieHelper(n.next, i - 1);
    }


    /** Initiates a new LinkedListDeque */
    // Invariants:
    // sentinel.next is always the first node
    // sentinel.last is always the last node
    public LinkedListDeque(){
        sentinel = new ItemNode(null, (Item) "Empty", null );
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;

    }


     /** Add one item to the front of the list */
     @Override
    public void addFirst(Item i){
        sentinel.next = new ItemNode(sentinel, i, sentinel.next);
        sentinel.next.next.prev= sentinel.next;
        size += 1;
    }

    /** Remove the last item of the queue */
    @Override
    public void addLast(Item i){
        sentinel.prev = new ItemNode(sentinel.prev, i, sentinel);
        sentinel.prev.prev.next = sentinel.prev;

        size += 1;
    }

    /** Returns a boolean to tell if a queue is empty */
    @Override
    public boolean isEmpty(){
        return size == 0;
    }

    /** Returns the size of a queue */
    @Override
    public int size(){
        return size;
    }

    /** prints out every elements of a queue, separated by a space*/
    /** the number of elements is size*/
    @Override
    public void printDeque(){
        int count = size;
        ItemNode ref = sentinel;
        ref = ref.next;
        for (;count > 0; count--){
            System.out.print(ref.current + " ");
            ref = ref.next;
        }

    }

    /** Deletes the last item and returns it */
    @Override
    public Item removeFirst(){
        if (size == 0){
            return null;
        }
        Item output = sentinel.next.current;
        sentinel.next.current = null;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return output;
    }

    /** Deletes the last item and returns it */
    @Override
    public Item removeLast(){
        if (size == 0){
            return null;
        }
        Item output = sentinel.prev.current;
        sentinel.prev.current = null;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return output;
    }

    /** Gets the ith item of a queue*/
    public Item getIterative(int i){
        if (i > size - 1){
            return null;
        }
        ItemNode ref = sentinel;
        ref = ref.next;
        for (int count = 0; count < i; count++ ){
            ref = ref.next;
        }
        return ref.current;
    }

    /** Gets the ith item of a queue but recursively */
    public Item getRecursive(int i) {
        if (i > size - 1) {
            return null;
        }
        ItemNode ref = sentinel.next;
        return getRecursvieHelper(ref, i);
    }
}
