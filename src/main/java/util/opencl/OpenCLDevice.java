package util.opencl;

import org.jocl.CL;
import org.jocl.Pointer;
import org.jocl.cl_device_id;

import static org.jocl.CL.*;
import static util.opencl.OpenCLTypeUtil.*;

public class OpenCLDevice extends OpenCLInfoObject<cl_device_id> {
    public static final long
            TYPE_ALL = CL_DEVICE_TYPE_ALL,
            TYPE_CPU = CL_DEVICE_TYPE_CPU,
            TYPE_GPU = CL_DEVICE_TYPE_GPU,
            TYPE_ACCELERATOR = CL_DEVICE_TYPE_ACCELERATOR,
            TYPE_CUSTOM = CL_DEVICE_TYPE_CUSTOM,
            TYPE_DEFAULT = CL_DEVICE_TYPE_DEFAULT;

    final cl_device_id deviceID;

    boolean
            available,
            compilerAvailable,
            endianLittle,
            errorCorrectionSupport,
//          hostUnifiedMemory,
            imageSupport;

    long
            addressBits,
            globalMemCacheSize,
            globalMemCacheLineSize,
            globalMemSize,
            image2DMaxHeight,
            image2DMaxWidth,
            image3DMaxDepth,
            image3DMaxHeight,
            image3DMaxWidth,
            localMemSize,
            maxClockFrequency,
            maxComputeUnits,
            maxConstantArgs,
            maxConstantBufferSize,
            maxMemAllocSize,
            maxParameterSize,
            maxReadImageArgs,
            maxSamples,
            maxWorkGroupSize,
            maxWorkItemDimensions,
            maxWriteImageArgs,
            memBaseAddrAlign,
            minDataTypeAlignSize,
            nativeVectorWidthChar,
            nativeVectorWidthShort,
            nativeVectorWidthInt,
            nativeVectorWidthLong,
            nativeVectorWidthFloat,
            nativeVectorWidthDouble,
            nativeVectorWidthHalf,
            preferredVectorWidthChar,
            preferredVectorWidthShort,
            preferredVectorWidthInt,
            preferredVectorWidthLong,
            preferredVectorWidthFloat,
            preferredVectorWidthDouble,
            preferredVectorWidthHalf,
            profilingTimerResolution,
            vendorID;


    OpenCLDevice(cl_device_id deviceID) {
        super(deviceID, CL::clGetDeviceInfo);
        this.deviceID = deviceID;
    }

    public int getAddressBits() {
        return getIntInfo(CL_DEVICE_ADDRESS_BITS);
    }

    public boolean isAvailable() {
        return getBooleanInfo(CL_DEVICE_AVAILABLE);
    }

    public boolean isCompilerAvailable() {
        return getBooleanInfo(CL_DEVICE_COMPILER_AVAILABLE);
    }

    public DoubleFPConfig getDoubleFPConfig() {
        return new DoubleFPConfig(getLongInfo(CL_DEVICE_DOUBLE_FP_CONFIG));
    }

    public boolean isEndianLittle() {
        return getBooleanInfo(CL_DEVICE_ENDIAN_LITTLE);
    }

    public boolean isErrorCorrectionSupport() {
        return getBooleanInfo(CL_DEVICE_ERROR_CORRECTION_SUPPORT);
    }

    public () {

    }

    public String[] getExtensions() {
        return getStringInfo(CL_DEVICE_EXTENSIONS).split(" ");
    }

    public long getGlobalMemCacheSize() {
        return getLongInfo(CL_DEVICE_GLOBAL_MEM_CACHE_SIZE);
    }

    public () {

    }

    public int getGlobalMemCacheLineSize() {
        return getIntInfo(CL_DEVICE_GLOBAL_MEM_CACHELINE_SIZE);
    }

    public long getGlobalMemSize() {
        return getLongInfo(CL_DEVICE_GLOBAL_MEM_SIZE);
    }



    @Deprecated
    public boolean isHostUnifiedMemory() {
        return getBooleanInfo(CL_DEVICE_HOST_UNIFIED_MEMORY);
    }

