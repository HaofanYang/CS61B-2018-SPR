package byog.lab5;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {

    public static int getNumOfOnes(int column, int size){
        if (column == 0){
            return 2;
        }
        int output = 2; // minimum number of ones
        final int firstMaxRow = (2 * size - 2) / 2;
        final int finalMaxRow = firstMaxRow + size - 1;
        for (int i = 1; i <= column; i++) {
            if (i <= firstMaxRow){
                output += 2;
            } else if (i <= finalMaxRow){
                continue;
            }
            else {
                output -= 2;
            }
        }
        return output;
    }



    /** return 2D coordinates of each element within a n-sized hexagon, starting from (0, 0) */
    public static int[][] generateHexagon(int n){
        int[][] output = new int[3 * n -2][2 * n];
        final int WIDTH = output.length;
        final int HEIGHT = output[0].length;
        int numOfOnes = n;
        for (int i = 0; i < WIDTH; i++) {
            numOfOnes = getNumOfOnes(i, n);
            int numOfZeros = (HEIGHT - numOfOnes);
            for (int k = 0; k < HEIGHT; k++) {
                if(k < numOfZeros / 2 || k >= (HEIGHT - numOfZeros / 2)){
                    output[i][k] = 0;
                } else {
                    output[i][k] = 1;
                }
            }
        }
        return output;
    }


    private static TETile randomGeneration() {
        Random random = new Random();
        switch (random.nextInt(3)) {
            case (0):
                return Tileset.SAND;
            case (1):
                return Tileset.WALL;
            case (2):
                return Tileset.FLOOR;
            default:
                return Tileset.NOTHING;
        }
    }


    public static void addHexagon(int size, int positionX, int postitionY, TETile[][] world) {
        int[][] index = generateHexagon(size);
        final int WIDTH = index.length;
        final int HEIGHT = index[0].length;
        final int xBoundary = positionX + WIDTH;
        final int yBoundary = postitionY + HEIGHT;
        int i = -1; //index of x
            for (int x = positionX; x < xBoundary; x += 1) {
                i++;
                int j = -1; //index of y
                for (int y = postitionY; y < yBoundary; y += 1) {
                    j++;
                    try {
                        if (index[i][j] == 0) {
                            world[x][y] = Tileset.NOTHING;
                        } else {
                            world[x][y] = randomGeneration();
                        }
                    }
                    catch (ArrayIndexOutOfBoundsException e){
                        System.out.println("The world is too small!!!");
                        return;
                    }
                    }
                }
            }


    public static void main(String[] args){
        TERenderer ter = new TERenderer();
        ter.initialize(13, 10);
        TETile[][] world =  new TETile[13][9];
        addHexagon(5, 0, 0, world);
        ter.renderFrame(world);
    }
}
