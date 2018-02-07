public interface Deque<Item> {


        /**
         * Add one item to the front of the list
         */
        public void addFirst(Item i);

        /**
         * Remove the last item of the queue
         */
        public void addLast(Item i);

        /**
         * Returns a boolean to tell if a queue is empty
         */
        public boolean isEmpty();

        /**
         * Returns the size of a queue
         */
        public int size();

        /** prints out every elements of a queue, separated by a space*/
        /**
         * the number of elements is size
         */
        public void printDeque();
        /**
         * Deletes the last item and returns it
         */
        public Item removeFirst();

        /**
         * Deletes the last item and returns it
         */
        public Item removeLast();

    }
