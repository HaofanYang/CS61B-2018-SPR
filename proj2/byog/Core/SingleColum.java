package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import java.util.ArrayList;


public class SingleColum {
    private int[] representation;
    private int next;
    private int HEIGHT;
    private ArrayList<Integer> edges = new ArrayList<>(); //Record the position of Edges
    //private String output = "";

    protected SingleColum(int Height){
        HEIGHT = Height;
        representation = new int[Height];
        next = 0;
    }


    /**
    public SingleColum(int[] l) {
        representation = l;
        next = l.length - 1;
        HEIGHT = l.length;
    }
     */

    /** Add one number to the end of th list */
    protected void add(int i){
        if (next >= HEIGHT) {
            throw new ArrayIndexOutOfBoundsException("Array is overflow: The current " +
                    "index is " + next + " , But the Height is " + HEIGHT);
        }
        representation[next] = i;
        next += 1;
    }

    /** Returns edges coordinates based on initialized representation */
    protected void iniEdges(){
        int status = representation[0];
        for(int i = 0; i < HEIGHT; i++){
            // in this case we encounter an FLOOR in the rep
            if (representation[i] == 1){
                // status: 1, and encounter 1
                if (status == 1){
                    if(i == 0 || i == HEIGHT - 1){
                        edges.add(i);
                    } else {
                        // if i is not the front or end, we are in status 1, and encounter another 1, which means we haven't encounter the edge.
                        continue;
                    }
                }
                // status: 0, and encounter 1
                else if (status == 0) {
                    // If status is 0, we encounter 1, which means the current position is edge and we are entering FLOOR ZONE
                    edges.add(i);
                    status = 1;
                }
            }
            // in this case we encounter NOTHING in the rep
            else {
                // status: 1, and encounter NOTHING
                if (status == 1){
                    edges.add(i - 1);
                }
                // status: 0, and encounter NOTHING, just continue
                else {
                    continue;
                }

            }
        }
    }

    protected ArrayList getEdges(){
        return edges;
    }

    /** A discard method, which returns edges in terms of 2D coordinates
    protected int[][] getEdgeRange(){
        // edges.size() mush not be odd
        int[][] output = new int[edges.size() / 2][2];
        int index = 0;
        for (int k = 0; k < output.length; k++){
            int x = edges.get(index);
            index += 1;
            int y = edges.get(index);
            index += 1;
            output[k] = new int[]{x, y};
        }
        return output;
    }
     */

    // Might be Buggy
    protected void addToLineWorld(TETile[] lineWorld){
        for(int i = 0; i < HEIGHT; i++) {
            switch (representation[i]){
                case(0): {
                    lineWorld[i] = Tileset.NOTHING;
                    break;
                }
                case(1): {
                    lineWorld[i] = Tileset.FLOOR;
                }
                case(2): {
                    lineWorld[i] = Tileset.WALL;
                }
            }
        }
    }
    /** Used it for test

    public String toString(){
        for (int i : representation) {
            output += i + " ";
        }
        return output;
    }
    */



}
