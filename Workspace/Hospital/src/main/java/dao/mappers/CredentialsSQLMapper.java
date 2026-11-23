package dao.mappers;

import dao.model.Credentials;
import dao.repositories.JDBCPatientRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CredentialsSQLMapper {

    public List<Credentials> mapCredentials(ResultSet rs) {
        List<Credentials> credentials = new ArrayList<>();
        try {
            while (rs.next()) {
                Credentials credential = new Credentials();
                credential.setUsername(rs.getString("username")); // also with indexes: rs.getInt(1)
                credential.setPassword(rs.getString("password")); //also with indexes: rs.getString(2)
                credentials.add(credential);
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBCPatientRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return credentials;
    }
}