    protected void initializeInfo() {
        available = getBoolean(getDeviceInfo(CL_DEVICE_AVAILABLE));
        compilerAvailable = getBoolean(getDeviceInfo(CL_DEVICE_COMPILER_AVAILABLE));
        endianLittle = getBoolean(getDeviceInfo(CL_DEVICE_ENDIAN_LITTLE));
        errorCorrectionSupport = getBoolean(getDeviceInfo(CL_DEVICE_ERROR_CORRECTION_SUPPORT));
//      hostUnifiedMemory = getBoolean(getDeviceInfo(CL_DEVICE_HOST_UNIFIED_MEMORY));
        imageSupport = getBoolean(getDeviceInfo(CL_DEVICE_IMAGE_SUPPORT));

        addressBits = getUInt(getDeviceInfo(CL_DEVICE_ADDRESS_BITS), endianLittle);
        globalMemCacheSize = getULong(getDeviceInfo(CL_DEVICE_GLOBAL_MEM_CACHE_SIZE), endianLittle);
        globalMemCacheLineSize = getUInt(getDeviceInfo(CL_DEVICE_GLOBAL_MEM_CACHELINE_SIZE), endianLittle);
        globalMemSize = getULong(getDeviceInfo(CL_DEVICE_GLOBAL_MEM_SIZE), endianLittle);
        image2DMaxHeight = getSizeT(getDeviceInfo(CL_DEVICE_IMAGE2D_MAX_HEIGHT), endianLittle);
        image2DMaxWidth = getSizeT(getDeviceInfo(CL_DEVICE_IMAGE2D_MAX_WIDTH), endianLittle);
        image3DMaxDepth = getSizeT(getDeviceInfo(CL_DEVICE_IMAGE3D_MAX_DEPTH), endianLittle);
        image3DMaxHeight = getSizeT(getDeviceInfo(CL_DEVICE_IMAGE3D_MAX_HEIGHT), endianLittle);
        image3DMaxWidth = getSizeT(getDeviceInfo(CL_DEVICE_IMAGE3D_MAX_WIDTH), endianLittle);
        localMemSize = getULong(getDeviceInfo(CL_DEVICE_LOCAL_MEM_SIZE), endianLittle);
        maxClockFrequency = getUInt(getDeviceInfo(CL_DEVICE_MAX_CLOCK_FREQUENCY), endianLittle);
        maxComputeUnits = getUInt(getDeviceInfo(CL_DEVICE_MAX_COMPUTE_UNITS), endianLittle);
        maxConstantArgs = getUInt(getDeviceInfo(CL_DEVICE_MAX_CONSTANT_ARGS), endianLittle);
        maxConstantBufferSize = getULong(getDeviceInfo(CL_DEVICE_MAX_CONSTANT_BUFFER_SIZE), endianLittle);
        maxMemAllocSize = getULong(getDeviceInfo(CL_DEVICE_MAX_MEM_ALLOC_SIZE), endianLittle);
        maxParameterSize = getSizeT(getDeviceInfo(CL_DEVICE_MAX_PARAMETER_SIZE), endianLittle);
        maxReadImageArgs = getUInt(getDeviceInfo(CL_DEVICE_MAX_READ_IMAGE_ARGS), endianLittle);
        maxSamples = getUInt(getDeviceInfo(CL_DEVICE_MAX_SAMPLERS), endianLittle);
        maxWorkGroupSize = getSizeT(getDeviceInfo(CL_DEVICE_MAX_WORK_GROUP_SIZE), endianLittle);
        maxWorkItemDimensions = getUInt(getDeviceInfo(CL_DEVICE_MAX_WORK_ITEM_DIMENSIONS), endianLittle);
        maxWriteImageArgs = getUInt(getDeviceInfo(CL_DEVICE_MAX_WRITE_IMAGE_ARGS), endianLittle);
        memBaseAddrAlign = getUInt(getDeviceInfo(CL_DEVICE_MEM_BASE_ADDR_ALIGN), endianLittle);
        minDataTypeAlignSize = getUInt(getDeviceInfo(CL_DEVICE_MIN_DATA_TYPE_ALIGN_SIZE), endianLittle);
        nativeVectorWidthChar = getUInt(getDeviceInfo(CL_DEVICE_NATIVE_VECTOR_WIDTH_CHAR), endianLittle);
        nativeVectorWidthShort = getUInt(getDeviceInfo(CL_DEVICE_NATIVE_VECTOR_WIDTH_SHORT), endianLittle);
        nativeVectorWidthInt = getUInt(getDeviceInfo(CL_DEVICE_NATIVE_VECTOR_WIDTH_INT), endianLittle);
        nativeVectorWidthLong = getUInt(getDeviceInfo(CL_DEVICE_NATIVE_VECTOR_WIDTH_LONG), endianLittle);
        nativeVectorWidthFloat = getUInt(getDeviceInfo(CL_DEVICE_NATIVE_VECTOR_WIDTH_FLOAT), endianLittle);
        nativeVectorWidthDouble = getUInt(getDeviceInfo(CL_DEVICE_NATIVE_VECTOR_WIDTH_DOUBLE), endianLittle);
        nativeVectorWidthHalf = getUInt(getDeviceInfo(CL_DEVICE_NATIVE_VECTOR_WIDTH_HALF), endianLittle);
        preferredVectorWidthChar = getUInt(getDeviceInfo(CL_DEVICE_PREFERRED_VECTOR_WIDTH_CHAR), endianLittle);
        preferredVectorWidthShort = getUInt(getDeviceInfo(CL_DEVICE_PREFERRED_VECTOR_WIDTH_SHORT), endianLittle);
        preferredVectorWidthInt = getUInt(getDeviceInfo(CL_DEVICE_PREFERRED_VECTOR_WIDTH_INT), endianLittle);
        preferredVectorWidthLong = getUInt(getDeviceInfo(CL_DEVICE_PREFERRED_VECTOR_WIDTH_LONG), endianLittle);
        preferredVectorWidthFloat = getUInt(getDeviceInfo(CL_DEVICE_PREFERRED_VECTOR_WIDTH_FLOAT), endianLittle);
        preferredVectorWidthDouble = getUInt(getDeviceInfo(CL_DEVICE_PREFERRED_VECTOR_WIDTH_DOUBLE), endianLittle);
        preferredVectorWidthHalf = getUInt(getDeviceInfo(CL_DEVICE_PREFERRED_VECTOR_WIDTH_HALF), endianLittle);
        profilingTimerResolution = getSizeT(getDeviceInfo(CL_DEVICE_PROFILING_TIMER_RESOLUTION), endianLittle);
        vendorID = getUInt(getDeviceInfo(CL_DEVICE_VENDOR_ID), endianLittle);
    }

