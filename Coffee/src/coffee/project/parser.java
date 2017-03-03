package coffee.project;


import coffee.TokenList;
import coffee.datatypes.Token;

import java.util.StringTokenizer;

//Busra ARSLAN 131044021
///////////////Description:////////////////////////////////////////////////////////////
//RightMost yolu ile degiskenleri parcaladim.
//Ilk parttaki birkac eksik olan kisim giderildi.Bazý degisiklikler yapildi Lexer.java parser.java icin.
//Tekli islemlerde tam olarak calisiyor. EXPB ve EXPI islemlerinin hepsi taimlandi.
//String array kullanimi disinda internetten bir sey alinmadi.

//ikili islemlerde de calismaktadir.
//algoritmasi tamamen bana aittir.internetten bakilmadi.

public class parser {

    public static String parserim(){

        int i;
        int size=0;
        int check=0;
        int temp1=0, temp2=0, temp3=0, temp4=0;
        int temp5=0, temp6=0, temp7=0, temp8=0;


        //arrayin hepsi
        TokenList token_list = TokenList.getInstance();

        //string array kullandim
        String strList[]= new String[250];

        //arrayin bir elemani
        for(Token token:token_list.getAllTokens()) {
            strList[size] = token.toString();
            ++size;
        }

        //Parantezle baslayip parantezle bitmis mi kontrolu
        if(strList[0].contains("Operator_(") ) {
                check = 1;
        }
        else{
            check = 0;
            System.out.println("Dogru sekilde input girmediniz :/Tekrar deneyin");
            return null;
        }


        if(check == 1) {

            System.out.println("");
            System.out.println("START -> INPUT");
            System.out.println("      -> EXP");


            if (strList[1].contains("Operator_"))
                System.out.println("      -> EXPI");


            //////////////////////////////////////////////////////////////
            //EXPI durumu icin yapiyor
            //temp1, temp2 >> + icin
            //temp2, temp3 >> - icin
            //temp4, temp5 >> / icin
            //temp6, temp7 >> * icin
            for (i = 0; i < size; ++i) {

                if (strList[i].contains("Operator_+")) {
                    for (int j = 0; j < size; ++j) {
                        if (strList[j].contains("IDENTIFIER_")) {
                            ++temp1;
                        }
                        if (strList[j].contains("VALUE_INT_")) {
                            ++temp2;
                        }
                    }
                }


                if (strList[i].contains("Operator_-")){
                    for (int j = 0; j < size; ++j) {
                        if (strList[j].contains("IDENTIFIER_")) {
                            ++temp3;
                        }
                        if (strList[j].contains("VALUE_INT_")) {
                            ++temp4;
                        }
                    }
                }


                if (strList[i].contains("Operator_/")){
                    for (int j = 0; j < size; ++j) {
                        if (strList[j].contains("IDENTIFIER_")) {
                            ++temp5;
                        }
                        if (strList[j].contains("VALUE_INT_")) {
                            ++temp6;
                        }
                    }
                }


                if (strList[i].contains("Operator_*")){
                    for (int j = 0; j < size; ++j) {
                        if (strList[j].contains("IDENTIFIER_")) {
                            ++temp7;
                        }
                        if (strList[j].contains("VALUE_INT_")) {
                            ++temp8;
                        }
                    }
                }
            }
        }

        /////////////////////////////////////////////////////////////
        ////////////Sadece tek operator varsa (+,-,*,/)  ///////////
        //sadece + operatoru varsa
        if ((temp1 !=0 || temp2 !=0) && temp3==0 && temp4==0 && temp5==0 && temp6==0 && temp7==0 && temp8==0 ) {
            System.out.println("      -> (+ EXPI EXPI)");
            System.out.println("      -> (+ EXPI  Id)");
            System.out.println("      -> (+ Id  Id)");
        }
        //sadece - operatoru varsa
        if ((temp3 !=0 || temp4 !=0)  && temp1==0 && temp2==0 && temp5==0 && temp6==0 && temp7==0 && temp8==0) {
            System.out.println("      -> (- EXPI EXPI)");
            System.out.println("      -> (- EXPI  Id)");
            System.out.println("      -> (- Id  Id)");
        }
        //sadece / operatoru varsa
        if ((temp5 !=0 || temp6 !=0) && temp1==0 && temp2==0 && temp3==0 && temp4==0 && temp7==0 && temp8==0) {
            System.out.println("      -> (/ EXPI EXPI)");
            System.out.println("      -> (/ EXPI  Id)");
            System.out.println("      -> (/ Id  Id)");
        }
        //sadece * operatoru varsa
        if ((temp7 !=0 || temp8 !=0)  && temp1==0 && temp2==0 && temp3==0 && temp4==0 && temp5==0 && temp6==0) {
            System.out.println("      -> (* EXPI EXPI)");
            System.out.println("      -> (* EXPI  Id)");
            System.out.println("      -> (* Id  Id)");
        }


        /////////////////////////////////////////////////////////////////////////////////////
        //ilk islem + olup 2. islemin diger operatorlerden biri oldugu durumlar
        if (temp1 !=0 || temp2 !=0) {
            if (temp3 != 0 || temp4 != 0 && (temp5 == 0 && temp6 == 0 && temp7 == 0 && temp8 == 0)) {
                if (strList[1].contains("Operator_+")) {
                    System.out.println("      -> (+ EXPI (- EXPI EXPI))");
                    System.out.println("      -> (+ EXPI (- EXPI Id))");
                    System.out.println("      -> (+ EXPI (- Id Id))");
                    System.out.println("      -> (+ Id (- Id Id))");
                }
            } else if (temp5 != 0 || temp6 != 0 && (temp3 == 0 && temp4 == 0 && temp7 == 0 && temp8 == 0)) {
                if (strList[1].contains("Operator_+")) {
                    System.out.println("      -> (+ EXPI (/ EXPI EXPI))");
                    System.out.println("      -> (+ EXPI (/ EXPI Id))");
                    System.out.println("      -> (+ EXPI (/ Id Id))");
                    System.out.println("      -> (+ Id (/ Id Id))");
                }
            } else if (temp7 != 0 || temp8 != 0 && (temp5 == 0 && temp6 == 0 && temp3 == 0 && temp4 == 0)) {
                if (strList[1].contains("Operator_+")) {
                    System.out.println("      -> (+ EXPI (* EXPI EXPI))");
                    System.out.println("      -> (+ EXPI (* EXPI Id))");
                    System.out.println("      -> (+ EXPI (* Id Id))");
                    System.out.println("      -> (+ Id (* Id Id))");
                }
            } else if (temp7 != 0 || temp8 != 0 && (temp5 == 0 && temp6 == 0 && temp3 == 0 && temp4 == 0)) {
                if (strList[1].contains("Operator_+")) {
                    System.out.println("      -> (+ EXPI (* EXPI EXPI))");
                    System.out.println("      -> (+ EXPI (* EXPI Id))");
                    System.out.println("      -> (+ EXPI (* Id Id))");
                    System.out.println("      -> (+ Id (* Id Id))");
                }

            }if(temp3!=0 && temp7!=0  && temp5==0 && temp6==0){
                if(strList[1].contains( "Operator_+")) {
                    System.out.println("      -> (+ EXPI (- EXPI EXPI) (* EXPI EXPI))");
                    System.out.println("      -> (+ EXPI (- EXPI EXPI) (* EXPI Id))");
                    System.out.println("      -> (+ EXPI (- EXPI EXPI) (* Id Id))");
                    System.out.println("      -> (+ EXPI (- EXPI Id) (* Id Id))");
                    System.out.println("      -> (+ EXPI (- EXPI Id) (* Id Id))");
                    System.out.println("      -> (+ EXPI (- Id Id) (* Id Id))");
                    System.out.println("      -> (+ Id (- Id Id) (* Id Id))");

                }
            }
        }

        //ilk islem - olup 2. islemin diger operatorlerden biri oldugu durumlar
        if (temp3 !=0 || temp4 !=0) {
            if (temp1 != 0 || temp2 != 0  && (temp5==0 && temp6==0 && temp7==0 && temp8==0)) {
                if(strList[1].contains( "Operator_-")) {
                    System.out.println("      -> (- EXPI (+ EXPI EXPI))");
                    System.out.println("      -> (- EXPI (+ EXPI Id))");
                    System.out.println("      -> (- EXPI (+ Id Id))");
                    System.out.println("      -> (- Id (+ Id Id))");
                }
            } else if (temp5 != 0 || temp6 != 0  && (temp1==0 && temp2==0 && temp7==0 && temp8==0)) {
                if((strList[1].contains( "Operator_-")) ){
                    System.out.println("      -> (- EXPI (/ EXPI EXPI))");
                    System.out.println("      -> (- EXPI (/ EXPI Id))");
                    System.out.println("      -> (- EXPI (/ Id Id))");
                    System.out.println("      -> (- Id (/ Id Id))");
                }
            } else if (temp7 != 0 || temp8 != 0  && (temp1==0 && temp2==0 && temp5==0 && temp6==0)) {
                if((strList[1].contains( "Operator_-")) ){
                    System.out.println("      -> (- EXPI (* EXPI EXPI))");
                    System.out.println("      -> (- EXPI (* EXPI Id))");
                    System.out.println("      -> (- EXPI (* Id Id))");
                    System.out.println("      -> (- Id (* Id Id))");
                }
            }

        }

        //ilk islem / olup 2. islemin diger operatorlerden biri oldugu durumlar
        if (temp5 !=0 || temp6 !=0) {
            if (temp3 != 0 || temp4 != 0  && (temp1==0 && temp2==0 && temp7==0 && temp8==0)) {
                if((strList[1].contains( "Operator_/")) ) {
                    System.out.println("      -> (/ EXPI (- EXPI EXPI))");
                    System.out.println("      -> (/ EXPI (- EXPI Id))");
                    System.out.println("      -> (/ EXPI (- Id Id))");
                    System.out.println("      -> (/ Id (- Id Id))");
                }
            } else if (temp1 != 0 || temp2 != 0  && (temp3==0 && temp4==0 && temp7==0 && temp8==0)) {
                if((strList[1].contains( "Operator_/")) ) {
                    System.out.println("      -> (/ EXPI (+ EXPI EXPI))");
                    System.out.println("      -> (/ EXPI (+ EXPI Id))");
                    System.out.println("      -> (/ EXPI (+ Id Id))");
                    System.out.println("      -> (/ Id (+ Id Id))");
                }
            } else if (temp7 != 0 || temp8 != 0  && (temp1==0 && temp2==0 && temp3==0 && temp4==0)) {
                if((strList[1].contains( "Operator_/")) ) {
                    System.out.println("      -> (/ EXPI (* EXPI EXPI))");
                    System.out.println("      -> (/ EXPI (* EXPI Id))");
                    System.out.println("      -> (/ EXPI (* Id Id))");
                    System.out.println("      -> (/ Id (* Id Id))");
                }
            }
        }

        //ilk islem * olup 2. islemin diger operatorlerden biri oldugu durumlar
        if (temp7 !=0 || temp8 !=0) {
            if (temp3 != 0 || temp4 != 0  && (temp5==0 && temp6==0 && temp1==0 && temp2==0)) {
                if((strList[1].contains( "Operator_*")) ) {
                    System.out.println("      -> (* EXPI (- EXPI EXPI))");
                    System.out.println("      -> (* EXPI (- EXPI Id))");
                    System.out.println("      -> (* EXPI (- Id Id))");
                    System.out.println("      -> (* Id (- Id Id))");
                }
            } else if (temp5 != 0 || temp6 != 0  && (temp1==0 && temp2==0 && temp3==0 && temp4==0)) {
                if((strList[1].contains( "Operator_*")) ) {
                    System.out.println("      -> (* EXPI (/ EXPI EXPI))");
                    System.out.println("      -> (* EXPI (/ EXPI Id))");
                    System.out.println("      -> (* EXPI (/ Id Id))");
                    System.out.println("      -> (* Id (/ Id Id))");
                }
            } else if (temp1 != 0 || temp2 != 0  && (temp5==0 && temp6==0 && temp3==0 && temp4==0)) {
                if((strList[1].contains( "Operator_*")) ) {
                    System.out.println("      -> (* EXPI (+ EXPI EXPI))");
                    System.out.println("      -> (* EXPI (+ EXPI Id))");
                    System.out.println("      -> (* EXPI (+ Id Id))");
                    System.out.println("      -> (* Id (+ Id Id))");
                }
            }
        }

        ////////////////////////////////////////////////////////////////////////////
        //EXPB durumlar icin///////////////////////////
        //temp1, temp2 >> and icin
        //temp2, temp3 >> or icin
        //temp4, temp5 >> not icin
        //temp6, temp7 >> equal icin
        temp1=0; temp2=0;  temp3=0; temp4=0;
        temp5=0; temp6=0;  temp7=0; temp8=0;

        if(check==1)
        {

            //EXPB olma kontrolu
            if (strList[1].contains("Keyword_"))
                System.out.println("      -> EXPB");


            for(int h=0; h<size; ++h)
            {
                if (strList[h].contains("Keyword_and")){
                    for (int j = 0; j < size; ++j) {
                        if (strList[j].contains("Operator_") ) {
                            ++temp1;
                        }
                        if (strList[j].contains("VALUE_INT_")) {
                            ++temp2;
                        }
                    }
                }


                if (strList[h].contains("Keyword_or")){
                    for (int j = 0; j < size; ++j) {
                        if (strList[j].contains("Operator_")) {
                            ++temp3;
                        }
                        if (strList[j].contains("VALUE_INT_")) {
                            ++temp4;
                        }
                    }
                }


                if (strList[h].contains("Keyword_not")){
                    for (int j = 0; j < size; ++j) {
                        if (strList[j].contains("Operator_")) {
                            ++temp5;
                        }
                        if (strList[j].contains("VALUE_INT_")) {
                            ++temp6;
                        }
                    }
                }


                if (strList[h].contains("Keyword_equal")){
                    for (int j = 0; j < size; ++j) {
                        if (strList[j].contains("Operator_")) {
                            ++temp7;
                        }
                        if (strList[j].contains("VALUE_INT_")) {
                            ++temp8;
                        }
                    }
                }
            }
        }

        ///////////////////////////////////////////////////////////////////
        ////////////Sadece tek keyword varsa (and,or ,not,equal) //////////
        //sadece + operatoru varsa
        if ((temp1 !=0 || temp2 !=0) && temp3==0 && temp4==0 && temp5==0 && temp6==0 && temp7==0 && temp8==0 ) {
            System.out.println("      -> (and EXPB  EXPB)");
            System.out.println("      -> (and EXPB  Id)");
            System.out.println("      -> (and Id  Id)");
        }
        //sadece - operatoru varsa
        if ((temp3 !=0 || temp4 !=0)  && temp1==0 && temp2==0 && temp5==0 && temp6==0 && temp7==0 && temp8==0) {
            System.out.println("      -> (or EXPB  EXPB)");
            System.out.println("      -> (or EXPB  Id)");
            System.out.println("      -> (or Id  Id)");
        }
        //sadece / operatoru varsa
        if ((temp5 !=0 || temp6 !=0) && temp1==0 && temp2==0 && temp3==0 && temp4==0 && temp7==0 && temp8==0) {
            System.out.println("      -> (not EXPB  EXPB)");
            System.out.println("      -> (not EXPB  Id)");
            System.out.println("      -> (not Id  Id)");
        }
        //sadece * operatoru varsa
        if ((temp7 !=0 || temp8 !=0)  && temp1==0 && temp2==0 && temp3==0 && temp4==0 && temp5==0 && temp6==0) {
            System.out.println("      -> (equal EXPB  EXPB)");
            System.out.println("      -> (equal EXPB  Id)");
            System.out.println("      -> (equal Id  Id)");
        }



        /////////////////////////////////////////////////////////////////////////////////////
        //ilk islem "and" olup 2. islemin diger operatorlerden biri oldugu durumlar
        if (temp1 !=0 || temp2 !=0) {
            if(temp3!=0 || temp4!=0 && (temp5==0 && temp6==0 && temp7==0 && temp8==0)){
                if (strList[1].contains("Keyword_and")) {
                    System.out.println("      -> (and EXPB (or EXPB EXPB))");
                    System.out.println("      -> (and EXPB (or EXPB Id))");
                    System.out.println("      -> (and EXPB (or Id Id))");
                    System.out.println("      -> (and Id (or Id Id))");
                }
            }
            else if(temp5!=0 || temp6!=0 && (temp3==0 && temp4==0 && temp7==0 && temp8==0)){
                if (strList[1].contains("Keyword_and")) {
                    System.out.println("      -> (and EXPB (not EXPB EXPB))");
                    System.out.println("      -> (and EXPB (not EXPB Id))");
                    System.out.println("      -> (and EXPB (not Id Id))");
                    System.out.println("      -> (and Id (not Id Id))");
                }
            }
            else if(temp7!=0 || temp8!=0  && (temp5==0 && temp6==0 && temp3==0 && temp4==0)){
                if (strList[1].contains("Keyword_and")) {
                    System.out.println("      -> (and EXPB (equal EXPB EXPB))");
                    System.out.println("      -> (and EXPB (equal EXPB Id))");
                    System.out.println("      -> (and EXPB (equal Id Id))");
                    System.out.println("      -> (and Id (equal Id Id))");
                }
            }

        }
        //ilk islem "or" olup 2. islemin diger operatorlerden biri oldugu durumlar
        if (temp3 !=0 || temp4 !=0) {
            if (temp1 != 0 || temp2 != 0  && (temp5==0 && temp6==0 && temp7==0 && temp8==0)) {
                if (strList[1].contains("Keyword_or")) {
                    System.out.println("      -> (or EXPB (and EXPB EXPB))");
                    System.out.println("      -> (or EXPB (and EXPB Id))");
                    System.out.println("      -> (or EXPB (and Id Id))");
                    System.out.println("      -> (or Id (and Id Id))");
                }
            } else if (temp5 != 0 || temp6 != 0  && (temp1==0 && temp2==0 && temp7==0 && temp8==0)) {
                if (strList[1].contains("Keyword_or")) {
                    System.out.println("      -> (or EXPB (not EXPB EXPB))");
                    System.out.println("      -> (or EXPB (not EXPB Id))");
                    System.out.println("      -> (or EXPB (not Id Id))");
                    System.out.println("      -> (or Id (not Id Id))");
                }
            } else if (temp7 != 0 || temp8 != 0  && (temp1==0 && temp2==0 && temp5==0 && temp6==0)) {
                if (strList[1].contains("Keyword_or")) {
                    System.out.println("      -> (or EXPB (equal EXPB EXPB))");
                    System.out.println("      -> (or EXPB (equal EXPB Id))");
                    System.out.println("      -> (or EXPB (equal Id Id))");
                    System.out.println("      -> (or Id (equal Id Id))");
                }
            }
        }
        //ilk islem "not" olup 2. islemin diger operatorlerden biri oldugu durumlar
        if (temp5 !=0 || temp6 !=0) {
            if (temp3 != 0 || temp4 != 0  && (temp1==0 && temp2==0 && temp7==0 && temp8==0)) {
                if (strList[1].contains("Keyword_not")) {
                    System.out.println("      -> (not EXPB (or EXPB EXPB))");
                    System.out.println("      -> (not EXPB (or EXPI Id))");
                    System.out.println("      -> (not EXPB (or Id Id))");
                    System.out.println("      -> (not Id (or Id Id))");
                }
            } else if (temp1 != 0 || temp2 != 0  && (temp3==0 && temp4==0 && temp7==0 && temp8==0)) {
                if (strList[1].contains("Keyword_not")) {
                    System.out.println("      -> (not EXPB (and EXPB EXPB))");
                    System.out.println("      -> (not EXPB (and EXPB Id))");
                    System.out.println("      -> (not EXPB (and Id Id))");
                    System.out.println("      -> (not Id (and Id Id))");
                }
            } else if (temp7 != 0 || temp8 != 0  && (temp1==0 && temp2==0 && temp3==0 && temp4==0)) {
                if (strList[1].contains("Keyword_not")) {
                    System.out.println("      -> (not EXPB (equal EXPB EXPB))");
                    System.out.println("      -> (not EXPB (equal EXPB Id))");
                    System.out.println("      -> (not EXPB (equal Id Id))");
                    System.out.println("      -> (not Id (equal Id Id))");
                }
            }
        }
        //ilk islem "equal" olup 2. islemin diger operatorlerden biri oldugu durumlar
        if (temp7 !=0 || temp8 !=0) {
            if (temp3 != 0 || temp4 != 0  && (temp5==0 && temp6==0 && temp1==0 && temp2==0)) {
                if (strList[1].contains("Keyword_equal")) {
                    System.out.println("      -> (equal EXPB (or EXPB EXPB))");
                    System.out.println("      -> (equal EXPB (or EXPB Id))");
                    System.out.println("      -> (equal EXPB (or Id Id))");
                    System.out.println("      -> (equal Id (or Id Id))");
                }
            } else if (temp5 != 0 || temp6 != 0  && (temp1==0 && temp2==0 && temp3==0 && temp4==0)) {
                if (strList[1].contains("Keyword_equal")) {
                    System.out.println("      -> (equal EXPB (not EXPB EXPB))");
                    System.out.println("      -> (equal EXPB (not EXPB Id))");
                    System.out.println("      -> (equal EXPB (not Id Id))");
                    System.out.println("      -> (equal Id (not Id Id))");
                }
            } else if (temp1 != 0 || temp2 != 0  && (temp5==0 && temp6==0 && temp3==0 && temp4==0)) {
                if (strList[1].contains("Keyword_equal")) {
                    System.out.println("      -> (equal EXPB (and EXPB EXPB))");
                    System.out.println("      -> (equal EXPB (and EXPB Id))");
                    System.out.println("      -> (equal EXPB (and Id Id))");
                    System.out.println("      -> (equal Id (and Id Id))");
                }
            }
        }

        return null;
    }


}
