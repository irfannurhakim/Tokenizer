/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tokenizer;

import com.tokenizer.util.FileRecursiveReader;
import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author irfannurhakim
 */
public class Tokenizer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //int nrOfProcessors = Runtime.getRuntime().availableProcessors();
        ExecutorService es = Executors.newFixedThreadPool(50);


        File file = new File("/Users/hadipratama/Documents/Kuliah/Search_Engine_Tech/enron_mail_20110402/maildir/allen-p");
        FileRecursiveReader frs = new FileRecursiveReader(es);
        frs.getListDirectory(file);
        System.exit(0);
    }
}
