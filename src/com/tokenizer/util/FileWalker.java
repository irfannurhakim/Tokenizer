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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author irfannurhakim
 */
public class FileWalker extends SimpleFileVisitor<Path> {

    ExecutorService es = Executors.newFixedThreadPool(10);
    ArrayList<String> collection = new ArrayList<String>();
    private Map<String, Integer> myMap = new HashMap<String, Integer>();
    int i = 0;

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

    public void callback(String result, int count) throws InterruptedException {
        //if (count % 1000 == 0) {
        System.out.println(i + " " + count);
        //}

        //String[] words = result.split("\\s");

        for (String string : result.split("\\s")) {
            if (myMap.get(string) == 0) {
                myMap.put(string, myMap.get(string) + 1);
            } else {
                myMap.put(string, 1);
            }
        }

        //System.out.println(result);

        if (count >= 100) {
            //System.out.println(collection.size());
            es.awaitTermination((long) 1, TimeUnit.SECONDS);
            System.exit(0);
        }


    }

    public Map<String, Integer> getMyMap() {
        return myMap;
    }
}