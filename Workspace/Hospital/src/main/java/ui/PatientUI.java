package ui;

import common.Constants;
import domain.exceptions.DatabaseException;
import domain.exceptions.DuplicatedUsernameException;
import domain.exceptions.ForeignKeyException;
import domain.exceptions.PatientDBException;
import domain.service.PatientService;
import jakarta.inject.Inject;
import domain.model.PatientDTO;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PatientUI {

    private final PatientService patientService;
    private final Scanner sc = new Scanner(System.in);

    @Inject
    public PatientUI(PatientService patientService){
        this.patientService=patientService;
    }



    public List<PatientDTO> getAll(){

        return patientService.getAll();

    }

    public void add(PatientDTO patientDTO){
        int id = 0;
        try {
            id = patientService.add(patientDTO);
        } catch (DuplicatedUsernameException e) {
            System.out.println(Constants.NOMBRE_DUPLICADO);
            String respuesta = sc.nextLine().trim();
            patientDTO.getCredentials().setUsername(respuesta);
            id=patientService.add(patientDTO);
        }catch (Exception e) {
            Logger.getLogger(PatientUI.class.getName()).log(Level.SEVERE,e.getMessage());
        }
    }

    public void update(PatientDTO patientDTO){

        try {
            patientService.update(patientDTO);
        }catch (PatientDBException e){
            Logger.getLogger(PatientUI.class.getName()).log(Level.SEVERE,e.getMessage());
            System.out.println(Constants.ERROR_ANYADIR);
        }catch (DuplicatedUsernameException e) {
            System.out.println(Constants.NOMBRE_DUPLICADO);
            String respuesta = sc.nextLine().trim();
            patientDTO.getCredentials().setUsername(respuesta);
            patientService.update(patientDTO);
        }
    }

    public void delete(int idDelete) {
        try {
            patientService.delete(idDelete, false);
        } catch (ForeignKeyException e) {
            System.out.println(Constants.ASK_CONFIRMATION_MEDRECS);
            patientService.delete(idDelete, sc.nextLine().charAt(0)=='Y');
        } catch (Exception e) {
            Logger.getLogger(PatientUI.class.getName()).log(Level.SEVERE,e.getMessage());
        }
    }

    public void restorePatient(int id) {
        patientService.restorePatient(id);
    }
}
