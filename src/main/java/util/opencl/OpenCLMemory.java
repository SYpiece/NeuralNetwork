package util.opencl;

import org.jocl.Pointer;
import org.jocl.Sizeof;
import org.jocl.cl_mem;

import static org.jocl.CL.*;

public class OpenCLMemory implements AutoCloseable {
    final cl_mem memory;

    public static OpenCLMemory createReadOnlyMemory(OpenCLContext context, float[] data) {
        return new OpenCLMemory(context, CL_MEM_READ_ONLY | CL_MEM_COPY_HOST_PTR, data);
    }

    OpenCLMemory(OpenCLContext context, long flags, float[] data) {
        memory = clCreateBuffer(context.context, flags, (long)Sizeof.cl_float * data.length, Pointer.to(data), null);
    }

    @Override
    public void close() throws Exception {
        clReleaseMemObject(memory);
    }
}
