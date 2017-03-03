package coffee.project;

import coffee.IdentifierList;
import coffee.REPL;
import coffee.TokenList;
import coffee.datatypes.Identifier;
import coffee.datatypes.Keyword;
import coffee.datatypes.Operator;
import coffee.datatypes.ValueInt;
import coffee.syntax.Keywords;
import coffee.syntax.Operators;

import java.util.StringTokenizer;

/**
 *
 *  BUSRA ARSLAN
 *  131044021
 */

//////COMMENTS/////////////////
//Yararlanilan siteler
//http://www.mkyong.com/java/java-stringtokenizer-example/
//http://www.tutorialspoint.com/java/
//identifier icin  http://forum.gronia.org/konu-java-karakterler-880.html
//catch expection icin  http://www.tutorialspoint.com/java/java_exceptions.htm
//Description:
// catch expection yapildi.
//operator ve keywordlar teker teker tanimlandi.
//NOT >>>>> ValueBool icin belirtilmis true ve false sadece Keywords olarak alindi.
//identifierlar bosluklu bir sekilde girildiyse kabul edilip hem token_list hem de identifier_list 'e
//ekleniyorlar.
//Bazý durumlar icin parantez kontrolleri yapildi.


public class Lexer implements REPL.LineInputCallback {
    @Override
    public String lineInput(String line) {

        try {
            TokenList token_list = TokenList.getInstance();
            IdentifierList identifier_list = IdentifierList.getInstance();

            //StringTokenizer bosluk gorunce stringi parcalar
            StringTokenizer broken = new StringTokenizer(line, " ");

            int i, int_temp=0, temp2=0, t=0;
            char karakter;

            //bosluga gore ayirdigim her string parcasi icin dongu
            while(broken.hasMoreElements())
            {
                //next_broken stringi parcalanan her bir kismi
                String next_broken = broken.nextToken();

                /////////////////////////////OPERATORS/////////////////////////////////
                ///////////////////////////////////////////////////////////////////////
                //boslukla bolunce birden fazla sol ve sag parantez varsa her birini
                //operator olarak alsin diye bolunen stringin tum elemanlarina bakilacak.
                for(i=0; i< next_broken.length() ; ++i)
                {
                    char ch_2= next_broken.charAt(i);
                    if(ch_2 == '('  || ch_2 == ')')
                    {
                        if (next_broken.contains(Operators.LEFT_PARENTHESIS)){
                            token_list.addToken(new Operator(Operators.LEFT_PARENTHESIS));
                        }
                        if (next_broken.contains(Operators.RIGHT_PARENTHESIS)) {
                            token_list.addToken(new Operator(Operators.RIGHT_PARENTHESIS));
                        }
                    }
                }

                if (next_broken.contains(Operators.PLUS)) {
                    token_list.addToken(new Operator(Operators.PLUS));
                    //System.out.println(broken.equals(Operators.PLUS));
                }
                if (next_broken.contains(Operators.MINUS)) {
                    token_list.addToken(new Operator(Operators.MINUS));
                }
                if (next_broken.contains(Operators.SLASH)) {
                    token_list.addToken(new Operator(Operators.SLASH));
                }
                if (next_broken.contains(Operators.ASTERISK)) {
                    token_list.addToken(new Operator(Operators.ASTERISK));
                }


                //////////////KEYWORDS///////////////////
                if (next_broken.contains(Keywords.AND)) {
                    token_list.addToken(new Keyword(Keywords.AND));
                }
                if (next_broken.contains(Keywords.OR)) {
                    token_list.addToken(new Keyword(Keywords.OR));
                }
                if (next_broken.contains(Keywords.NOT)) {
                    token_list.addToken(new Keyword(Keywords.NOT));
                }
                if (next_broken.contains(Keywords.EQUAL)) {
                    token_list.addToken(new Keyword(Keywords.EQUAL));
                }
                if (next_broken.contains(Keywords.APPEND)) {
                    token_list.addToken(new Keyword(Keywords.APPEND));
                }
                if (next_broken.contains(Keywords.CONCAT)) {
                    token_list.addToken(new Keyword(Keywords.CONCAT));
                }
                if (next_broken.contains(Keywords.SET)) {
                    token_list.addToken(new Keyword(Keywords.SET));
                }
                if (next_broken.contains(Keywords.DEFFUN)) {
                    token_list.addToken(new Keyword(Keywords.DEFFUN));
                }
                if (next_broken.contains(Keywords.FOR)) {
                    token_list.addToken(new Keyword(Keywords.FOR));
                }
                if (next_broken.contains(Keywords.WHILE)) {
                    token_list.addToken(new Keyword(Keywords.WHILE));
                }
                if (next_broken.contains(Keywords.IF)) {
                    token_list.addToken(new Keyword(Keywords.IF));
                }
                if (next_broken.contains(Keywords.THEN)) {
                    token_list.addToken(new Keyword(Keywords.THEN));
                }
                if (next_broken.contains(Keywords.ELSE)) {
                    token_list.addToken(new Keyword(Keywords.ELSE));
                }
                if (next_broken.contains(Keywords.TRUE)) {
                    token_list.addToken(new Keyword(Keywords.TRUE));
                }
                if (next_broken.contains(Keywords.FALSE)) {
                    token_list.addToken(new Keyword(Keywords.FALSE));
                }


                ////////////////////////IDENTIFIER///////////////////////
                /////////////////////////////////////////////////////////

                for(i=0; i< next_broken.length() ; ++i)
                {
                    int_temp=  next_broken.length();
                    char ch= next_broken.charAt(i);    //System.out.println(ch);


                    if(Character.isLetter(ch) == true && ch != ')' && ch != '(' )
                    {
                        if(i+1 < next_broken.length() && next_broken.charAt(i+1) == ')') {
                            String str=String.valueOf(ch);
                            identifier_list.addIdentifier(str);         //identifier_list e eklendi.
                            token_list.addToken(new Identifier(str));   //token_list e eklendi.
                        }
                        else{
                            identifier_list.addIdentifier(next_broken);         //identifier_list e eklendi.
                            token_list.addToken(new Identifier(next_broken));
                        }

                    }

                }



                ///////////////////////////VALUE INT///////////////////////
                ///////////////////////////////////////////////////////////
                for(i=0; i< next_broken.length() ; ++i)
                {
                    char ch_temp = next_broken.charAt(i);    //System.out.println(ch_temp);

                    if(Character.isDigit(ch_temp) == true )
                    {
                        temp2= 1;
                        karakter = next_broken.charAt(i);
                        t= Character.getNumericValue(karakter);       ///integers casting
                    }
                    else if(i>0){
                        if(Character.isDigit(t) == true)
                        {
                            if(next_broken.charAt(i-1)  == '('    ||  next_broken.charAt(i+1)  == ')')
                            {
                                temp2= 1;
                            }
                        }
                    }
                    else
                        temp2=0;
                }

                if(temp2 == 1)
                {
                    token_list.addToken(new ValueInt(t));
                }

                //////////////////////////////////////////////////////

            }

        } catch (Exception e) {     ////catch exception
            e.printStackTrace();
        }

        return null;

    }
}


