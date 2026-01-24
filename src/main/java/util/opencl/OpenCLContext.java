package util.opencl;

import org.jocl.CreateContextFunction;
import org.jocl.Pointer;
import org.jocl.cl_context;
import org.jocl.cl_device_id;

import static org.jocl.CL.*;

public class OpenCLContext implements AutoCloseable {
    final cl_context context;

    final CreateContextFunction createContextFunction = (String errorInfo, Pointer private_info, long cb, Object user_data) -> {

    };

    public OpenCLContext(OpenCLDevice... devices) {
        cl_device_id[] deviceIDs = new cl_device_id[devices.length];
        for (int i = 0; i < devices.length; i++) {
            deviceIDs[i] = devices[i].deviceID;
        }
        context = clCreateContext(null, deviceIDs.length, deviceIDs, createContextFunction, null, null);
    }

    @Override
    public void close() {
        clReleaseContext(context);
    }
}
