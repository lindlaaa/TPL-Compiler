package src.codegen;

public class Generator{

  //TODO
  public void generate(){}; //Main
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
