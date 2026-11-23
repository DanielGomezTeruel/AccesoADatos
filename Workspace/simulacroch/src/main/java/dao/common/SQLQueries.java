package dao.common;

public class SQLQueries {

    public static final String SELECT_patients_QUERY = "select * from patients";
    public static final String SELECT_doctors_QUERY = "select * from doctors";
    public static final String SELECT_getCredentials_QUERY = "select * from user_login where username= ?";
    public static final String SELECT_getMedRecordsById_QUERY = "select * from medical_records where patient_id= ?";
    public static final String SELECT_getMedicationsByMedRecord_QUERY = "select * from prescribed_medications where record_id= ?";
    public static final String SELECT_appointmentsById = "select * from appointments where patient_id=?";
    public static final String SELECT_paymentsById = "select * from patient_payments where patient_id=?";
    public static final String SELECT_patientById = "select * from patients where patient_id=?";
    public static final String SELECT_completedMissionsById = "select c.Mission_ID, ch.Name from completed_missions c join characters ch on c.Character_ID=ch.Character_ID where ch.Player_ID = ?";



    public static final String INSERT_PLAYER = "INSERT INTO players (Player_ID, Name, Email, Tokens) VALUES (null, ?, ?, ?)";
    public static final String INSERT_PLAYER_CHARACTERS = "INSERT INTO characters (Character_ID, Name, Type, Level, Player_ID) VALUES (null, ?, ?, ?, ?)";
    public static final String INSERT_PLAYER_COMPLETED_MISSIONS = "INSERT INTO completed_missions (Mission_ID, Character_ID, Completion_Date) VALUES (?, ?, ?)";


    public static final String INSERT_doctor = "INSERT INTO doctors (doctor_id, name, specialization, phone) VALUES (null, ?, ?, ?)";
    public static final String INSERT_doctor_credentials = "INSERT INTO user_login (username, password, doctor_id) VALUES (?, ?, ?)";
    public static final String INSERT_medRecord = "INSERT INTO medical_records (record_id, patient_id, doctor_id, diagnosis, admission_date) VALUES (null, ?, ?, ?, ?)";
    public static final String INSERT_medications = "INSERT INTO prescribed_medications (prescription_id, record_id, medication_name) VALUES (null, ?, ?)";
    public static final String INSERT_patient_appointment = "INSERT INTO appointments (appointment_id, patient_id, doctor_id, appointment_date) VALUES (null, ?, ?, ?)";
    public static final String INSERT_patient_payment = "INSERT INTO patient_payments (payment_id, patient_id, amount, payment_date) VALUES (null, ?, ?, ?)";



    public static final String DELETE_patient = "delete from patients where patient_id = ?";
    public static final String DELETE_patient_cred = "delete from user_login where patient_id = ?";
    public static final String DELETE_patient_medRecords = "delete from medical_records where patient_id = ?";
    public static final String DELETE_patient_medications = "delete from prescribed_medications where record_id in (select record_id from medical_records where patient_id = ?)";
    public static final String DELETE_patient_appointments = "delete from appointments where patient_id = ?";
    public static final String DELETE_patient_payments = "delete from patient_payments where patient_id = ?";

    public static final String DELETE_doctor = "delete from doctors where doctor_id = ?";
    public static final String DELETE_doctor_cred = "delete from user_login where doctor_id = ?";
    public static final String DELETE_doctor_medRecords = "delete from medical_records where doctor_id = ?";
    public static final String DELETE_doctor_medications = "delete from prescribed_medications where record_id in (select doctor_id from medical_records where patient_id = ?)";
    public static final String DELETE_doctor_appointments = "delete from appointments where doctor_id = ?";

    public static final String DELETE_medRecord_medications = "delete from prescribed_medications where record_id = ?";
    public static final String DELETE_medRecord = "delete from medical_records where record_id = ?";


    public static final String UPDATE_PATIENT = "update patients set name = ?, date_of_birth = ?, phone = ? where patient_id = ?";
    public static final String UPDATE_PATIENT_CREDENTIALS = "update user_login set username = ?, password = ? where patient_id = ?";
    public static final String UPDATE_DOCTOR_CREDENTIALS = "update user_login set username = ?, password = ? where doctor_id = ?";
    public static final String UPDATE_MEDRECORD = "update medical_records set doctor_id = ?, patient_id = ?, diagnosis = ?, admission_date = ? where record_id = ?";
    public static final String UPDATE_DOCTOR = "update doctors set name = ?, specialization = ?, phone = ? where doctor_id = ?";



}
