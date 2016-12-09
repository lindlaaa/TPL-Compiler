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


    public void Do() throws Exception{

//        //this would have been added to the table
//        testTable.setVal("t1", 7);
//        testTable.setVal("t2", 7);
//        testTable.setVal("t3", 7);
//        testTable.setVal("t4", Integer.MIN_VALUE);
//        testTable.setVal("t5", 4);
//        testTable.setVal("t6", Integer.MIN_VALUE);
//        testTable.setVal("t7", Integer.MIN_VALUE);
//        testTable.setVal("t8", 5);
//        testTable.setVal("t9", 5);
//        testTable.setVal("t10", 5);
//        testTable.setVal("t11", Integer.MIN_VALUE);
//        testTable.setVal("t12", Integer.MIN_VALUE);
//        testTable.setVal("t13", 4);
//        testTable.setVal("t14", Integer.MIN_VALUE);
//        testTable.setVal("t15", Integer.MIN_VALUE);
//        testTable.setVal("test", 99);
//        testTable.setVal("t16", Integer.MIN_VALUE);
//
//        q.emit("add","t2","t3","t4");
//        q.emit("add","t4","t5","t6");
//        q.emit("mult", "t1", "t6", "t7");
//        q.emit("add", "t9", "t10", "t11");
//        q.emit("add" ,"t8", "t11", "t12" );
//        q.emit("add", "t12", "t13", "t14" );
//        q.emit("div", "t7", "t14", "t15");
//        q.emit("Return", "t15");
//        q.emit("print","t15");
//        q.emit("assign", "test", "t16");



//        q.emit("sub","addResult","sub2","subResult");
//        q.emit("mult","mult1","mult2","multResult");
//        q.emit("negate","arg1","negateResult");
//        q.emit("print","printArg");
//
//        System.out.println(q);


//        RunTimeStack dMemory = new RunTimeStack();
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
        System.out.println( imem.tmString );
    }
}
