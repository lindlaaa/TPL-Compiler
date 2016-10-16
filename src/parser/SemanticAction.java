package src.parser;

public class SemanticAction
{
  public static final SemanticAction Program = new SemanticAction(1);
  public static final SemanticAction Identifier = new SemanticAction(2);
  public static final SemanticAction Formals = new SemanticAction(3);
  public static final SemanticAction Definitions = new SemanticAction(4);
  public static final SemanticAction Body = new SemanticAction(5);
  public static final SemanticAction Def = new SemanticAction(6);
  /*public static final SemanticAction Program = new SemanticAction(7);
  public static final SemanticAction Program = new SemanticAction(8);
  public static final SemanticAction Program = new SemanticAction(9);
  public static final SemanticAction Program = new SemanticAction(10);
  public static final SemanticAction Program = new SemanticAction(11);
  public static final SemanticAction Program = new SemanticAction(12);
  public static final SemanticAction Program = new SemanticAction(13);
  public static final SemanticAction Program = new SemanticAction(14);
  public static final SemanticAction Program = new SemanticAction(15);
  public static final SemanticAction Program = new SemanticAction(16);
  public static final SemanticAction Program = new SemanticAction(17);
  public static final SemanticAction Program = new SemanticAction(18);
  public static final SemanticAction Program = new SemanticAction(19);
  public static final SemanticAction Program = new SemanticAction(20);
  public static final SemanticAction Program = new SemanticAction(21);
  public static final SemanticAction Program = new SemanticAction(22);
  public static final SemanticAction Program = new SemanticAction(23);
  public static final SemanticAction Program = new SemanticAction(24);
  public static final SemanticAction Program = new SemanticAction(25);
  public static final SemanticAction Program = new SemanticAction(26);
  public static final SemanticAction Program = new SemanticAction(27);
  public static final SemanticAction Program = new SemanticAction(28);
  public static final SemanticAction Program = new SemanticAction(29);
  public static final SemanticAction Program = new SemanticAction(30);
  public static final SemanticAction Program = new SemanticAction(31);
  public static final SemanticAction Program = new SemanticAction(32);
  public static final SemanticAction Program = new SemanticAction(33);
  public static final SemanticAction Program = new SemanticAction(34);
  public static final SemanticAction Program = new SemanticAction(35);
  public static final SemanticAction Program = new SemanticAction(36);
  public static final SemanticAction Program = new SemanticAction(37);
  public static final SemanticAction Program = new SemanticAction(38);
  public static final SemanticAction Program = new SemanticAction(39);
  public static final SemanticAction Program = new SemanticAction(40);*/

  private int typeNumber;

  public SemanticAction( int semanticActionNumber )
  {
    typeNumber = semanticActionNumber;
  }

	public int getSemanticActionName(){
		return typeNumber;
	}

  @Override
  public String toString()
  {
    return Integer.toString(typeNumber);
  }
}

/*package src.parser;

public class SemAction
{
    public static final SemAction Action01 =
                        new SemAction(5);
    public static final SemAction Action02 =
                        new SemAction(2);
    public static final SemAction Action03 =
                        new SemAction(5);
    public static final SemAction Action04 =
                        new SemAction(1);
    public static final SemAction Action05 =
                        new SemAction(2);
    public static final SemAction Action06 =
                        new SemAction(1);
    public static final SemAction Action07 =
                        new SemAction(3);
    public static final SemAction Action08 =
                        new SemAction(1);
    public static final SemAction Action09 =
                        new SemAction(2);
    public static final SemAction Action10 =
                        new SemAction(1);
    public static final SemAction Action11 =
                        new SemAction(0);
    public static final SemAction Action12 =
                        new SemAction(0);
    public static final SemAction Action13 =
                        new SemAction(2);
    public static final SemAction Action14 =
                        new SemAction(1);
    public static final SemAction Action15 =
                        new SemAction(1);
    public static final SemAction Action16 =
                        new SemAction(2);
    public static final SemAction Action17 =
                        new SemAction(1);
    public static final SemAction Action18 =
                        new SemAction(1);
    public static final SemAction Action19 =
                        new SemAction(1);
    public static final SemAction Action20 =
                        new SemAction(2);
    public static final SemAction Action21 =
                        new SemAction();
    public static final SemAction Action22 =
                        new SemAction(1);
    public static final SemAction Action23 =
                        new SemAction(1);
    public static final SemAction Action24 =
                        new SemAction(3);
    public static final SemAction Action25 =
                        new SemAction(1);
    public static final SemAction Action26 =
                        new SemAction(3);
    public static final SemAction Action27 =
                        new SemAction(1);
    public static final SemAction Action28 =
                        new SemAction(1);
    public static final SemAction Action29 =
                        new SemAction(1);
    public static final SemAction Action30 =
                        new SemAction(1);
    public static final SemAction Action31 =
                        new SemAction(1);
    public static final SemAction Action32 =
                        new SemAction(2);
    public static final SemAction Action33 =
                        new SemAction(2);
    public static final SemAction Action34 =
                        new SemAction(2);
    public static final SemAction Action35 =
                        new SemAction(2);
    public static final SemAction Action36 =
                        new SemAction(1);
    public static final SemAction ActionIdentifier =
                        new SemAction(1);
    public static final SemAction ActionNumber =
                        new SemAction(1);
    public static final SemAction ActionBoolean =
                        new SemAction(1);						
					
    private int itemsToPop;

    private SemAction( int itemsToPop )
    {
        this.itemsToPop = itemsToPop;
    }
	
	public int getSemanticAction(){
		return itemsToPop;
	}

    @Override
    public String toString()
    {
        return itemsToPop.toString();
    }
}

*/
