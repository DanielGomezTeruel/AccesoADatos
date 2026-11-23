package dao.repositories;

import dao.PatientRepository;
import dao.common.DBConnection;
import dao.common.SQLQueries;
import dao.mappers.PatientSQLMapper;
import dao.model.Patient;
import dao.xml.domain.*;
import dao.xml.mappers.XMLMapper;
import dao.xml.parser.JAXBParser;
import domain.exceptions.DatabaseException;
import domain.exceptions.DuplicatedUsernameException;
import domain.exceptions.ForeignKeyException;
import domain.exceptions.PatientDBException;
import jakarta.inject.Inject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JDBCPatientRepository implements PatientRepository {
    private final DBConnection db;
    private final PatientSQLMapper patientSQLMapper;
    private final JAXBParser jaxbParser;
    private final XMLMapper xmlMapper;
    List<Patient> patients = new ArrayList<>();


    @Inject
    public JDBCPatientRepository(DBConnection db, PatientSQLMapper patientSQLMapper, JAXBParser jaxbParser, XMLMapper xmlMapper) {
        this.db = db;
        this.patientSQLMapper = patientSQLMapper;
        this.jaxbParser = jaxbParser;
        this.xmlMapper = xmlMapper;
    }

    @Override
    public List<Patient> getAll() {
        try (Connection con = db.getConnection();
             Statement statement = con.createStatement()) {

            ResultSet rs = statement.executeQuery(SQLQueries.SELECT_patients_QUERY);
            patients = patientSQLMapper.mapPatients(rs);

        } catch (SQLException ex) {
            Logger.getLogger(JDBCPatientRepository.class.getName()).log(Level.SEVERE, null, ex);
            throw new PatientDBException();
        }
        return patients;
    }

    @Override
    public int add(Patient patient) {
        int id = 0;
        try (Connection con = db.getConnection()) {
            try (PreparedStatement preparedStatement1 = con.prepareStatement(SQLQueries.INSERT_patient, Statement.RETURN_GENERATED_KEYS);
                 PreparedStatement preparedStatement2 = con.prepareStatement(SQLQueries.INSERT_patient_credentials)) {

                con.setAutoCommit(false);

                preparedStatement1.setString(1, patient.getName());
                preparedStatement1.setDate(2, Date.valueOf(patient.getBirthDate()));
                preparedStatement1.setString(3, patient.getPhone());

                int rowsAffected = preparedStatement1.executeUpdate();
                if (rowsAffected > 0) {
                    ResultSet rs = preparedStatement1.getGeneratedKeys();
                    if (rs.next()) {
                        id = rs.getInt(1);
                        preparedStatement2.setString(1, patient.getCredential().getUsername());
                        preparedStatement2.setString(2, patient.getCredential().getPassword());
                        preparedStatement2.setInt(3, id);
                        preparedStatement2.executeUpdate();
                        con.commit();
                    }
                } else {
                    throw new DuplicatedUsernameException();
                }


            } catch (SQLException sqle) {
                Logger.getLogger(JDBCPatientRepository.class.getName()).log(Level.SEVERE, null, sqle);
                try {
                    con.rollback();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } finally {
                con.setAutoCommit(true);
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBCPatientRepository.class.getName()).log(Level.SEVERE, null, ex);
            throw new PatientDBException();
        }
        return id;
    }

    @Override
    public void update(Patient patient) {
        int id = 0;
        try (Connection con = db.getConnection()) {
            try (PreparedStatement preparedStatement1 = con.prepareStatement(SQLQueries.UPDATE_PATIENT, Statement.RETURN_GENERATED_KEYS);
                 PreparedStatement preparedStatement2 = con.prepareStatement(SQLQueries.UPDATE_PATIENT_CREDENTIALS)) {

                con.setAutoCommit(false);

                preparedStatement1.setString(1, patient.getName());
                preparedStatement1.setDate(2, Date.valueOf(patient.getBirthDate()));
                preparedStatement1.setString(3, patient.getPhone());
                preparedStatement1.setInt(4, patient.getId());

                int rowsAffected = preparedStatement1.executeUpdate();
                if (rowsAffected > 0) {
                    ResultSet rs = preparedStatement1.getGeneratedKeys();
                    if (rs.next()) {
                        id = rs.getInt(1);
                        preparedStatement2.setString(1, patient.getCredential().getUsername());
                        preparedStatement2.setString(2, patient.getCredential().getPassword());
                        preparedStatement2.setInt(3, id);
                        preparedStatement2.executeUpdate();
                        con.commit();
                    }
                } else {
                    throw new DatabaseException("User doesn't exist");
                }

            } catch (SQLException sqle) {
                Logger.getLogger(JDBCPatientRepository.class.getName()).log(Level.SEVERE, null, sqle);
                try {
                    con.rollback();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } finally {
                con.setAutoCommit(true);
            }

        } catch (SQLException ex) {
            Logger.getLogger(JDBCPatientRepository.class.getName()).log(Level.SEVERE, null, ex);
            throw new PatientDBException();
        }
    }

    @Override
    public void delete(int idDelete, boolean answer) {
        try (Connection con = db.getConnection()) {

            con.setAutoCommit(false);

            try (PreparedStatement delete_cred = con.prepareStatement(SQLQueries.DELETE_patient_cred);
                 PreparedStatement delete_appointments = con.prepareStatement(SQLQueries.DELETE_patient_appointments);
                 PreparedStatement delete_payments = con.prepareStatement(SQLQueries.DELETE_patient_payments);
                 PreparedStatement delete_medications = con.prepareStatement(SQLQueries.DELETE_patient_medications);
                 PreparedStatement delete_medrecords = con.prepareStatement(SQLQueries.DELETE_patient_medRecords);
                 PreparedStatement delete_patient = con.prepareStatement(SQLQueries.DELETE_patient)) {


                saveToXML(idDelete);


                delete_cred.setInt(1, idDelete);
                delete_appointments.setInt(1, idDelete);
                delete_payments.setInt(1, idDelete);

                if (answer) {
                    delete_medications.setInt(1, idDelete);
                    delete_medrecords.setInt(1, idDelete);

                    delete_medications.executeUpdate();
                    delete_medrecords.executeUpdate();
                }
                delete_patient.setInt(1, idDelete);

                delete_cred.executeUpdate();
                delete_appointments.executeUpdate();
                delete_payments.executeUpdate();
                delete_patient.executeUpdate();

                con.commit();

            } catch (SQLIntegrityConstraintViolationException e) {
                con.rollback();
                throw new ForeignKeyException("Patient deletion incomplete");

            } catch (SQLException sqle) {
                con.rollback();
            } finally {
                con.setAutoCommit(true);
            }

        } catch (SQLException sqle) {
            Logger.getLogger(JDBCPatientRepository.class.getName()).log(Level.SEVERE, null, sqle);
        }
    }

    private void saveToXML(int idDelete) {
        try (
                Connection con = db.getConnection();
                PreparedStatement get_appointments = con.prepareStatement(SQLQueries.SELECT_appointmentsById);
                PreparedStatement get_payments = con.prepareStatement(SQLQueries.SELECT_paymentsById);
                PreparedStatement get_medications = con.prepareStatement(SQLQueries.SELECT_getMedicationsByMedRecord_QUERY);
                PreparedStatement get_medrecords = con.prepareStatement(SQLQueries.SELECT_getMedRecordsById_QUERY);
                PreparedStatement get_patient = con.prepareStatement(SQLQueries.SELECT_patientById)
        ) {
            get_patient.setInt(1, idDelete);
            get_appointments.setInt(1, idDelete);
            get_payments.setInt(1, idDelete);
            get_medrecords.setInt(1, idDelete);


            Patient patient = patientSQLMapper.mapPatient(get_patient.executeQuery());

            List<AppointmentXML> appointments = xmlMapper.mapAppointments(get_appointments.executeQuery());

            List<PaymentXML> payments = xmlMapper.mapPayments(get_payments.executeQuery());

            List<MedRecordXML> records = xmlMapper.mapMedRecord(get_medrecords.executeQuery());


            records.forEach(record ->
            {
                try {
                    get_medications.setInt(1, record.getId());
                    record.setMedications(xmlMapper.mapMeds(get_medications.executeQuery()));

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });


            jaxbParser.addToXML(new PatientXML(idDelete, patient.getName(), patient.getBirthDate(), patient.getPhone(), appointments, payments, records));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public Patient get(int id) {
        return getAll().stream().filter(patient -> patient.getId() == id).findFirst().orElse(null);
    }

    public void restorePatient(int idPatient) {
        PatientsXML patients = jaxbParser.getXMLData();
        PatientXML patient = patients.getPatients().stream().filter(p -> p.getId() == idPatient).toList().getFirst();
        if (patient != null) {
            try (Connection con = db.getConnection()) {

                con.setAutoCommit(false);

                try (
                        PreparedStatement insert_patient =
                                con.prepareStatement(SQLQueries.INSERT_patient, Statement.RETURN_GENERATED_KEYS);

                        PreparedStatement insert_appointment =
                                con.prepareStatement(SQLQueries.INSERT_patient_appointment);

                        PreparedStatement insert_payment =
                                con.prepareStatement(SQLQueries.INSERT_patient_payment);

                        PreparedStatement insert_medRecord =
                                con.prepareStatement(SQLQueries.INSERT_medRecord, Statement.RETURN_GENERATED_KEYS);

                        PreparedStatement insert_medication =
                                con.prepareStatement(SQLQueries.INSERT_medications);

                ) {
                    int id;

                    insert_patient.setString(1, patient.getName());
                    insert_patient.setDate(2, Date.valueOf(patient.getBirthDate()));
                    insert_patient.setString(3, patient.getPhone());

                    int rowsAffected = insert_patient.executeUpdate();
                    if (rowsAffected > 0) {
                        ResultSet rs = insert_patient.getGeneratedKeys();
                        if (rs.next()) {
                            id = rs.getInt(1);
                        } else {
                            id = 0;
                        }
                    } else {
                        id = 0;
                        throw new DuplicatedUsernameException();
                    }

                    List<AppointmentXML> appointments = patient.getAppointments();

                    appointments.forEach(appointment -> {
                        try {
                            insert_appointment.setInt(1, id);
                            insert_appointment.setInt(2, appointment.getDoctor_id());
                            insert_appointment.setDate(3, Date.valueOf(appointment.getAppointment_date()));
                            insert_appointment.executeUpdate();
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    });

                    List<PaymentXML> payments = patient.getPayments();

                    payments.forEach(payment -> {
                        try {
                            insert_payment.setInt(1, id);
                            insert_payment.setDouble(2, payment.getAmount());
                            insert_payment.setDate(3, Date.valueOf(payment.getPayment_date()));
                            insert_payment.executeUpdate();
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    });

                    List<MedRecordXML> medRecords = patient.getMed_records();
                    medRecords.forEach(record -> {
                        try {
                            insert_medRecord.setInt(1, id);
                            insert_medRecord.setInt(2, record.getDoctorID());
                            insert_medRecord.setString(3, record.getDiagnosis());
                            insert_medRecord.setDate(4, Date.valueOf(record.getAdmission_date()));

                            int rowsAffected2 = insert_medRecord.executeUpdate();
                            if (rowsAffected2 > 0) {
                                ResultSet rs = insert_medRecord.getGeneratedKeys();
                                if (rs.next()) {
                                    int recordId = rs.getInt(1);
                                    for (int i = 0; i < record.getMedications().size(); i++) {
                                        insert_medication.setInt(1, recordId);
                                        insert_medication.setString(2, record.getMedications().get(i));
                                        insert_medication.executeUpdate();
                                    }
                                }
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    });

                    con.commit();

                } catch (SQLException e) {
                    con.rollback();
                    throw e;

                } finally {
                    con.setAutoCommit(true);
                }

            } catch (SQLException e) {
                Logger.getLogger(JDBCPatientRepository.class.getName())
                        .log(Level.SEVERE, null, e);

                throw new PatientDBException();
            }
        }
    }
}
