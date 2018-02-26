package byog.Core;

public class HallwayVertical extends Sector{
    public HallwayVertical(int length, int xPos, int yPos) {
        super(xPos, yPos);
        rightTopPos = new int[]{xPos + 2, yPos + length - 1};
        representation = new int[3][length];
        iniRepresentation();
    }
}
