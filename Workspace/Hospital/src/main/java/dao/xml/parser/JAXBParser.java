/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.xml.parser;


import common.Configuration;
import dao.repositories.JDBCPatientRepository;
import dao.xml.domain.PatientXML;
import dao.xml.domain.PatientsXML;
import jakarta.inject.Inject;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lucia
 */
@Slf4j
public class JAXBParser {

    public void addToXML(PatientXML patientData) {
        try {
            JAXBContext context = JAXBContext.newInstance(PatientsXML.class);

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            Unmarshaller unmarshaller = context.createUnmarshaller();

            Path xmlFile = Paths
                    .get(Configuration.getInstance().getProperty("xmlPatientsPath"));

            PatientsXML patients;

            if (Files.exists(xmlFile) && Files.size(xmlFile) > 0) {
                patients = (PatientsXML) unmarshaller.unmarshal(
                        Files.newInputStream(xmlFile)
                );
            } else {
                patients = new PatientsXML();
            }

            patients.addPatient(patientData);

            marshaller.marshal(patients, Files.newOutputStream(xmlFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public PatientsXML getXMLData() {
        PatientsXML patients = null;
        try {
            JAXBContext context = JAXBContext.newInstance(PatientsXML.class);

            Unmarshaller unmarshaller = context.createUnmarshaller();

            Path xmlFile = Paths
                    .get(Configuration.getInstance().getProperty("xmlPatientsPath"));

            patients = (PatientsXML) unmarshaller.unmarshal(Files.newInputStream(xmlFile));
        } catch (Exception e) {
            Logger.getLogger(JAXBParser.class.getName()).log(Level.SEVERE, e.getMessage());
        }
        return patients;
    }


}
  
