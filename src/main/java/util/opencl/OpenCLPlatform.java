package util.opencl;

import org.jocl.*;

import static org.jocl.CL.*;

public class OpenCLPlatform {
    final cl_platform_id platformID;

    String
            name,
            vendor,
            profile,
            version,
            extensions;

    OpenCLPlatform(cl_platform_id platformID) {
        this.platformID = platformID;
    }

    public String getName() {
        if (name == null) {
            byte[] buffer = getPlatformInfo(CL_PLATFORM_NAME);
            name = new String(buffer, 0, buffer.length - 1);
        }
        return name;
    }

    public String getVendor() {
        if (vendor == null) {
            byte[] buffer = getPlatformInfo(CL_PLATFORM_VENDOR);
            vendor = new String(buffer, 0, buffer.length - 1);
        }
        return vendor;
    }

    public String getProfile() {
        if (profile == null) {
            byte[] buffer = getPlatformInfo(CL_PLATFORM_PROFILE);
            profile = new String(buffer, 0, buffer.length - 1);
        }
        return profile;
    }

    public String getVersion() {
        if (version == null) {
            byte[] buffer = getPlatformInfo(CL_PLATFORM_VERSION);
            version = new String(buffer, 0, buffer.length - 1);
        }
        return version;
    }

    public String getExtensions() {
        if (extensions == null) {
            byte[] buffer = getPlatformInfo(CL_PLATFORM_EXTENSIONS);
            extensions = new String(buffer, 0, buffer.length - 1);
        }
        return extensions;
    }

    public int getDeviceCount() {
        int[] numDevices = new int[1];
        int result = clGetDeviceIDs(platformID, CL_DEVICE_TYPE_ALL, 0, null, numDevices);
        if (result != CL_SUCCESS) {
            throw new RuntimeException("Error: " + result);
        }
        return numDevices[0];
    }

    public OpenCLDevice[] getDevices() {
        int numDevices = getDeviceCount();
        cl_device_id[] devices = new cl_device_id[numDevices];
        int result = clGetDeviceIDs(platformID, CL_DEVICE_TYPE_ALL, numDevices, devices, null);
        if (result != CL_SUCCESS) {
            throw new RuntimeException("Error: " + result);
        }
        OpenCLDevice[] openCLDevices = new OpenCLDevice[numDevices];
        for (int i = 0; i < numDevices; i++) {
            openCLDevices[i] = new OpenCLDevice(devices[i]);
        }
        return openCLDevices;
    }

    protected byte[] getPlatformInfo(int paramName) {
        long[] size = new long[1];
        int result = clGetPlatformInfo(platformID, paramName, 0, null, size);
        if (result != CL_SUCCESS) {
            throw new RuntimeException("Error: " + result);
        }
        byte[] buffer = new byte[(int) size[0]];
        result = clGetPlatformInfo(platformID, paramName, size[0], Pointer.to(buffer), null);
        if (result != CL_SUCCESS) {
            throw new RuntimeException("Error: " + result);
        }
        return buffer;
    }
}
