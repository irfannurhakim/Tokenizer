/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tokenizer.controller;

import com.tokenizer.util.Parser;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author user
 */
public class subject_bodyTokenizer {
    
    public static HashMap<String, Integer> getListTerm(String data)
    {
         HashMap<String, Integer> termList = new HashMap<String, Integer>();
         data = Parser.removeHTMLTag(data);
        String [] ax = data.split("\\s+");
        
        for (String s : ax) {
            
            if(s.matches("\\W") || s.matches("\\s*") )
            {
                //System.out.println(s);
                
            }
            else
            {
                s= Parser.removeApostrope(s);
                s= Parser.removeHypenate(s);
                if (Parser.isNeedSplit(s))
                {
                    String [] slices =Parser.splitSpecialChar(s);
                    for (String slice : slices) {
                        if(!slice.equals(""))
                        {
                            putToHashMap(slice,termList);
                        }
                    }
                }
                else
                {
                    putToHashMap(s,termList);
                }
                
            }
        
        }
        //System.out.println(termList);
        return termList;
    }
    
    public static void putToHashMap(String key,HashMap<String, Integer> map)
    {
      
            
            Integer freq = (Integer)map.get(key);
            if(freq==null)
            {
                freq = new Integer(1);
            }
            else
            {
                int value = freq.intValue();
                freq = new Integer(value+1);
            }
            map.put(key, freq);
    }
    
}
