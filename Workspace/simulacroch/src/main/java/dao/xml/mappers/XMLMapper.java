package dao.xml.mappers;

import dao.xml.domain.CompletedMissionXML;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class XMLMapper {

    public List<CompletedMissionXML> mapMissions(ResultSet rs) {
        List<CompletedMissionXML> missions = new ArrayList<>();
        try {
            while (rs.next()) {
                CompletedMissionXML mission=new CompletedMissionXML();
                mission.setId(rs.getInt("Mission_ID"));
                mission.setCharacter(rs.getString("Name"));
                missions.add(mission);
            }

        } catch (SQLException ex) {
            Logger.getLogger(XMLMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return missions;

    }


}
