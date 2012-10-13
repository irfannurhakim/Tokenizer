/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tokenizer.util;

import com.tokenizer.controller.toTokenizer;
import com.tokenizer.model.Email;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.Callable;

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

    public FileReader() {
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

        String line = Files.readAllLines(this.path, StandardCharsets.UTF_8).toString();
        line = line.toLowerCase();
        //System.out.println(line);
        //line = Parser.removeApostrope(line);
        //line = Parser.removeHeadAndTail(line);
        //line = Parser.removeHypenate(line);
        //line = Parser.removeSpecialChar(line);

        /*
         * raw -> array 0 head, array 1 tail
//         */
//        Email email = new Email();
//        String[] raw = line.split("date: ", 2);
//        
//        String[] date = raw[1].split("from: ", 2);
//        email.setDate(date[0]);
//
//        String[] from = date[1].split("to: ", 2);
//        email.setFrom(from[0].replaceAll(", ", ""));
//
//        String[] to = from[1].split("subject: ", 2);
//        email.setTo(to[0]);
//        //System.out.println(toTokenizer.getListTo(email.getTo()));
//
//        String[] subject = to[1].split("mime-version: ", 2);
//        email.setSubject(subject[0]);
//
//        String[] body = subject[1].split(".*(.pst)", 2);
//        email.setBody(body[1]);
//        
//        email.setPath(path.toString());

        //System.out.println(email.toString());
        /*
         * split head
         */
        //String[] head = raw[0].split("from");
        /*
         * split tail
         */
        //String[] tail = raw[1].split("subject");
        /*
         * split date
         */

        //String date = head[0].split("date")[1];
        /*
         * split head to get 'from' in array 1
         */
        //String[] from = head[1].split("\\s");
        /*
         * split tail, and get array 0 to perform get 'to'
         */
        //String[] to = tail[0].split("\\s");
        /*
         * split to get raw body
         */
        //String[] body = tail[1].split("\\s");


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
        fileWalker.callback("string", "string", new String[]{"test"}, new String[]{"test"}, count);
        //fileWalker.callback(head[0].split("date")[1], head[1].split("\\s"), tail[0].split("\\s"), tail[1].split("\\s"), count);
        return null;
    }
}