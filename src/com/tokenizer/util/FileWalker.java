/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tokenizer.util;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author irfannurhakim
 */
public class FileWalker extends SimpleFileVisitor<Path> {
    
    private ExecutorService es = Executors.newFixedThreadPool(100);
    private Map<String, Integer> fromList = new HashMap<String, Integer>();
    private Runtime rt = Runtime.getRuntime();
    private int i = 0;
    
    @Override
    public FileVisitResult visitFile(
            Path aFile, BasicFileAttributes aAttrs) throws IOException {
        
        if (!aFile.getFileName().toString().equalsIgnoreCase(".DS_Store")) {
            
            if (i % 1000 == 0) {
                System.out.println("job send " + i);
            }
            i++;
            FileReader task = new FileReader(aFile, i);
            task.setCaller(this);
            es.submit(task);
            
        }
        return FileVisitResult.CONTINUE;
    }
    
    @Override
    public FileVisitResult preVisitDirectory(
            Path aDir, BasicFileAttributes aAttrs) throws IOException {
        if (aDir.endsWith("all_documents/")) {
            return FileVisitResult.SKIP_SUBTREE;
        }
        return FileVisitResult.CONTINUE;
    }
    
    public void callback(String date, String from, String[] to, String[] body, int jobDone) throws InterruptedException {

        /*
         * if (!from.matches("")) { Integer freq = (Integer) fromList.get(from);
         * if (freq == null) { freq = new Integer(1); } else { int value =
         * freq.intValue(); freq = new Integer(value + 1); } fromList.put(from,
         * freq); }
         */
        
        if (jobDone % 1000 == 0) {
            System.out.println("job done " + jobDone + " from: " + i);
            rt.gc();
            rt.gc();
        }
        
        //if (jobDone >= i) {
          //  es.awaitTermination((long) 100, TimeUnit.MILLISECONDS);
            //ValueComparator c = new ValueComparator(fromList);
            //TreeMap<String, Integer> sorted_map = new TreeMap<String, Integer>(c);
            //sorted_map.putAll(fromList);
            //System.out.println(sorted_map);
           // System.exit(0);
        //}
        
    }
    
    public Map<String, Integer> getFromList() {
        return fromList;
    }
}
