package util.opencl;

class OpenCLTypeUtil {
    static String getString(byte[] bytes) {
        return new String(bytes, 0, bytes.length - 1);
    }

    static long getULong(byte[] bytes, boolean endianLittle) {
        if (endianLittle) {
            return (long)bytes[0] | (long)bytes[1] << 8 | (long)bytes[2] << 16 | (long)bytes[3] << 24 |
                    (long)bytes[4] << 32 | (long)bytes[5] << 40 | (long)bytes[6] << 48 | (long)bytes[7] << 56;
        } else {
            return (long)bytes[7] | (long)bytes[6] << 8 | (long)bytes[5] << 16 | (long)bytes[4] << 24 |
                    (long)bytes[3] << 32 | (long)bytes[2] << 40 | (long)bytes[1] << 48 | (long)bytes[0] << 56;
        }
    }

    static long getUInt(byte[] bytes, boolean endianLittle) {
        if (endianLittle) {
            return (long)bytes[0] | (long)bytes[1] << 8 | (long)bytes[2] << 16 | (long)bytes[3] << 24;
        } else {
            return (long)bytes[3] | (long)bytes[2] << 8 | (long)bytes[1] << 16 | (long)bytes[0] << 24;
        }
    }

    static long getSizeT(byte[] bytes, boolean endianLittle) {
        return 0;
    }

    static boolean getBoolean(byte[] bytes) {
        return bytes[0] != 0 || bytes[3] != 0;
    }

    protected OpenCLTypeUtil() {}
}
