/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tokenizer.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import tokenizer.Tokenizer;

/**
 *
 * @author user
 */
public class BigConcurentHashMap {
    
    public static ConcurrentHashMap<String, TermCounter> dateConcurentMap = new ConcurrentHashMap<String, TermCounter>();
    public static ConcurrentHashMap<String, TermCounter> toConcurentMap = new ConcurrentHashMap<String, TermCounter>();
    public static ConcurrentHashMap<String, TermCounter> fromConcurentMap = new ConcurrentHashMap<String, TermCounter>();
    public static ConcurrentHashMap<String, TermCounter> subjectConcurentMap = new ConcurrentHashMap<String, TermCounter>();
    public static ConcurrentHashMap<String, TermCounter> bodyConcurentMap = new ConcurrentHashMap<String, TermCounter>();
    public static ConcurrentHashMap<String, TermCounter> allConcurentMap = new ConcurrentHashMap<String, TermCounter>();
    
    public static void mergeBigHashMap(ConcurrentHashMap<String, TermCounter> a, HashMap<String, Integer> b) {
        // Get a set of the entries 
        
        Set set = b.entrySet();
        Iterator i = set.iterator();
        while (i.hasNext()) {
            Map.Entry me = (Map.Entry) i.next();
            String newKey = (String) me.getKey();
            Integer newval = (Integer)me.getValue();
            
             TermCounter freq = (TermCounter)a.get(newKey);
            if(freq==null)
            {
                freq= new TermCounter(newval.longValue(), (long)1,0);
                
            }
            else
            {
                //System.out.println(newKey+" = "+(freq.getTotalDocument()+1));
                freq = new TermCounter(freq.getTotalTerm()+newval.longValue(), (freq.getTotalDocument()+1),0);
                //System.out.println(freq);
                
            }
            a.put(newKey, freq);
        }
     }
        public static void calculateTermWight (ConcurrentHashMap<String, TermCounter> a , int index)
        {
            Set set = a.entrySet();
            Iterator i = set.iterator();
             while (i.hasNext())
             {
                 Map.Entry me = (Map.Entry) i.next();
                 String newKey = (String) me.getKey();
                 TermCounter newval = (TermCounter)me.getValue();
                 double tokenWeight = ((double)newval.getTotalTerm()/Tokenizer.N_messagge[index])*Math.log(Tokenizer.N_messagge[index]/(double)newval.getTotalDocument());
                 newval.setTokenWeight(tokenWeight);
                 a.put(newKey, newval);
             }

            // Collections.sort(a, new TermCounter.WeightComparator());
             
        }
    
    
}
