package domain.service;


import dao.CredentialRepository;
import domain.mappers.CredentialDTOMapper;
import domain.model.CredentialDTO;
import jakarta.inject.Inject;

public class CredentialService {
    private final CredentialRepository credentialRepository;
    private final CredentialDTOMapper credentialDTOMapper;


    @Inject
    public CredentialService(CredentialRepository credentialRepository, CredentialDTOMapper credentialDTOMapper){
        this.credentialRepository=credentialRepository;
        this.credentialDTOMapper=credentialDTOMapper;
    }

    public boolean login(CredentialDTO credentialDTO){
        return credentialRepository.getByUsername(credentialDTO.getUsername())!=null&&credentialDTO.getPassword().equals(credentialRepository.getByUsername(credentialDTO.getUsername()).getPassword());
    }
}
