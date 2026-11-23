/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.xml.parser;


import common.Configuration;
import dao.xml.domain.CompletedMissionXML;
import dao.xml.domain.CompletedMissionsXML;
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

    public void addToXML(int player_id, CompletedMissionXML completedMissionData) {
        try {
            JAXBContext context = JAXBContext.newInstance(CompletedMissionsXML.class);

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            Unmarshaller unmarshaller = context.createUnmarshaller();

            Path xmlFile = Paths
                    .get(Configuration.getInstance().getProperty("xmlMissionsPath"));

            CompletedMissionsXML completedMissions;

            if (Files.exists(xmlFile) && Files.size(xmlFile) > 0) {
                completedMissions = (CompletedMissionsXML) unmarshaller.unmarshal(
                        Files.newInputStream(xmlFile)
                );
                System.out.println(completedMissions);
            } else {
                completedMissions = new CompletedMissionsXML();
            }

            completedMissions.addPlayer(player_id);

            completedMissions.addMission(player_id, completedMissionData);

            marshaller.marshal(completedMissions, Files.newOutputStream(xmlFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public CompletedMissionsXML getXMLData() {
        CompletedMissionsXML missions = null;
        try {
            JAXBContext context = JAXBContext.newInstance(CompletedMissionsXML.class);

            Unmarshaller unmarshaller = context.createUnmarshaller();

            Path xmlFile = Paths
                    .get(Configuration.getInstance().getProperty("xmlMissionsPath"));

            missions = (CompletedMissionsXML) unmarshaller.unmarshal(Files.newInputStream(xmlFile));
        } catch (Exception e) {
            Logger.getLogger(JAXBParser.class.getName()).log(Level.SEVERE, e.getMessage());
        }
        return missions;
    }


}
  
