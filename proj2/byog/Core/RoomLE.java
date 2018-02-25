package byog.Core;

/** Rooms with exits on the left */
public class RoomLE extends Room {

    public RoomLE(int width, int height, int xPos, int yPos){
        super(width, height, xPos, yPos);
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
