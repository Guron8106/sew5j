package UE01_RIP.Subnet;

public class Subnet implements Comparable {

    /**
     * network address
     */
    private IPAddress net;
    /**
     * network mask
     */
    private IPAddress mask;

    /**
     * create netmask from network ip and number of bits
     * @param net     network address
     * @param cidr    number of bits
     */
    public Subnet(IPAddress net, int cidr) {
        createMask(net, cidr);
    }



    private void createMask(IPAddress net, int cidr) {
        this.net = net;

    }
}
