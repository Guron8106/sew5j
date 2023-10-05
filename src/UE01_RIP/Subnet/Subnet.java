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

    public IPAddress getNet() {
        return net;
    }

    public IPAddress getMask() {
        return mask;
    }

    public IPAddress getBroadcast() {
        return new IPAddress( net.getIP() + ~mask.getIP() );
    }

    /**
     * is IP in this network
     * @param ip
     * @return
     */
    public boolean contains(IPAddress ip) {
        return (ip.getIP() & mask.getIP()) == net.getIP();
    }

    @Override
    public String toString() {
        return "Subnet [net=" + net + ", mask=" + mask + "]";
    }

    @Override
    public int compareTo(Object o){
        if(!(o instanceof Subnet)) throw new IllegalArgumentException("Element ist kein Subnet");
        Subnet other = (Subnet) o;
        int maskCompare = other.getMask().compareTo(getMask());

        if(maskCompare == 0){
            return other.getNet().compareTo(getNet());
        }
        return maskCompare;
    }

    @Override
    public boolean equals(Object o){
        if(o.getClass() != Subnet.class) throw new ClassCastException("Wrong type");
        Subnet other = (Subnet) o;
        return getMask().equals(other.getMask()) && getNet().equals(other.getNet());
    }




}
