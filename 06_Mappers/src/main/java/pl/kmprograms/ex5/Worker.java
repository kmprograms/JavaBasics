package pl.kmprograms.ex5;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Worker {
    private Long id;
    private String name;
    private LocalDate hireDate;
    private Company company;
}
