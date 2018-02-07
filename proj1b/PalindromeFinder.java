/** This class outputs all palindromes in the words file in the current directory. */
public class PalindromeFinder {
    public static void main(String[] args) {
        int minLength = 4;
        //In in = new In("../library-sp18/data/words.txt");
        Palindrome palindrome = new Palindrome();
        //CharacterComparator cc = new OffByN(3);

        /**
         * while (!in.isEmpty()) {
            String word = in.readString();
            if (word.length() >= minLength && palindrome.isPalindrome(word, cc)) {
                System.out.println(word);
            }
        }
        int numOfWord = 0;
        while (!in.isEmpty()) {
            in.readString();
            numOfWord++;
        }
        System.out.println(numOfWord);
         */

        /** this chunk will find N that has the most palindromes in English*/
        int mostPalindromeGap = 0;
        int previousNumOfParlindrome = 0;
        for (int i = 0; i <= 25; i++){
            In in = new In("../library-sp18/data/words.txt");
            int numOfPalindrome = 0;
            CharacterComparator cc = new OffByN(i);
            while (!in.isEmpty()) {
                if (palindrome.isPalindrome(in.readString(), cc)) {
                    numOfPalindrome += 1;
                }
            }
            if (numOfPalindrome > previousNumOfParlindrome){
                previousNumOfParlindrome = numOfPalindrome;
                mostPalindromeGap = i;
            }
        }
        System.out.println("When N is " + mostPalindromeGap + ", there are " +
                previousNumOfParlindrome + " Palindromes at most. \n");

        /** Find the Longest palindrome */
        int greatestLength = 0;
        String greatest = "";
        int greatestGap = 0;
        for (int i = 0; i <= 25; i++){
            In in = new In("../library-sp18/data/words.txt");
            int iGreatestLength = 0;
            String currentGreatest = "";
            CharacterComparator cc = new OffByN(i);
            while (!in.isEmpty()) {
                String curr = in.readString();
                if (palindrome.isPalindrome(curr, cc) && curr.length() > iGreatestLength) {
                    iGreatestLength = curr.length();
                    currentGreatest = curr;
                }
            }
            if (iGreatestLength > greatestLength){
                greatestLength = iGreatestLength;
                greatestGap = i;
                greatest = currentGreatest;
            }
        }
        System.out.println("The word " + greatest + " is the longest OffByN (OffBy" +
                greatestGap + ") Palindrome in English.");
    }
}
