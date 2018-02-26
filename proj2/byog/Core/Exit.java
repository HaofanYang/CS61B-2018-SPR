package byog.Core;

public class Exit {
    private int[] coordinate;
    private String type = null;

    public Exit(int x, int y){
        coordinate = new int[]{x, y};
    }

    public int[] position(){
        return coordinate;
    }


    //TODO
    public String getType(Sector s){
        int width = s.getWidth();
        int height = s.getHeight();
        if (coordinate[0] == 0 & !s.atCorner(coordinate)){
            type = "Left";
        }
        return type;
    }

}
