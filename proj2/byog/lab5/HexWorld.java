package byog.lab5;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {

    public static int getNumOfOnes(int row, int size){
        if (row == 0){
            return size;
        }
        int output = size; // minimum number of ones
        final int firstMaxRow = size - 1;
        final int secondMaxRow = firstMaxRow + 1;
        for (int i = 1; i <= row; i++) {
            if (i <= firstMaxRow){
                output += 2;
            } else if (i == secondMaxRow){
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
        final int LENGTH = output[0].length;
        final int HEIGHT = output.length;
        int numOfOnes = n;
        for (int i = 0; i < HEIGHT; i++) {
            numOfOnes = getNumOfOnes(i, n);
            int numOfZeros = (LENGTH - numOfOnes) / 2;
            for (int k = 0; k < LENGTH; k++) {
                if(k < numOfZeros || k >= (LENGTH - numOfZeros)){
                    output[i][k] = 0;
                } else {
                    output[i][k] = 1;
                }
            }
        }
        return output;
    }


    public static TETile[][] addHexagon(int size, int positionX, int postitionY){
        int[][] index = generateHexagon(size);
        final int WIDTH = index[0].length;
        final int HEIGHT = index.length;
        final int overallWIDTH = positionX + WIDTH + 1;
        final int overallHEIGHT = postitionY + HEIGHT + 1;
        TETile[][] world = new TETile[overallHEIGHT][overallWIDTH];
        int i = -1; //index of x
        int j = -1; //index of y
        Random random = new Random(3);
            for (int x = positionX; x < HEIGHT; x++){
            i++;
            for (int y = postitionY; y < WIDTH; y++){
                j++;
                if (index[i][j] == 0){
                    world[x][y] = Tileset.NOTHING;
                } else {
                    switch (random.nextInt()) {
                        case(0): world[x][y] = Tileset.SAND;
                        case(1): world[x][y] = Tileset.FLOWER;
                        case(2): world[x][y] = Tileset.WALL;
                    }
                }
            }
        }
        return world;
    }

    public static void main(String[] args){
        TERenderer ter = new TERenderer();
        //TETile[][] world = addHexagon(5, 0, 0);
        //ter.renderFrame(world);
        int[][] test = generateHexagon(3);
        System.out.println(test[0][3]);
    }
}
