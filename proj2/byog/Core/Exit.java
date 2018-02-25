package byog.Core;

public class Exit {
    private int[] coordinate;


    public Exit(int x, int y){
        coordinate = new int[]{x, y};
    }

    public int[] position(){
        return coordinate;
    }

}
