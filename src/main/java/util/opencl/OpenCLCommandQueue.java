package util.opencl;

import org.jocl.CL;
import org.jocl.cl_command_queue;
import org.jocl.cl_event;
import org.jocl.cl_queue_properties;

import static org.jocl.CL.*;

public class OpenCLCommandQueue extends OpenCLInfoObject<cl_command_queue> implements AutoCloseable {
    public static OpenCLCommandQueue create(OpenCLContext context, OpenCLDevice device) {
        return create(context, device, null);
    }

    public static OpenCLCommandQueue create(OpenCLContext context, OpenCLDevice device, Properties properties) {
        return new OpenCLCommandQueue(clCreateCommandQueueWithProperties(context.context, device.deviceID, properties == null ? null : properties.properties, null));
    }

    final cl_command_queue commandQueue;

    OpenCLCommandQueue(cl_command_queue commandQueue) {
        super(commandQueue, CL::clGetCommandQueueInfo);
        this.commandQueue = commandQueue;
    }

    public OpenCLContext getContext() {
        return getContextInfo(CL_QUEUE_CONTEXT);
    }

    public OpenCLDevice getDevice() {
        return getDeviceInfo(CL_QUEUE_DEVICE);
    }

    public int getReferenceCount() {
        return getIntInfo(CL_QUEUE_REFERENCE_COUNT);
    }

    public void enqueueNDRangeKernel(OpenCLKernel kernel, Range range) {
        clEnqueueNDRangeKernel(commandQueue, kernel.kernel, range.workDim, range.globalWorkOffset, range.globalWorkSize, range.localWorkSize, 0, null, null);
    }

    public OpenCLEvent enqueueNDRangeKernel(OpenCLKernel kernel, Range range, OpenCLEvent[] waitList) {
        cl_event[] events = null;
        if (waitList != null) {events = new cl_event[waitList.length];
            for (int i = 0; i < waitList.length; i++) {
                events[i] = waitList[i].event;
            }
        }
        cl_event event = new cl_event();
        clEnqueueNDRangeKernel(
                commandQueue, kernel.kernel, range.workDim, range.globalWorkOffset, range.globalWorkSize, range.localWorkSize,
                events == null ? 0 : events.length, events, event
        );
        return new OpenCLEvent(event);
    }

    public void finish() {
        clFinish(commandQueue);
    }

    @Override
    public void close() {
        clReleaseCommandQueue(commandQueue);
    }

    public static class Properties {
        public static final int
                PROPERTIES = CL_QUEUE_PROPERTIES,
                SIZE = CL_QUEUE_SIZE;

        public static final long
                OUT_OF_ORDER_EXEC_MODE_ENABLE = CL_QUEUE_OUT_OF_ORDER_EXEC_MODE_ENABLE,
                PROFILING_ENABLE = CL_QUEUE_PROFILING_ENABLE,
                ON_DEVICE = CL_QUEUE_ON_DEVICE,
                ON_DEVICE_DEFAULT = CL_QUEUE_ON_DEVICE_DEFAULT;

        final cl_queue_properties properties = new cl_queue_properties();

        public Properties() {}

        public Properties addOutOfOrderExecModeEnable() {
            properties.addProperty(CL_QUEUE_PROPERTIES, OUT_OF_ORDER_EXEC_MODE_ENABLE);
            return this;
        }

        public Properties addProfilingEnable() {
            properties.addProperty(CL_QUEUE_PROPERTIES, PROFILING_ENABLE);
            return this;
        }

        public Properties addOnDevice() {
            properties.addProperty(CL_QUEUE_PROPERTIES, ON_DEVICE);
            return this;
        }

        public Properties addOnDeviceDefault() {
            properties.addProperty(CL_QUEUE_PROPERTIES, ON_DEVICE_DEFAULT);
            return this;
        }

        public Properties add(int property, long value) {
            properties.addProperty(property, value);
            return this;
        }
    }

    public static class Range {
        final int workDim;

        final long[]
                globalWorkOffset,
                globalWorkSize,
                localWorkSize;

        public static Range create(long globalWorkSize) {
            return createND(1, null, new long[]{globalWorkSize}, null);
        }

        public static Range create(long globalWorkOffset, long globalWorkSize) {
            return createND(1, new long[]{globalWorkOffset}, new long[]{globalWorkSize}, null);
        }

        public static Range create(long globalWorkOffset, long globalWorkSize, long localWorkSize) {
            return createND(1, new long[]{globalWorkOffset}, new long[]{globalWorkSize}, new long[]{localWorkSize});
        }

        public static Range create2D(long globalWorkSizeX, long globalWorkSizeY) {
            return createND(2, null, new long[]{globalWorkSizeX, globalWorkSizeY}, null);
        }

        public static Range createND(int workDim, long[] globalWorkOffset, long[] globalWorkSize, long[] localWorkSize) {
            return new Range(workDim, globalWorkOffset, globalWorkSize, localWorkSize);
        }

        Range(int workDim, long[] globalWorkOffset, long[] globalWorkSize, long[] localWorkSize) {
            this.workDim = workDim;
            this.globalWorkOffset = globalWorkOffset;
            this.globalWorkSize = globalWorkSize;
            this.localWorkSize = localWorkSize;
        }
    }
}
