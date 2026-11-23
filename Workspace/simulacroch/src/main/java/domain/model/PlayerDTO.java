package domain.model;

import dao.xml.domain.CompletedMissionXML;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class PlayerDTO {


    int id;
    private String name;
    private String email;
    private int tokens;
    private List<CompletedMissionDTO> missions;
    private List<GameCharacterDTO> characters;


    public void addMission(CompletedMissionDTO mission){
        missions.add(mission);
    }


}

