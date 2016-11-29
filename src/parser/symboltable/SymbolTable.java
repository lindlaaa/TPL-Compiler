package src.parser.symboltable;

import java.util.HashMap;
import src.parser.semanticanalyzer.BranchType;

@SuppressWarnings("unchecked")
public class SymbolTable{


	/** The HashMap to hold all of our function and variable pairs. */
	private HashMap table;

	/* Constructor */
	public SymbolTable(){
		this.table = new HashMap();
	}



	/**
	 *  Used to get a copy of the HashMap
	 *  @return HashMap
	 */
	public HashMap getTable(){
		return this.table;
	}



	/**
	 *  Puts a contents of the hashmap to stdout.
	 */
	public void printTable(){
		System.out.println("\n------ Symbol table contents: ---"); //FIXME
		System.out.println(table.keySet());
	}



	/**
	 *  Prints the LexicalPairs of a key to stdout
	 *  @param key String representing key to print pairs from.
	 */
	public void printPairs(String key){
		get(key).printPair();
	}


	/**
	 *  Returns the BranchType of the given key in the symbolTable.
	 *  @param  key String representing key to get type from.
	 *  @return     BranchType representing the type of the symbol.
	 */
	public BranchType getType(String key){
		return get(key).getType();
	}



	/**
	 *  Checks and returns the current size of the hashmap.
	 *  @return int representing the size of the hashmap
	 */
	public int tableSize(){
		System.out.println("\nSymbol Table size:"); // FIXME
		System.out.println(table.size()+"\n"); // FIXME
		return table.size();
	}



	/**
	 *  Checks the hashtable for a key. Returns TRUE if
	 *  	key is already present.
	 *  @param  tempKey String representing the key to look up.
	 *  @return         boolean represting if the key is present.
	 */
	public boolean isSet(String tempKey){
		if(table.containsKey(tempKey)){
			return true;
		}
		return false;
	}



	/**
	 *  Adds a new key to the hashtable. It first checks if it is already
	 *  	present and in that case returns false.
	 *  	If the addition is successful it returns True.
	 *  @param  tempKey String representing new key to be input.
	 *  @return         boolean representing the result of the insertion.
	 */
	public boolean put(String tempKey){
		if(isSet(tempKey)){
			//System.out.println("Already Exists.\n"); // FIXME
			return false;
		}else{
			Symbol tempSymbol = new Symbol(tempKey, "-Parent Name-"); //FIXME
			table.put(tempKey, tempSymbol);
			//System.out.println("added new pair.\n"); // FIXME
			return true;
		}
	}



	/**
	 *  Changes the name of the function that calls the key.
	 *  @param  key String used to find the key to be changed
	 *  @param  new String representing the callername to be set to.
	 */
	public void setCallerName(String key, String name){
		get(key).setCallerName(name);
	}



	/**
	 *  Sets the isFunction attribute of the symbol with the given key.
	 *  @param  key String used to find the key to be changed
	 *  @param  i boolean the vakue to be set.
	 */
  public void setIsFunction(String key, boolean i){
    get(key).setIsFunction(i);
  }



	/**
	 *  Adds a new LexicalPair to a certain key object.
	 *  @param  name String used to find the key to be changed.
	 *  @param  pair LexicalPair representing the new pair to be inserted.
	 */
	public void addPair(String name, LexicalPair pair){
    get(name).addPair(pair);
  }



	public Symbol get(String key){
		return (Symbol)table.get(key);
	}

}
