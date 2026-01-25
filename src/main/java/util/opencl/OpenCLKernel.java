package util.opencl;

import org.jocl.Pointer;
import org.jocl.Sizeof;
import org.jocl.cl_kernel;

import static org.jocl.CL.clReleaseKernel;
import static org.jocl.CL.clSetKernelArg;

public class OpenCLKernel implements AutoCloseable {
    final cl_kernel kernel;

    OpenCLKernel(cl_kernel kernel) {
        this.kernel = kernel;
    }

    public void setArg(int index, OpenCLMemory memory) {
        clSetKernelArg(kernel, index, Sizeof.cl_mem, Pointer.to(memory.memory));
    }

    public void setArg(int index, byte... value) {
        clSetKernelArg(kernel, index, (long) Sizeof.cl_char * value.length, Pointer.to(value));
    }

    public void setArg(int index, short... value) {
        clSetKernelArg(kernel, index, (long) Sizeof.cl_short * value.length, Pointer.to(value));
    }

    public void setArg(int index, int... value) {
        clSetKernelArg(kernel, index, (long) Sizeof.cl_int * value.length, Pointer.to(value));
    }

    public void setArg(int index, long... value) {
        clSetKernelArg(kernel, index, (long) Sizeof.cl_long * value.length, Pointer.to(value));
    }

    public void setArg(int index, float... value) {
        clSetKernelArg(kernel, index, (long) Sizeof.cl_float * value.length, Pointer.to(value));
    }

    public void setArg(int index, double... value) {
        clSetKernelArg(kernel, index, (long) Sizeof.cl_double * value.length, Pointer.to(value));
    }

    @Override
    public void close() {
        clReleaseKernel(kernel);
    }
}
