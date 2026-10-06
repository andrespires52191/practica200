package org.example.idao;

import org.example.dao.PatientDao;
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
        return 0;
    }

    @Override
    public void delete(int id) {

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
            String query = "update patients set name=?, lastname=?, dni=?, age=?, phone=?, disease=?, id_doctor=? where id=?";
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
                ps.setInt(8, patient.getDoctor().getId());

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
