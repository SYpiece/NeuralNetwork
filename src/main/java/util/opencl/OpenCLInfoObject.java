package util.opencl;

import org.jocl.Pointer;

abstract class OpenCLInfoObject<T> {
    final T baseObject;
    final InfoGetter<T> infoGetter;

    OpenCLInfoObject(T baseObject, InfoGetter<T> infoGetter) {
        this.baseObject = baseObject;
        this.infoGetter = infoGetter;
    }

    public String getStringInfo(int paramName) {
        long[] size = new long[1];
        infoGetter.getInfo(baseObject, paramName, 0, null, size);
        byte[] buffer = new byte[(int) size[0]];
        infoGetter.getInfo(baseObject, paramName, size[0], Pointer.to(buffer), null);
        return new String(buffer, 0, buffer.length - 1);
    }

    public boolean getBooleanInfo(int paramName) {
        long[] size = new long[1];
        infoGetter.getInfo(baseObject, paramName, 0, null, size);
        int[] buffer = new int[(int) size[0]];
        infoGetter.getInfo(baseObject, paramName, size[0], Pointer.to(buffer), null);
        return buffer[0] != 0;
    }

    public int getIntInfo(int paramName) {
        long[] size = new long[1];
        infoGetter.getInfo(baseObject, paramName, 0, null, size);
        int[] buffer = new int[(int) size[0]];
        infoGetter.getInfo(baseObject, paramName, size[0], Pointer.to(buffer), null);
        return buffer[0];
    }

    public long getLongInfo(int paramName) {
        long[] size = new long[1];
        infoGetter.getInfo(baseObject, paramName, 0, null, size);
        long[] buffer = new long[(int) size[0]];
        infoGetter.getInfo(baseObject, paramName, size[0], Pointer.to(buffer), null);
        return buffer[0];
    }

    interface InfoGetter<T> {
        int getInfo(T baseObject, int paramName, long size, Pointer buffer, long[] sizeRet);
    }
}
