package org.example.idao;

import org.example.models.Patient;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PatientDaoImplTest {
    @Test
    void prueba_conjunta() {
        PatientDaoImpl patientDao = new PatientDaoImpl();

        Patient patient;
        int id_prueba = 300;

        // borrar en caso de fallo anterior
        //try {
        //    patientDao.delete(id_prueba);
        //} catch (Exception e) {
        //    System.out.println("Error: " + e.getMessage());
        //}

        try {
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
        } finally {
            // limpieza
            try {
                patientDao.delete(id_prueba);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
