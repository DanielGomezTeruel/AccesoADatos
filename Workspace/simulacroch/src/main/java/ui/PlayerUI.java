package ui;

import common.Constants;
import domain.exceptions.DuplicatedUsernameException;
import domain.exceptions.ForeignKeyException;
import domain.exceptions.PlayerDbException;
import domain.model.PlayerDTO;
import domain.service.PlayerService;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PlayerUI {

    private final PlayerService playerService;
    private final Scanner sc = new Scanner(System.in);

    @Inject
    public PlayerUI(PlayerService playerService){
        this.playerService = playerService;
    }



    public List<PlayerDTO> getAll(){
        return playerService.getAll();

    }

    public void add(PlayerDTO playerDTO){
        try {
            playerService.add(playerDTO);
        }catch (Exception e) {
            Logger.getLogger(PlayerUI.class.getName()).log(Level.SEVERE,e.getMessage());
        }
    }

    public void update(PlayerDTO playerDTO){

        try {
            playerService.update(playerDTO);
        }catch (PlayerDbException e){
            Logger.getLogger(PlayerUI.class.getName()).log(Level.SEVERE,e.getMessage());
            System.out.println(Constants.ERROR_ANYADIR);
        }
    }

    public void delete(int idDelete) {
        try {
            playerService.delete(idDelete, false);
        } catch (ForeignKeyException e) {
            System.out.println(Constants.ASK_CONFIRMATION_MEDRECS);
            playerService.delete(idDelete, sc.nextLine().charAt(0)=='Y');
        } catch (Exception e) {
            Logger.getLogger(PlayerUI.class.getName()).log(Level.SEVERE,e.getMessage());
        }
    }

    public void restorePatient(int id) {
        playerService.restorePlayer(id);
    }

    public void saveToXML(int playerId) {
        playerService.saveToXml(playerId);
    }


}
