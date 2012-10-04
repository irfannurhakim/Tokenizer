/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tokenizer.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;

/**
 *
 * @author irfannurhakim
 */
public class FileWalker extends SimpleFileVisitor<Path> {

    private ExecutorService es = Executors.newFixedThreadPool(50);
    private Map<String, String> myMap = new HashMap<String, String>();
    int i = 0;

    @Override
    public FileVisitResult visitFile(
            Path aFile, BasicFileAttributes aAttrs) throws IOException {

        //System.out.println("Processing file: " + aFile);
        i++;
        if (i % 1000 == 0) {
            System.out.println(i);
        }

        if (!aFile.getFileName().toString().equalsIgnoreCase(".DS_Store")) {

            //FileReader task = new FileReader(aFile);
            //task.setCaller(this);
            //es.submit(task);
            //System.out.println(String.valueOf(Files.readAllLines(aFile, StandardCharsets.UTF_8)).toLowerCase());
            String[] xx = Parser.slice(String.valueOf(Files.readAllLines(aFile, StandardCharsets.ISO_8859_1)).toLowerCase());
            //int toIdx = Parser.findIdx(xx,"to");
            //Boolean isTo = true;
            
            
            for (int j = 0; j < xx.length; j++) {
                if(!xx[j].equalsIgnoreCase("")){
                
                }
                //myMap.put(aFile.toString(), xx[j]);
                //System.out.println(xx[j]);
                //System.out.println(xx[j].indexOf("to"));
            }
        }
        //es.shutdown();
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(
            Path aDir, BasicFileAttributes aAttrs) throws IOException {
        if (aDir.endsWith("all_documents/")) {
            return FileVisitResult.SKIP_SUBTREE;
        }
        //System.out.println("Processing directory:" + aDir);
        return FileVisitResult.CONTINUE;
    }

    public void callback(List<String> result) {
        //System.out.println("hasil :" + String.valueOf(result));
        //System.out.println(new java.util.Date().getTime());
    }

    public Map<String, String> getMyMap() {
        return myMap;
    }

    public void setMyMap(Map<String, String> myMap) {
        this.myMap = myMap;
    }
}