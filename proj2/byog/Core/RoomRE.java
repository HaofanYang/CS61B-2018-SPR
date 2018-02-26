package byog.Core;

/** Rooms with exits on the right */
public class RoomRE extends Sector {
    public RoomRE(int width, int height, int xPos, int yPos){
        super(xPos, yPos);
        representation = new int[width][height];
        rightTopPos = new int[]{xPos + width - 1, yPos + height - 1};
        iniRepresentation();
    }

    @Override
    protected boolean wallCanAppear(int x, int y){
        final int WIDTH = representation.length;
        if (x == WIDTH - 1){
            if (atCorner(new int[]{x, y})){
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

}
