package org.example.irepositories;

import org.example.idao.DoctorDaoImpl;
import org.example.idao.PatientDaoImpl;
import org.example.models.Doctor;
import org.example.models.Patient;
import org.example.repositories.PatientRepository;

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
}
