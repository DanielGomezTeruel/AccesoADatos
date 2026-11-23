package dao.xml.domain;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PatientsXML {

    @XmlElement(name="patient")
    private List<PatientXML> patients = new ArrayList<>();

    public void addPatient(PatientXML patient) {
        patients.add(patient);
    }
}
