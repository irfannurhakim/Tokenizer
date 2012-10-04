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
}
