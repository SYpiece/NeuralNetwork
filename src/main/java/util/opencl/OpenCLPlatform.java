package util.opencl;

import org.jocl.*;

import java.util.Arrays;

import static org.jocl.CL.*;
import static util.opencl.OpenCLTypeUtil.*;

public class OpenCLPlatform extends OpenCLInfoObject<cl_platform_id> {
    final cl_platform_id platformID;

    OpenCLPlatform(cl_platform_id platformID) {
        super(platformID, CL::clGetPlatformInfo);
        this.platformID = platformID;
    }

    public String getName() {
        return getStringInfo(CL_PLATFORM_NAME);
    }

    public String getVendor() {
        return getStringInfo(CL_PLATFORM_VENDOR);
    }

    public String getProfile() {
        return getStringInfo(CL_PLATFORM_PROFILE);
    }

    public String getVersion() {
        return getStringInfo(CL_PLATFORM_VERSION);
    }

    public String[] getExtensions() {
        return getStringInfo(CL_PLATFORM_EXTENSIONS).split(" ");
    }

    public int getDeviceCount() {
        return getDeviceCount(OpenCLDevice.TYPE_ALL);
    }

    public int getDeviceCount(long deviceType) {
        int[] numDevices = new int[1];
        clGetDeviceIDs(platformID, deviceType, 0, null, numDevices);
        return numDevices[0];
    }

    public OpenCLDevice[] getDevices() {
        return getDevices(OpenCLDevice.TYPE_ALL);
    }

    public OpenCLDevice[] getDevices(long deviceType) {
        int numDevices = getDeviceCount(deviceType);
        cl_device_id[] devices = new cl_device_id[numDevices];
        clGetDeviceIDs(platformID, deviceType, numDevices, devices, null);
        OpenCLDevice[] openCLDevices = new OpenCLDevice[numDevices];
        for (int i = 0; i < numDevices; i++) {
            openCLDevices[i] = new OpenCLDevice(devices[i]);
        }
        return openCLDevices;
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
                getName(), getVendor(), getProfile(), getVendor(), Arrays.toString(getExtensions()));
    }
}
