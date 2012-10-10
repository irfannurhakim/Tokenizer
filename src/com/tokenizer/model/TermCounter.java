/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tokenizer.model;

/**
 *
 * @author user
 */
public class TermCounter {
    
    long totalTerm;
    long totalDocument;

    public TermCounter() {
    }

    public TermCounter(long totalTerm, long totalDocument) {
        this.totalTerm = totalTerm;
        this.totalDocument = totalDocument;
    }

    public long getTotalTerm() {
        return totalTerm;
    }

    public void setTotalTerm(long totalTerm) {
        this.totalTerm = totalTerm;
    }

    public long getTotalDocument() {
        return totalDocument;
    }

    public void setTotalDocument(long totalDocument) {
        this.totalDocument = totalDocument;
    }
    
    
}
