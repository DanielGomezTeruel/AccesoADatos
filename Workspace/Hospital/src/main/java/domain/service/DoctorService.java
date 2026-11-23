package domain.service;

import dao.DoctorRepository;
import domain.mappers.DoctorDTOMapper;
import domain.model.DoctorDTO;
import jakarta.inject.Inject;


import java.util.List;
import java.util.stream.Collectors;

public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final DoctorDTOMapper doctorDTOMapper;

    @Inject
    public DoctorService(DoctorRepository doctorRepository, DoctorDTOMapper doctorDTOMapper){
        this.doctorRepository = doctorRepository;
        this.doctorDTOMapper = doctorDTOMapper;
    }

    public List<DoctorDTO> getAll(){
        return doctorRepository.getAll().stream().map(doctorDTOMapper::entityToDto).collect(Collectors.toList());
    }

    public void add(DoctorDTO doctorDTO){
        doctorRepository.add(doctorDTOMapper.dtoToEntity(doctorDTO));
    }

    public void update(DoctorDTO doctorDTO){doctorRepository.update(doctorDTOMapper.dtoToEntity(doctorDTO));}

    public void delete(int idDelete, boolean answer){doctorRepository.delete(idDelete, answer);}
}
