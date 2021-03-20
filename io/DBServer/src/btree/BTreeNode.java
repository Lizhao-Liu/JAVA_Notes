package btree;

public class BTreeNode extends Node{
    private BTreeNode right;
    private BTreeNode left;
    private String value;

    public void setRight(BTreeNode right){
        this.right = right;
    }
    public void setLeft(BTreeNode left){
        this.left = left;
    }
    public void setValue(String value){
        this.value=value;
    }
    public BTreeNode getRight(){
        return right;
    }
    public BTreeNode getLeft(){
        return left;
    }

}
