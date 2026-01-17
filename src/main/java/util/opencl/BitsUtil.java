package util.opencl;

class BitsUtil {
    public static String getString(byte[] bits) {
        return new String(bits, 0, bits.length - 1);
    }

    public static long getULong(byte[] bits, boolean littleEndian) {
        return 0;
    }

    public static long getLong(byte[] bits, boolean littleEndian) {
        return 0;
    }

    public static long getUInt(byte[] bits, boolean littleEndian) {
        return 0;
    }

    public static boolean getBoolean(byte[] bits) {
        assert bits.length == 1;
        return bits[0] != 0;
    }

    protected BitsUtil() {}
}
