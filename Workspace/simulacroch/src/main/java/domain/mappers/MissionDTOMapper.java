package domain.mappers;


import dao.model.CompletedMission;
import domain.model.CompletedMissionDTO;

public class MissionDTOMapper {

    public CompletedMission dtoToEntity(CompletedMissionDTO missionDTO) {
        return new CompletedMission(missionDTO.getId(), missionDTO.getCharacter_id(), missionDTO.getCharacter());
    }

    public CompletedMissionDTO entityToDto(CompletedMission mission) {
        return new CompletedMissionDTO(mission.getId(), mission.getCharacter_id(), mission.getCharacter());
    }
}
