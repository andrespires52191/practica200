package org.example.idao;

import org.example.dao.DoctorDao;
import org.example.models.Doctor;
import org.example.utils.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoctorDaoImpl implements DoctorDao {
    PatientDaoImpl patientDao;

    public DoctorDaoImpl(PatientDaoImpl patientDao) {
        this.patientDao = patientDao;
    }

    public DoctorDaoImpl() {

    }

    @Override
    public int add(Doctor doctor) {
        String query = "INSERT INTO doctors (id, name, lastname, dni, salary, speciality) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps;
        int rs = 0;
        try {
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
            ps.setInt(1, doctor.getId());
            ps.setString(2, doctor.getName());
            ps.setString(3, doctor.getLastname());
            ps.setString(4, doctor.getDni());
            ps.setDouble(5, doctor.getSalary());
            ps.setString(6, doctor.getSpeciality());
            rs = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public void delete(int id) throws IllegalArgumentException {
        int rs = 0;
        if (doctorExists(id)) {
            String query = "delete from doctors where id=?";
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
    public Doctor getDoctor(int id) {
        String query = "select * from doctors where id=?";
        PreparedStatement ps = null;
        Doctor doctor = null;
        try {
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                doctor = new Doctor(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("lastname"),
                        rs.getString("dni"),
                        rs.getDouble("salary"),
                        rs.getString("speciality"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctor;
    }

    @Override
    public List<Doctor> getDoctors() {
        List<Doctor> lista = new ArrayList<>();

        String query = "select * from doctors";
        PreparedStatement ps = null;
        Doctor doctor = null;
        try {
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                doctor = new Doctor(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("lastname"),
                        rs.getString("dni"),
                        rs.getDouble("salary"),
                        rs.getString("speciality"));
                lista.add(doctor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public boolean update(Doctor doctor) {
        if (doctorExists(doctor.getId())) {
            String query = "update doctors set name=?, lastname=?, dni=?, salary=?, speciality=? where id=?";
            PreparedStatement ps;
            int rs = 0;
            try {
                ps = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
                ps.setString(1, doctor.getName());
                ps.setString(2, doctor.getLastname());
                ps.setString(3, doctor.getDni());
                ps.setDouble(4, doctor.getSalary());
                ps.setString(5, doctor.getSpeciality());
                ps.setInt(6, doctor.getId());
                rs = ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return (rs > 0);
        }
        return false;
    }

    @Override
    public Doctor getDoctorByPatientId(int patient_id) {
        return patientDao.getPatient(patient_id).getDoctor();
    }

    private boolean doctorExists(int id) {
        return getDoctor(id) != null;
    }
}
