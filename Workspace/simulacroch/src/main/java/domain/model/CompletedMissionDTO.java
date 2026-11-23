package domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CompletedMissionDTO {

    private int id;
    private int character_id;
    private String character;

}
