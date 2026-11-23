package dao;

import dao.model.Credentials;


public interface CredentialRepository {
    Credentials getByUsername(String username);

    int add(Credentials credentials);

    void update(Credentials credentialsDatabase);

    void delete(int idDelete);
}
