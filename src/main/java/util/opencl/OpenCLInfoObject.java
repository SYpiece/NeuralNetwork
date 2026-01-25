package util.opencl;

import org.jocl.*;

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

    protected OpenCLContext getContextInfo(int paramName) {
        long[] size = new long[1];
        infoGetter.getInfo(baseObject, paramName, 0, null, size);
        cl_context[] buffer = new cl_context[(int) size[0] / Sizeof.cl_context];
        infoGetter.getInfo(baseObject, paramName, size[0], Pointer.to(buffer), null);
        return new OpenCLContext(buffer[0]);
    }

    public OpenCLDevice getDeviceInfo(int paramName) {
        long[] size = new long[1];
        infoGetter.getInfo(baseObject, paramName, 0, null, size);
        cl_device_id[] buffer = new cl_device_id[(int) size[0] / Sizeof.cl_device_id];
        infoGetter.getInfo(baseObject, paramName, size[0], Pointer.to(buffer), null);
        return new OpenCLDevice(buffer[0]);
    }

    protected OpenCLPlatform getPlatformInfo(int paramName) {
        long[] size = new long[1];
        infoGetter.getInfo(baseObject, paramName, 0, null, size);
        cl_platform_id[] buffer = new cl_platform_id[(int) size[0] / Sizeof.cl_platform_id];
        infoGetter.getInfo(baseObject, paramName, size[0], Pointer.to(buffer), null);
        return new OpenCLPlatform(buffer[0]);
    }

    protected long[] getSizeTArrayInfo(int paramName) {
        long[] size = new long[1];
        infoGetter.getInfo(baseObject, paramName, 0, null, size);
        long[] buffer = new long[(int) size[0] / Sizeof.size_t];
        infoGetter.getInfo(baseObject, paramName, size[0], Pointer.to(buffer), null);
        return buffer;
    }

    protected OpenCLDevice[] getDeviceArrayInfo(int paramName) {
        long[] size = new long[1];
        infoGetter.getInfo(baseObject, paramName, 0, null, size);
        cl_device_id[] buffer = new cl_device_id[(int) size[0] / Sizeof.cl_device_id];
        infoGetter.getInfo(baseObject, paramName, size[0], Pointer.to(buffer), null);
        OpenCLDevice[] devices = new OpenCLDevice[buffer.length];
        for (int i = 0; i < buffer.length; i++) {
            devices[i] = new OpenCLDevice(buffer[i]);
        }
        return devices;
    }

    interface InfoGetter<T> {
        int getInfo(T baseObject, int paramName, long size, Pointer buffer, long[] sizeRet);
    }
}
