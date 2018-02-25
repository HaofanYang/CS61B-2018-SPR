package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import org.junit.Test;

import java.lang.reflect.WildcardType;

import static org.junit.Assert.*;

public class TestRoomLE {

    public static boolean onTheLeft(int[] coor){
        return coor[0] == 0;
    }

    public boolean onTheRight(int[] coor, Room room){
        final int WIDTH = room.representation.length;
        return coor[0] == WIDTH - 1;
    }

    public boolean onTheTop(int[] coor, Room room){
        final int HEIGHT = room.representation[0].length;
        return coor[0] == HEIGHT - 1;
    }

    public boolean onTheBottom(int[] coor){
        return coor[0] == 0;
    }


    @Test
    public void testRoomLE() {
        for (int i = 0; i < 1000; i++) {
            Room roomle1 = new RoomLE(10, 10, 30, 42);
            for (Exit e : roomle1.getExits()) {
                int[] coor1 = e.position();
                assertTrue("The coordinate (" + coor1[0] + ", " + coor1[1] + ") appears!",onTheLeft(coor1));
            }

        }
    }

    @Test
    public void testRoomRE() {
        for (int i = 0; i < 1000; i++) {
            RoomRE roomre1 = new RoomRE(10, 10, 30, 42);
            for (Exit e : roomre1.getExits()) {
                int[] coor1 = e.position();
                assertTrue("The coordinate (" + coor1[0] + ", " + coor1[1] + ") appears!",onTheRight(coor1, roomre1));
            }

        }
    }

    @Test
    public void testRoomTE() {
        for (int i = 0; i < 1000; i++) {
            RoomTE roomte1 = new RoomTE(10, 10, 30, 42);
            for (Exit e : roomte1.getExits()) {
                int[] coor1 = e.position();
                assertTrue("The coordinate (" + coor1[0] + ", " + coor1[1] + ") appears!",onTheTop(coor1, roomte1));
            }

        }
    }

    @Test
    public void testRoomBE() {
        for (int i = 0; i < 1000; i++) {
            RoomBE roombe1 = new RoomBE(10, 10, 30, 42);
            for (Exit e : roombe1.getExits()) {
                int[] coor1 = e.position();
                assertTrue("The coordinate (" + coor1[0] + ", " + coor1[1] + ") appears!",onTheBottom(coor1));
            }

        }
    }
/**
    public static void main(String[] args){
        TERenderer tile = new TERenderer();
        tile.initialize(60, 60);
        TETile[][] world = new TETile[60][60];
        for (int i = 0; i < world.length; i++){
            for (int k = 0; k < world[0].length; k++) {
                world[i][k] = Tileset.NOTHING;
            }
        }
        RoomLE roomle1 = new RoomLE(10, 10, 30, 42);
        roomle1.addRoom(world);
        tile.renderFrame(world);
    }
 */
}
