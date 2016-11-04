package src.parser;

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
<<<<<<< HEAD
        this.type = inputInt;
=======
        this.type = name;
>>>>>>> origin/master
    }
}

