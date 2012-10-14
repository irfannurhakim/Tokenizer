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
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.concurrent.Callable;

/**
 *
 * @author irfannurhakim
 */
public class FileReader implements Callable {

    private FileWalker fileWalker;
    private File file;
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

        String line = Files.readAllLines(this.path, StandardCharsets.UTF_8).toString().toLowerCase();

        /*
         * raw -> array 0 head, array 1 tail
         */
        String[] raw = line.split("date: ", 2);

        String[] date = raw[1].split("from: ", 2);
        HashMap<String, Integer> dateMap = dateTokenizer.getListDate(date[0]);
        //System.out.println(dateMap);
        if (date.length == 1) {
            date[1] = "";
        }

        String[] from;
        if (date[1].contains("to: ")) {
            from = date[1].split("to: ", 2);
        } else {
            from = date;
        }

        HashMap<String, Integer> fromMap = FromTokenizer.getListFrom(from[0]);
        //System.out.println(fromMap);

        if (from.length == 1) {
            from[1] = "";
        }

        String[] to;
        if (from[1].contains("subject: ")) {
            to = from[1].split("subject: ", 2);
        } else {
            to = from;
        }

        HashMap<String, Integer> toMap = toTokenizer.getListTo(to[0]);
        //System.out.println(toMap);
        if (to.length == 1) {
            to[1] = "";
        }

        String[] subject;
        if (to[1].contains("mime-version : ")) {
            subject = to[1].split("mime-version: ", 2);
        } else {
            subject = to;
        }

        HashMap<String, Integer> subjectMap = subject_bodyTokenizer.getListTerm(subject[0]);
        //System.out.println(subjectMap);

        if (subject.length == 1) {
            subject[1] = "";
        }

        String[] body;
        if (subject[1].contains(".pst") || subject[1].contains(".nsf")) {
            body = subject[1].split("(\\.pst)|(\\.nsf)", 2);
        } else {
            body = subject;
        }

        if (body.length == 1) {
            body[1] = "";
        }
        HashMap<String, Integer> bodyMap = subject_bodyTokenizer.getListTerm(body[1]);
        //System.out.println(bodyMap);

        HashMap<String, Integer> allFieldMap = AllFieldTokenizer.allFieldTermList(dateMap, toMap, fromMap, subjectMap, bodyMap);
        //System.out.println(allFieldMap);

        /*
         * split head
         */
        // String[] head = raw[0].split("from");
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
        //System.out.println(path.toString());
        fileWalker.callback(dateMap, fromMap, toMap, subjectMap, bodyMap, allFieldMap, count);
        return null;
    }
}
