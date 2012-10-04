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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        //int nrOfProcessors = Runtime.getRuntime().availableProcessors();
        /*
         * ExecutorService es = Executors.newFixedThreadPool(50);
         *
         *
         * File file = new
         * File("/Users/hadipratama/Documents/Kuliah/Search_Engine_Tech/enron_mail_20110402/maildir/allen-p");
         * FileRecursiveReader frs = new FileRecursiveReader(es);
         * frs.getListDirectory(file); System.exit(0);
         */

        String root = "/Users/hadipratama/Documents/Kuliah/Search_Engine_Tech/enron_mail_20110402/maildir/allen-p";
        FileVisitor<Path> fileVisitor = new FileWalker();
        Files.walkFileTree(Paths.get(root), fileVisitor);
        //System.exit(0);
    }
}
