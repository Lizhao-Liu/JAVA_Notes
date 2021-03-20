package parse;

import java.util.ArrayList;

public class Tokenizer {
    private int pos;
    private int length;
    private char[] currLine;

    public Tokenizer(String line){
        pos = 0;
        this.currLine=line.toCharArray();
        length = line.length();
    }


    public Token peek(){
        int origPos = pos;
        Token next = nextToken();
        pos = origPos;
        return next;
    }
    public Token peek(int n){
        int origPos = pos;
        Token next = new Token();
        while(n>0){
            next = nextToken();
            n--;
        }
        pos = origPos;
        return next;
    }



    public Token nextToken()  {
        Token token = new Token();
        while (pos<length && isBlank(currLine[pos])) {
            pos++;
        }
        if(pos>=length){
            token.setIsEmpty();
            return token;
        }

        char curr = currLine[pos];
        if (isSymbol(curr)) {
            token.setContent(Character.toString(curr));
            token.setIsSymbol();
            if(curr==';'){
                token.setIsEndSymbol();
            }
            pos++;
            return token;
        }
        if (isSingleQuote(curr)) {
            try {
                return nextQuoteLine();
            } catch (InvalidTokenException e) {
                System.out.println(e);
                System.exit(1);
            }
        }
        if(isDigital(curr)){
            if((token = nextFloat())!=null) return token;
        }
        if (isLetter(curr) || isDigital(curr)) {
            return nextString();
        }
        System.out.println("invalid token");
        System.exit(1);
        return token;
    }

    public Token nextFloat()
    {
        int count= 0;
        int i = pos;
        StringBuilder s = new StringBuilder();
        Token token = null;
        while(i<length && (isDigital(currLine[i])||currLine[i]=='.')){
            if(currLine[i]=='.'){count++;}
            s.append(currLine[i]);
            i++;
        }
        if((i-pos)>2 && count==1 && (currLine[i-1]!='.')){
            token = new Token(s.toString());
            pos = i;
        }
        return token;
    }

    public Token nextString(){
        StringBuilder s = new StringBuilder();
        while(pos<length&&(isLetter(currLine[pos])||isDigital(currLine[pos]))){
            s.append(currLine[pos]);
            pos++;
        }
        Token token = new Token(s.toString());
        token.setIsAlphanumeric();
        return token;
    }

    public Token nextQuoteLine() throws InvalidTokenException{
        StringBuilder s = new StringBuilder();
        pos++;
        try{
            while(!isSingleQuote(currLine[pos])){
                s.append(currLine[pos]);
                pos++;
            }
            pos++;
            Token token = new Token(s.toString());
            token.setIsStringLiteral();
            return token;
        }catch(ArrayIndexOutOfBoundsException e){
            throw new InvalidTokenException("Missing close quote");
        }

    }

    private boolean isSymbol(char c){
        return (c == '>' || c == '<' || c == '=' || c == '*' ||
                c == ',' || c == '(' || c == ')'||c==';'|| c=='!'|| c=='|'|| c=='.');
    }
    private boolean isLetter(char c){
        return (c>='a'&&c<='z')||(c>='A'&&c<='Z');
    }
    private boolean isDigital(char c){
        return((c>='0'&&c<='9'));
    }
    private boolean isBlank(char c){
        return (c==' '||c=='\n'||c=='\t');
    }
    private boolean isSingleQuote(char c){
        return c=='\'';
    }

    public static void main(String[] args){
        Tokenizer tokenizer = new Tokenizer("1.22");
        Token token = tokenizer.nextToken();
        System.out.println(token.getContent());
        System.out.println(token.isAlphanumeric);
//        Tokenizer tokenizer = new Tokenizer("sELECT ***= FROM 'ABC  22' 'WHERE' (anc =1) AND ((cba >!= 2);");
//        Token token = tokenizer.nextToken();
//        while(!token.isEndSymbol){
//            System.out.println(token.getContent());
//            System.out.println(tokenizer.pos);
//            System.out.println(tokenizer.peek());
//            System.out.println(tokenizer.pos);
//            System.out.println(token.isSymbol);
//            System.out.println(token.isAlphanumeric);
//            System.out.println(token.isStringLiteral);
//            token = tokenizer.nextToken();
//        }
//        System.out.println(token.getContent());
//        token=tokenizer.nextToken();
//        System.out.println(token.isEmpty);
//        System.out.println(token.getContent());

    }

}
