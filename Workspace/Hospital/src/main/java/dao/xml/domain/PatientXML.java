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

@XmlRootElement(name="employee")
@XmlAccessorType(XmlAccessType.FIELD)
public class PatientXML {

    @XmlElement
    private int id;

    @XmlElement
    private String name;

    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDate birthDate;

    @XmlElement
    private String phone;

    @XmlElementWrapper(name="appointments")
    @XmlElement(name="appointment")
    private List<AppointmentXML> appointments;

    @XmlElementWrapper(name="payments")
    @XmlElement(name="payment")
    private List<PaymentXML> payments;

    @XmlElementWrapper(name="med_records")
    @XmlElement(name="med_record")
    private List<MedRecordXML> med_records;

}
