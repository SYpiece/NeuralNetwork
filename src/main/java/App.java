import util.opencl.OpenCL;
import util.opencl.OpenCLDevice;
import util.opencl.OpenCLPlatform;

import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        testA();
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
}
