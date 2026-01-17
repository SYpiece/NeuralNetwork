package util.opencl;

import org.jocl.*;

import static org.jocl.CL.*;
import static util.opencl.BitsUtil.*;

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
            name = getString(getPlatformInfo(CL_PLATFORM_NAME));
        }
        return name;
    }

    public String getVendor() {
        if (vendor == null) {
            vendor = getString(getPlatformInfo(CL_PLATFORM_VENDOR));
        }
        return vendor;
    }

    public String getProfile() {
        if (profile == null) {
            profile = getString(getPlatformInfo(CL_PLATFORM_PROFILE));
        }
        return profile;
    }

    public String getVersion() {
        if (version == null) {
            version = getString(getPlatformInfo(CL_PLATFORM_VERSION));
        }
        return version;
    }

    public String getExtensions() {
        if (extensions == null) {
            extensions = getString(getPlatformInfo(CL_PLATFORM_EXTENSIONS));
        }
        return extensions;
    }

    public int getDeviceCount() {
        return getDeviceCount(OpenCLDevice.TYPE_ALL);
    }

    public int getDeviceCount(long deviceType) {
        int[] numDevices = new int[1];
        int result = clGetDeviceIDs(platformID, deviceType, 0, null, numDevices);
        if (result != CL_SUCCESS) {
            throw new RuntimeException("Error: " + result);
        }
        return numDevices[0];
    }

    public OpenCLDevice[] getDevices() {
        return getDevices(OpenCLDevice.TYPE_ALL);
    }

    public OpenCLDevice[] getDevices(long deviceType) {
        int numDevices = getDeviceCount(deviceType);
        cl_device_id[] devices = new cl_device_id[numDevices];
        int result = clGetDeviceIDs(platformID, deviceType, numDevices, devices, null);
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
