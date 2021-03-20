package parse.command.common;

public class Value {
    ValueType valueType;
    String content;
    public boolean isEmpty;

    public Value(){isEmpty=true;}
    public void setContent(String content){
        this.content=content;
        isEmpty=false;
    }
    public void setValueType(ValueType type){
        this.valueType = type;
    }
    public String getContent(){
        return content;
    }
    public ValueType getValueType(){
        return valueType;
    }

    public enum ValueType{
        StringLiteral,  BooleanLiteral, FloatLiteral, IntegerLiteral
    }
}
