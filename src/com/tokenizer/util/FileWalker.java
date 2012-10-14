
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tokenizer.util;

import com.tokenizer.model.BigConcurentHashMap;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import tokenizer.Tokenizer;

/**
 *
 * @author irfannurhakim
 */
public class FileWalker extends SimpleFileVisitor<Path> {

    private static long startTime;
    private int nrOfProcessors = Runtime.getRuntime().availableProcessors();
    private ExecutorService es = Executors.newFixedThreadPool(nrOfProcessors);
    private Runtime rt = Runtime.getRuntime();
    private int i = 0;

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
            System.out.println("<==> job done " + jobDone + " from: " + i + " in : " + ((System.nanoTime() - startTime) / 1000000000.0) + " secs");
            rt.gc();
            rt.gc();
        }

        if (jobDone >= i) {
            es.shutdown();
            es.awaitTermination((long) 100, TimeUnit.MILLISECONDS);

            Tokenizer.N_messagge = i;
            try {
                LinkedHashMap dateList = BigConcurentHashMap.calculateTermWight(BigConcurentHashMap.dateConcurentMap, Tokenizer.N_messagge);
                BigConcurentHashMap.printStatistic(dateList, "date", Tokenizer.N_messagge);
                LinkedHashMap fromList = BigConcurentHashMap.calculateTermWight(BigConcurentHashMap.fromConcurentMap, Tokenizer.N_messagge);
                BigConcurentHashMap.printStatistic(fromList, "from", Tokenizer.N_messagge);
                LinkedHashMap toList = BigConcurentHashMap.calculateTermWight(BigConcurentHashMap.toConcurentMap, Tokenizer.N_messagge);
                BigConcurentHashMap.printStatistic(toList, "to", Tokenizer.N_messagge);
                LinkedHashMap subjectList = BigConcurentHashMap.calculateTermWight(BigConcurentHashMap.subjectConcurentMap, Tokenizer.N_messagge);
                BigConcurentHashMap.printStatistic(subjectList, "subject", Tokenizer.N_messagge);
                LinkedHashMap bodyList = BigConcurentHashMap.calculateTermWight(BigConcurentHashMap.bodyConcurentMap, Tokenizer.N_messagge);
                BigConcurentHashMap.printStatistic(bodyList, "body", Tokenizer.N_messagge);
                LinkedHashMap allList = BigConcurentHashMap.calculateTermWight(BigConcurentHashMap.allConcurentMap, Tokenizer.N_messagge);
                BigConcurentHashMap.printStatistic(allList, "all", Tokenizer.N_messagge);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.exit(0);
        }

    }

    public void writeToFile(String fileName, String text) {
        try {
            // Create file 
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
