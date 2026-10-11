package org.example.idao;

import org.example.models.Patient;
import org.example.utils.DatabaseConnection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PatientDaoImplTest {
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
        PatientDaoImpl patientDao = new PatientDaoImpl();

        Patient patient;
        int id_prueba = 100;

        // crear
        patient = new Patient(id_prueba, "name", "lastname", "dni", 20, "phone", "disease");
        assert patientDao.add(patient) == 1;

        // comprobar creacion
        patient = patientDao.getPatient(id_prueba);
        assert patient.getAge() == 20;

        // actualizar
        patient = new Patient(id_prueba, "name", "lastname", "dni", 30, "phone", "disease");
        assert patientDao.update(patient);

        // comprobar actualización
        patient = patientDao.getPatient(id_prueba);
        assert patient.getAge() == 30;

        // borrar e intentar borrar de nuevo
        assertDoesNotThrow(() -> patientDao.delete(id_prueba));
        assertThrows(IllegalArgumentException.class, () -> patientDao.delete(id_prueba));
    }
}
