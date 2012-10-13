/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tokenizer.model;

/**
 *
 * @author user
 */
public class Email {
    String from;
    String to;
    String subject;
    String date;
    String body;
    String path;

    public Email() {
    }

    public Email(String from, String to, String subject, String date, String body, String path) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.date = date;
        this.body = body;
        this.path = path;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    
    
    @Override 
    public String toString() {
        return path + "\n{\n" + " from=" + from + "\n to=" + to + "\n subject=" + subject + "\n date=" + date + "\n body=" + body + "\n}";
    }
    
    
}
