package dao.xml.domain;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@XmlRootElement(name = "completedMissions")
@XmlAccessorType(XmlAccessType.FIELD)
public class CompletedMissionsXML {

    @XmlElement(name="player")
    private List<PlayerXML> players;

    public void addPlayer(int player_id) {
        if (players == null) {
            players = new ArrayList<>();
        }
        if (players.stream().filter(playerXML -> playerXML.getId() == player_id).toList().isEmpty()) {
            players.add(new PlayerXML(player_id, new ArrayList<>()));
        }

    }

    public void addMission(int player_id, CompletedMissionXML mission) {
        players.stream()
                .filter(playerXML -> playerXML.getId() == player_id)
                .toList()
                .getFirst()
                .getMissions()
                .add(mission);
    }

}
