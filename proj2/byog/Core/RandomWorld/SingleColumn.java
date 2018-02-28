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
            int numOfNewZones = RandomUtils.uniform(RANDOM, 1, totalNumOfZones + 1);
            ArrayList<Integer> newEdges = selectEdges(prevCol.edges, numOfNewZones);
            int index = 0;
            for (int i = 0; i < numOfNewZones; i++) {
                int a = newEdges.get(2 * i);
                int b = newEdges.get(2 * i + 1);
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
         *             The number of blanks in this range must be t - b + 1
         *             Floor must be continuous and more than 3
         *             If number of blanks is less than 3, DONT add any floor
         *             If number of blanks is 3, either adding 3 floor or 3 Nothing
         *             If number of blanks is more than 3:
         *                                               use a random number to determine the first item type:
         *                                                                                                    Add 3 floor
         *                                                                                                    Add nothing
         *                                               update NumOfBlank
         */
        private void randomExpend(int b, int t) {
            int numOfBlanks = t - b;
            while(numOfBlanks > 0) {
                if (floorCanAppear(numOfBlanks)) {
                    // if true add 1, otherwise add 0;
                    boolean jug = RandomUtils.bernoulli(RANDOM, 0.1);
                    if (jug) {
                        for (int i = 0; i < 3; i++) {
                            representation.add(1);
                            numOfBlanks -= 1;
                        }
                    } else {
                        representation.add(0);
                        numOfBlanks -= 1;
                    }
                } else {
                    for (int k = 0; k < numOfBlanks; k++) {
                        representation.add(0);
                        numOfBlanks -= 1;
                    }
                }
            }
        }

        /**
         * Add Floor or Nothing in a given range [b, t), based on passed-in boolean
         */
        private void randomContract(int b, int t){
                int numOfBlanks = t - b;
                int index = b;
                for(int i = 0; i < numOfBlanks; i++, index++) {
                    representation.add(index, 1);
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

        /** Helper method for randomExpend() */
        private static boolean floorCanAppear(int num) {
            return num >= 3;
        }

        //TODO change this to private when finish
        public static ArrayList<Integer> selectEdges(ArrayList<Integer> edges, int n) {
            if (n > edges.size() / 2) {
                throw new ArrayIndexOutOfBoundsException("Opps, doesn't have enough Floor zones");
            }
                ArrayList<Integer> output = new ArrayList();
                HashSet<Integer> selected = selectOutOf(n, edges.size() / 2);
                for(int i : selected) {
                    int toAppend1 = edges.get(2 * i);
                    int toAppend2 = edges.get(2 * i + 1);
                    output.add(toAppend1);
                    output.add(toAppend2);
                }
            Collections.sort(output);
            return output;
        }

        // TODO change this to private when finish
        public static HashSet<Integer> selectOutOf(int i, int c){
            HashSet<Integer> output = new HashSet<>();
            Random rd = new Random();
            while (output.size() < i) {
                int toAppend = rd.nextInt(c);
                    output.add(toAppend);
                }
            return output;
        }

        private void addWall() {
            for (int i : edges) {
                representation.add(i, 2);
            }
        }

    }

