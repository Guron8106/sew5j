package UE00_WHP;

/**
 * FlipFlop
 *
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
    private State state;

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
        this.state = State.OFF;
    }

    private enum State {
        /**
         * SET
         */
        ON {
            @Override
            State handle(FlipFlop c) {
                if (!c.interfaces[IS] && c.interfaces[IR])
                    return OFF;
                return this;
            }
        },
        /**
         * RESET
         */
        OFF {
            @Override
            State handle(FlipFlop c) {
                if (c.interfaces[IS] && !c.interfaces[IR])
                    return ON;
                return this;
            }
        };

        /**
         * abstract method handling the state switches
         *
         * @param c Context
         * @return State
         */
        abstract State handle(FlipFlop c);
    }


    /**
     * Alle Inputs pullen
     */
    @Override
    public void pull() {
        interfaces[IS] = values.get("S").isOn();
        interfaces[IR] = values.get("R").isOn();
    }

    /**
     * Inputs verarbeiten und Ausgänge setzen
     */
    @Override
    public void calc() {
        state = state.handle(this);
        if (state == State.ON) {
            values.get("Q").setOn(true);
            values.get("!Q").setOn(false);
        } else {
            values.get("Q").setOn(false);
            values.get("!Q").setOn(true);
        }
    }


}
