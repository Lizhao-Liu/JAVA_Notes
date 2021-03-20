package parse.command.Where;


public class ConditionNode {
    private ConditionNodeType type;
    private SingleExpr expression;
    private ConditionNode left;
    private ConditionNode right;

    public ConditionNode(){expression = null; left = null; right = null; type = ConditionNodeType.UNDEFINED;}

    public ConditionNode(String nodeTypeText){
        if(nodeTypeText.equals("OR")){
            this.type=ConditionNodeType.OR;
        }else if(nodeTypeText.equals("AND")){
            this.type=ConditionNodeType.AND;}
        else{
            this.type=ConditionNodeType.UNDEFINED;
        }
        expression = null; left = null; right = null;
    }
    public ConditionNode(SingleExpr expr){
        this.expression = expr;
        this.type = ConditionNodeType.EXPRESSION;
        left = null; right = null;
    }

    public SingleExpr getExpression() {
        return expression;
    }

    public void setExpression(SingleExpr singleExpr) {
        this.expression = singleExpr;
    }

    public void setType(ConditionNodeType type){
        this.type = type;
    }

    public ConditionNodeType getType(){
        return type;
    }
    public void setLeft(ConditionNode leftNode) {
        this.left = leftNode;
    }

    public void setRight(ConditionNode rightNode) {
        this.right = rightNode;
    }

    public ConditionNode getLeft() {
        return left;
    }

    public ConditionNode getRight() {
        return right;
    }

    public String toString(){
        if(this.type==ConditionNodeType.EXPRESSION){
            return String.valueOf(expression)+" ";
        }else if(this.type==ConditionNodeType.AND){
            return "AND ";
        }else if(this.type==ConditionNodeType.OR){
            return "OR ";
        }else{
            return "? ";
        }
    }

    public enum ConditionNodeType{OR, AND, EXPRESSION, UNDEFINED}
}

