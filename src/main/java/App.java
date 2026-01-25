import util.opencl.*;

import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        testC();
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
        OpenCLDevice device = OpenCL.getPlatforms()[0].getDevices()[0];
        device.getDoubleFPConfig();
        device.getExecutionCapabilities();
        device.getGlobalMemoryCacheType();
        device.getHalfFPConfig();
        device.getLocalMemoryType();
        device.getQueueProperties();
        device.getSingleFPConfig();
    }

    static void testC() {
        OpenCLDevice device = OpenCL.getPlatforms()[0].getDevices(OpenCLDevice.TYPE_GPU)[0];
        try (
                OpenCLContext context = OpenCLContext.create(device);
                OpenCLCommandQueue queue = OpenCLCommandQueue.create(context, device);
        ) {

        }
    }
}
