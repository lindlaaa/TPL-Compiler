package src.codegen;

import src.parser.nodes.WriteString;

public class Generator{

  //TODO


  public void generate(String fileName){

    String program =
      "0:	LDA		6,1(7)	; Store runtime return addr in R6\n"
      +"1:	LDA		7,7(0)	; Jump to main\n"
      +"2:	LDA		6,1(7)	; Store runtime return addr in R6\n"
      +"3:	LDA		7,5(0)	; Jump to print\n"
      +"4:	HALT	0,0,0	  ; Done\n"
      +"\n/*\n"
      +" * 	Print\n"
      +" */\n"
      +"5:	OUT		5,0,0	  ; Print value in R5\n"
      +"6:	LDA		7,0(6)	; Jump back to main in R6\n"
      +"\n/*\n"
      +" *	Main\n"
      +" */\n"
      +"7:	LDA		5,1(0)	; Save number 1 into R5\n"
      +"8:	LDA		7,0(6)	; Jump back to runtime in R6\n";

    System.out.println(program);
    createRuntime();
    createPrint();

    WriteString writer = new WriteString();
    writer.write(program, fileName);
  }; //Main

  public void createRuntime(){};
  public void createPrint(){};

  public void makeNewTemp(){};
  public void emitCode(){};
  private void emitRegister(){}; //Optional, not necessary.
  public void makeNewLabel(){};

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
