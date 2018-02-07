public class OffByOne implements CharacterComparator {
    @Override
    public boolean equalChars(char x, char y){
        int diff;
        diff = x - y;
        return (Math.abs(diff) == 1);
    }
}
