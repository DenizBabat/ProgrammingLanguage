package coffee.project;

import coffee.IdentifierList;
import coffee.REPL;
import coffee.TokenList;
import coffee.datatypes.*;
import coffee.syntax.Keywords;
import coffee.syntax.Operators;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by deniz babat on 10/14/15.
 */
public class Lexer implements REPL.LineInputCallback {

    TokenList tokenList = TokenList.getInstance();      //token list
    IdentifierList identifierList = IdentifierList.getInstance();   //identifier list

    @Override
    public String lineInput(String line) {

        if (line.isEmpty())
            return null;
        else {
            String[] strings = line.split(" ");
            for (String s : strings) {
                if (!isLex(s)) {
                    lex(s); // lex2 method tokenize to token and using lex method
                }
            }
        }
        return null;
    }

    //look at whether string is lexer
    public boolean lex(String token){
        List<String> temptoken = splitbyLRpranthes(token);
        // print(temptoken);
        for (int i = 0; i <temptoken.size() ; i++) {
            if( !isLex(temptoken.get(i)) ){
                throw new IllegalArgumentException("This token is invalid "+"'"+temptoken.get(i)+"'");
            }
        }

        return false;
    }

    public boolean isLex(String token) {
       if (isOperator(token))
       {
           tokenList.addToken(new Operator(token));
           return true;
       }
       if (isInteger(token))
       {
           tokenList.addToken(new ValueInt(Integer.parseInt(token)));
           return true;
       }

       if(isAlphabet(token)){
            if(isKeywords(token)){
                if(isTrue(token)|| isFalse(token)){
                    tokenList.addToken(new ValueBinary(strToBool(token)));
                }else {
                    tokenList.addToken(new Keyword(token));
                }
            }
            else
            {
                this.addIdentifier(token);
            }
           return true;
       }

        return false;
    }



    private  void addIdentifier(String token){
        tokenList.addToken(new Identifier(token));
        identifierList.addIdentifier(token);
    }
    /**
     *  Split to string by Operators and left rigth parantheses
     *  @param str String
     *  @return List<String>
     */
    public List<String> splitbyLRpranthes(String str){
        char[] chars = str.toCharArray();

        List<String> tokens=new ArrayList<String>();
        StringBuilder stringBuilder=new StringBuilder();
        String tempStr;

        for (int i = 0; i <chars.length ; i++)
        {
            if (isleftParenthesis(chars[i])|| isrightParenthesis(chars[i]))
            {
                tempStr=stringBuilder.toString();
                if(!tempStr.equals("")) {
                    tokens.add(tempStr);
                    stringBuilder = new StringBuilder();
                }
                tokens.add(Character.toString(chars[i]));

            }else{
                stringBuilder.append(chars[i]);
            }
        }
        tempStr=stringBuilder.toString();
        if(!tempStr.equals("")) {
            tokens.add(tempStr);
        }

        return tokens;
    }

