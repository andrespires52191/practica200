package org.example.idao;

import org.example.models.Doctor;
import org.example.utils.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DoctorDaoImpl {
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
                        rs.getString("speciality")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctor;
    }

    @Override
    public boolean update(Doctor doctor) {
        if (doctorExists(doctor.getId())) {
            String query = "update doctors set name=?, lastname=?, dni=?, salary=?, speciality=? where id=doctor.getId()";
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

    private boolean doctorExists(int id) {
        return false;
    }
}
