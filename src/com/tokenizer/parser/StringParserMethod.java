/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tokenizer.parser;

/**
 *
 * @author user
 */
public class StringParserMethod {
    
    public static String removeApostrope(String partString)
    {
        partString=partString.replaceAll("'s", "");
        partString=partString.replaceAll("'", "");
        return partString;
    }
    public static String removeHypenate(String partString)
    {
        /*if(s.matches("(\\d+\\w*-)*\\d+\\w*"))
        {
            return s;
        }*/
        if(partString.matches("([a-zA-Z]*-)*[a-zA-Z]*"))
        {
        partString=partString.replaceAll("-", "");
        }
        return partString;
    }
    public static String removeHTMLTag(String allString){
        // menghapus semua html tag beserta atribut2nya
        return (allString.replaceAll("<(\"[^\"]*\"|'[^']*'|[^'\">])*>", "")); 
    }
    
    
}
