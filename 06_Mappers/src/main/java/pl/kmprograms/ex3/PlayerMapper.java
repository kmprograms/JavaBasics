package pl.kmprograms.ex3;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface PlayerMapper {

    @Mappings({
        @Mapping(source = "id", target = "stadiumId"),
        @Mapping(source = "name", target = "stadiumName")
    })
    StadiumDto stadiumToStadiumDto(Stadium stadium);

    @Mappings({
            @Mapping(source = "stadiumId", target = "id"),
            @Mapping(source = "stadiumName", target = "name")
    })
    Stadium stadiumDtoToStadium(StadiumDto stadiumDto);

    @Mappings({
            @Mapping(source = "id", target = "teamId"),
            @Mapping(source = "name", target = "teamName"),
            @Mapping(source = "points", target = "teamPoints")
    })
    TeamDto teamToTeamDto(Team team);

    @Mappings({
            @Mapping(source = "teamId", target = "id"),
            @Mapping(source = "teamName", target = "name"),
            @Mapping(source = "teamPoints", target = "points")
    })
    Team teamDtoToTeam(TeamDto teamDto);


    @Mappings({
            @Mapping(source = "id", target = "playerId"),
            @Mapping(source = "name", target = "playerName"),
            @Mapping(source = "goals", target = "playerGoals")
    })
    PlayerDto playerToPlayerDto(Player player);

    @Mappings({
            @Mapping(source = "playerId", target = "id"),
            @Mapping(source = "playerName", target = "name"),
            @Mapping(source = "playerGoals", target = "goals")
    })
    Player playerDtoToPlayer(PlayerDto playerDto);
}
