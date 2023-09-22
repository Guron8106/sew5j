package UE00_WHP;

/**
 * LED
 * @author Karanbir Guron 5AX
 */
public class LED extends Components {

    /**
     * Gepullter Zustand I
     */
    private static final int I1 = 0;

    /**
     * Constructor
     * @param connection connection to the other device
     */
    public LED(Cable connection) {
        super(1);
        values.put("I", connection);
    }


}
