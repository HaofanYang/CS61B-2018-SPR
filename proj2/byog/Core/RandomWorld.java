package byog.Core;

import byog.TileEngine.TETile;
import com.sun.org.apache.regexp.internal.RE;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;



public class RandomWorld {
    private SingleColum[] representation; // 0 NOTHING, 1 FLOOR, 2
    private int WIDTH;
    private int HEIGHT;
    private TETile[][] WORLD;


    /**
     * 0: NOTHING
     * 1: WALL
     * in first row, Floor cannot appear
     * WALL length must be greater than 2
     */
    private void generateLand(){
        generateFirstCol();
        int index = 0;
        for (int i = 1; i < WIDTH; i++) {
            addNextCol(representation[i], index);
            index += 1;
        }
    }

    public void generateFirstCol(){
        int next = 0;
        Random rd1 = new Random(); // 0 or 1
        Random rd2 = new Random(); // random length
        while(next < HEIGHT) {
            int increment = 0;
            int numOfBlanks = HEIGHT - next;
            switch (rd1.nextInt(2)) {
                case (0): {
                    // Add 1, represent FLOOR
                    if(numOfBlanks == 3) {
                        increment = 3;
                    } else if (numOfBlanks < 3){
                        break;
                    } else {
                        increment = 3 + rd2.nextInt(numOfBlanks - 2);
                    }
                    for(int k = 0; k < increment; k++){
                        representation[0].add(1);
                        next += 1;
                    }
                    break;
                }
                case (1): {
                    // Add 0, represent WALL
                    increment = rd2.nextInt(numOfBlanks + 1);
                    for(int k = 0; k < increment; k++){
                        representation[0].add(0);
                        next += 1;
                    }
                    break;
                }
            }
        }
        representation[0].iniEdges();
    }


    //Might be buggy
    private void addToWorld(){
        for (int i = 0; i < WIDTH; i++){
            representation[i].addToLineWorld(WORLD[i]);
        }
    }

    //TODO
    private void addNextCol(SingleColum col, int prev) {
        Random rd = new Random();
        SingleColum prevCol = representation[prev];
        ArrayList<Integer> range = prevCol.getEdges();
        int indexOfCol = 0;
        int nextEdges = 0;
        while(indexOfCol < HEIGHT) {
            if (range.get(nextEdges) == indexOfCol){
                nextEdges += 1; //Hits the edge and increments the index of edge, must be true

                // once this condition returns true, we enter in the 'FLOOR SPACE'
                if (nextEdges % 2 == 0){
                    // Once we enter the FLOOR SPACE, we need to determine the size of it.
                    // To do so, just do following
                    //TODO nextEdges might outOfBoundary
                    int numOfBlanks = range.get(nextEdges + 1) - range.get(nextEdges) + 1;
                    int increment = 0;
                    // The numOfBlanks must not be smaller than 3
                    // New FLOOR SPACE must be not less than 3 as well
                    if (numOfBlanks == 3){
                        increment = 3;
                    } else if (numOfBlanks < 3){
                        increment = numOfBlanks;
                    } else {
                        increment = 3 + rd.nextInt(numOfBlanks - 2);
                    }
                    for (int i = 0; i < numOfBlanks; i++) {
                        if (i < numOfBlanks) {
                            col.add(1);
                            indexOfCol += 1;
                        } else {
                            col.add(0);
                            indexOfCol += 1;
                        }
                    }
                }
            }
            // Haven't encounter the edge:
            // case 1: in the FLOOR ZONE (if nextEdges % 2 != 0)
            // case 2: in the NOTHING ZONE (if nextEdges & 2 = 0)
            else {
                //TODO make it more efficient and support to add FLOOR
                if (nextEdges % 2 != 0) {
                    // if we are in the floor zone and haven't encountered the edge , we just skip to the edge
                    indexOfCol = range.get(nextEdges);


                }
                int nextEdge = range.get(nextEdges);
                int numOfBlanks = nextEdge - indexOfCol;
                // if there are more than 3 blanks, we can add either
                if (numOfBlanks >= 3) {

                }
                col.add(0);
                indexOfCol += 1;

            }

        }
        //TODO initialize edges of this column.
    }

    /** this helper method seems not working, but i kept it anyway

    private void fillRange(SingleColum col, int[] range){
        Random rd = new Random();
        int start = range[0];
        int end = range[1];
        for (int i = start; i <= end; i ++) {
            switch (rd.nextInt(2)){
                case(0): {

                }
            }
        }
    }
     */
//--------------------------------- public methods below this line ---------------------------
    public RandomWorld(TETile[][] world) {
        WORLD = world;
        WIDTH = world.length;
        HEIGHT = world[0].length;
        representation = new SingleColum[WIDTH];
        for (int i = 0; i < WIDTH; i++){
            representation[i] = new SingleColum(HEIGHT);
        }
        generateLand();
        addToWorld();
    }

    public TETile[][] getWORLD(){
        return WORLD;
    }



}
