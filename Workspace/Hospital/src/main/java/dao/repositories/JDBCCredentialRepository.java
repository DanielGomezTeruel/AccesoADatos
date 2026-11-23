package dao.repositories;

import dao.CredentialRepository;
import dao.common.DBConnection;
import dao.common.SQLQueries;
import dao.mappers.CredentialsSQLMapper;
import dao.model.Credentials;
import domain.exceptions.CredentialsDBException;
import jakarta.inject.Inject;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class JDBCCredentialRepository implements CredentialRepository {


    private final DBConnection db;
    private final CredentialsSQLMapper credentialsSQLMapper;

    @Inject
    public JDBCCredentialRepository(DBConnection db, CredentialsSQLMapper credentialsSQLMapper) {
        this.db = db;
        this.credentialsSQLMapper = credentialsSQLMapper;
    }

    @Override
    public Credentials getByUsername(String username){
        Credentials credential;
        try (Connection con = db.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(SQLQueries.SELECT_getCredentials_QUERY)){
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            credential=credentialsSQLMapper.mapCredentials(rs).getFirst();

        }catch (SQLException ex) {
            Logger.getLogger(JDBCPatientRepository.class.getName()).log(Level.SEVERE, null, ex);
            throw new CredentialsDBException();
        }
        return credential;
    }


    @Override
    public int add(Credentials credentials) {
        return 0;
    }

    @Override
    public void update(Credentials credentialsDatabase) {

    }

    @Override
    public void delete(int idDelete) {

    }
}
