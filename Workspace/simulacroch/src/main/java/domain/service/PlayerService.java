package domain.service;

import dao.PlayerRepository;
import dao.model.CompletedMission;
import dao.model.Player;
import domain.mappers.MissionDTOMapper;
import domain.mappers.PlayerDTOMapper;
import domain.model.PlayerDTO;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerService {
    private final PlayerRepository playerRepository;
    private final PlayerDTOMapper playerDTOMapper;
    private final MissionDTOMapper missionDTOMapper;

    @Inject
    public PlayerService(PlayerRepository playerRepository, PlayerDTOMapper playerDTOMapper, MissionDTOMapper missionDTOMapper){
        this.playerRepository = playerRepository;
        this.playerDTOMapper = playerDTOMapper;
        this.missionDTOMapper = missionDTOMapper;
    }
    public List<PlayerDTO> getAll(){
        return playerRepository.getAll().stream().map(playerDTOMapper::entityToDto).collect(Collectors.toCollection(ArrayList::new));
    }

    public int add(PlayerDTO playerDTO){
        return playerRepository.add(playerDTOMapper.dtoToEntity(playerDTO));
    }

    public void update(PlayerDTO playerDTO){
        playerRepository.update(playerDTOMapper.dtoToEntity(playerDTO));
    }

    public void delete(int idDelete, boolean answer){
        playerRepository.delete(idDelete, answer);
    }

    public void restorePlayer(int id) {
        playerRepository.restorePlayer(id);
    }

    public void saveToXml(int playerId) {
        playerRepository.saveToXml(playerId);
    }

   
}
