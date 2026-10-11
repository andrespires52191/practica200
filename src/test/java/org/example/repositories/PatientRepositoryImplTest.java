package org.example.repositories;

import org.example.models.Doctor;
import org.example.models.Patient;
import org.junit.jupiter.api.Test;

class PatientRepositoryImplTest {

    @Test
    void isPatientAttendedByDoctor() {
        PatientRepositoryImpl patientRepository = new PatientRepositoryImpl();
        DoctorRepositoryImpl doctorRepository = new DoctorRepositoryImpl();

        // datos de prueba
        Doctor doctor1 = new Doctor(500, "name", "lastname", "dni", 1000, "speciality");
        Doctor doctor2 = new Doctor(501, "name", "lastname", "dni", 1000, "speciality");

        Patient patient = new Patient(502, "name", "lastname", "dni", 30, "phone", "disease", doctor1);


        //try {
        //    patientRepository.remove(patient);
        //} catch (Exception e) {
        //    System.out.println("Error: " + e.getMessage());
        //}
        //try {
        //    doctorRepository.remove(doctor1);
        //} catch (Exception e) {
        //    System.out.println("Error: " + e.getMessage());
        //}
        //try {
        //    doctorRepository.remove(doctor2);
        //} catch (Exception e) {
        //    System.out.println("Error: " + e.getMessage());
        //}

        try {
            doctorRepository.add(doctor1);
            doctorRepository.add(doctor2);

            patientRepository.add(patient);

            assert patientRepository.isPatientAttendedByDoctor(patient.getId(), doctor1.getId());
            assert !patientRepository.isPatientAttendedByDoctor(patient.getId(), doctor2.getId());
        } finally {

            // limpieza
            try {
                patientRepository.remove(patient);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            try {
                doctorRepository.remove(doctor1);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            try {
                doctorRepository.remove(doctor2);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}