    /**
     *
     *  @param str String
     *  @return boolean
     */
    public boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
        }catch (IllegalArgumentException e){
            return false;
        }

        return true;
    }

    /**
     *  Coffee Syntax Identifier [a-zA-z]+
     *  @param str String
     *  @return boolean
     */
    public boolean isAlphabet(String str) {
        char[] chars = str.toCharArray();

        for (char c : chars) {
            if(!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }
    /**
     *  If param is Operators return true otherwise false
     *  @param str String
     *  @return boolean
     */
    public boolean isOperator(String str){

        if(isplus(str)|| isminus(str)|| isslash(str)|| isasterisk(str)|| isleftParenthesis(str)|| isrightParenthesis(str))
            return true;
        return false;
    }

    /**
     *  If param is Operators return true otherwise false
     *  @param ch char
     *  @return boolean
     */
    public boolean isOperator(char ch){
        if(isplus(ch)|| isminus(ch)|| isslash(ch)|| isasterisk(ch)|| isleftParenthesis(ch)|| isrightParenthesis(ch))
            return true;
        return false;
    }
    /**
     *  If param is Keywords return true otherwise false
     *  @param str String
     *  @return boolean
     */
    public boolean isKeywords(String str){

        if(isand(str)|| isor(str)|| isNot(str)|| isEqual(str)|| isset(str)|| isTrue(str)
                || isappend(str)|| isappend(str)|| isconcat(str)||isIF(str)|| isElse(str)
                || isdeffun(str)|| isFor(str)|| isWhile(str)|| isFalse(str)|| isThen(str)
                )
            return true;
        return false;
    }
    //OPERATORS
    public boolean isplus(String str) {

        if (str.equals(Operators.PLUS))
            return true;
        return false;
    }

    public boolean isplus(char ch) {
        if (ch == '+')
            return true;
        return false;
    }

    public boolean isminus(String str) {

        if (str.equals(Operators.MINUS))
            return true;
        return false;
    }

    public boolean isminus(char ch) {
        if (ch == '-')
            return true;
        return false;
    }

    public boolean isslash(String str) {

        if (str.equals(Operators.SLASH))
            return true;
        return false;
    }

    public boolean isslash(char ch) {
        if (ch == '/')
            return true;
        return false;
    }

    public boolean isasterisk(String str) {

        if (str.equals(Operators.ASTERISK))
            return true;
        return false;
    }

    public boolean isasterisk(char ch) {
        if (ch == '*')
            return true;
        return false;
    }

    public boolean isleftParenthesis(String str) {

        if (str.equals(Operators.LEFT_PARENTHESIS))
            return true;
        return false;
    }

    public boolean isleftParenthesis(char ch) {
        if (ch == '(')
            return true;
        return false;
    }

    public boolean isrightParenthesis(String str) {

        if (str.equals(Operators.RIGHT_PARENTHESIS))
            return true;
        return false;
    }

    public boolean isrightParenthesis(char ch) {
        if (ch == ')')
            return true;
        return false;
    }

    //KEYWORDS
    public boolean isand(String str) {
        if (str.equalsIgnoreCase(Keywords.AND))
            return true;
        return false;

    }

    public boolean isor(String str) {
        if (str.equalsIgnoreCase(Keywords.OR))
            return true;
        return false;

    }

    public boolean isNot(String str) {
        if (str.equalsIgnoreCase(Keywords.NOT))
            return true;
        return false;

    }

    public boolean isEqual(String str) {
        if (str.equalsIgnoreCase(Keywords.EQUAL))
            return true;
        return false;

    }

    public boolean isappend(String str) {
        if (str.equalsIgnoreCase(Keywords.APPEND))
            return true;
        return false;

    }

    public boolean isconcat(String str) {
        if (str.equalsIgnoreCase(Keywords.CONCAT))
            return true;
        return false;

    }

    public boolean isset(String str) {
        if (str.equalsIgnoreCase(Keywords.SET))
            return true;
        return false;

    }

    public boolean isdeffun(String str) {
        if (str.equalsIgnoreCase(Keywords.DEFFUN))
            return true;
        return false;

    }

    public boolean isFor(String str) {
        if (str.equalsIgnoreCase(Keywords.FOR))
            return true;
        return false;

    }

    public boolean isWhile(String str) {
        if (str.equalsIgnoreCase(Keywords.WHILE))
            return true;
        return false;

    }

    public boolean isIF(String str) {
        if (str.equalsIgnoreCase(Keywords.IF))
            return true;
        return false;

    }

    public boolean isThen(String str) {
        if (str.equalsIgnoreCase(Keywords.THEN))
            return true;
        return false;

    }

    public boolean isElse(String str) {
        if (str.equalsIgnoreCase(Keywords.ELSE))
            return true;
        return false;

    }

    ///BOOLEANS
    public boolean isTrue(String str) {
        if (str.equalsIgnoreCase(Keywords.TRUE))
            return true;
        return false;

    }

    public boolean isFalse(String str) {
        if (str.equalsIgnoreCase(Keywords.FALSE))
            return true;
        return false;

    }


    //if string false return false(caseignore) if true return true otherwise return false
    public boolean strToBool(String str) {
        if (isFalse(str))
            return false;
        if (isTrue(str))
            return true;
        return false;
    }

}