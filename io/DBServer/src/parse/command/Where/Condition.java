package parse.command.Where;

import exception.ParseCommandException;

import java.util.ArrayList;

public class Condition {
    ConditionNode root;

    public Condition(ArrayList<Object> list) throws ParseCommandException{
        root= buildConditionTree(list, 0, list.size());
    }

    ConditionNode buildConditionTree(ArrayList<Object> list, int start, int end) throws ParseCommandException
    {
        System.out.println("start " + start +"end " + end);
        int depth = 0;
        int separator=-1;
        if(start == end - 1){
            Object leafExpr = list.get(start);
            if(leafExpr instanceof SingleExpr) {
                ConditionNode leafNode = new ConditionNode((SingleExpr) leafExpr);
                return leafNode;
            }
            else throw new ParseCommandException("invalid condition format, please check the brackets");
        }
        for(int i = start; i<end; i++){
            if(list.get(i) instanceof String){
                String temp= (String) list.get(i);
                if(temp.equals("(")){
                    depth++;
                }else if(temp.equals(")")){
                    depth--;
                }else if(temp.equalsIgnoreCase("OR") || temp.equalsIgnoreCase("AND")){
                    if(list.get(i-1)!=")" || list.get(i+1)!="(") throw new ParseCommandException("invalid query: wrong condition format");
                    if(depth==0){
                        separator = i;
                    }
                }
            }

        }
        if(separator==-1) return buildConditionTree(list, start+1, end-1);
        ConditionNode node = new ConditionNode((String)list.get(separator));
        node.setLeft(buildConditionTree(list, start, separator));
        node.setRight(buildConditionTree(list, separator+1, end));
        return node;
    }

    private String inOrder(ConditionNode node)
    {
        if(node!=null){
            String s="";

            if(node.getType().equals(ConditionNode.ConditionNodeType.AND) || node.getType().equals(ConditionNode.ConditionNodeType.OR)){
                s+="(";
            }

            s+=inOrder(node.getLeft());

            s+=node.toString();

            s+=inOrder(node.getRight());

            if(node.getType().equals(ConditionNode.ConditionNodeType.AND) || node.getType().equals(ConditionNode.ConditionNodeType.OR)){
                s+=")";
            }

            return s;
        }

        return "";
    }
    public String getInfix(){
        System.out.println(root);
        String s="";
        s=inOrder(root);

        if(s.startsWith("(") && s.endsWith(")") && (root.getType()== ConditionNode.ConditionNodeType.OR || root.getType()== ConditionNode.ConditionNodeType.AND) ){
            return s.substring(1, s.length()-1);
        }else{
            return s;
        }

    }

}

