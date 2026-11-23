package dao.xml.domain;

import dao.xml.mappers.LocalDateAdapter;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor


@XmlRootElement(name="appointment")
@XmlAccessorType(XmlAccessType.FIELD)
public class AppointmentXML {

    @XmlElement(name="id")
    private int appointment_id;

    @XmlElement
    private int patient_id;

    @XmlElement
    private int doctor_id;

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate appointment_date;

}
