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

  private static int curRow = -1;
  private static List<List<String>> quadruple = new ArrayList<>();


  public static int tempAmount= 1;
  public static int tempLabel = 1;

  private Project5 p5;
  private static TempTable tt;


  public Generator(ProgramNode ast, SymbolTable t) throws Exception{
    this.root = ast;
    this.table = t;
    this.p5 = new Project5(this);
    this.tt = new TempTable();
    this.root.evaluate();
    System.out.println(this); //TODO FOXME
  }


  /**
   *  This method kickastarts the TM generation process.
   *  This includes the interpretation of the AST to 3AC and
   *  also the interpretation to TM code.
   *
   *  @param fileName String representing the desired name of a file.
   */
  public void generate(String fileName){

    // TODO FIXME REDO this!m
    String program =
       "0:  LDA		3,1(7)	; Store runtime return addr in R3\n"
      +"1:  ST    1,7(6)  ; Store arg\n"
      +"2:  ST    5,9(6)  ; Store return addr\n"
      +"3:  ADD   5,10,5  ; Update bottom\n"
      +"4:  ST    6,10,6  ; Update top\n"
      +"5:  LDA		7,7(0)	; Jump to main\n"
      +"6:  LD    4,1(6)  ; Get return value from main\n"
      +"7:  ST    4,2(6)  ; Store arg\n"
      +"8:  ST    3,1(7)  ; Store Return addr\n"
      +"9:  LDA		7,5(0)	; Jump to print\n"
      +"10: HALT	0,0,0	  ; Done\n"
      +"\n/*\n"
      +" * 	Print\n"
      +" */\n"
      +"11:  LD    4,2(6)  ; load arg\n"
      +"12:  LD    3,-2(6) ; load return addr\n"
      +"13:  OUT	 4,0,0	 ; Print value in R5\n"
      +"14:  LDA	 7,0(3)	 ; Jump back to main\n"
      +"\n/*\n"
      +" *	Main\n"
      +" */\n"
      +"15: LD    4,2(6)  ; load arg\n"
      +"16: LD    3,-2(6) ; load return addr\n"
      +"17: LDA   5,1(0)	; Save number 1 into R5\n"
      +"18: ST    5,0(5)  ; Store return value\n"
      +"19: ADD   5,-10,5 ; Update bottom\n"
      +"20: ST    6,-10,6 ; Update top\n"
      +"21: LDA   7,0(3)	; Jump back to runtime\n";

    generatePrelude();
    generatePrint();
    generateMain();

    WriteString writer = new WriteString();
    writer.write(program, fileName);
  }; //Main

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
  public static void addTemp(String s, int i){
    Generator.tt.setVal(s, i);
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

  public static String newTemp(){
    return "t"+tempAmount++;
  }

  public static String newLabel(){
    return "L"+tempLabel++;
  }
  //public static void emit(String s){}
  //public static void emit(String a, String v){}
  //public static void emit(String a, String s, String f){}
  //public static void emit(String a, String s, String f, String h){}

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



  public void generatePrelude(){}
  public void generatePrint(){}
  public void generateMain(){}
  public void makeNewTemp(){}
  public void emitCode(){}


  /**
   *  emitRO takes parameters and outputs a string in the format
   *  of a TM file. Following TM syntax semantics.
   *  @param   line_num int representing the line of the instructions
   *  @param   opcode   String representing the operation to output
   *  @param   r1       int representing the register 1 output
   *  @param   r2       int representing the register 2 output
   *  @param   r3       int representing the register 3 output
   *  @param   comment  String representing the comment to attach
   *  @return  String of the properly formatted TM RO command.
   */
  public String emitRO(int line_num, String opcode, int r1, int r2, int r3, String comment){
    return String.format("%-4s%-6s%s,%s,%-7s;%-10s", line_num+":",opcode,r1,r2,r3,comment);
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
   *  @return  String of the properly formatted TM RM command.
   */
  public String emitRM(int line_num, String opcode, int r1, int offset, int r3, String comment){
    return String.format("%-4s%-6s%s,%s%-8s;%-10s", line_num+":",opcode,r1,offset,"("+r3+")",comment);
  }



  /**
   *  emitComment takes a string and creates a TM comment
   *  @param  String comment String representing the comment
   *  @return        String of the properly formed TM comment
   */
  public String emitComment(String comment){
    return String.format(";;%6s",comment);
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
