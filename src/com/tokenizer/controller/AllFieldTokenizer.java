/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tokenizer.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author user
 */
public class AllFieldTokenizer {

    public static HashMap<String, Integer> allFieldTermList(HashMap<String, Integer> date, HashMap<String, Integer> to, HashMap<String, Integer> from, HashMap<String, Integer> subject, HashMap<String, Integer> body) {
        HashMap<String, Integer> all = new HashMap<String, Integer>();
        all = mergeHashMap(all, date);
        all = mergeHashMap(all, to);
        all = mergeHashMap(all, from);
        all = mergeHashMap(all, subject);
        all = mergeHashMap(all, body);
        return all;

    }

    public static HashMap<String, Integer> mergeHashMap(HashMap<String, Integer> a, HashMap<String, Integer> b) {
        // Get a set of the entries 

        Set set = b.entrySet();
// Get an iterator 
        Iterator i = set.iterator();
// Display elements 
        while (i.hasNext()) {
            Map.Entry me = (Map.Entry) i.next();
            String newKey = (String) me.getKey();
            Integer newval = (Integer) me.getValue();

            Integer freq = (Integer) a.get(newKey);
            if (freq == null) {
                freq = newval;
            } else {
                int value = freq.intValue();
                freq = new Integer(value + newval.intValue());
            }
            a.put(newKey, freq);
        }
        return a;
    }
}
