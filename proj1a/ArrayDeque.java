public class ArrayDeque<Item> {

    private Item[] items;
    private int size;
    private int nextFirst = 0;
    private int nextLast = 1;

    /** Move nextFirst or nextLast forwards several steps
     *
     * index 0 1 2 3 4 5 6 7 8
     * curr        i
     *
     * move forwards 6 steps (s)
     *
     * index 0 1 2 3 4 5 6 7 8
     * curr  i
     *
     * invariants:
     *             i + s = 3 + 6 = 9
     *             length = 9
     *             3 --> 0 = length - ( i + s )
     * */
    private int moveForward(int i, int s) {
        if (i + s >= items.length) {
            return ((i + s) - items.length);
        }
        return i + s;
    }

    /** Move nextFirst or nextLast backwards
     *
     * index 0 1 2 3 4 5 6 7 8
     * curr        i
     *
     * Move back 4 steps (s)
     *
     * index 0 1 2 3 4 5 6 7 8
     * curr                  i
     *
     * invariants:
     *            length = 9
     *            i - s = 3 - 4 = -1 < 0
     *            3 --> 8 = length - abs( i - s) = 9 - 1
     * */
    private int moveBack(int i, int s) {
        if (i - s < 0) {
            return items.length - (s - i);
        }
        return i - s;
    }

    /** If the array items[] is full, returns true. Otherwise, returns false. */
    private boolean ifFull() {
        return items.length == size;
    }

    /**
     * If the efficiency is less than 0.25, then returns true. Otherwise returns false.
     * */
    private boolean ifTooEmpty() {
        if (items.length <= 8) {
            return false;
        }
        float efficiency = (float) size / (float) items.length;
        return efficiency < 0.25;
    }

    /**
     * Resize the array depending on the status
     * In order to keep adding (addFirst and addLast) things to the array
     * We need to make space for:
     *                           nextFirst to move backwards
     *                           nextLast to move forwards
     *----------------------------------------------------------------------------------------
     * As a result, the relative positions of nextFirst and nextLast should look like:
     *
     * Index 0 1 2 3 4 ........... 30 31(Last) | 32(First) 33 34 35 36 ...................n
     *                                    |        |
     *                                    |        |
     *                                    |        |
     *                                nextFirst   nextLast
     *
     *                                     NEW POSITIONS
     *
     *Index 0 1 2 3 4 ...........LAST 32 ....NEW SPACE (n).....31+n 32+n(First) ...........n
     *                                 |                        |
     *                                 |                        |
     *                                 |                        |
     *                             nextLast -->          <-- nextFirst
     *------------------------------------------------------------------------------------------
     * However, we have to consider several boundary cases in order to make numbers correct!!!!
     *
     * [GENERAL CASE]
     * index 0 1 2 3 4 5 6
     *             L F
     * 3 items to move (i.e. length - 4) [F,2*length]
     *
     * [BOUNDARY CASES]
     * index 0 1 2 3 4 5 6
     *                 L F
     * index 0 1 2 3 4 5 6
     *       F           L
     * -----------------------------------------------------------------------------------------
     * No matter which case it might be, we have several invariants (things must be true!):
     *            1. The index of the Last item is "moveBack(nextLast, 1)"
     *            2. The number of elements before LAST = index of the last + 1
     *            3. number of remainder = (items.length) - (num of things that have been moved)
     *            4. newFirstIndex = (newItems.length) - (num of remainder)
     *            5. newNextFirst = [moveBack(newFirstIndex)]
     *            6. newNextLast = [moveForward(index of the last)]
     * */
    private void resize() {
        if (ifFull() || ifTooEmpty()) {
            int capacity = items.length;
            if (ifFull()) {
                capacity *= 2;
            } else if (ifTooEmpty()) {
                capacity /= 2;
            }
            Item[] newArray = (Item[]) new Object[capacity];
            int lastIndex = moveBack(nextLast, 1);
            int firstIndex = moveForward(nextFirst, 1);
            System.arraycopy(items, 0, newArray, 0, lastIndex + 1);
            int numOfReminder = size - (lastIndex + 1);
            int newFirstIndex = newArray.length - numOfReminder;
            System.arraycopy(items, firstIndex, newArray, newFirstIndex, numOfReminder);
            items = newArray;
            nextFirst = moveBack(newFirstIndex, 1);
            nextLast = moveForward(lastIndex, 1);
            return;
        }
        return;
    }

    /** Constructor */
    public ArrayDeque() {
        items = (Item[]) new Object[8];
        size = 0;
    }


    /** Add one item to the front of the list and Update nextLast*/
    public void addFirst(Item i) {
        resize();
        items[nextFirst] = i;
        size += 1;
        nextFirst = moveBack(nextFirst, 1);
    }

    /** Remove the last item of the queue and Update nextLast*/
    public void addLast(Item i) {
        resize();
        items[nextLast] = i;
        size += 1;
        nextLast = moveForward(nextLast, 1);
    }

    /** Returns a boolean to tell if a queue is empty */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Returns the size of a queue */
    public int size(){
        return size;
    }

    /** prints out every elements of a queue
     *  Invariants:
     *  the first element is items[moveForward(nextFirst)]
     * */

    public void printDeque() {
        int index = moveForward(nextFirst, 1);
        for (int count = 1; count <= size; count++) {
            System.out.print(items[index] + " ");
            index = moveForward(index, 1);
        }
    }

    /** Deletes the last item and returns it
     * Invariants:
     * The index of the last element is moveBack[nextLast]
     * */
    public Item removeFirst() {
        nextFirst = moveForward(nextFirst, 1);
        Item output = items[nextFirst];
        items[nextFirst] = null;
        size -= 1;
        return output;
    }

    /** Deletes the last item and returns it */
    public Item removeLast() {
        nextLast = moveBack(nextLast, 1);
        Item output = items[nextLast];
        items[nextLast] = null;
        size -= 1;
        return output;
    }

    /** Gets the ith item of a queue
     * Suppose I want to get the 1st element
     * i.e. list[0]
     * I should let nextFirst moves forward 1 step, which is index + 1 steps.
     * */
    public Item get(int index) {
        int track = nextFirst; //It does not modify nextFirst
        track = moveForward(track, index + 1);
        return items[track];
    }
}
