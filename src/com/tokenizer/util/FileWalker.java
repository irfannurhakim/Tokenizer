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

    int nrOfProcessors = Runtime.getRuntime().availableProcessors();
    private ExecutorService es = Executors.newFixedThreadPool(50);
    private Map<String, Integer> fromList = new HashMap<String, Integer>();
    private Runtime rt = Runtime.getRuntime();
    private int i = 0, j = 1;
    long startTime;

    public FileWalker() {
        startTime = System.nanoTime();
    }

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

    public void callback(HashMap<String, Integer> date, HashMap<String, Integer> from, HashMap<String, Integer> to, HashMap<String, Integer> subject, HashMap<String, Integer> body, HashMap<String, Integer> allFieldMap, int jobDone) throws InterruptedException {
                
        if (jobDone % 1000 == 0) {
            System.out.println("<==> job done " + jobDone + " from: " + i + " in : " + ((System.nanoTime() - startTime) / 1000000000.0) + "sec");
            rt.gc();
            rt.gc();
        }

        if (jobDone >= i) {
            //es.shutdown();
            es.awaitTermination((long) 1000, TimeUnit.MILLISECONDS);

            ValueComparator c = new ValueComparator(fromList);
            TreeMap<String, Integer> sorted_map = new TreeMap<String, Integer>(c);
            sorted_map.putAll(fromList);
            System.out.println(sorted_map);
            System.exit(0);
        }

    }

    public Map<String, Integer> getFromList() {
        return fromList;
    }
}
