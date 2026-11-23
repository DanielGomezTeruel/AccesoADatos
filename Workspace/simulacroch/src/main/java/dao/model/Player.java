package dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {
        private int id;
        private String name;
        private String email;
        private int tokens;
        private List<CompletedMission> missions;
        private List<GameCharacter> characters;
}

