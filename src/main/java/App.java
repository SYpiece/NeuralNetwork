import util.opencl.*;

import java.util.Arrays;
import java.util.Random;

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
        // 1. 定义矩阵参数
        final int ROWS = 64;      // 矩阵行数
        final int COLS = 64;      // 矩阵列数
        final int MATRIX_SIZE = ROWS * COLS;  // 矩阵元素总数

        // 2. 创建并初始化矩阵
        float[] matrixA = new float[MATRIX_SIZE];
        float[] matrixB = new float[MATRIX_SIZE];
        float[] resultHost = new float[MATRIX_SIZE]; // CPU端结果

        // 3. OpenCL内核代码（执行矩阵加法）
        String kernelSource =
                "__kernel void matrixAdd(__global const float* a, " +
                        "                     __global const float* b, " +
                        "                     __global float* result, " +
                        "                     const int size) { " +
                        "    int gid = get_global_id(0); " +
//                        "    if (gid < size) { " +
                        "        result[gid] = a[gid] + b[gid]; " +
//                        "    } " +
                        "}";

        Random rand = new Random();
        System.out.println("矩阵 A:");
        for (int i = 0; i < MATRIX_SIZE; i++) {
            matrixA[i] = rand.nextFloat() * 10;
            System.out.printf("%6.2f%s", matrixA[i], (i + 1) % COLS == 0 ? "\n" : " ");
        }

        System.out.println("\n矩阵 B:");
        for (int i = 0; i < MATRIX_SIZE; i++) {
            matrixB[i] = rand.nextFloat() * 10;
            System.out.printf("%6.2f%s", matrixB[i], (i + 1) % COLS == 0 ? "\n" : " ");
        }

        // 4. 初始化OpenCL
        OpenCLDevice device = OpenCL.getPlatforms()[0].getDevices(OpenCLDevice.Type.GPU)[0];

        try (
                // 5. 创建OpenCL内存缓冲区
                OpenCLContext context = OpenCLContext.create(device);
                OpenCLCommandQueue queue = OpenCLCommandQueue.create(context, device);
                OpenCLMemory.Float matrixAMemory = OpenCLMemory.createFloatBuffer(context, new OpenCLMemory.Flags().setReadOnly().setCopyHostPtr(), matrixA);
                OpenCLMemory.Float matrixBMemory = OpenCLMemory.createFloatBuffer(context, new OpenCLMemory.Flags().setReadOnly().setCopyHostPtr(), matrixB);
                OpenCLMemory.Float resultMemory = OpenCLMemory.createFloatBuffer(context, new OpenCLMemory.Flags().setWriteOnly(), MATRIX_SIZE);

                // 6. 编译内核程序
                OpenCLProgram program = OpenCLProgram.create(context, kernelSource);
                OpenCLKernel kernel = program.build().createKernel("matrixAdd");
        ) {
            // 7. 设置内核参数
            kernel.setArg(0, matrixAMemory);
            kernel.setArg(1, matrixBMemory);
            kernel.setArg(2, resultMemory);
            kernel.setArg(3, MATRIX_SIZE);

            // 8. 执行内核
//            queue.enqueueNDRangeKernel(kernel, OpenCLCommandQueue.Range.create(MATRIX_SIZE));
            queue.enqueueNDRangeKernel(kernel, OpenCLCommandQueue.Range.create(MATRIX_SIZE));

            // 9. 读取结果到主机内存
            queue.enqueueReadBuffer(resultMemory, resultHost);
        }

        // 10. 输出结果
        System.out.println("\n矩阵相加结果 (A + B):");
        for (int i = 0; i < MATRIX_SIZE; i++) {
            System.out.printf("%6.2f%s", resultHost[i], (i + 1) % COLS == 0 ? "\n" : " ");
        }

        // 11. 验证结果（CPU计算对比）
        System.out.println("\nCPU验证结果:");
        boolean correct = true;
        for (int i = 0; i < MATRIX_SIZE; i++) {
            float cpuResult = matrixA[i] + matrixB[i];
            System.out.printf("%6.2f%s", cpuResult, (i + 1) % COLS == 0 ? "\n" : " ");
            if (Math.abs(cpuResult - resultHost[i]) > 0.001) {
                correct = false;
            }
        }

        System.out.println("\n结果验证: " + (correct ? "✓ 正确" : "✗ 错误"));
    }

    static void testD() {
    }
}
