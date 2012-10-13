/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tokenizer.model;

import java.util.Comparator;

/**
 *
 * @author user
 */
public class TermCounter   {
    
    long totalTerm;
    long totalDocument;
    double tokenWeight;

    public TermCounter() {
    }

    public TermCounter(long totalTerm, long totalDocument, double tokenWeight) {
        this.totalTerm = totalTerm;
        this.totalDocument = totalDocument;
        this.tokenWeight = tokenWeight;
    }

    public double getTokenWeight() {
        return tokenWeight;
    }

    public void setTokenWeight(double tokenWeight) {
        this.tokenWeight = tokenWeight;
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

    @Override
    public String toString() {
        return "TermCounter{" + "totalTerm=" + totalTerm + ", totalDocument=" + totalDocument + '}';
    }

 
    static class WeightComparator implements Comparator {
    public int compare(Object o1, Object o2) {
      if (!(o1 instanceof TermCounter) || !(o2 instanceof TermCounter))
        throw new ClassCastException();

      TermCounter e1 = (TermCounter) o1;
      TermCounter e2 = (TermCounter) o2;

      return (int) (e1.getTokenWeight() - e2.getTokenWeight());
    }
  }
    
    
}
