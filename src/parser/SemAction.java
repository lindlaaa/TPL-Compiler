package src.parser;

public class SemAction
{
    public static final SemAction Program =
                        new SemAction(1);
    public static final SemAction Program =
                        new SemAction(2);
    public static final SemAction Program =
                        new SemAction(3);
    public static final SemAction Program =
                        new SemAction(4);
    public static final SemAction Program =
                        new SemAction(5);
    public static final SemAction Program =
                        new SemAction(6);
    public static final SemAction Program =
                        new SemAction(7);
    public static final SemAction Program =
                        new SemAction(8);
    public static final SemAction Program =
                        new SemAction(9);
    public static final SemAction Program =
                        new SemAction(10);
    public static final SemAction Program =
                        new SemAction(11);
    public static final SemAction Program =
                        new SemAction(12);
    public static final SemAction Program =
                        new SemAction(13);
    public static final SemAction Program =
                        new SemAction(14);
    public static final SemAction Program =
                        new SemAction(15);
    public static final SemAction Program =
                        new SemAction(16);
    public static final SemAction Program =
                        new SemAction(17);
    public static final SemAction Program =
                        new SemAction(18);
    public static final SemAction Program =
                        new SemAction(19);
    public static final SemAction Program =
                        new SemAction(20);
    public static final SemAction Program =
                        new SemAction(21);
    public static final SemAction Program =
                        new SemAction(22);
    public static final SemAction Program =
                        new SemAction(23);
    public static final SemAction Program =
                        new SemAction(24);
    public static final SemAction Program =
                        new SemAction(25);
    public static final SemAction Program =
                        new SemAction(26);
    public static final SemAction Program =
                        new SemAction(27);
    public static final SemAction Program =
                        new SemAction(28);
    public static final SemAction Program =
                        new SemAction(29);
    public static final SemAction Program =
                        new SemAction(30);
    public static final SemAction Program =
                        new SemAction(31);
    public static final SemAction Program =
                        new SemAction(32);
    public static final SemAction Program =
                        new SemAction(33);
    public static final SemAction Program =
                        new SemAction(34);
    public static final SemAction Program =
                        new SemAction(35);
    public static final SemAction Program =
                        new SemAction(36);
    public static final SemAction Program =
                        new SemAction(37);
    public static final SemAction Program =
                        new SemAction(38);
    public static final SemAction Program =
                        new SemAction(39);
    public static final SemAction Program =
                        new SemAction(40);						
    private int typeName;

    public SemAction( int semanticActionNumber )
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
