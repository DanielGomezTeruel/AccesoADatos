package domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameCharacterDTO {
    private int id;
    private String name;
    private String type;
    private int level;
    private int player_id;
}
