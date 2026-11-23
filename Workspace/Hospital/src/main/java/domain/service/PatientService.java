package domain.service;

import dao.PatientRepository;
import domain.exceptions.DatabaseException;
import domain.exceptions.ForeignKeyException;
import domain.mappers.PatientDTOMapper;
import domain.model.PatientDTO;
import jakarta.inject.Inject;

import java.util.List;
import java.util.stream.Collectors;

public class PatientService {
    private final PatientRepository patientRepository;
    private final PatientDTOMapper patientDTOMapper;

    @Inject
    public PatientService(PatientRepository patientRepository, PatientDTOMapper patientDTOMapper){
        this.patientRepository = patientRepository;
        this.patientDTOMapper = patientDTOMapper;
    }
    public List<PatientDTO> getAll(){
        return patientRepository.getAll().stream().map(patientDTOMapper::entityToDto).collect(Collectors.toList());
    }

    public int add(PatientDTO patientDTO){
        return patientRepository.add(patientDTOMapper.dtoToEntity(patientDTO));
    }

    public void update(PatientDTO patientDTO){
        patientRepository.update(patientDTOMapper.dtoToEntity(patientDTO));
    }

    public void delete(int idDelete, boolean answer){
        patientRepository.delete(idDelete, answer);
    }

    public void restorePatient(int id) {
        patientRepository.restorePatient(id);
    }
}
