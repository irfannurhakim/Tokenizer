/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tokenizer.controller;

import com.tokenizer.util.Parser;
import java.util.HashMap;

/**
 *
 * @author user
 */
public class subject_bodyTokenizer {

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static HashMap<String, Integer> getListTerm(String data) {
        HashMap<String, Integer> termList = new HashMap<String, Integer>();
        data = Parser.removeHTMLTag(data);
        String[] ax = data.split("\\s+|, ");

        for (String s : ax) {

            if (s.matches("\\W+")) {
                //System.out.println(s);
            } else {

                s = Parser.removeApostrope(s);
                s = Parser.removeHypenate(s);
                s = Parser.removePunc(s);
                //s = Parser.removePuncuation(s);

                if (!s.matches(EMAIL_PATTERN)) {
                    //System.out.println(s);
                    s = Parser.removePuncuation(s);
                    String[] slices = s.split(" ");
                    for (String slice : slices) {
                        if (!slice.equals("\\W+")) {
                            putToHashMap(slice, termList);
                        }
                    }
                } else {
                    putToHashMap(s, termList);
                }

            }

        }
        //System.out.println(termList);
        return termList;
    }

    public static void putToHashMap(String key, HashMap<String, Integer> map) {


        Integer freq = (Integer) map.get(key);
        if (freq == null) {
            freq = new Integer(1);
        } else {
            int value = freq.intValue();
            freq = new Integer(value + 1);
        }
        map.put(key, freq);
    }
}
