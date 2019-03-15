package pl.kmprograms.ex5;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkerDto {
    private Long id;
    private String name;
    private String hireDate;
    private String companyName;
}
