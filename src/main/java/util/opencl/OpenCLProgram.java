package util.opencl;

import org.jocl.BuildProgramFunction;
import org.jocl.cl_program;

import static org.jocl.CL.*;

public class OpenCLProgram implements AutoCloseable {
    final cl_program program;

    public OpenCLProgram(OpenCLContext context, String... source) {
        program = clCreateProgramWithSource(context.context, source.length, source, null, null);
    }

    public void build() {
        clBuildProgram(program, 0, null, null, null, null);
    }

    @Override
    public void close() {
        clReleaseProgram(program);
    }
}