    protected byte[] getDeviceInfo(int paramName) {
        long[] size = new long[1];
        int result = clGetDeviceInfo(deviceID, paramName, 0, null, size);
        if (result != CL_SUCCESS) {
            throw new RuntimeException("Error: " + result);
        }
        byte[] buffer = new byte[(int) size[0]];
        result = clGetDeviceInfo(deviceID, paramName, size[0], Pointer.to(buffer), null);
        if (result != CL_SUCCESS) {
            throw new RuntimeException("Error: " + result);
        }
        return buffer;
    }

    public static class DoubleFPConfig {
        public static final long
                DENORM = CL_FP_DENORM,
                INF_NAN = CL_FP_INF_NAN,
                ROUND_TO_NEAREST = CL_FP_ROUND_TO_NEAREST,
                ROUND_TO_ZERO = CL_FP_ROUND_TO_ZERO,
                ROUND_TO_INF = CL_FP_ROUND_TO_INF,
                FMA = CL_FP_FMA;

        public final long value;

        public boolean supportDenorm() {
            return (value & DENORM) != 0;
        }

        public boolean supportInfNan() {
            return (value & INF_NAN) != 0;
        }

        public boolean supportRoundToNearest() {
            return (value & ROUND_TO_NEAREST) != 0;
        }

        public boolean supportRoundToZero() {
            return (value & ROUND_TO_ZERO) != 0;
        }

        public boolean supportRoundToInf() {
            return (value & ROUND_TO_INF) != 0;
        }

        public boolean supportFMA() {
            return (value & FMA) != 0;
        }

        public boolean support(long flag) {
            return (value & flag) != 0;
        }

        public DoubleFPConfig(long value) {
            this.value = value;
        }
    }

    public static class ExecutionCapabilities {
    }
}
