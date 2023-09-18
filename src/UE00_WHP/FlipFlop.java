package UE00_WHP;

/**
 * FlipFlop
 * @author Karanbir Guron 5AX
 */
public class FlipFlop extends Components {

    /**
     * Gepullter Zustand S
     */
    private static final int IS = 0;

    /**
     * Gepullter Zustand R
     */
    private static final int IR = 1;

    /**
     * Statemachine
     */
    //private State state;

    /**
     * Konstruktor
     *
     * @param s  Connection Eingang S
     * @param r  Connection Eingang R
     * @param q  Connection Ausgang Q
     * @param nQ Connection Ausgang !Q
     */
    public FlipFlop(Cable s, Cable r, Cable q, Cable nQ) {
        super(2);
        values.put("S", s);
        values.put("R", r);
        values.put("Q", q);
        values.put("!Q", nQ);
        //this.state = State.OFF;
    }

}
