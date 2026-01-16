package util.opencl;

import org.jocl.Pointer;
import org.jocl.cl_device_id;

import static org.jocl.CL.*;

public class OpenCLDevice {
    final cl_device_id deviceID;

    OpenCLDevice(cl_device_id deviceID) {
        this.deviceID = deviceID;
    }

    protected byte[] getDeviceInfo(int paramName) {
        long[] size = new long[1];
        int result = clGetDeviceInfo(deviceID, paramName, 0, null, size);
        if (result != CL_SUCCESS) {
            throw new RuntimeException("Error: " + result);
        }
        byte[] buffer = new byte[(int) size[0]];
        result = clGetDeviceInfo(deviceID, paramName, size[0], Pointer.to(buffer), null);
        if (result != CL_SUCCESS) {
            throw new RuntimeException("Error: " + result);
        }
        return buffer;
    }
}
