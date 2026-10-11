package org.example.irepositories;

import org.example.idao.DoctorDaoImpl;
import org.example.idao.PatientDaoImpl;
import org.example.models.Doctor;
import org.example.repositories.DoctorRepository;

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
