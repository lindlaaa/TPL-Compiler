package src.codegen;

import src.parser.nodes.WriteString;

public class Generator{

  private int line_num = 0;


  public void generate(String fileName){

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



  public void generatePrelude(){}
  public void generatePrint(){}
  public void generateMain(){}
  public void makeNewTemp(){}
  public void emitCode(){}
  public String emitRO(int line_num, String opcode, int r1, int r2, int r3, String comment){
    //String result = new String();
    //result = line_num + ": " + opcode + " " + r1 + " " + r2 + " " + r3;
    String result = String.format("%-4s%-6s%s,%s,%-7s;%-10s", line_num+":",opcode,r1,r2,r3,comment);

    return result;
  }
  public String emitRM(int line_num, String opcode, int r1, int offset, int r3, String comment){
    //String result = new String();
    //result = line_num + ": " + opcode + " " + r1 + " " + r2 + " (" + r3 + ")";
    String result = String.format("%-4s%-6s%s,%s%-8s;%-10s", line_num+":",opcode,r1,offset,"("+r3+")",comment);

    return result;
  }
  private void emitRegister(){} //Optional, not necessary.
  public void makeNewLabel(){}

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
