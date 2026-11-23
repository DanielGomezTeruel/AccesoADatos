package domain.mappers;

import dao.model.Credentials;
import dao.model.Doctor;
import domain.model.CredentialDTO;
import domain.model.DoctorDTO;

public class DoctorDTOMapper {
    public Doctor dtoToEntity(DoctorDTO doctorDTO) {

        return new Doctor(doctorDTO.getId(),doctorDTO.getName(),null, null, new Credentials(doctorDTO.getId(), doctorDTO.getCredentials().getUsername(),doctorDTO.getCredentials().getPassword()));

    }

    public DoctorDTO entityToDto(Doctor doctor) {
        return new DoctorDTO(doctor.getId(),doctor.getName(), new CredentialDTO(doctor.getCredentials().getUsername(), doctor.getCredentials().getPassword()));
    }
}
