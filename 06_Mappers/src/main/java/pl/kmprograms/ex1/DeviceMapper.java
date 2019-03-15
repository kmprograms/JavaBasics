package pl.kmprograms.ex1;

import org.mapstruct.*;

@Mapper
public interface DeviceMapper {

    @Mappings({
            @Mapping(source = "deviceType", target = "deviceDtoType")
    })
    DeviceDto deviceToDeviceDto(Device device);

    @Mappings({
            @Mapping(source = "deviceDtoType", target = "deviceType")
    })
    Device deviceDtoToDevice(DeviceDto device);

    @ValueMappings({
            @ValueMapping(source = "A", target = "AA"),
            @ValueMapping(source = "B", target = "BB"),
            @ValueMapping(source = "C", target = "CC")
    })
    DeviceDtoType deviceTypeToDeviceDtoType(DeviceType deviceType);

    @ValueMappings({
            @ValueMapping(source = "AA", target = "A"),
            @ValueMapping(source = "BB", target = "B"),
            @ValueMapping(source = "CC", target = "C")
    })
    DeviceType deviceDtoTypeToDeviceType(DeviceDtoType deviceDtoType);
}
