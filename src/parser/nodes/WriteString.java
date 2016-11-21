package src.parser.nodes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class WriteString {

    public void write(String content, String name) {
        FileWriter fileWriter = null;
        try {
            File newTextFile = new File("../"+name+".tm");
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

    public void writeTree(String content, String name) {
        FileWriter fileWriter = null;
        try {
            File newTextFile = new File("../"+name+".dot");
            fileWriter = new FileWriter(newTextFile);
            content = "digraph g {\n"
                      +"graph [compound = true,"
                              +"nodesep = 0.3,"
                              +"ranksep = 2.0,"
                              +"layout = dot,"
                              +"rankdir = LR,"
                              +"color=white]"
                      +"\n" + content;

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
