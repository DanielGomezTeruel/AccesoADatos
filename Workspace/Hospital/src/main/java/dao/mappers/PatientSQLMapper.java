package dao.mappers;

import dao.model.Credentials;
import dao.model.Patient;
import dao.repositories.JDBCPatientRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PatientSQLMapper {

    public List<Patient> mapPatients(ResultSet rs) {
        List<Patient> patients = new ArrayList<>();
        try {
            while (rs.next()) {
                Patient patient = new Patient();
                patient.setId(rs.getInt("patient_id")); // also with indexes: rs.getInt(1)
                patient.setName(rs.getString("name")); //also with indexes: rs.getString(2)
                patient.setBirthDate(rs.getDate("date_of_birth").toLocalDate());
                patient.setPhone(rs.getString("phone"));
                patient.setCredential(new Credentials(rs.getInt("patient_id"), null, null));
                patients.add(patient);
            }

        } catch (SQLException ex) {
            Logger.getLogger(JDBCPatientRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return patients;
    }

    public Patient mapPatient(ResultSet rs) {
        Patient patient = null;
        try {
            rs.next();
            patient = new Patient();
            patient.setId(rs.getInt("patient_id")); // also with indexes: rs.getInt(1)
            patient.setName(rs.getString("name")); //also with indexes: rs.getString(2)
            patient.setBirthDate(rs.getDate("date_of_birth").toLocalDate());
            patient.setPhone(rs.getString("phone"));
            patient.setCredential(new Credentials(rs.getInt("patient_id"), null, null));

        } catch (SQLException ex) {
            Logger.getLogger(JDBCPatientRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return patient;
    }
}
