/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tokenizer.util;

import java.io.File;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author irfannurhakim
 */
public class FileRecursiveReader {
    
    public File file;
    
    public FileRecursiveReader(String rootFolder){
        this.file = new File(rootFolder);
        
        this.getListDirectory(file);
    }
    
    
    private List<File> getListDirectory(File file){
        List<File> list = new ArrayList<File>();                
        
        if(this.isDirectory(file)){
            for(int i=0;i<file.listFiles().length;i++){
                this.getListDirectory(file.listFiles()[i]);
            }
        } else {
            System.out.println(file.getAbsolutePath());
        }
        
        return list;
    }
    
    private Boolean isDirectory(File file){
        return (file.isDirectory());
    }
    
}
