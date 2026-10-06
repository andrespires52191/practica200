package org.example.repositories;

import org.example.dao.DoctorDao;
import org.example.dao.PatientDao;
import org.example.idao.DoctorDaoImpl;
import org.example.idao.PatientDaoImpl;
import org.example.irepositories.PatientRepository;
import org.example.models.Doctor;
import org.example.models.Patient;

public class PatientRepositoryImpl implements PatientRepository {
    private PatientDao patientDao = new PatientDaoImpl();
    private DoctorDao doctorDao = new DoctorDaoImpl();
    @Override
    public Patient getPatient(int id) {
//        Patient patient = patientDao.getPatient(id);
//        Doctor doctor = doctorDao.getDoctorByPatientId(patient.getId());
//        patient.setDoctor(doctor);
//        return patient;
        return null;
    }
}
