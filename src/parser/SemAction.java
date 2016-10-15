package src.parser;

public class SemAction
{
    public static final SemAction Action01 =
                        new SemAction(7);
    public static final SemAction Action02 =
                        new SemAction(2);
    public static final SemAction Action03 =
                        new SemAction(9);
    public static final SemAction Action04 =
                        new SemAction(1);
    public static final SemAction Action05 =
                        new SemAction(2);
    public static final SemAction Action06 =
                        new SemAction(1);
    public static final SemAction Action07 =
                        new SemAction(3);
    public static final SemAction Action08 =
                        new SemAction(3);
    public static final SemAction Action09 =
                        new SemAction(2);
    public static final SemAction Action10 =
                        new SemAction(2);
    public static final SemAction Action11 =
                        new SemAction(1);
    public static final SemAction Action12 =
                        new SemAction(1);
    public static final SemAction Action13 =
                        new SemAction(2);
    public static final SemAction Action14 =
                        new SemAction(2);
    public static final SemAction Action15 =
                        new SemAction(1);
    public static final SemAction Action16 =
                        new SemAction(2);
    public static final SemAction Action17 =
                        new SemAction(2);
    public static final SemAction Action18 =
                        new SemAction(2);
    public static final SemAction Action19 =
                        new SemAction(2);
    public static final SemAction Action20 =
                        new SemAction(2);
    public static final SemAction Action21 =
                        new SemAction(2);
    public static final SemAction Action22 =
                        new SemAction(2);
    public static final SemAction Action23 =
                        new SemAction(2);
    public static final SemAction Action24 =
                        new SemAction(6);
    public static final SemAction Action25 =
                        new SemAction(2);
    public static final SemAction Action26 =
                        new SemAction(2);
    public static final SemAction Action27 =
                        new SemAction(1);
    public static final SemAction Action28 =
                        new SemAction(2);
    public static final SemAction Action29 =
                        new SemAction(3);
    public static final SemAction Action30 =
                        new SemAction(3);
    public static final SemAction Action31 =
                        new SemAction(1);
    public static final SemAction Action32 =
                        new SemAction(2);
    public static final SemAction Action33 =
                        new SemAction(3);
    public static final SemAction Action34 =
                        new SemAction(1);
    public static final SemAction Action35 =
                        new SemAction(1);
    public static final SemAction Action36 =
                        new SemAction(5);
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
