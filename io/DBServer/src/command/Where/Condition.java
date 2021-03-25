package command.Where;

import dbStructure.Table;
import exception.CommandExecutionException;
import exception.InvalidQueryException;

import java.util.ArrayList;

public class Condition {
    ConditionNode root;

    public Condition(ArrayList<Object> list) throws InvalidQueryException {
        root= buildConditionTree(list, 0, list.size());
    }

    ConditionNode buildConditionTree(ArrayList<Object> list, int start, int end) throws InvalidQueryException {
        int separator=-1;
        if(start == end - 1){
            Object leafExpr = list.get(start);
            if(leafExpr instanceof SingleExpr) {
                ConditionNode leafNode = new ConditionNode((SingleExpr) leafExpr);
                return leafNode;
            }
            else throw new InvalidQueryException("invalid condition format, please check the brackets");
        }
        separator = findSeparator(list, start, end);
        if(separator==-1) return buildConditionTree(list, start+1, end-1);
        ConditionNode node = new ConditionNode((String)list.get(separator));
        node.setLeft(buildConditionTree(list, start, separator));
        node.setRight(buildConditionTree(list, separator+1, end));
        return node;
    }

    public int findSeparator(ArrayList<Object> list, int start, int end) throws InvalidQueryException {
        int depth = 0;
        for(int i = start; i<end; i++){
            if(list.get(i) instanceof String){
                String temp= (String) list.get(i);
                if(temp.equals("(")){
                    depth++;
                }else if(temp.equals(")")){
                    depth--;
                }else if(temp.equalsIgnoreCase("OR") || temp.equalsIgnoreCase("AND")){
                    if(list.get(i-1)!=")" || list.get(i+1)!="(") throw new InvalidQueryException("invalid query: wrong condition format");
                    if(depth==0){
                        return i;
                    }
                }
            }
        }
        return -1;
    }

    public ArrayList<Integer> getRowList(Table table) throws CommandExecutionException {
        return root.getRowList(table);
    }

}

