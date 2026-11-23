package ui;


import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;


public class Exercise1 {

    static void main() {
        SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        PlayerUI playerUI;
        SeContainer container = initializer.initialize();
        playerUI = container.select(PlayerUI.class).get();


        playerUI.saveToXML(2);

    }
}



