package ui;


import common.Constants;
import dao.xml.parser.JAXBParser;
import domain.exceptions.PatientDBException;
import domain.model.CredentialDTO;
import domain.model.MedRecordDTO;
import domain.model.PatientDTO;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    static void main() {
        SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        final SeContainer container = initializer.initialize();

        PatientUI patientUI = container.select(PatientUI.class).get();
        CredentialsUI credentialsUI = container.select(CredentialsUI.class).get();
        DoctorUI doctorUI = container.select(DoctorUI.class).get();
        MedRecordUI medRecordUI = container.select(MedRecordUI.class).get();
        JAXBParser jaxbParser = container.select(JAXBParser.class).get();


        Scanner sc = new Scanner(System.in);
        for (boolean bucleCred = true; bucleCred; ) {
            System.out.println(Constants.USERNAME);
            String username = sc.nextLine();
            System.out.println(Constants.PASSWORD);
            String password = sc.nextLine();
            CredentialDTO tempCred = new CredentialDTO(username, password);
            if (credentialsUI.login(tempCred)) {
                System.out.println(Constants.WELCOME + username);
                bucleCred = false;
            } else {
                System.out.println(Constants.WRONG_CREDENTIALS);
            }
        }
            for (boolean bucle = true; bucle; ) {
                IO.println(Constants.MAIN_MENU);
                switch (sc.nextInt()) {
                    case 1:
                        try {
                            IO.println(patientUI.getAll());
                        }catch(PatientDBException e){
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE,e.getMessage());
                            e.printStackTrace();
                        }
                        break;
                    case 2:
                        patientUI.add(new PatientDTO(0, "e", LocalDate.parse("2000-01-01"), "0000000", 0, new CredentialDTO("e", "e")));
                        break;
                    case 3:
                        patientUI.update(new PatientDTO(1,"oscar",LocalDate.parse("2000-03-04"),"69696969",0,new CredentialDTO("a", "a")));
                        break;
                    case 4:
                        System.out.println(Constants.ID);
                        patientUI.delete(sc.nextInt());
                        break;
                    case 5:
                        IO.println(doctorUI.getAll());
                        break;
                    case 6:
                        System.out.println(Constants.ID);
                        IO.println(medRecordUI.getAllbyPatientId(sc.nextInt()));
                        break;
                    case 7:
                        medRecordUI.add(new MedRecordDTO(1, "desc", "2025-12-10", 1, 2, List.of("paracetamol")));
                        break;
                    case 8:
                        medRecordUI.update(new MedRecordDTO(6, "description", "2024-12-11", 1, 2, List.of()));
                        break;
                    case 9:
                        System.out.println(Constants.ID);
                        medRecordUI.delete(sc.nextInt());
                        break;
                    case 10:
                        System.out.println(jaxbParser.getXMLData());
                        break;
                    case 11:
                        System.out.println(Constants.ID);
                        patientUI.restorePatient(sc.nextInt());
                        break;
                    case 12:
                        bucle = false;
                        break;
                }
            }
        }
    }



