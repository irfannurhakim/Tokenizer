/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tokenizer.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
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
    public StringBuilder getText() {
        return text;
    }

    public void setText(StringBuilder text) {
        this.text = text;
    }
*/
    public void setCaller(FileWalker fileWalker) {
        this.fileWalker = fileWalker;
    }

    public FileWalker getCaller() {
        return fileWalker;
    }

    @Override
    public Object call() throws IOException {
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
         * (IOException e) { e.printStackTrace();
        }
         */

        String line = Files.readAllLines(this.path, StandardCharsets.UTF_8).toString();
        line = line.toLowerCase();
        line = Parser.removeApostrope(line);
        line = Parser.removeHeadAndTail(line);
        line = Parser.removeHypenate(line);
        line = Parser.removeSpecialChar(line);
        
        fileWalker.callback(line, count);
        return null;
    }
}
