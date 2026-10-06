package org.example.idao;

import org.example.dao.PatientDao;
import org.example.models.Patient;

import java.util.List;

public class PatientDaoImpl implements PatientDao {
    @Override
    public int add(Patient patient) {
        return 0;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Patient getPatient(int id) {
        return null;
    }

//    @Override
//    public List<Patient> getPatients() {
//        return List.of();
//    }

    @Override
    public boolean update(Patient patient) {
        return false;
    }
}
