/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tokenizer.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
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
            Integer newval = (Integer) me.getValue();

            TermCounter freq = (TermCounter) a.get(newKey);
            if (freq == null) {
                freq = new TermCounter(newval.longValue(), (long) 1, 0);

            } else {
                //System.out.println(newKey+" = "+(freq.getTotalDocument()+1));
                freq = new TermCounter(freq.getTotalTerm() + newval.longValue(), (freq.getTotalDocument() + 1), 0);
                //System.out.println(freq);

            }
            a.put(newKey, freq);
        }
    }

    public static LinkedHashMap<String, TermCounter> calculateTermWight(ConcurrentHashMap<String, TermCounter> a, long N_document) {
        Set set = a.entrySet();
        Iterator i = set.iterator();
        while (i.hasNext()) {
            Map.Entry me = (Map.Entry) i.next();
            String newKey = (String) me.getKey();
            TermCounter newval = (TermCounter) me.getValue();
            double tokenWeight = ((double) newval.getTotalTerm() / N_document) * Math.log(N_document / (double) newval.getTotalDocument());
            newval.setTokenWeight(tokenWeight);
            a.put(newKey, newval);
        }

        LinkedHashMap<String, TermCounter> hasil =  (LinkedHashMap<String, TermCounter>) sortByComparator(a);
        // Collections.sort(a, new TermCounter.WeightComparator());
        return (hasil);
    }

    private static Map sortByComparator(Map unsortMap) {

        List list = new LinkedList(unsortMap.entrySet());

        //sort list based on comparator
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {

                Map.Entry e1 =(Map.Entry) o1;
                Map.Entry e2 =(Map.Entry) o2;
                TermCounter t1 = (TermCounter) e1.getValue();
                TermCounter t2 = (TermCounter) e2.getValue();
//                if (t1.getTokenWeight() >= t2.getTokenWeight()) {
//                    return -1;
//                } else {
//                    return 1;
//                }
                return (Double.compare(t2.getTokenWeight(), t1.getTokenWeight()));
                
            }
        });
        

        //put sorted list into map again
        Map sortedMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }
    
    public static void printStatistic (LinkedHashMap termList, String field, long totalMessage)
    {
        System.out.println(field);
            int i=1;
            String temp="";
            Set set = termList.entrySet();
            Iterator it = set.iterator();
            long totAllTerm=0;
            while (it.hasNext() && i<=Tokenizer.top_k_token) {
                Map.Entry me = (Map.Entry) it.next();
                String newKey = (String) me.getKey();
                TermCounter newval = (TermCounter) me.getValue();
                temp+=Tokenizer.codeName+" "+field+" "+i+" "+newKey+" "+newval.getTotalTerm()+" "+newval.getTotalDocument()+" "+newval.getTokenWeight()+"\n";
                i++;
                totAllTerm+=newval.getTotalTerm();
            
            }
            String hasil = Tokenizer.codeName+" "+field+" N "+totalMessage+"\n";
            hasil+=Tokenizer.codeName+" "+field+" TO "+totAllTerm+"\n";
            hasil+=Tokenizer.codeName+" "+field+" UT "+termList.size()+"\n";
            hasil+=temp;
            
            writeToFile(Tokenizer.codeName+" "+field+".txt", hasil);
    }
    
     public static void writeToFile(String fileName, String text) {
        try {
            // Create file 
            System.out.println(fileName);
            FileWriter fstream = new FileWriter(fileName);
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(text);
            //Close the output stream
            out.close();
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }
}
