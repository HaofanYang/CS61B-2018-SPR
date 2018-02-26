package byog.Core;

/** Rooms with exits on the left */
public class RoomLE extends Sector {

    public RoomLE(int width, int height, int xPos, int yPos){
        super(xPos, yPos);
        representation = new int[width][height];
        rightTopPos = new int[]{xPos + width - 1, yPos + height - 1};
        iniRepresentation();
    }

    @Override
    protected boolean wallCanAppear(int x, int y){
        if (x == 0){
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
