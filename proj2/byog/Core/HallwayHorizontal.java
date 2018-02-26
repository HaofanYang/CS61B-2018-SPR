package byog.Core;

public class HallwayHorizontal extends Sector {
    public HallwayHorizontal(int length, int xPos, int yPos) {
        super(xPos, yPos);
        rightTopPos = new int[]{xPos + length - 1, yPos + 2};
        representation = new int[length][3];
        iniRepresentation();
    }
}
