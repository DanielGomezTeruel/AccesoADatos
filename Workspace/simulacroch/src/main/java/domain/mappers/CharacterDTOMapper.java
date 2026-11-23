package domain.mappers;

import dao.model.CompletedMission;
import dao.model.GameCharacter;
import domain.model.CompletedMissionDTO;
import domain.model.GameCharacterDTO;

public class CharacterDTOMapper {

    public GameCharacter dtoToEntity(GameCharacterDTO characterDTO) {
        return new GameCharacter(characterDTO.getId(), characterDTO.getName(), characterDTO.getType(), characterDTO.getLevel(), characterDTO.getPlayer_id());
    }

    public GameCharacterDTO entityToDto(GameCharacter character) {
        return new GameCharacterDTO(character.getId(), character.getName(), character.getType(), character.getLevel(), character.getPlayer_id());
    }
}
