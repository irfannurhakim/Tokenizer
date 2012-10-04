/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tokenizer.util;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 *
 * @author irfannurhakim
 */
public class FileRecursiveReader {

    public File file;
    public List<File> list = new ArrayList<File>();
    private ExecutorService es;
    int iter = 0;

    public FileRecursiveReader(String rootFolder) {
        this.file = new File(rootFolder);
    }

    public FileRecursiveReader() {
    }

    public FileRecursiveReader(ExecutorService es) {
        this.es = es;
    }

    public void getListDirectory(File file) {
        
        
        
        if (this.isDirectory(file) && !file.getName().equalsIgnoreCase("all_documents")) {
            for (int i = 0; i < file.listFiles().length; i++) {
                this.getListDirectory(file.listFiles()[i]);
            }
        } else {
            /*
             * throw this scan job to other process
             */

            //System.out.println(file.getAbsoluteFile());
            if (!file.getName().equalsIgnoreCase("all_documents")) {
                //FileReader task = new FileReader(file);
                //task.setCaller(this);
                //es.submit(task);
            }
            //System.out.println(i++);
            /*
             * StringBuilder text = new StringBuilder();
             *
             * Scanner scanner; text.append(file.getAbsoluteFile()); try {
             * scanner = new Scanner(file); while (scanner.hasNextLine()) {
             * text.append(scanner.nextLine()); } } catch (FileNotFoundException
             * ex) {
             * Logger.getLogger(FileRecursiveReader.class.getName()).log(Level.SEVERE,
             * null, ex); }
             */
            //System.out.println("hasil :" + text);
            //System.out.println(new java.util.Date().getTime());

        }
    }

    public void callback(StringBuilder result) {
        System.out.println("hasil :" + result);
        //System.out.println(new java.util.Date().getTime());
    }

    public List<File> getList() {
        return list;
    }

    private Boolean isDirectory(File file) {
        return (file.isDirectory());
    }
}
