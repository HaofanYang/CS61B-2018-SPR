package hw3.hash;

import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        int[] counter = new int[M];
        for (int i = 0; i < M; i++) {
            counter[i] = 0;
        }
        for (Oomage o : oomages) {
            int bucketNum = (o.hashCode() & 0x7FFFFFFF) % M;
            counter[bucketNum] += 1;
        }
        double upBound = ((double) oomages.size()) / 2.5;
        double lowBound = ((double) oomages.size()) / 50;
        int max = OomageTestUtility.findMax(counter);
        int min = OomageTestUtility.findMin(counter);
        if (max > upBound || min < lowBound) {
            return false;
        } else {
            return true;
        }
    }

    public static int findMax(int[] array) {
        int max = 0;
        int current = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] > current) {
                current = array[i];
                max = i;
            } else {
                continue;
            }
        }
        return current;
    }

    public static int findMin(int[] array) {
        int min = 0;
        int current = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] < current) {
                current = array[i];
                min = i;
            } else {
                continue;
            }
        }
        return current;
    }
}
