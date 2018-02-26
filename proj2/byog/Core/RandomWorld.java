package byog.Core;

import byog.TileEngine.TETile;
import java.util.Random;



public class RandomWorld {
    private int[][] representation;
    private int WIDTH;
    private int HEIGHT;

    public RandomWorld(TETile[][] world) {
        WIDTH = world.length;
        HEIGHT = world[0].length;
        representation = new int[WIDTH][HEIGHT];
        // Initialize the representation with zeros
        for (int i = 0; i < WIDTH; i++) {
            for (int k = 0; k < HEIGHT; k++) {
                representation[i][k] = 0;
            }
        }
    }

    /**
     * 0: NOTHING
     * 1: WALL
     * in first row, Floor cannot appear
     * WALL length must be greater than 2
     */
    public int[] generateFirstRow(){
        Random rd1 = new Random();
        Random rd2 = new Random();
        int[] firstRow = representation[0];
        int current = 0;
        while (current < HEIGHT) {
            int determination = rd1.nextInt(2);
                if (determination == 0) {
                    int increment;
                    if (HEIGHT - current < 3) {
                        continue;
                    } else if (HEIGHT - current == 3){
                        increment = 3;
                    } else {
                        increment = 3 + rd2.nextInt(HEIGHT - current - 2 );
                    }
                    addWall(firstRow, current, increment);
                    current += increment;
                } else {
                    int increment = rd2.nextInt(HEIGHT - current + 1);
                    current += increment;
                }
        }
        return firstRow;
    }

    public static void addWall(int[] row, int pos, int num){
        if (pos + num > row.length){
            throw new ArrayIndexOutOfBoundsException("Array overflow error");
        }
        for (int index = pos; index < pos + num; index++) {
                row[index] = 1;
        }
    }
}
