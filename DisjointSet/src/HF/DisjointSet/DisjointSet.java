package HF.DisjointSet;

public interface DisjointSet {
    boolean isConnect(int q, int p);
    void connect(int q, int p);
    int find(int i);
}
