/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tokenizer;

import com.tokenizer.util.FileRecursiveReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

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

        FileRecursiveReader frs = new FileRecursiveReader("/Users/hadipratama/Documents/Kuliah/Search_Engine_Tech/enron_mail_20110402/maildir/symes-k/");

        for (int i = 0; i < frs.getList().size(); i++) {
            
            System.out.println(frs.getList().get(i).getName());
           /* StringBuilder text = new StringBuilder();

            try {
                Scanner scanner = new Scanner(frs.getList().get(i));
                while (scanner.hasNextLine()) {
                    text.append(scanner.nextLine());
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Tokenizer.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(text);*/
        }
    }
}
