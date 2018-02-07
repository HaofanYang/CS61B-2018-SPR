public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> result = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            result.addLast(word.charAt(i));
        }
        return result;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> wordArray = wordToDeque(word);
        if (wordArray.size() == 0 || wordArray.size() == 1){
            return true;
        }
        while (wordArray.size() > 1){
            boolean result = wordArray.removeFirst() == wordArray.removeLast();
            if (result){
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator comparator){
        Deque<Character> deque = wordToDeque(word);
        if (deque.size() == 0 || deque.size() == 1){
            return true;
        }
        for (; deque.size() > 1;) {
            if (comparator.equalChars(deque.removeFirst(), deque.removeLast())) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }
}
