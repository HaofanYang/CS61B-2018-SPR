package HF.DisjointSet;

import HF.DisjointSet.DisjointSet;

import java.util.HashMap;
import java.util.Map;


public class NaiveDisjointSet implements DisjointSet {
    private int[] representation;
    private Map<Integer, Integer> size;

    public NaiveDisjointSet(int n){
        representation = new int[n];
        size = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i++) {
            representation[i] = i;
            size.put(i, 1);
        }

    }

    @Override
    public boolean isConnect(int q, int p) {
        return findParent(q) == findParent(p);
    }

    @Override
    public void connect(int q,int p){
        int parentOfq = findParent(q);
        int parentOfp = findParent(p);
        int newSize = size(q) + size(p);
        if (size(q) > size(p)) {
            representation[parentOfp] = parentOfq;
            size.put(q, newSize);
        } else {
            representation[parentOfq] = parentOfp;
            size.put(p, newSize);
        }
    }

    @Override
    public int find(int i) {
        return findParent(i);
    }

    private int findParent(int p) {
        while (p != representation[p]){
            p = representation[p];
        }
        return p;
    }

    private int size(int p){
        Integer parent = findParent(p);
        return size.get(parent);
    }
}
