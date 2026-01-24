package util.opencl;

import org.jocl.Pointer;
import org.jocl.cl_command_queue;

import static org.jocl.CL.*;

public class OpenCLCommandQueue implements AutoCloseable {
    final cl_command_queue commandQueue;

    public OpenCLCommandQueue(OpenCLContext context, OpenCLDevice device) {
        commandQueue = clCreateCommandQueueWithProperties(context.context, device.deviceID, null, null);
    }

    public OpenCLContext getContext() {
        return context;
    }

    protected void getCommandQueueInfo(int paramName) {
        long[] size = new long[1];
        clGetCommandQueueInfo(commandQueue, paramName, 0, null, size);
        byte[] buffer = new byte[(int) size[0]];
        clGetCommandQueueInfo(commandQueue, paramName, size[0], Pointer.to(buffer), null);
    }

    @Override
    public void close() throws Exception {
        clReleaseCommandQueue(commandQueue);
    }
}
