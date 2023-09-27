package UE00_WHP;

public class Main {
    public static void main(String[] args) {
        Cable t1_ff = new Cable();
        Cable t2_ff = new Cable();
        Cable ff_led = new Cable();
        Cable ff_led2 = new Cable();

        Taster t1 = new Taster(t1_ff);
        Taster t2 = new Taster(t2_ff);
        FlipFlop ff = new FlipFlop(t1_ff, t2_ff, ff_led, ff_led2);
        LED led = new LED(ff_led);
        LED led2 = new LED(ff_led);

        runCircuit tact = new runCircuit(t1, t2, ff, led, led2);

        tact.run();
    }
}
