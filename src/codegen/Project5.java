package src.codegen;

import java.util.List;
import src.parser.symboltable.*;

public class Project5 {

    public static TempTable testTable;
    private Generator g;


    public Project5(Generator gen) throws Exception{
      this.g = gen;
      testTable = new TempTable();
    }

    public void setTemp(String t, long l){
      this.testTable.setVal(t,l);
    }

    public String Do() throws Exception{
      System.out.println(testTable);
      ImemManager imem = new ImemManager(testTable);
      for(int i = 0; i < g.Length();i++){
          List<String> nextRow = g.get3ACLine();
          imem.createTemplate(nextRow);
          //NEED TO MAKE SURE REGISTERS DON'T OVERFLOW!
          boolean isReused = g.checkReuse(nextRow.get(nextRow.size() - 1));
          if(!isReused){
              imem.openAllRegisters();
          }
      }
      return imem.tmString;
    }
}
