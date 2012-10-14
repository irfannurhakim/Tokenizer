/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tokenizer.util;

/**
 *
 * @author irfannurhakim
 */
public class Parser {

    public static String[] slice(String collection) {
        return collection.replaceAll("[,!;:?%'=_&#~/\\\\^\\+\\*\\{\\}\\$\\|\\-<>\\[\\]\\(\\)\"]+", "").split("\\s");

        //return collection.split("\\s");
    }

    public static int findIdx(String collection, String keyword) {
        return collection.indexOf(keyword);
    }

    public static String removePunc(String s) {
        //menghapus tanda baca pada awal atau akhir kata, mis: Budi. atau ,tono
        return (s.matches("\\W*\\w*\\W*")) ? s.replaceAll("\\W+", "") : s;
    }

    public static String removeApostrope(String s) {
        return s.replaceAll("'s", "").replaceAll("'", "");
    }

    public static String removeHypenate(String s) {
        if (s.matches(".*([a-zA-Z]+-)*[a-zA-Z]+.*")) {
            s = s.replaceAll("-", "");
        }
        return s;
    }

//    public static String removeSpecialChar(String s) {
//        return s.replaceAll("[,!;:?%=_&#/\\\\^\\+\\*\\{\\}\\$\\|\\-<>\\[\\]\\(\\)\"]+", "");
//    }

    public static String removePuncuation(String s) {
        return s.replaceAll("\\p{Punct}", " ");
    }
    
    public static String removeHTMLTag(String allString){
        // menghapus semua html tag beserta atribut2nya
        return (allString.replaceAll("<(\"[^\"]*\"|'[^']*'|[^'\">])*>", "")); 
    }
    
    public static boolean isNeedSplit(String partString)
    {
        // mengembalikan nilai apakah suatu string perlu dipotong, misalkan tono.budi , tolong!saya dst.
        return (partString.matches("([a-zA-Z]*\\W+)+[a-zA-Z]*"));
    }
    public static String[] splitSpecialChar(String partString)
    {
        return (partString.split("\\W"));
    }
}
