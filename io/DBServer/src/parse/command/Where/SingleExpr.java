package parse.command.Where;

import parse.command.common.Value;

public class SingleExpr {
    String columnName;
    Value value;
    OperatorType operatorType;

    public String getColumnName() {
        return columnName;
    }

    public OperatorType getOperator() {
        return operatorType;
    }

    public Value getValue() {
        return value;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public void setOperator(OperatorType operator) {
        this.operatorType = operator;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public enum OperatorType{
        EQUAL, GREATER, LESS, EQUALORGREATER, EQUALORLESS, NOTEQUAL, LIKE
    }
    public String toString(){
        return  columnName +" " + operatorInString() + " " + value.getContent() + " (" + value.getValueType() + ")";
    }
    public String operatorInString(){
        String op = "";
        if (operatorType==OperatorType.EQUAL) op="==";
        if (operatorType==OperatorType.EQUALORGREATER) op=">=";
        if (operatorType==OperatorType.LESS) op= "<";
        if (operatorType==OperatorType.GREATER) op=">";
        if (operatorType==OperatorType.EQUALORLESS) op="<=";
        if (operatorType==OperatorType.LIKE) op="LIKE";
        if (operatorType==OperatorType.NOTEQUAL) op="!=";
        return op;
    }
}
