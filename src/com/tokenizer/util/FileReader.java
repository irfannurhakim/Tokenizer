/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tokenizer.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
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
        line = line.toLowerCase();
        line = Parser.removeApostrope(line);
        line = Parser.removeHeadAndTail(line);
        line = Parser.removeHypenate(line);
        line = Parser.removeSpecialChar(line);

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


        fileWalker.callback(line, count);
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
}
