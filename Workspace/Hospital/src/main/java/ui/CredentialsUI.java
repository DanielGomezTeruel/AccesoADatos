package ui;

import domain.exceptions.CredentialsDBException;
import domain.model.CredentialDTO;
import domain.service.CredentialService;
import jakarta.inject.Inject;

import java.util.logging.Level;
import java.util.logging.Logger;


public class CredentialsUI {
    private final CredentialService credentialService;

    @Inject
    public CredentialsUI(CredentialService credentialService){
        this.credentialService=credentialService;
    }

    public boolean login(CredentialDTO credentialDTO){
        boolean result=false;
        try {
            result=credentialService.login(credentialDTO);
        } catch (CredentialsDBException e) {
            Logger.getLogger(CredentialsUI.class.getName()).log(Level.SEVERE,e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

}