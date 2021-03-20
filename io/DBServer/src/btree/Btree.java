package btree;

public interface Btree<T>{
    public boolean isEmpty();
    public void add(T node);
    public int size();
    public void remove(T node);
    public int height();
}
