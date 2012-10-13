/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tokenizer.util;

import com.tokenizer.controller.AllFieldTokenizer;
import com.tokenizer.controller.FromTokenizer;
import com.tokenizer.controller.dateTokenizer;
import com.tokenizer.controller.subject_bodyTokenizer;
import com.tokenizer.controller.toTokenizer;
import com.tokenizer.model.BigConcurentHashMap;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.concurrent.Callable;
import tokenizer.Tokenizer;

/**
 *
 * @author irfannurhakim
 */
public class FileReader implements Callable {

    //private FileRecursiveReader fileRecursiveReader;
    private FileWalker fileWalker;
    private File file;
    //private StringBuilder text = new StringBuilder();
    private Path path;
    private int count;

    public FileReader(){
        
    }
    
    public FileReader(File file) {
        this.file = file;
    }

    public FileReader(Path path, int count) {
        this.path = path;
        this.count = count;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
    /*
     * public StringBuilder getText() { return text; }
     *
     * public void setText(StringBuilder text) { this.text = text; }
     */

    public void setCaller(FileWalker fileWalker) {
        this.fileWalker = fileWalker;
    }

    public FileWalker getCaller() {
        return fileWalker;
    }

    @Override
    public Object call() throws IOException, InterruptedException {
        /*
         * FileInputStream fis = null; try { fis = new
         * FileInputStream(this.path.toFile()); FileChannel fileChannel =
         * fis.getChannel(); ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
         *
         * int bytes = fileChannel.read(byteBuffer); while(bytes!=-1){
         * byteBuffer.flip(); while (byteBuffer.hasRemaining()){
         * System.out.print((char)byteBuffer.get()); } byteBuffer.clear(); bytes
         * = fileChannel.read(byteBuffer); } if(fis!=null){ fis.close(); } }
         * catch (FileNotFoundException e) { e.printStackTrace(); } catch
         * (IOException e) { e.printStackTrace(); }
         */

        String line = Files.readAllLines(this.path, StandardCharsets.UTF_8).toString();
        line = line.toLowerCase().replaceAll("x-to:|x-form:", "");
        
        //System.out.println(line);
        //line = Parser.removeApostrope(line);
        //line = Parser.removeHeadAndTail(line);
        //line = Parser.removeHypenate(line);
        //line = Parser.removeSpecialChar(line);
        
        /*
         * raw -> array 0 head, array 1 tail
         */

        String[] raw = line.split("date: ", 2);
        
        String [] date = raw[1].split("from: ",2);
        HashMap <String,Integer> dateMap = dateTokenizer.getListDate(date[0]);
        if(dateMap.size()!=0)
        {
            synchronized(Tokenizer.N_messagge)
            {
                Tokenizer.N_messagge[0]++;
            }
        }
        synchronized(BigConcurentHashMap.dateConcurentMap )
        {
        BigConcurentHashMap.mergeBigHashMap(BigConcurentHashMap.dateConcurentMap, dateMap);
        }
        
        //System.out.println(dateMap);
        
        String [] from = date[1].split("to: ", 2);
        HashMap <String,Integer> fromMap = FromTokenizer.getListFrom(from[0].replaceAll(", ", ""));
        if(fromMap.size()!=0)
        {
            synchronized(Tokenizer.N_messagge)
            {
                Tokenizer.N_messagge[1]++;
            }
        }
        synchronized(BigConcurentHashMap.fromConcurentMap )
        {
        BigConcurentHashMap.mergeBigHashMap(BigConcurentHashMap.fromConcurentMap, fromMap);
        }
        //System.out.println(fromMap);
        
        String [] to = from[1].split("subject: ",2);
         HashMap <String,Integer> toMap = toTokenizer.getListTo(to[0]);
         if(toMap.size()!=0)
        {
            synchronized(Tokenizer.N_messagge)
            {
                Tokenizer.N_messagge[2]++;
                System.out.println(Tokenizer.N_messagge[2]);
            }
        }
        synchronized(BigConcurentHashMap.toConcurentMap )
        {
        BigConcurentHashMap.mergeBigHashMap(BigConcurentHashMap.toConcurentMap, toMap);
        }
        
        String [] subject = to[1].split("mime-version: ", 2);
        HashMap <String,Integer> subjectMap = subject_bodyTokenizer.getListTerm(subject[0]);
        if(subjectMap.size()!=0)
        {
            synchronized(Tokenizer.N_messagge)
            {
                Tokenizer.N_messagge[3]++;
            }
        }
        synchronized(BigConcurentHashMap.subjectConcurentMap )
        {
        BigConcurentHashMap.mergeBigHashMap(BigConcurentHashMap.subjectConcurentMap, subjectMap);
        }
        //System.out.println(subjectMap);
        
        
       // String [] body = subject[1].split(",\\s+,\\s+,\\s+,\\s+", 2);
        HashMap <String,Integer> bodyMap = new HashMap<String, Integer>();
        String [] body = subject[1].split("(\\.pst)|(\\.nsf)", 2);
        bodyMap= subject_bodyTokenizer.getListTerm(body[1]);
       if(bodyMap.size()!=0)
        {
            synchronized(Tokenizer.N_messagge)
            {
                Tokenizer.N_messagge[4]++;
            }
        }
        synchronized(BigConcurentHashMap.bodyConcurentMap )
        {
        BigConcurentHashMap.mergeBigHashMap(BigConcurentHashMap.bodyConcurentMap, bodyMap);
        }
       
//        if(body.length!=2)
//        {
//        System.out.println(body.length);
//        }
        //System.out.println(bodyMap);

        HashMap <String,Integer> allFieldMap = AllFieldTokenizer.allFieldTermList(dateMap, toMap, fromMap, subjectMap, bodyMap);
        if(allFieldMap.size()!=0)
        {
            synchronized(Tokenizer.N_messagge)
            {
                Tokenizer.N_messagge[5]++;
            }
        }
        synchronized(BigConcurentHashMap.allConcurentMap )
        {
        BigConcurentHashMap.mergeBigHashMap(BigConcurentHashMap.allConcurentMap, allFieldMap);
        }
        //System.out.println(allFieldMap);
        
        
     


//Using FileChannel

//        String line = null;
//        FileInputStream stream = new FileInputStream(this.path.toFile());
//        try {
//            FileChannel fc = stream.getChannel();
//            MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
//            /*
//             * Instead of using default, pass in a decoder.
//             */
//            line = Charset.defaultCharset().decode(bb).toString();
//            line = line.toLowerCase();
//            line = Parser.removeApostrope(line);
//            line = Parser.removeHeadAndTail(line);
//            line = Parser.removeHypenate(line);
//            line = Parser.removeSpecialChar(line);
//
//        } finally {
//            stream.close();
//        }

//        String line = readFile(this.path.toString());
//        line = line.toLowerCase();
//        line = Parser.removeApostrope(line);
//        line = Parser.removeHeadAndTail(line);
//        line = Parser.removeHypenate(line);
//        line = Parser.removeSpecialChar(line);
        //line = Parser.removePunc(line);
        //fileWalker.callback(email.getDate(), email.getFrom(),new String[]{"test"} , new String[]{"test"},count);
        fileWalker.callback("", "",new String[]{"test"} , new String[]{"test"},count);
        //fileWalker.callback(head[0].split("date")[1], head[1].split("\\s"), tail[0].split("\\s"), tail[1].split("\\s"), count);
        return null;
    }

    private String readFile(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new java.io.FileReader(file));
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        //String ls = System.getProperty("line.separator");

        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            //stringBuilder.append(ls);
        }
        //reader.close();
        return stringBuilder.toString();
    }

    public void setProperty(Path aFile, int i) {
        this.path = aFile;
        this.count = i;
    }
}
