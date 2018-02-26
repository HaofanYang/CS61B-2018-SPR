package byog.Core;

/** Rooms with exits on the top */
public class RoomTE extends Sector {
    public RoomTE(int width, int height, int xPos, int yPos){
        super(xPos, yPos);
        representation = new int[width][height];
        rightTopPos = new int[]{xPos + width - 1, yPos + height - 1};
        iniRepresentation();
    }

    @Override
    protected boolean wallCanAppear(int x, int y){
        final int HEIGHT = representation[0].length;
        if (y == HEIGHT - 1){
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
