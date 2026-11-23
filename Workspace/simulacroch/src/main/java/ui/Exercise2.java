package ui;

import domain.model.GameCharacterDTO;
import domain.model.PlayerDTO;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

import java.util.ArrayList;
import java.util.List;

public class Exercise2 {

    static void main() {
        SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        PlayerUI playerUI;
        SeContainer container = initializer.initialize();
        playerUI = container.select(PlayerUI.class).get();

        playerUI.add(new PlayerDTO(0, "Anabel", "anabel@whatever.com", 100, new ArrayList<>(), new ArrayList<>(List.of(new GameCharacterDTO(0, "Mr. White", "type", 1, 0), new GameCharacterDTO(0, "Mr. Black", "type", 1, 0)))));


    }
}
