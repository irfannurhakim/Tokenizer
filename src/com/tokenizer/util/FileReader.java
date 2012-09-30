/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tokenizer.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author irfannurhakim
 */
public class FileReader implements Callable {

    private FileRecursiveReader fileRecursiveReader;
    private File file;
    private StringBuilder text = new StringBuilder();

    public FileReader(File file) {
        this.file = file;
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

    public void setCaller(FileRecursiveReader fileRecursiveReader) {
        this.fileRecursiveReader = fileRecursiveReader;
    }

    public FileRecursiveReader getCaller() {
        return fileRecursiveReader;
    }

    @Override
    public Object call() {
        Scanner scanner;
        this.text.append(file.getAbsoluteFile());
        try {
            scanner = new Scanner(this.file);
            while (scanner.hasNextLine()) {
                this.text.append(scanner.nextLine());
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileRecursiveReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        fileRecursiveReader.callback(this.text);

        return null;
    }
}
