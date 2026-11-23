package dao.xml.domain;

import dao.xml.mappers.LocalDateAdapter;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@XmlRootElement(name="med_record")
@XmlAccessorType(XmlAccessType.FIELD)
public class MedRecordXML {

    @XmlElement
    private int id;

    @XmlElement
    private int patientID;

    @XmlElement
    private int doctorID;

    @XmlElement
    private String diagnosis;

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate admission_date;

    @XmlElementWrapper(name="medications")
    @XmlElement(name="medication")
    private List<String> medications;
}
