/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tokenizer.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author irfannurhakim
 */
public class FileReader implements Callable {

    //private FileRecursiveReader fileRecursiveReader;
    private FileWalker fileWalker;
    private File file;
    private StringBuilder text = new StringBuilder();
    
    //
    private Path path;

    public FileReader(File file) {
        this.file = file;
    }
    
    public FileReader(Path path){
        this.path = path;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public StringBuilder getText() {
        return text;
    }

    public void setText(StringBuilder text) {
        this.text = text;
    }

    public void setCaller(FileWalker fileWalker) {
        this.fileWalker = fileWalker;
    }

    public FileWalker getCaller() {
        return fileWalker;
    }

    @Override
    public Object call() throws IOException {
        /*Scanner scanner;
        this.text.append(file.getAbsoluteFile());
        try {
            scanner = new Scanner(this.file);
            while (scanner.hasNextLine()) {
                this.text.append(scanner.nextLine());
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileRecursiveReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        
        fileWalker.callback(Files.readAllLines(this.path, StandardCharsets.UTF_8));

        return null;
    }
}
