package util.opencl;

import org.jocl.*;

import java.util.Arrays;

import static org.jocl.CL.*;
import static util.opencl.OpenCLTypeUtil.*;

public class OpenCLPlatform {
    final cl_platform_id platformID;

    String
            name,
            vendor,
            profile,
            version;
    String[]
            extensions;

    OpenCLPlatform(cl_platform_id platformID) {
        this.platformID = platformID;
        initializeInfo();
    }

    protected void initializeInfo() {
        name = getString(getPlatformInfo(CL_PLATFORM_NAME));
        vendor = getString(getPlatformInfo(CL_PLATFORM_VENDOR));
        profile = getString(getPlatformInfo(CL_PLATFORM_PROFILE));
        version = getString(getPlatformInfo(CL_PLATFORM_VERSION));
        extensions = getString(getPlatformInfo(CL_PLATFORM_EXTENSIONS)).split(" ");
    }

    public String getName() {
        return name;
    }

    public String getVendor() {
        return vendor;
    }

    public String getProfile() {
        return profile;
    }

    public String getVersion() {
        return version;
    }

    public String[] getExtensions() {
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

    @Override
    public String toString() {
        return String.format(
                "OpenCLPlatform{" +
                        "name='%s', " +
                        "vendor='%s', " +
                        "profile='%s', " +
                        "version='%s', " +
                        "extensions='%s'" +
                "}",
                name, vendor, profile, version, Arrays.toString(extensions));
    }
}
