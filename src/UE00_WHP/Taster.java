package UE00_WHP;
/**
 * Taster
 * @author Karanbir Guron 5AX
 */

public class Taster extends Components {

    /**
     * Gepullter Zustand
     */
    private static final int I1 = 0;

    /**
     * Konstruktor
     * @param cable t
     */
    public Taster(Cable cable) {
        super(1);
        values.put("O", cable);
    }

    /**
     * Zustand ändern
     */
    public void press() {
        interfaces[I1] = true;
    }

    /**
     * Alle Inputs pullen
     */
    @Override
    public void pull() {

    }

    /**
     * Inputs verarbeiten und Ausgänge setzen
     */
    @Override
    public void calc() {
        values.get("O").setOn(interfaces[I1]);
        interfaces[I1] = false;
    }


}