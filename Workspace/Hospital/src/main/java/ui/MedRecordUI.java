package ui;


import domain.model.MedRecordDTO;
import domain.service.MedRecordService;
import jakarta.inject.Inject;

import java.util.List;

public class MedRecordUI {

    private final MedRecordService medRecordService;


    @Inject
    public MedRecordUI(MedRecordService medRecordService){
        this.medRecordService=medRecordService;
    }

    public List<MedRecordDTO> getAllbyPatientId(int patientId){
        return medRecordService.getAllbyPatientId(patientId);
    }

    public void add(MedRecordDTO medRecordDTO){
        medRecordService.add(medRecordDTO);
    }

    public void update(MedRecordDTO medRecordDTO){medRecordService.update(medRecordDTO);}

    public void delete(int idDelete){medRecordService.delete(idDelete);}
}
