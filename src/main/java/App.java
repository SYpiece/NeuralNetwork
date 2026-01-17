import util.opencl.OpenCL;
import util.opencl.OpenCLDevice;
import util.opencl.OpenCLPlatform;

import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        testA();
    }

    static void testA() {
        OpenCLPlatform[] openCLPlatforms = OpenCL.getPlatforms();
        for (int i = 0; i < openCLPlatforms.length; i++) {
            OpenCLPlatform openCLPlatform = openCLPlatforms[i];
            System.out.println("Platform " + i + ": ");
            System.out.println("Name: " + openCLPlatform.getName());
            System.out.println("Vendor: " + openCLPlatform.getVendor());
            System.out.println("Profile: " + openCLPlatform.getProfile());
            System.out.println("Version: " + openCLPlatform.getVersion());
            System.out.println("Extensions: " + Arrays.toString(openCLPlatform.getExtensions()));
            System.out.println();
            OpenCLDevice[] openCLDevices = openCLPlatform.getDevices();
        }
    }
}
