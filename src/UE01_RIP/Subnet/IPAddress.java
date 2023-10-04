package UE01_RIP.Subnet;

public class IPAddress implements Comparable<Object> {
    /**
     * ip as integer
     */
    private int ip;

    public IPAddress(int a3, int a2, int a1, int a0) {
        createIP(a3, a2, a1, a0);
    }

    /**
     * Creates an IP address by combining four integer values.
     * The method combines these four bytes by shifting and bitwise operations
     * to create a single 32-bit integer representation of the IP address
     *
     * @param a3 The most significant byte of the IP address (0-255).
     * @param a2 The second most significant byte of the IP address (0-255).
     * @param a1 The third most significant byte of the IP address (0-255).
     * @param a0 The least significant byte of the IP address (0-255).
     */
    private void createIP(int a3, int a2, int a1, int a0) {
        this.ip = (a3 << 24) + (a2 << 16) + (a1 << 8) + a0;
        // System.out.format("%d.%d.%d.%d: %08x\n", a3,a2,a1,a0,ip);
    }

    /**
     * create IP from given integer (internal use)
     */
    IPAddress(int ip) {
        this.ip = ip;
    }

    /**
     * Creates IP with given String
     * "192.168.0.1" -> String
     * @param ip return integer IP
     */
    public IPAddress(String ip) {
        String[] nums = ip.split("\\.");
        if (nums.length != 4) {
            throw new IllegalArgumentException("illegal formed ip");
        }
        createIP(Integer.parseInt(nums[0]),
                Integer.parseInt(nums[1]),
                Integer.parseInt(nums[2]),
                Integer.parseInt(nums[3]));
    }

}
