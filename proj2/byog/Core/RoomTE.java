package byog.Core;

/** Rooms with exits on the top */
public class RoomTE extends Room {
    public RoomTE(int width, int height, int xPos, int yPos){
        super(width, height, xPos, yPos);
    }

    @Override
    protected boolean wallCanAppear(int x, int y){
        final int HEIGHT = representation[0].length;
        if (x == HEIGHT - 1){
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
