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
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author irfannurhakim
 */
public class FileWalker extends SimpleFileVisitor<Path> {

    ExecutorService es = Executors.newFixedThreadPool(10);
    ArrayList<String> collection = new ArrayList<String>();
    private Map<String, String> myMap = new HashMap<String, String>();
    int i = 0;

    @Override
    public FileVisitResult visitFile(
            Path aFile, BasicFileAttributes aAttrs) throws IOException {

        if (!aFile.getFileName().toString().equalsIgnoreCase(".DS_Store")) {
            i++;
            //if (i % 1000 == 0) {
            //  System.out.println(i);
            //}

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

    public void callback(String result, int count) {
        if (count % 1000 == 0) {
            System.out.println(count);
        }

        String[] words = result.split("\\s");
        
        collection.addAll(Arrays.asList(words));

        //System.out.println(result);
        if (count >= i) {
            System.out.println(collection.size());
            es.shutdown();
            System.exit(0);
        }
    }

    public Map<String, String> getMyMap() {
        return myMap;
    }

    public void setMyMap(Map<String, String> myMap) {
        this.myMap = myMap;
    }
}