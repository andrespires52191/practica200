package org.example.idao;

import org.example.models.Doctor;
import org.example.models.Patient;
import org.junit.jupiter.api.Test;

import java.util.List;

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


    @Test
    void prueba_getPatientsByDoctorId() {

        PatientDaoImpl patientDao = new PatientDaoImpl();
        DoctorDaoImpl doctorDao = new DoctorDaoImpl();

        // datos de prueba
        Doctor doctor = new Doctor(400, "name", "lastname", "dni", 1000, "speciality");
        Patient patient1 = new Patient(401, "name", "lastname", "dni", 30, "phone", "disease", doctor);
        Patient patient2 = new Patient(402, "name", "lastname", "dni", 30, "phone", "disease", doctor);


        try {
            doctorDao.add(doctor);
            patientDao.add(patient1);
            patientDao.add(patient2);

            List<Patient> pacientes_doctor = patientDao.getPatientsByDoctorId(doctor.getId());

            assert pacientes_doctor.size() == 2;
        } finally {

            // limpieza
            try {
                patientDao.delete(patient1.getId());
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            try {
                patientDao.delete(patient2.getId());
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
