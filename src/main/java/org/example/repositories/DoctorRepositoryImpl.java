package org.example.repositories;

import org.example.dao.DoctorDaoImpl;
import org.example.dao.PatientDaoImpl;
import org.example.irepositories.DoctorRepository;
import org.example.models.Doctor;

public class DoctorRepositoryImpl implements DoctorRepository {
    private PatientDaoImpl patientDao;
    private DoctorDaoImpl doctorDao;

    public DoctorRepositoryImpl() {
        patientDao = new PatientDaoImpl();
        doctorDao = new DoctorDaoImpl();
    }

    @Override
    public Doctor getDoctor(int id) {
        return doctorDao.getDoctor(id);
    }

    @Override
    public void add(Doctor doctor) {
        doctorDao.add(doctor);
    }

    @Override
    public void update(Doctor doctor) {
        doctorDao.update(doctor);
    }

    @Override
    public void remove(Doctor doctor) {
        doctorDao.delete(doctor.getId());
    }
}
