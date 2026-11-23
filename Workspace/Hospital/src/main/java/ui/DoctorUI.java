package ui;


import common.Constants;
import domain.exceptions.ForeignKeyException;
import domain.model.DoctorDTO;

import domain.service.DoctorService;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DoctorUI {

    private final DoctorService doctorService;
    private final Scanner sc = new Scanner(System.in);


    @Inject
    public DoctorUI(DoctorService doctorService){
        this.doctorService=doctorService;
    }

    public List<DoctorDTO> getAll(){
        return doctorService.getAll();
    }

    public void add(DoctorDTO doctorDTO){
        doctorService.add(doctorDTO);
    }

    public void update(DoctorDTO doctorDTO){doctorService.update(doctorDTO);}

    public void delete(int idDelete){
        try {
            doctorService.delete(idDelete, false);
        }catch (ForeignKeyException e) {
            System.out.println(Constants.ASK_CONFIRMATION_DOCTOR);
            doctorService.delete(idDelete, sc.nextLine().charAt(0)=='Y');
        } catch (Exception e) {
            Logger.getLogger(DoctorUI.class.getName()).log(Level.SEVERE,e.getMessage());
        }
    }
}
