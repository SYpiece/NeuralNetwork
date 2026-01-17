package util.opencl;

class BitsUtil {
    public static String getString(byte[] bits) {
        return new String(bits, 0, bits.length - 1);
    }

    public static long getULong(byte[] bits) {
        return 0;
    }

    public static long getUInt(byte[] bits) {
        assert bits.length == 4;
        return 0;
    }

    public static long getSizeT(byte[] bits) {
        return 0;
    }

    public static boolean getBoolean(byte[] bits) {
        if (bits.length != 4) {
            throw new RuntimeException("Invalid boolean size");
        }
        return bits[0] != 0;
    }

    protected BitsUtil() {}
}
