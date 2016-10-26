package src.parser.nodes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class WriteString {

    public void write(String content) {
        FileWriter fileWriter = null;
        try {
            File newTextFile = new File("../ast.dot");
            fileWriter = new FileWriter(newTextFile);
            content = "digraph g {\n"
                      +"graph [compound = true,"
                      +"nodesep = 0.1,"
                      +"ranksep = 0.1,"
                      +"layout = dot,"
                      +"rankdir = LR,"
                      +"color = white]\n" + content;
            content+="}";
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
