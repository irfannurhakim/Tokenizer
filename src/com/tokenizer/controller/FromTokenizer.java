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
public class FromTokenizer {

    public static HashMap<String, Integer> getListFrom(String from) {
        HashMap<String, Integer> termList = new HashMap<String, Integer>();

        termList.put(from, 1);
        //System.out.println(termList);
        return termList;
    }
}
