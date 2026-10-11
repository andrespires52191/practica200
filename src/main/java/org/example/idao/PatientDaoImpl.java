package org.example.idao;

import org.example.dao.PatientDao;
import org.example.models.Doctor;
import org.example.models.Patient;
import org.example.utils.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientDaoImpl implements PatientDao {
    @Override
    public int add(Patient patient) {
        String query = "INSERT INTO patients (id, name, lastname, dni, age, phone, disease, doctor_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps;
        int rs = 0;
        try {
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
            ps.setInt(1, patient.getId());
            ps.setString(2, patient.getName());
            ps.setString(3, patient.getLastname());
            ps.setString(4, patient.getDni());
            ps.setInt(5, patient.getAge());
            ps.setString(6, patient.getPhone());
            ps.setString(7, patient.getDisease());
            ps.setInt(8, -1);
            rs = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public void delete(int id) throws IllegalArgumentException {
        int rs = 0;
        if (patientExists(id)) {
            String query = "delete from patients where id=?";
            PreparedStatement ps;
            try {
                ps = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
                ps.setInt(1, id);
                rs = ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Para no modificar la interface, pero hacer la comprobación en JUnit.
        if (rs <= 0) {
            throw new IllegalArgumentException("No se ha podido borrar");
        }
    }

    @Override
    public Patient getPatient(int id) {
        String query = "select * from patients where id=?";
        PreparedStatement ps = null;
        Patient patient = null;
        try {
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                patient = new Patient(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("lastname"),
                        rs.getString("dni"),
                        rs.getInt("age"),
                        rs.getString("phone"),
                        rs.getString("disease")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patient;
    }

    @Override
    public List<Patient> getPatients() {
        List<Patient> lista = new ArrayList<>();

        String query = "select * from patients";
        PreparedStatement ps = null;
        Patient patient = null;
        try {
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                patient = new Patient(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("lastname"),
                        rs.getString("dni"),
                        rs.getInt("age"),
                        rs.getString("phone"),
                        rs.getString("disease")
                );
                lista.add(patient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public boolean update(Patient patient) {
        if (patientExists(patient.getId())) {
            String query = "update patients set name=?, lastname=?, dni=?, age=?, phone=?, disease=?, doctor_id=? where id=?";
            PreparedStatement ps;
            int rs = 0;
            try {
                Doctor doctor = patient.getDoctor();
                int doctor_id = (doctor == null) ? -1 : doctor.getId();

                ps = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
                ps.setString(1, patient.getName());
                ps.setString(2, patient.getLastname());
                ps.setString(3, patient.getDni());
                ps.setInt(4, patient.getAge());
                ps.setString(5, patient.getPhone());
                ps.setString(6, patient.getDisease());
                ps.setInt(7, doctor_id);
                ps.setInt(8, patient.getId());

                rs = ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return (rs > 0);
        }
        return false;
    }

    private boolean patientExists(int id) {
        return getPatient(id) != null;
    }
}
