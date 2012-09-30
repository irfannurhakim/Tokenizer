/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tokenizer.util;

import java.io.File;
import java.util.concurrent.Callable;

/**
 *
 * @author irfannurhakim
 */
public class ListFileWorker implements Callable<Integer> {
    
    private File file;
    
    public ListFileWorker(File file){
        this.file = file;
    }

    @Override
    public Integer call() {
        
        
        
        return 1;
    }
    
}
