package dao.xml.domain;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@XmlRootElement(name = "cMission")
@XmlAccessorType(XmlAccessType.FIELD)
public class CompletedMissionXML {

    @XmlElement
    private int id;

    @XmlElement
    private String character;


}
