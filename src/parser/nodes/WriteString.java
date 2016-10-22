package src.parser.nodes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 *
 */
public class WriteString {

    /**
     *
     */
    public void write(String content) {
        FileWriter fileWriter = null;
        try {
            File newTextFile = new File("/Users/ajathome/Desktop/TPL-Compiler/ast.txt");
            fileWriter = new FileWriter(newTextFile);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(WriteString.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fileWriter.close();
            } catch (IOException ex) {
                Logger.getLogger(WriteString.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
