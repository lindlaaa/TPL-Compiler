package src.parser;

public enum BranchType{ 
	INTEGER(0),
	BOOLEAN(1),
	EITHER(2);
   
	private final int branchTypeID;      
   
	BranchType(int branchID) {    
		this.branchTypeID = branchID;
	}
   
	int getBranchTypeID() {             
		return this.branchTypeID;
	}
}