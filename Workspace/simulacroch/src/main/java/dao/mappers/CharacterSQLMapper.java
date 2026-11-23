package dao.mappers;

import dao.model.CompletedMission;
import dao.model.GameCharacter;
import dao.repositories.JDBCPlayerRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CharacterSQLMapper {

    public List<GameCharacter> mapCharacters(ResultSet rs) {
        List<GameCharacter> characters = new ArrayList<>();
        try {
            while (rs.next()) {
                GameCharacter character = new GameCharacter();
                character.setId(rs.getInt("Character_ID"));
                character.setName(rs.getString("Name"));
                character.setType(rs.getString("Type"));
                character.setLevel(rs.getInt("Level"));
                character.setPlayer_id(rs.getInt("Player_ID"));
                characters.add(character);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CharacterSQLMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return characters;
    }

    public GameCharacter mapCharacter(ResultSet rs) {
        GameCharacter character = null;
        try {
            rs.next();
            character = new GameCharacter();
            character.setId(rs.getInt("Character_ID"));
            character.setName(rs.getString("Name"));
            character.setType(rs.getString("Type"));
            character.setLevel(rs.getInt("Level"));
            character.setPlayer_id(rs.getInt("Player_ID"));


        } catch (SQLException ex) {
            Logger.getLogger(CharacterSQLMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return character;
    }
}
