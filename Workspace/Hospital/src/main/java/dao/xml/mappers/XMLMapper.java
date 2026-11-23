package dao.xml.mappers;

import dao.mappers.MedRecordSQLMapper;

import dao.xml.domain.AppointmentXML;
import dao.xml.domain.MedRecordXML;
import dao.xml.domain.PaymentXML;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class XMLMapper {

    public List<MedRecordXML> mapMedRecord(ResultSet rsRecords){
        List<MedRecordXML> records = new ArrayList<>();
        try {
            while (rsRecords.next()) {
                MedRecordXML record=new MedRecordXML();
                record.setId(rsRecords.getInt("record_id"));
                record.setPatientID(rsRecords.getInt("patient_id"));
                record.setDoctorID(rsRecords.getInt("doctor_id"));
                record.setDiagnosis(rsRecords.getString("diagnosis"));
                record.setAdmission_date(rsRecords.getDate("admission_date").toLocalDate());
                records.add(record);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MedRecordSQLMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return records;
    }

    public List<String> mapMeds(ResultSet rsMeds){
        List<String> meds = new ArrayList<>();
        try {
            while (rsMeds.next()) {
                meds.add(rsMeds.getString("medication_name"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(MedRecordSQLMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return meds;
    }

    public List<AppointmentXML> mapAppointments(ResultSet rs) {
        List<AppointmentXML> appointments = new ArrayList<>();
        try {
            while (rs.next()) {
                AppointmentXML appointment=new AppointmentXML();
                appointment.setAppointment_id(rs.getInt("appointment_id"));
                appointment.setPatient_id(rs.getInt("patient_id"));
                appointment.setDoctor_id(rs.getInt("doctor_id"));
                appointment.setAppointment_date(rs.getDate("appointment_date").toLocalDate());
                appointments.add(appointment);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MedRecordSQLMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return appointments;
    }

    public List<PaymentXML> mapPayments(ResultSet rs) {
        List<PaymentXML> payments = new ArrayList<>();
        try {
            while (rs.next()) {
                PaymentXML payment=new PaymentXML();
                payment.setPayment_id(rs.getInt("payment_id"));
                payment.setPatient_id(rs.getInt("patient_id"));
                payment.setAmount(rs.getDouble("amount"));
                payment.setPayment_date(rs.getDate("payment_date").toLocalDate());
                payments.add(payment);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MedRecordSQLMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return payments;

    }
}
