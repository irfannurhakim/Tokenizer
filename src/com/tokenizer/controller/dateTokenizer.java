/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tokenizer.controller;

import java.util.HashMap;

/**
 *
 * @author user
 */
public class dateTokenizer {
    
    public static HashMap<String, Integer> getListDate(String date)
    {
        HashMap termList = new HashMap();
        String[] terms  = date.split(", | ");
        termList.put(terms[0], 1);
        termList.put(terms[1]+terms[2]+terms[3], 1);
        termList.put(terms[4], 1);
        termList.put(terms[5], 1);
        termList.put(terms[6], 1);
        System.out.println(termList);
        return termList;
    }

}
