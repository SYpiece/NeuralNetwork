package util.opencl;

import org.jocl.CL;
import org.jocl.Pointer;
import org.jocl.Sizeof;
import org.jocl.cl_mem;

import static org.jocl.CL.*;

public class OpenCLMemory extends OpenCLInfoObject<cl_mem> implements AutoCloseable {
    public static final int
            TYPE_BUFFER = CL_MEM_OBJECT_BUFFER,
            TYPE_IMAGE2D = CL_MEM_OBJECT_IMAGE2D,
            TYPE_IMAGE3D = CL_MEM_OBJECT_IMAGE3D;

    public static OpenCLMemory create(OpenCLContext context, Flags flags, byte[] values) {
        return new OpenCLMemory(clCreateBuffer(context.context, flags.value, (long) Sizeof.cl_char * values.length, Pointer.to(values), null));
    }

    public static OpenCLMemory create(OpenCLContext context, Flags flags, short[] values) {
        return new OpenCLMemory(clCreateBuffer(context.context, flags.value, (long) Sizeof.cl_short * values.length, Pointer.to(values), null));
    }

    public static OpenCLMemory create(OpenCLContext context, Flags flags, int[] values) {
        return new OpenCLMemory(clCreateBuffer(context.context, flags.value, (long) Sizeof.cl_int * values.length, Pointer.to(values), null));
    }

    public static OpenCLMemory create(OpenCLContext context, Flags flags, long[] values) {
        return new OpenCLMemory(clCreateBuffer(context.context, flags.value, (long) Sizeof.cl_long * values.length, Pointer.to(values), null));
    }

    public static OpenCLMemory create(OpenCLContext context, Flags flags, float[] values) {
        return new OpenCLMemory(clCreateBuffer(context.context, flags.value, (long) Sizeof.cl_float * values.length, Pointer.to(values), null));
    }

    public static OpenCLMemory create(OpenCLContext context, Flags flags, double[] values) {
        return new OpenCLMemory(clCreateBuffer(context.context, flags.value, (long) Sizeof.cl_double * values.length, Pointer.to(values), null));
    }

    final cl_mem memory;

    OpenCLMemory(cl_mem memory) {
        super(memory, CL::clGetMemObjectInfo);
        this.memory = memory;
    }

    public int getType() {
        return getIntInfo(CL_MEM_TYPE);
    }

    public ReadOnlyFlags getFlags() {
        return new ReadOnlyFlags(getLongInfo(CL_MEM_FLAGS));
    }

    public long getSize() {
        return getSizeTInfo(CL_MEM_SIZE);
    }

    public int getMapCount() {
        return getIntInfo(CL_MEM_MAP_COUNT);
    }

    public int getReferenceCount() {
        return getIntInfo(CL_MEM_REFERENCE_COUNT);
    }

    public OpenCLContext getContext() {
        return getContextInfo(CL_MEM_CONTEXT);
    }

    public OpenCLMemory getAssociatedMemObject() {
        return getMemoryInfo(CL_MEM_ASSOCIATED_MEMOBJECT);
    }

    public long getOffset() {
        return getSizeTInfo(CL_MEM_OFFSET);
    }

    @Override
    public void close() {
        clReleaseMemObject(memory);
    }

    public static class ReadOnlyFlags {
        public static final long
                READ_WRITE = CL_MEM_READ_WRITE,
                WRITE_ONLY = CL_MEM_WRITE_ONLY,
                READ_ONLY = CL_MEM_READ_ONLY,
                USE_HOST_PTR = CL_MEM_USE_HOST_PTR,
                ALLOC_HOST_PTR = CL_MEM_ALLOC_HOST_PTR,
                COPY_HOST_PTR = CL_MEM_COPY_HOST_PTR;

        public long value;

        public ReadOnlyFlags(long value) {
            this.value = value;
        }

        public boolean isReadWrite() {
            return (value & READ_WRITE) != 0;
        }

        public boolean isWriteOnly() {
            return (value & WRITE_ONLY) != 0;
        }

        public boolean isReadOnly() {
            return (value & READ_ONLY) != 0;
        }

        public boolean isUseHostPtr() {
            return (value & USE_HOST_PTR) != 0;
        }

        public boolean isAllocHostPtr() {
            return (value & ALLOC_HOST_PTR) != 0;
        }

        public boolean isCopyHostPtr() {
            return (value & COPY_HOST_PTR) != 0;
        }

        public boolean is(long flag) {
            return (value & flag) != 0;
        }
    }

    public static class Flags extends ReadOnlyFlags {
        public Flags() {
            super(0);
        }

        public Flags(long value) {
            super(value);
        }

        public Flags setReadWrite() {
            value |= READ_WRITE;
            return this;
        }

        public Flags setWriteOnly() {
            value |= WRITE_ONLY;
            return this;
        }

        public Flags setReadOnly() {
            value |= READ_ONLY;
            return this;
        }

        public Flags setUseHostPtr() {
            value |= USE_HOST_PTR;
            return this;
        }

        public Flags setAllocHostPtr() {
            value |= ALLOC_HOST_PTR;
            return this;
        }

        public Flags setCopyHostPtr() {
            value |= COPY_HOST_PTR;
            return this;
        }

        public Flags set(long flag) {
            value |= flag;
            return this;
        }
    }
}
