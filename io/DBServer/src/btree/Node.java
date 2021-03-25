package btree;

public class Node<T> {
    private T value;

    public Node(){}

    public Node(T value) {
        this.value = value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return this.value;
    }
}