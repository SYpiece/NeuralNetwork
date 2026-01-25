package util.opencl;

import org.jocl.Pointer;
import org.jocl.Sizeof;
import org.jocl.cl_platform_id;

abstract class OpenCLInfoObject<T> {
    final T baseObject;
    final InfoGetter<T> infoGetter;

    OpenCLInfoObject(T baseObject, InfoGetter<T> infoGetter) {
        this.baseObject = baseObject;
        this.infoGetter = infoGetter;
    }

    protected String getStringInfo(int paramName) {
        long[] size = new long[1];
        infoGetter.getInfo(baseObject, paramName, 0, null, size);
        byte[] buffer = new byte[(int) size[0] / Sizeof.cl_char];
        infoGetter.getInfo(baseObject, paramName, size[0], Pointer.to(buffer), null);
        return new String(buffer, 0, buffer.length - 1);
    }

    protected boolean getBooleanInfo(int paramName) {
        long[] size = new long[1];
        infoGetter.getInfo(baseObject, paramName, 0, null, size);
        int[] buffer = new int[(int) size[0] / Sizeof.cl_int];
        infoGetter.getInfo(baseObject, paramName, size[0], Pointer.to(buffer), null);
        return buffer[0] != 0;
    }

    protected int getIntInfo(int paramName) {
        long[] size = new long[1];
        infoGetter.getInfo(baseObject, paramName, 0, null, size);
        int[] buffer = new int[(int) size[0] / Sizeof.cl_int];
        infoGetter.getInfo(baseObject, paramName, size[0], Pointer.to(buffer), null);
        return buffer[0];
    }

    protected long getLongInfo(int paramName) {
        long[] size = new long[1];
        infoGetter.getInfo(baseObject, paramName, 0, null, size);
        long[] buffer = new long[(int) size[0] / Sizeof.cl_long];
        infoGetter.getInfo(baseObject, paramName, size[0], Pointer.to(buffer), null);
        return buffer[0];
    }

    protected long getSizeTInfo(int paramName) {
        long[] size = new long[1];
        infoGetter.getInfo(baseObject, paramName, 0, null, size);
        long[] buffer = new long[(int) size[0] / Sizeof.size_t];
        infoGetter.getInfo(baseObject, paramName, size[0], Pointer.to(buffer), null);
        return buffer[0];
    }

    protected long[] getSizeTArrayInfo(int paramName) {
        long[] size = new long[1];
        infoGetter.getInfo(baseObject, paramName, 0, null, size);
        long[] buffer = new long[(int) size[0] / Sizeof.size_t];
        infoGetter.getInfo(baseObject, paramName, size[0], Pointer.to(buffer), null);
        return buffer;
    }

    protected OpenCLPlatform getPlatformInfo(int paramName) {
        long[] size = new long[1];
        infoGetter.getInfo(baseObject, paramName, 0, null, size);
        cl_platform_id[] buffer = new cl_platform_id[(int) size[0] / Sizeof.cl_platform_id];
        infoGetter.getInfo(baseObject, paramName, size[0], Pointer.to(buffer), null);
        return new OpenCLPlatform(buffer[0]);
    }

    interface InfoGetter<T> {
        int getInfo(T baseObject, int paramName, long size, Pointer buffer, long[] sizeRet);
    }
}
