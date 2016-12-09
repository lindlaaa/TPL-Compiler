package src.codegen;

import src.parser.nodes.ProgramNode;
import src.parser.nodes.WriteString;
import src.parser.symboltable.SymbolTable;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;

public class Generator{

  private int line_num = 0;
  private ProgramNode root;
  private SymbolTable table;
  private String program = "";

  private static int curRow = -1;
  private static List<List<String>> quadruple = new ArrayList<>();


  public static int tempAmount= 1;
  public static int tempLabel = 1;

  private static Project5 p5;
  private static TempTable tt;


  public Generator(ProgramNode ast, SymbolTable t){
    this.root = ast;
    this.table = t;
    this.root.evaluate();
  }


  /**
   *  This method kickastarts the TM generation process.
   *  This includes the interpretation of the AST to 3AC and
   *  also the interpretation to TM code.
   *
   *  @param fileName String representing the desired name of a file.
   */
  public void generate(String fileName) throws Exception{

    generatePrelude();

    this.p5 = new Project5(this);
    this.tt = new TempTable();

    System.out.println(this); //TODO FIXME
    this.p5.Do();

    WriteString writer = new WriteString();
    writer.write(program, fileName);
  }



  public boolean checkReuse(String tempVar){
      //a relatively expensive operation for checking if the arg is reused
      for(int i = this.curRow; i < this.quadruple.size();i++){
          if(this.quadruple.get(i).get(1).equals(tempVar)){
              return true;
          }
          if(this.quadruple.get(i).get(2).equals(tempVar)){
              return true;
          }
      }
      return false;
  }

  public int Length(){
      return this.quadruple.size();
  }

  public static void addTemp(String s, long i){
    Generator.p5.setTemp(s, i);
  }

  public static void emit(String op, String arg1, String arg2, String res){
      List<String> tempRow = new ArrayList<>(Arrays.asList(op,arg1,arg2,res));
      Generator.quadruple.add(tempRow);
  }
  public static void emit(String op, String arg1, String res){
      List<String> tempRow = new ArrayList<>(Arrays.asList(op,arg1,"",res));
      Generator.quadruple.add(tempRow);
  }
  public static void emit(String op,String res){
      List<String> tempRow = new ArrayList<>(Arrays.asList(op,"","",res));
      Generator.quadruple.add(tempRow);
  }
  public static void emit(String op){
      List<String> tempRow = new ArrayList<>(Arrays.asList(op,"","",""));
      Generator.quadruple.add(tempRow);
  }
  public static List<String> get3ACLine(){
      curRow++;
      return Generator.quadruple.get(curRow);
  }


  public static String newTemp(){
    return "t"+tempAmount++;
  }

  public static String newLabel(){
    return "L"+tempLabel++;
  }


  public static void emitFunctionCall(String a, ArrayList params, String t){/*
    List<String> tempRow = new ArrayList<>(Arrays.asList("BEGIN_CALL"));
    Generator.quadruple.add(tempRow);
    for(String param : params){
      tempRow = new ArrayList<>(Arrays.asList(param));
      Generator.quadruple.add(tempRow);
    }
    tempRow = new ArrayList<>(Arrays.asList("Call "+a));
    Generator.quadruple.add(tempRow);
    tempRow = new ArrayList<>(Arrays.asList("Recieve "+t));
    Generator.quadruple.add(tempRow);*/
  }



  public void generatePrelude(){

    emitComment("Prelude");
    //emitRM(line_num++,   'LDC', 5, -1,       0, "initialize status ptr"); //TODO
    //emitRM(line_num++, 'LDC', 6, stack_base, 0, "initialize top ptr"); //TODO
    emitComment("Call Main");
    emitRM(line_num++, "LD",  7, 999, 7, "Jump to main"); //TODO FIXME
    emitRO(line_num++, "OUT", 1, 0, 0,   "print result from main");
    emitRO(line_num++, "HALT", 1, 0, 0,  "stop");

  }


  /**
   *  emitRO takes parameters and outputs a string in the format
   *  of a TM file. Following TM syntax semantics.
   *  @param   line_num int representing the line of the instructions
   *  @param   opcode   String representing the operation to output
   *  @param   r1       int representing the register 1 output
   *  @param   r2       int representing the register 2 output
   *  @param   r3       int representing the register 3 output
   *  @param   comment  String representing the comment to attach
   */
  public void emitRO(int line, String opcode, int r1, int r2, int r3, String comment){
    this.program += String.format("%-4s%-6s%s,%s,%-7s;%-10s\n", line+":",opcode,r1,r2,r3,comment);
  }


  /**
   *  emitRM takes parameters and outputs a string in the format
   *  of a TM file. Following TM syntax semantics.
   *  @param   line_num int representing the line of the instructions
   *  @param   opcode   String representing the operation to output
   *  @param   r1       int representing the register 1 output
   *  @param   offset   int representing the offset portion of a RM op
   *  @param   r3       int representing the register 3 output
   *  @param   comment  String representing the comment to attach
   */
  public void emitRM(int line, String opcode, int r1, int offset, int r3, String comment){
    this.program += String.format("%-4s%-6s%s,%s%-8s;%-10s\n", line+":",opcode,r1,offset,"("+r3+")",comment);
  }



  /**
   *  emitComment takes a string and creates a TM comment
   *  @param  String comment String representing the comment
   */
  public void emitComment(String comment){
    this.program += String.format(";;      %s\n",comment);
  }


  @Override
  public String toString(){
      String outString = "";
      for(int i = 0; i < Generator.quadruple.size();i++){
          for(int k = 0; k < Generator.quadruple.get(i).size(); k++){
              outString += Generator.quadruple.get(i).get(k).toString() + " ";
          }
          outString += "\n";
      }
      return outString;
  }
}

/*
  S->id := E
  S.code := [[E.code]
             [emitCode (id.place, ":=" , E.place)]]

  E->E1+E2
  E.place := makeNewTemp()
  E.code := [E1.code]
            [E2.code]
            emitCode(E.place, ":=",
                     E1.place, "+",
                     E2.place)
  t^k = t^i + t^j

  E->E1-E2
  E.place := makeNewTemp()
  E.code := [E1.code]
            [E2.code]
            emitCode(E.place, ":=",
                     E1.place, "-",
                     E2.place)
  t^k = t^i + t^j

  E-> -E1
  E.place := makeNewTemp()
  E.code := [E1.code]
            emitCode(E.place, ":=",
                     "-", E.place)

  E->(E1)
  E.place := makeNewTemp()
  E.code  := E1.code

  E-> id
  E.place := id.place
  E.code  := ""

  S->while E do S1
  L1 = makeNewLabel()
  L2 = makeNewLabel()
  S.code := emitCode(L1, ": ")
            [E.code]
            emitCode("if ",E.place, " = 0 goto ",L2)
            [S1.code]
            emitCode("goto ", L1)
            emitCode(L2, ": ")

Maybe instead of outputting to a String you may emit to a record, an array of instructions?

a = b * -c + b * -c

*/
