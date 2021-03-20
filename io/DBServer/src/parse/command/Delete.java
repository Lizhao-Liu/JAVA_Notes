package parse.command;

import parse.command.Where.Condition;

public class Delete extends CommandType{
    String tableName;
    Condition condition;
    public Delete(){}

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public String getTableName() {
        return tableName;
    }

    public Condition getCondition() {
        return condition;
    }
}
