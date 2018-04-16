package Word;

import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.regexp.internal.RE;
import edu.princeton.cs.algs4.MinPQ;

public class Solver {
    private MinPQ<Node> pq;
    private List<Node> steps;
    private int moves;
    private List<WorldState> solution;

    private class Node implements Comparable<Node> {
        private WorldState current;
        private Node previous;
        private int priority;
        private int moves;

        private Node(WorldState c, Node p, int m) {
            current = c;
            previous = p;
            moves = m;
            priority = c.estimatedDistanceToGoal() + moves;
        }

        @Override
        public int compareTo(Node n) {
            return this.priority - n.priority;
        }

        @Override
        public boolean equals(Object o) {
            Node toCompareWith = (Node) o;
            return this.current.equals(toCompareWith.current);
        }

        @Override
        public int hashCode() {
            return this.current.hashCode();
        }
    }

    public Solver(WorldState initial){
        pq = new MinPQ<>();
        steps = new ArrayList<>();
        pq.insert(new Node(initial, null, 0));
        moves = 0;
        movesHelper();
        solution = new ArrayList<>();
    }

    private void insertNeighbors(Node n) {
        for (WorldState w: n.current.neighbors()) {
            Node toAdd = new Node(w, n, moves + 1);
            if (steps.contains(toAdd)){
                continue;
            } else {
                pq.insert(toAdd);
            }
        }
    }

    public int moves() {
        return moves;
    }

    private void movesHelper() {
        Node c = pq.delMin();
        moves = c.moves;
        steps.add(c);
        if (c.current.isGoal()) {
            return;
        } else {
            insertNeighbors(c);
            movesHelper();
        }
    }



    public Iterable<WorldState> solution() {
        Node goal = steps.get(steps.size() - 1);
        solution.add(goal.current);
        Node previous = goal.previous;
        while (previous != null) {
            solution.add(0, previous.current);
            previous = previous.previous;
        }
        return solution;
    }

}
