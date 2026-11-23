package domain.mappers;


import dao.model.Player;
import domain.model.PlayerDTO;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class PlayerDTOMapper {

    private final MissionDTOMapper missionDTOMapper;
    private final CharacterDTOMapper characterDTOMapper;

    @Inject
    public PlayerDTOMapper(MissionDTOMapper missionDTOMapper, CharacterDTOMapper characterDTOMapper) {
        this.missionDTOMapper = missionDTOMapper;
        this.characterDTOMapper = characterDTOMapper;
    }

    public Player dtoToEntity(PlayerDTO playerDTO) {
        Player player = new Player(playerDTO.getId(), playerDTO.getName(), playerDTO.getEmail(), playerDTO.getTokens(), new ArrayList<>(), new ArrayList<>());
        player.setMissions(playerDTO.getMissions().stream().map(missionDTOMapper::dtoToEntity).collect(Collectors.toCollection(ArrayList::new)));
        player.setCharacters(playerDTO.getCharacters().stream().map(characterDTOMapper::dtoToEntity).collect(Collectors.toCollection(ArrayList::new)));

        return player;
    }

    public PlayerDTO entityToDto(Player player) {
        PlayerDTO playerDTO = new PlayerDTO(player.getId(), player.getName(), player.getEmail(), player.getTokens(), new ArrayList<>(), new ArrayList<>());
        playerDTO.setMissions(player.getMissions().stream().map(missionDTOMapper::entityToDto).collect(Collectors.toCollection(ArrayList::new)));
        playerDTO.setCharacters(player.getCharacters().stream().map(characterDTOMapper::entityToDto).collect(Collectors.toCollection(ArrayList::new)));

        return playerDTO;
    }

}
