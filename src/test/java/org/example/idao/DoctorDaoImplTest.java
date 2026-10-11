package org.example.idao;

import org.example.models.Doctor;
import org.example.utils.DatabaseConnection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DoctorDaoImplTest {
    Connection connection;

    @BeforeEach
    void setUp() {
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void prueba_conjunta() {
        DoctorDaoImpl doctorDao = new DoctorDaoImpl();

        Doctor doctor;
        int id_prueba = 100;

        // crear
        doctor = new Doctor(id_prueba, "name", "lastname", "din", 1000, "speciality");
        assert doctorDao.add(doctor) == 1;

        // comprobar creacion
        doctor = doctorDao.getDoctor(id_prueba);
        assert doctor.getSalary() == 1000;

        // actualizar
        doctor = new Doctor(id_prueba, "name", "lastname", "din", 2000, "speciality");
        assert doctorDao.update(doctor);

        // comprobar actualización
        doctor = doctorDao.getDoctor(id_prueba);
        assert doctor.getSalary() == 2000;

        // borrar e intentar borrar de nuevo
        assertDoesNotThrow(() -> doctorDao.delete(id_prueba));
        assertThrows(IllegalArgumentException.class, () -> doctorDao.delete(id_prueba));
    }
}