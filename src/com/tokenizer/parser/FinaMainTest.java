/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tokenizer.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.StringTokenizer;

/**
 *
 * @author user
 */
public class FinaMainTest {

    public static void main(String[] args) {

        String a = "UBS have,organized an important.meeting  will be held tomorrow for time all employees who "
                + ": 1) have accepted offers or 2) intend to accept offers ( to the best of their "
                + "knowledge) but who have issues that are being resolved All employees in these "
                + "not get this message in time Tammie's Schopp mother-in-law  100-20-1233 10am-1pm ";

//        a= a.toLowerCase();
//        String [] ax = a.split(" ");
//        ArrayList<String> as = new ArrayList<String>();
//        
//        for (String s : ax) {
//            
//            if(s.matches("\\W") || s.matches("\\s*") )
//            {
//                System.out.println(s);
//                
//            }
//            else
//            {
//                s= removeHeadAndTail(s);
//                s= removeApostrope(s);
//                s= removeHypenate(s);
//                s= removeSpecialChar(s);
//                as.add(s);
//            }
//        
//        }
//        

        String[] x = a.split("timex", 2);

        for (String string : x) {
            System.out.println(string);
        }


    }

    public static String removeHeadAndTail(String anyString) {
        String result = "";

        result = (anyString.matches("\\W*\\w*\\W*")) ? anyString.replaceAll("\\W+", "") : anyString;
        return result;

    }

    public static String removeApostrope(String s) {
        s = s.replaceAll("'s", "");
        s = s.replaceAll("'", "");
        return s;
    }

    public static String removeHypenate(String s) {
        /*
         * if(s.matches("(\\d+\\w*-)*\\d+\\w*")) { return s;
        }
         */
        if (s.matches("([a-zA-Z]*-)*[a-zA-Z]*")) {
            s = s.replaceAll("-", "");
        }
        return s;
    }

    public static String removeSpecialChar(String s) {
        return s.replaceAll("[,!;:?%=_&#/\\\\^\\+\\*\\{\\}\\$\\|\\<>\\[\\]\\(\\)\"]+", "");
    }
}
