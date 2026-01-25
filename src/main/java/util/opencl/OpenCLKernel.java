package util.opencl;

import org.jocl.cl_kernel;

import static org.jocl.CL.clCreateKernel;
import static org.jocl.CL.clReleaseKernel;

public class OpenCLKernel implements AutoCloseable {
    final cl_kernel kernel;

    public OpenCLKernel(OpenCLProgram program, String kernelName) {
        kernel = clCreateKernel(program.program, kernelName, null);
    }

    @Override
    public void close() {
        clReleaseKernel(kernel);
    }
}
