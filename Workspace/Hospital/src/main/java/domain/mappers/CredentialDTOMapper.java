package domain.mappers;


import dao.model.Credentials;
import domain.model.CredentialDTO;

public class CredentialDTOMapper {

    public Credentials dtoToEntity(CredentialDTO credentialDTO) {

        return credentialDTO==null?null:new Credentials(0, credentialDTO.getUsername(),credentialDTO.getPassword());

    }

    public CredentialDTO entityToDto(Credentials credentials) {
        return credentials==null?null:new CredentialDTO(credentials.getUsername(),credentials.getPassword());
    }
}
