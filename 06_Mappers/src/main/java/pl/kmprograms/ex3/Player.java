package pl.kmprograms.ex3;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Player {
    private Long id;
    private String name;
    private int goals;
    private Team team;
    private List<Stadium> stadiums;
}
