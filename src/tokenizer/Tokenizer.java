/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tokenizer;

import com.tokenizer.util.FileWalker;
import java.io.IOException;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author irfannurhakim
 */
public class Tokenizer {

    public static long N_messagge = 0; // date, to, from, subject, body, all
    public static int top_k_token = 40;
    public static String codeName = "irfan_elisafina_pandapotan";

    /**
     * @param args the command line arguments
     */
    @SuppressWarnings("empty-statement")
    public static void main(String[] args) throws IOException {
        // TODO code application logic here

        String root = "/Users/hadipratama/Documents/Kuliah/Search_Engine_Tech/enron_mail_20110402/maildir/allen-p";
        //String root = "/Users/hadipratama/Documents/Kuliah/Search_Engine_Tech/enron_mail_20110402/maildir/allen-p/_sent_mail/10.";
        //String root = "/Users/hadipratama/Documents/Kuliah/Search_Engine_Tech/enron_mail_20110402/maildir/allen-p/straw/7.";

        FileVisitor<Path> fileVisitor = new FileWalker();
        Files.walkFileTree(Paths.get(root), fileVisitor);
    }
}
