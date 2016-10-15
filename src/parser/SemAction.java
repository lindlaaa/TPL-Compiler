package src.parser;

public class SemAction
{
    public static final SemAction Action01 =
                        new SemAction(1);
    public static final SemAction Action02 =
                        new SemAction(2);
    public static final SemAction Action03 =
                        new SemAction(3);
    public static final SemAction Action04 =
                        new SemAction(4);
    public static final SemAction Action05 =
                        new SemAction(5);
    public static final SemAction Action06 =
                        new SemAction(6);
    public static final SemAction Action07 =
                        new SemAction(7);
    public static final SemAction Action08 =
                        new SemAction(8);
    public static final SemAction Action09 =
                        new SemAction(9);
    public static final SemAction Action10 =
                        new SemAction(10);
    public static final SemAction Action11 =
                        new SemAction(11);
    public static final SemAction Action12 =
                        new SemAction(12);
    public static final SemAction Action13 =
                        new SemAction(13);
    public static final SemAction Action14 =
                        new SemAction(14);
    public static final SemAction Action15 =
                        new SemAction(15);
    public static final SemAction Action16 =
                        new SemAction(16);
    public static final SemAction Action17 =
                        new SemAction(17);
    public static final SemAction Action18 =
                        new SemAction(18);
    public static final SemAction Action19 =
                        new SemAction(19);
    public static final SemAction Action20 =
                        new SemAction(20);
    public static final SemAction Action21 =
                        new SemAction(21);
    public static final SemAction Action22 =
                        new SemAction(22);
    public static final SemAction Action23 =
                        new SemAction(23);
    public static final SemAction Action24 =
                        new SemAction(24);
    public static final SemAction Action25 =
                        new SemAction(25);
    public static final SemAction Action26 =
                        new SemAction(26);
    public static final SemAction Action27 =
                        new SemAction(27);
    public static final SemAction Action28 =
                        new SemAction(28);
    public static final SemAction Action29 =
                        new SemAction(29);
    public static final SemAction Action30 =
                        new SemAction(30);
    public static final SemAction Action31 =
                        new SemAction(31);
    public static final SemAction Action32 =
                        new SemAction(32);
    public static final SemAction Action33 =
                        new SemAction(33);
    public static final SemAction Action34 =
                        new SemAction(34);
    public static final SemAction Action35 =
                        new SemAction(35);
    public static final SemAction Action36 =
                        new SemAction(36);
					
    private int typeName;

    private SemAction( int semanticActionNumber )
    {
        typeName = semanticActionNumber;
    }
	
	public int getSemanticAction(){
		return typeName;
	}

    @Override
    public String toString()
    {
        return typeName.toString();
    }
}
