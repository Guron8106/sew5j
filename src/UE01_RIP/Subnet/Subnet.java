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
        this.mask = IPAddress.createNetmask(cidr);

        if ((this.net.getIP() & mask.getIP()) != this.net.getIP() || cidr>32 || cidr<0) {
            throw new IllegalArgumentException("bad network");
        }

    }

    /**
     * create netmask from ip (four number) and number of bits
     * @param a3
     * @param a2
     * @param a1
     * @param a0
     * @param cidr
     */
    public Subnet(int a3, int a2, int a1, int a0, int cidr) {
        this(new IPAddress(a3, a2, a1, a0), cidr);
    }

    public Subnet(String mask) {
        String[] parts = mask.split("/");
        if (parts.length != 2) {
            throw new IllegalArgumentException("ill formed subnet");
        }
        IPAddress ip = new IPAddress(parts[0]);
        int cidr = Integer.parseInt(parts[1]);
        createMask(ip, cidr);
    }

}
