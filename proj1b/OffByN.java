public class OffByN implements CharacterComparator{
    private int reference;

    public OffByN(int n){
        reference = n;
    }
    @Override
    public boolean equalChars(char x, char y){
        int diff;
        diff = x - y;
        return (Math.abs(diff) == reference);
    }
}
