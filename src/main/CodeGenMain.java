package src.main;

import java.io.File;
import src.parser.TableDrivenParser;
import src.scanner.Scanner;
import src.scanner.ScanException;
import src.codegen.Generator;


public class CodeGenMain{

  private static boolean treeVisible;

  @SuppressWarnings("checkstyle:javadocmethod")
  /**
   *  <b>Runs the Parser</b>
   *
   *  @param args List of args, first being the
   *              path of file to Scan and Parse.
   */
  public static void main(String[] args) throws ScanException, Exception{

    String fileName;
    try{
      if(args[0].equals("-t")){
        treeVisible = true;
        fileName = args[1];
      }else{
        fileName = args[0];
      }
    }catch(Exception e){
      fileName = args[0];
    }

    File file = new File(fileName);
    String name = file.getName();
    name = name.substring(0, name.length()-4);
    //System.out.println("\n------ File name ---\n" + name);

    /*May be used to put the output file in the original files dirextory
    String path;
    path = file.getPath();
    path = path.substring(0, path.length()-4);
    System.out.println(path);
    */

    TableDrivenParser parser = new TableDrivenParser( new Scanner( fileName ));
    parser.parseProgram(treeVisible, name);


    Generator generator = new Generator(parser.getAST(), parser.getTable());
    generator.generate(name);
  }
}
