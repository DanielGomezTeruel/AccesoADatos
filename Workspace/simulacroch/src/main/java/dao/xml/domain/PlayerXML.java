package dao.xml.domain;

import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@XmlRootElement(name="player")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlayerXML {

    @XmlAttribute
    int id;

    @XmlElement(name = "cMission")
    private List<CompletedMissionXML> missions = new ArrayList<>();

    public void addMission(CompletedMissionXML mission){
        missions.add(mission);
    }
}
