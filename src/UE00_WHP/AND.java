package UE00_WHP;

/**
 * AND
 *
 * @author Karanbir Guron
 */
public class AND extends Components {

    /**
     * Gepullter Zustand A
     */
    private static final int IA = 0;

    /**
     * Gepullter Zustand B
     */
    private static final int IB = 1;


    /**
     * Konstruktor
     *
     * @param a Connection Eingang A
     * @param b Connection Eingang B
     * @param x Connection Ausgang X
     */
    public AND(Cable a, Cable b, Cable x) {
        super(2);
        values.put("A", a);
        values.put("B", b);
        values.put("X", x);
    }

    /**
     * Alle Inputs pullen
     */
    @Override
    public void pull() {
        interfaces[IA] = values.get("A").isOn();
        interfaces[IB] = values.get("B").isOn();
    }

    /**
     * Inputs verarbeiten und Ausgänge setzen
     */
    @Override
    public void calc() {
        values.get("X").setOn(interfaces[IA] && interfaces[IB]);
    }


}
