import util.opencl.*;

import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        testD();
    }

    static void testA() {
        for (OpenCLPlatform platform : OpenCL.getPlatforms()) {
            System.out.println(platform);
            System.out.println();
            for (OpenCLDevice device : platform.getDevices()) {
                System.out.println(device);
                System.out.println();
            }
        }
    }

    static void testB() {
        OpenCLDevice device = OpenCL.getPlatforms()[0].getDevices(OpenCLDevice.Type.GPU)[0];
        OpenCLContext context1 = null;
        try (
                OpenCLContext context = OpenCLContext.create(device);
                OpenCLCommandQueue queue = OpenCLCommandQueue.create(context, device);
                ) {
            context1 = queue.getContext();
            System.out.println(context1.getReferenceCount());
        }
        System.out.println(context1.getReferenceCount());
    }

    static void testC() {
        OpenCLDevice device = OpenCL.getPlatforms()[0].getDevices(OpenCLDevice.Type.GPU)[0];
        try (
                OpenCLContext context = OpenCLContext.create(device);
                OpenCLCommandQueue queue = OpenCLCommandQueue.create(context, device);
        ) {

        }
    }

    static void testD() {
        OpenCLDevice device = OpenCL.getPlatforms()[0].getDevices(OpenCLDevice.Type.GPU)[0];
        long[] buffer = new long[16];
        try (
                OpenCLContext context = OpenCLContext.create(device);
                OpenCLMemory memory = OpenCLMemory.create(context, new OpenCLMemory.Flags().setReadWrite().setUseHostPtr(), buffer);
        ) {
        }
    }
}
