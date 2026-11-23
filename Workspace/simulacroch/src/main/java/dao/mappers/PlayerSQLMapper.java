package dao.mappers;

import dao.model.Player;
import dao.repositories.JDBCPlayerRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlayerSQLMapper {

    public List<Player> mapPlayers(ResultSet rs) {
        List<Player> players = new ArrayList<>();
        try {
            while (rs.next()) {
                Player player = new Player();
                player.setId(rs.getInt("patient_id")); // also with indexes: rs.getInt(1)
                player.setName(rs.getString("name")); //also with indexes: rs.getString(2)
                players.add(player);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PlayerSQLMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return players;
    }

    public Player mapPlayer(ResultSet rs) {
        Player player = null;
        try {
            rs.next();
            player = new Player();
            player.setId(rs.getInt("patient_id"));
            player.setName(rs.getString("name"));

        } catch (SQLException ex) {
            Logger.getLogger(PlayerSQLMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return player;
    }
}
