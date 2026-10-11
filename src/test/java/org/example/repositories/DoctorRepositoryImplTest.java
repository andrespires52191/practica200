package org.example.repositories;

import org.example.models.Doctor;
import org.example.models.Patient;
import org.junit.jupiter.api.Test;

import java.util.List;

class DoctorRepositoryImplTest {

    @Test
    void getDoctor() {

        PatientRepositoryImpl patientRepository = new PatientRepositoryImpl();
        DoctorRepositoryImpl doctorRepository = new DoctorRepositoryImpl();

        // datos de prueba
        Doctor doctor = new Doctor(400, "name", "lastname", "dni", 1000, "speciality");
        Patient patient1 = new Patient(401, "name", "lastname", "dni", 30, "phone", "disease", doctor);
        Patient patient2 = new Patient(402, "name", "lastname", "dni", 30, "phone", "disease", doctor);


        try {
            doctorRepository.add(doctor);
            patientRepository.add(patient1);
            patientRepository.add(patient2);

            List<Patient> pacientes_doctor = doctorRepository
                    .getDoctor(doctor.getId())
                    .getAttendedPatients();

            assert pacientes_doctor.size() == 2;
        } finally {

            // limpieza
            try {
                patientRepository.remove(patient1);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            try {
                patientRepository.remove(patient2);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            try {
                doctorRepository.remove(doctor);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

    }
}
