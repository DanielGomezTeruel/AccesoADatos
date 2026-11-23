package dao.mappers;

import dao.model.CompletedMission;
import dao.model.Player;
import dao.repositories.JDBCPlayerRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MissionSQLMapper {

    public List<CompletedMission> mapMissions(ResultSet rs) {
        List<CompletedMission> missions = new ArrayList<>();
        try {
            while (rs.next()) {
                CompletedMission mission = new CompletedMission();
                mission.setId(rs.getInt("mission_id"));
                mission.setCharacter_id(rs.getInt("character_id"));
                mission.setCharacter(rs.getString("name"));
                missions.add(mission);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MissionSQLMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return missions;
    }

    public CompletedMission mapMission(ResultSet rs) {
        CompletedMission mission = null;
        try {
            rs.next();
            mission = new CompletedMission();
            mission.setId(rs.getInt("mission_id"));
            mission.setCharacter_id(rs.getInt("character_id"));
            mission.setCharacter(rs.getString("name"));

        } catch (SQLException ex) {
            Logger.getLogger(MissionSQLMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mission;
    }
}
