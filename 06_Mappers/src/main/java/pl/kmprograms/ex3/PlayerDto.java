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
public class PlayerDto {
    private Long playerId;
    private String playerName;
    private int playerGoals;
    private TeamDto team;
    private List<StadiumDto> stadiums;
}
