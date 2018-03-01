package byog.Core.RandomWorld;

import java.lang.reflect.Array;
import java.util.*;

import byog.Core.RandomUtils; //Directly use its methods, as they are all static
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;


/** The class should only be used within RandomWorld package */

public class SingleColumn {
        private ArrayList<Integer> representation;
        private ArrayList<Integer> edges; // Record the position of Edges
        private int HEIGHT;
        private Random RANDOM;

        //Constructor for the first column
        SingleColumn(int Height, Random rd){
            HEIGHT = Height;
            edges = new ArrayList<>();
            representation = new ArrayList<>();
            RANDOM = rd;
            iniFirstColumn();
        }

        // Constructor for next column
        SingleColumn(SingleColumn prevCol){
            HEIGHT = prevCol.HEIGHT;
            edges = new ArrayList<>();
            representation = new ArrayList<>();
            RANDOM = prevCol.RANDOM;
            iniThisColumn(prevCol);
        }

        ArrayList getEdges(){
            return edges;
        }

        public String toString(){
            return  representation.toString();
        }

        void addToWorld(TETile[] subWorld) {
            for(int i = 0; i < HEIGHT; i++){
                if (representation.get(i) == 0) {
                    subWorld[i] = Tileset.NOTHING;
                } else if (representation.get(i) == 1) {
                    subWorld[i] = Tileset.SAND;
                } else {
                    subWorld[i] = Tileset.WALL;
                }
            }
        }


//---------------------------------------------- private methods below this line ---------------------------------------------

        private void iniFirstColumn() {
            randomExpend(0, HEIGHT);
            iniEdges();
            //addWall();
        }

        /**
         *  find the number of Floor Zones
         *  retain at least on Floor Zone and treat the rest as normal
         */
        // Might be buggy
        private void iniThisColumn(SingleColumn prevCol) {
            int totalNumOfZones = prevCol.edges.size() / 2;

            int index = 0;
            for (int i = 0; i < totalNumOfZones; i++) {
                int a = prevCol.edges.get(2 * i);
                int b = prevCol.edges.get(2 * i + 1);
                randomExpend(index, a);
                randomContract(a, b);
                index = b;
            }
            if (index < HEIGHT) {
                randomExpend(index, HEIGHT);
            }
            iniEdges();
            //addWall();
        }

        /** Expend the area outside the Floor range [b, t)
         *  Invariance:
         *             The number of blanks in this range must be: t - b
         *             Floor must be continuous and more than 3
         *             If number of blanks is less than 3:
         *                                                if t == HEIGHT:
         *                                                                Add nothing
         *                                                if t != HEIGHT:
         *                                                                Add floor or nothing
         *             If number of blanks is 3, either adding 3 floor or 3 Nothing
         *             If number of blanks is more than 3:
         *                                               use a random number to determine the first item type:
         *                                                                                                    Add 3 floor
         *                                                                                                    Add nothing
         *                                               update NumOfBlank
         */
        private void randomExpend(int b, int t) {
            int numOfBlanks = t - b;
            if (numOfBlanks < 3) {
                // If determ == false, add 1
                boolean determ = true;
                if (t != HEIGHT) {
                    determ = RandomUtils.bernoulli(RANDOM, 0.5);
                }
                if(determ) {
                    for(int i = b; i < t; i++) {
                        representation.add(i, 0);
                    }
                } else {
                    for(int i = b; i < t; i++) {
                        representation.add(i, 1);
                    }
                }
            } else if (numOfBlanks == 3) {
                // If true, add 1
                boolean determ = RandomUtils.bernoulli(RANDOM, 0.7);
                    for(int i = b; i < t; i++) {
                        if (determ){
                            representation.add(i, 1);
                        } else {
                            representation.add(i, 0);
                        }
                    }
            } else if (numOfBlanks > 3){
                int index = b;
                while (index < t) {
                    boolean determ = RandomUtils.bernoulli(RANDOM, 0.7);
                    if (determ) {
                        int numToAdd = RandomUtils.uniform(RANDOM, 3, numOfBlanks + 1);
                        int maxOfForward = (numOfBlanks - numToAdd);
                        int startPos = RandomUtils.uniform(RANDOM, b, b + maxOfForward + 1);
                        int endPos = startPos + numToAdd;
                        for (; index < startPos; index++) {
                            representation.add(index, 0);
                        }
                        for (; index < endPos; index++) {
                            representation.add(index, 1);
                        }
                        for (; index < t; index++) {
                            representation.add(index, 0);
                        }

                    } else {
                        for (; index < t; index++) {
                            representation.add(index, 0);
                        }
                    }
                }
            }
        }

        /**
         * if number of blanks is more than 3, add a RANDOM NUMBER floor at a RANDOM POSITION
         * is number of blanks is 3, just add 3 floor
         */
        public void randomContract(int b, int t){
                int numOfBlanks = t - b;
                if (numOfBlanks == 3) {
                    for (int i = b; i < numOfBlanks + b; i++) {
                        representation.add(i, 1);
                    }
                } else if (numOfBlanks > 3){
                    boolean isDecreased = RandomUtils.bernoulli(RANDOM, 0.3);
                    if (isDecreased) {
                        int numToAdd = RandomUtils.uniform(RANDOM, 3, numOfBlanks);
                        int maxOfForward = (numOfBlanks - numToAdd);
                        int startPos = RandomUtils.uniform(RANDOM, b, b + maxOfForward + 1);
                        int currPos = b;
                        int endPos = startPos + numToAdd;
                        while(currPos < t) {
                            for (; currPos < startPos; currPos++) {
                                representation.add(currPos, 0);
                            }
                            for (; currPos < endPos; currPos++) {
                                representation.add(currPos, 1);
                            }
                            for (; currPos < t; currPos++) {
                                representation.add(currPos,0);
                            }
                        }
                    } else {
                        for (int i = b; i < t; i++) {
                            representation.add(i, 0);
                        }
                    }
                }
            }

        /** Find edges based on representation:
         *                                     status is the current zone, Where the pointer is
         *                                     status 0 --> 1: Entrance of FLOOR zone, add current index to edges
         *                                     status 1 --> 0: Exit of FLOOR zone, add current index to edges
         *                                     boundary cases:
         *                                                    1) first item is 1:
         *                                                                       add index 0
         *                                                    2) first item is 0:
         *                                                                       do as usual
         *                                                    3) last item is 1:
         *                                                                       must be exit, but add index + 1
         *                                                    4) last item is 0:
         *                                                                       do as usual
         */
        private void iniEdges(){
            int status = representation.get(0);
            for (int i = 0; i < HEIGHT; i++) {
                if (i == 0 & status == 1) {
                    edges.add(i);
                    continue;
                }
                if (i == HEIGHT - 1 & representation.get(i) == 1) {
                    edges.add(HEIGHT);
                    break;
                }
                int cur = representation.get(i);
                if (status == 0) {
                    if (cur == 1){
                        edges.add(i);
                        status = 1;
                        continue;
                    } else {
                        continue;
                    }
                } else {
                    if (cur  == 1) {
                        continue;
                    } else {
                        edges.add(i);
                        status = 0;
                        continue;
                    }
                }
            }
        }


    }

