package src.parser.semanticanalyzer;

public class BranchType{
	int type;
    public static final BranchType INTEGER =
                        new BranchType(0);
    public static final BranchType BOOLEAN =
                        new BranchType(1);
    public static final BranchType EITHER =
                        new BranchType(2);
    private String typeName;

    private BranchType(int inputInt)
    {
        this.type = inputInt;
    }

		public String toString(){
			if(this.type == 0){
				return "INTEGER";
			}else if(this.type == 1){
				return "BOOLEAN";
			}else if(this.type == 2){
				return "EITHER";
			}
			return "ERROR";
		}
}
