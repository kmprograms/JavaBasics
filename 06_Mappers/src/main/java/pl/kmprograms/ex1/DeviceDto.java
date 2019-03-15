package pl.kmprograms.ex1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeviceDto {
    private Long id;
    private String name;
    private double power;
    private BigDecimal price;
    private DeviceDtoType deviceDtoType;
}
