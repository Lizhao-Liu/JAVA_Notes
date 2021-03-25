package command.Where;

import command.common.Value;
import dbStructure.Row;
import dbStructure.Table;
import exception.CommandExecutionException;

import java.util.ArrayList;

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

    public ArrayList<Integer> getRowIds(Table table) throws CommandExecutionException {
        int column = table.getColumnIndex(columnName);
        if(table.getColumn(columnName).getType()!=value.getValueType()){
            throw new CommandExecutionException("attribute "+columnName +" cannot be converted to "+ value.getValueType());
        }
        ArrayList<Integer> keys= new ArrayList<>();
        ArrayList<Row> rows = table.getRows();
        for(int i = 0; i<rows.size(); i++){
            if(isTarget(rows.get(i).getValue(column))){
                keys.add(rows.get(i).getId());
            }
        }
        return keys;
    }
    boolean isTarget(Value element) {
        if (operatorType==OperatorType.EQUAL) return isEqual(element.getContent(),value.getContent());
        if (operatorType==OperatorType.EQUALORGREATER) return isGreaterOrEqual(element.getFloat(), value.getFloat());
        if (operatorType==OperatorType.LESS) return isLess(element.getFloat(), value.getFloat());
        if (operatorType==OperatorType.GREATER) return isGreater(element.getFloat(), value.getFloat());
        if (operatorType==OperatorType.EQUALORLESS) return isLessOrEqual(element.getFloat(), value.getFloat());
        if (operatorType==OperatorType.LIKE) return isLike(element.getContent(), value.getContent());
        if (operatorType==OperatorType.NOTEQUAL) return isNotEqual(element.getContent(), value.getContent());
        return false;
    }
    boolean isEqual(String element, String comparator){
        if(element.equals(comparator)) return true;
        return false;
    }
    boolean isGreaterOrEqual(Float element, Float comparator){
        if(element>=comparator) return true;
        return false;
    }
    boolean isLess(Float element, Float comparator){
        if(element<comparator) return true;
        return false;
    }
    boolean isGreater(Float element, Float comparator){
        if(element>comparator) return true;
        return false;
    }
    boolean isLessOrEqual(Float element, Float comparator){
        if(element<=comparator) return true;
        return false;
    }
    boolean isLike(String element, String comparator){
        if(comparator.length()>element.length()) return false;
        return element.contains(comparator);
    }
    boolean isNotEqual(String element, String comparator){
        if(element.equals(comparator)) return false;
        return true;
    }
    public static void main(String[] args){
        SingleExpr e = new SingleExpr();
        System.out.println(e.isLike("a", "bc"));
        System.out.println(e.isLike("l23", "l23"));
        System.out.println(e.isGreater((float)1.200, (float)1.2));
        System.out.println(e.isLess((float)3,(float)4));
        System.out.println(e.isGreater((float)4,(float)3));
        System.out.println(e.isNotEqual("33", "34"));
        System.out.println(e.isLike("Tr", "tr"));
        System.out.println(e.isLike("llllll", "lll"));
        System.out.println(e.isEqual("true", "true"));
        System.out.println(e.isNotEqual("false", "true"));
        System.out.println(e.isGreaterOrEqual((float)2, (float)2));
        System.out.println(e.isLessOrEqual((float)2,(float)3));


    }
}
