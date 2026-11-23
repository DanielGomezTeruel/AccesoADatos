package domain.service;

import dao.MedRecordRepository;
import domain.mappers.MedRecordDTOMapper;
import domain.model.MedRecordDTO;
import jakarta.inject.Inject;

import java.util.List;
import java.util.stream.Collectors;

public class MedRecordService {
    private final MedRecordRepository medRecordRepository;
    private final MedRecordDTOMapper medRecordDTOMapper;

    @Inject
    public MedRecordService(MedRecordRepository medRecordRepository, MedRecordDTOMapper medRecordDTOMapper){
        this.medRecordRepository = medRecordRepository;
        this.medRecordDTOMapper = medRecordDTOMapper;
    }

    public List<MedRecordDTO> getAllbyPatientId(int patientId){
        return medRecordRepository.getAllbyPatientId(patientId).stream().map(medRecordDTOMapper::entityToDto).collect(Collectors.toList());
    }

    public void add(MedRecordDTO medRecordDTO){
        medRecordRepository.add(medRecordDTOMapper.dtoToEntity(medRecordDTO));
    }

    public void update(MedRecordDTO medRecordDTO){medRecordRepository.update(medRecordDTOMapper.dtoToEntity(medRecordDTO));}

    public void delete(int idDelete){medRecordRepository.delete(idDelete);}
}
