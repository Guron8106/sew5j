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

    /**
     * Alle Inputs pullen
     */
    @Override
    public void pull() {
        interfaces[I1] = values.get("I").isOn();
    }

    /**
     * Inputs verarbeiten und Ausgänge setzen
     */
    @Override
    public void calc() {
        if (interfaces[I1]) {
            System.out.println("LED: ON");
        } else {
            System.out.println("LED: OFF");
        }
    }

}
