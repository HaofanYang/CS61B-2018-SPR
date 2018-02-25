package byog.Core;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import java.util.Arrays;
import java.util.Random;

public class Room {
    private int[] leftBottomPos;
    private int[] rightTopPos;
    public Exit[] wallCoordinates; // coordinates of "wall" elements (defined within "representation")
    public int[][] representation; // the 2D array that represent the room, where 1 represents wall and 0 represents floor.



    /**
     * Parameterization of representation, 1 represents wall and 0 represent floor, including exits
     */
    private void iniRepresentation() {
        createStandardRoom();
        randomSelectExits();
        addExits();
    }

    // Create a standard room without exits and record wallSpace
    private void createStandardRoom() {
        final int WIDTH = representation.length;
        final int HEIGHT = representation[0].length;
        wallCoordinates = new Exit[2 * (WIDTH + HEIGHT) - 4];
        // Create a standard room, without exits
        int index = 0;
        for (int i = 0; i < WIDTH; i++) {
            for (int k = 0; k < HEIGHT; k++) {
                if (i == 0 || i == WIDTH - 1 || k == 0 || k == HEIGHT - 1) {
                    representation[i][k] = 1;
                    // Save the current coordinate into wallSpace
                    if (wallCanAppear(i, k)) {
                        wallCoordinates[index] = new Exit(i, k);
                        index += 1;
                    }
                } else {
                    representation[i][k] = 0;
                }
            }
        }
    }

    // determine if a coordinate (x, y) can be an exit
    protected boolean wallCanAppear(int x, int y){
        final int WIDTH = representation.length;
        final int HEIGHT = representation[0].length;
        if (x == 0 || x == WIDTH - 1 || y == 0 || y == HEIGHT - 1){
            if (atCorner(new int[]{x, y})){
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }


    protected boolean atCorner(int[] coor){
        int[] c1 = (new int[] {0, 0});
        int[] c2 = (new int[] {0, getHeight() - 1});
        int[] c3 = (new int[] {getWidth() - 1, 0});
        int[] c4 = (new int[] {getWidth() - 1, getHeight() - 1});
        return Arrays.equals(coor, c1) || Arrays.equals(coor, c2) || Arrays.equals(coor, c3) || Arrays.equals(coor, c4);

    }

    // From each Exit in the wallSpace, they have 20% probability to appear.
    private void randomSelectExits() {
        Random rd = new Random();
        for (int i = 0; i < wallCoordinates.length; i++){
            int a = rd.nextInt(5);
            if (a < 4){
                wallCoordinates[i] = null;
            }
        }
    }

    private void addExits() {
        for (Exit e : wallCoordinates) {
            if (e == null){
                continue;
            } else {
                int x = e.position()[0];
                int y = e.position()[1];
                representation[x][y] = 0;
            }
        }
    }

    // ----------------------------------Public methods are below this line -----------------------------

    // generate a rectangular room in a given world
    public void addRoom(TETile[][] world) {
        int xCurrent = leftBottomPos[0];
        int xEnd = rightTopPos[0];
        int yEnd = rightTopPos[1];
        int xRep = 0;
        for (; xCurrent <= xEnd; xCurrent++) {
            int yRep = 0;
            for (int yCurrent = leftBottomPos[1]; yCurrent <= yEnd; yCurrent++) {
                if (representation[xRep][yRep] == 1) {
                    world[xCurrent][yCurrent] = Tileset.WALL;
                } else {
                    world[xCurrent][yCurrent] = Tileset.FLOOR;
                }
                yRep += 1;
            }
            xRep += 1;
        }

    }

    // Constructor
    public Room(int width, int height, int x, int y) {
        representation = new int[width][height];
        leftBottomPos = new int[]{x, y};
        rightTopPos = new int[]{x + width - 1, y + height - 1};
        iniRepresentation();
    }

    public int getWidth(){
        return representation.length;
    }

    public int getHeight(){
        return  representation[0].length;
    }

}