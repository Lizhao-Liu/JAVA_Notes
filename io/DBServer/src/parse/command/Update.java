package parse.command;

import parse.command.Where.Condition;
import parse.command.common.NameValuePair;

import java.util.ArrayList;

public class Update extends CommandType{
    String tableName;
    ArrayList<NameValuePair> nameValuePairs;
    Condition condition;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public ArrayList<NameValuePair> getNameValuePairs() {
        return nameValuePairs;
    }

    public void setNameValuePairs(ArrayList<NameValuePair> nameValuePairs) {
        this.nameValuePairs = nameValuePairs;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }
}
