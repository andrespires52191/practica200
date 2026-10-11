package org.example.dao;

import org.example.models.Doctor;
import org.example.models.Patient;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DoctorDaoImplTest {
    @Test
    void prueba_conjunta() {
        DoctorDaoImpl doctorDao = new DoctorDaoImpl();

        Doctor doctor;
        int id_prueba = 100;

        // borrar en caso de fallo anterior
        //try {
        //    doctorDao.delete(id_prueba);
        //} catch (Exception ignored) {
        //}

        try {
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
        } finally {
            // limpieza
            try {
                doctorDao.delete(id_prueba);
            } catch (Exception ignored) {
            }
        }
    }


    @Test
    void prueba_getDoctorByPatientId() {
        PatientDaoImpl patientDao = new PatientDaoImpl();
        DoctorDaoImpl doctorDao = new DoctorDaoImpl();

        // datos de prueba
        Doctor doctor = new Doctor(200, "name", "lastname", "dni", 1000, "speciality");
        Patient patient = new Patient(200, "name", "lastname", "dni", 30, "phone", "disease", doctor);

        // borrar en caso de fallo anterior
        //try {
        //    patientDao.delete(patient.getId());
        //} catch (Exception e) {
        //    System.out.println("Error: " + e.getMessage());
        //}
        //try {
        //    doctorDao.delete(doctor.getId());
        //} catch (Exception e) {
        //    System.out.println("Error: " + e.getMessage());
        //}

        try {
            doctorDao.add(doctor);
            patientDao.add(patient);

            // comprobar resultado consulta
            assert doctorDao.getDoctorByPatientId(patient.getId()).getId() == doctor.getId();
        } finally {
            // limpieza
            try {
                patientDao.delete(patient.getId());
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            try {
                doctorDao.delete(doctor.getId());
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
