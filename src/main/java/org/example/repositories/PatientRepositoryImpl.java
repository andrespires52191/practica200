package org.example.repositories;

import org.example.dao.DoctorDaoImpl;
import org.example.dao.PatientDaoImpl;
import org.example.irepositories.PatientRepository;
import org.example.models.Doctor;
import org.example.models.Patient;

public class PatientRepositoryImpl implements PatientRepository {
    private PatientDaoImpl patientDao;
    private DoctorDaoImpl doctorDao;

    public PatientRepositoryImpl() {
        patientDao = new PatientDaoImpl();
        doctorDao = new DoctorDaoImpl();
    }

    @Override
    public Patient getPatient(int id) {
        Patient patient = patientDao.getPatient(id);
        Doctor doctor = doctorDao.getDoctorByPatientId(patient.getId());
        patient.setDoctor(doctor);
        return patient;
    }

    @Override
    public void add(Patient patient) {
        patientDao.add(patient);
    }

    @Override
    public void update(Patient patient) {
        patientDao.update(patient);
    }

    @Override
    public void remove(Patient patient) {
        patientDao.delete(patient.getId());
    }

    @Override
    public boolean isPatientAttendedByDoctor(int patient_id, int doctor_id) {
        Patient patient = patientDao.getPatient(patient_id);
        Doctor doctor = patient.getDoctor();
        if (doctor == null) {
            return false;
        }
        return doctor_id == doctor.getId();
    }
}
