package util.opencl;

import org.jocl.Pointer;
import org.jocl.cl_device_id;

import static org.jocl.CL.*;
import static util.opencl.BitsUtil.*;

public class OpenCLDevice {
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
        this.deviceID = deviceID;
        initializeInfo();
    }

    protected void initializeInfo() {
        available = getBoolean(getDeviceInfo(CL_DEVICE_AVAILABLE));
        compilerAvailable = getBoolean(getDeviceInfo(CL_DEVICE_COMPILER_AVAILABLE));
        endianLittle = getBoolean(getDeviceInfo(CL_DEVICE_ENDIAN_LITTLE));
        errorCorrectionSupport = getBoolean(getDeviceInfo(CL_DEVICE_ERROR_CORRECTION_SUPPORT));
//      hostUnifiedMemory = getBoolean(getDeviceInfo(CL_DEVICE_HOST_UNIFIED_MEMORY));
        imageSupport = getBoolean(getDeviceInfo(CL_DEVICE_IMAGE_SUPPORT));

        addressBits = getUInt(getDeviceInfo(CL_DEVICE_ADDRESS_BITS));
        globalMemCacheSize = getULong(getDeviceInfo(CL_DEVICE_GLOBAL_MEM_CACHE_SIZE));
        globalMemCacheLineSize = getUInt(getDeviceInfo(CL_DEVICE_GLOBAL_MEM_CACHELINE_SIZE));
        globalMemSize = getULong(getDeviceInfo(CL_DEVICE_GLOBAL_MEM_SIZE));
        image2DMaxHeight = getSizeT(getDeviceInfo(CL_DEVICE_IMAGE2D_MAX_HEIGHT));
        image2DMaxWidth = getSizeT(getDeviceInfo(CL_DEVICE_IMAGE2D_MAX_WIDTH));
        image3DMaxDepth = getSizeT(getDeviceInfo(CL_DEVICE_IMAGE3D_MAX_DEPTH));
        image3DMaxHeight = getSizeT(getDeviceInfo(CL_DEVICE_IMAGE3D_MAX_HEIGHT));
        image3DMaxWidth = getSizeT(getDeviceInfo(CL_DEVICE_IMAGE3D_MAX_WIDTH));
        localMemSize = getULong(getDeviceInfo(CL_DEVICE_LOCAL_MEM_SIZE));
        maxClockFrequency = getUInt(getDeviceInfo(CL_DEVICE_MAX_CLOCK_FREQUENCY));
        maxComputeUnits = getUInt(getDeviceInfo(CL_DEVICE_MAX_COMPUTE_UNITS));
        maxConstantArgs = getUInt(getDeviceInfo(CL_DEVICE_MAX_CONSTANT_ARGS));
        maxConstantBufferSize = getULong(getDeviceInfo(CL_DEVICE_MAX_CONSTANT_BUFFER_SIZE));
        maxMemAllocSize = getULong(getDeviceInfo(CL_DEVICE_MAX_MEM_ALLOC_SIZE));
        maxParameterSize = getSizeT(getDeviceInfo(CL_DEVICE_MAX_PARAMETER_SIZE));
        maxReadImageArgs = getUInt(getDeviceInfo(CL_DEVICE_MAX_READ_IMAGE_ARGS));
        maxSamples = getUInt(getDeviceInfo(CL_DEVICE_MAX_SAMPLERS));
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
}
