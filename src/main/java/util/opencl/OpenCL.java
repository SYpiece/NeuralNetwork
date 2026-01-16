package util.opencl;

import org.jocl.*;

import java.util.List;

import static org.jocl.CL.*;

public class OpenCL {
    public static int getPlatformCount() {
        int[] numPlatforms = new int[1];
        int result = clGetPlatformIDs(0, null, numPlatforms);
        if (result != CL_SUCCESS) {
            throw new RuntimeException("Error: " + result);
        }
        return numPlatforms[0];
    }

    public static OpenCLPlatform[] getPlatforms() {
        int numPlatforms = getPlatformCount();
        cl_platform_id[] platforms = new cl_platform_id[numPlatforms];
        int result = clGetPlatformIDs(numPlatforms, platforms, null);
        if (result != CL_SUCCESS) {
            throw new RuntimeException("Error: " + result);
        }
        OpenCLPlatform[] openCLPlatforms = new OpenCLPlatform[numPlatforms];
        for (int i = 0; i < numPlatforms; i++) {
            openCLPlatforms[i] = new OpenCLPlatform(platforms[i]);
        }
        return openCLPlatforms;
    }

    protected OpenCL() {}
}
