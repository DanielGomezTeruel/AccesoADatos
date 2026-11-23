package domain.mappers;


import dao.model.Credentials;
import dao.model.Patient;
import domain.model.CredentialDTO;
import domain.model.PatientDTO;

public class PatientDTOMapper {
    public Patient dtoToEntity(PatientDTO patientDTO) {

        return new Patient(patientDTO.getId(),patientDTO.getName(),patientDTO.getBirthDate(),patientDTO.getPhone(),new Credentials(patientDTO.getId(), patientDTO.getCredentials().getUsername(), patientDTO.getCredentials().getPassword()));

    }

    public PatientDTO entityToDto(Patient patient) {
        return new PatientDTO(patient.getId(),patient.getName(),
                patient.getBirthDate(),patient.getPhone(),0,new CredentialDTO(patient.getCredential().getUsername(), patient.getCredential().getPassword()));
    }

}
