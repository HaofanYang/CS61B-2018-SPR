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

    public boolean onTheRight(int[] coor, Sector Sector){
        final int WIDTH = Sector.representation.length;
        return coor[0] == WIDTH - 1;
    }

    public boolean onTheTop(int[] coor, Sector Sector){
        final int HEIGHT = Sector.representation[0].length;
        return coor[1] == HEIGHT - 1;
    }

    public boolean onTheBottom(int[] coor){
        return coor[1] == 0;
    }


    @Test
    public void testSectorLE() {
        for (int i = 0; i < 1000; i++) {
            Sector Sectorle1 = new RoomLE(10, 10, 30, 42);
            for (Exit e : Sectorle1.getExits()) {
                int[] coor1 = e.position();
                assertTrue("The coordinate (" + coor1[0] + ", " + coor1[1] + ") appears!",onTheLeft(coor1));
            }

        }
    }

    @Test
    public void testSectorRE() {
        for (int i = 0; i < 1000; i++) {
            Sector Sectorre1 = new RoomRE(10, 10, 30, 42);
            for (Exit e : Sectorre1.getExits()) {
                int[] coor1 = e.position();
                assertTrue("The coordinate (" + coor1[0] + ", " + coor1[1] + ") appears!",onTheRight(coor1, Sectorre1));
            }

        }
    }

    @Test
    public void testSectorTE() {
        for (int i = 0; i < 1000; i++) {
            Sector Sectorte1 = new RoomTE(10, 10, 30, 42);
            for (Exit e : Sectorte1.getExits()) {
                int[] coor1 = e.position();
                assertTrue("The coordinate (" + coor1[0] + ", " + coor1[1] + ") appears!",onTheTop(coor1, Sectorte1));
            }

        }
    }

    @Test
    public void testSectorBE() {
        for (int i = 0; i < 1000; i++) {
            Sector Sectorbe1 = new RoomBE(10, 10, 30, 42);
            for (Exit e : Sectorbe1.getExits()) {
                int[] coor1 = e.position();
                assertTrue("The coordinate (" + coor1[0] + ", " + coor1[1] + ") appears!",onTheBottom(coor1));
            }

        }
    }

    public static void main(String[] args){
        TERenderer tile = new TERenderer();
        tile.initialize(60, 60);
        TETile[][] world = new TETile[60][60];
        for (int i = 0; i < world.length; i++){
            for (int k = 0; k < world[0].length; k++) {
                world[i][k] = Tileset.NOTHING;
            }
        }
        Sector Sectorle1 = new HallwayVertical(10,  30, 42);
        Sectorle1.addToWorld(world);
        tile.renderFrame(world);
    }
}
