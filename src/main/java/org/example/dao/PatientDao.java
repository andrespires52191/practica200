package org.example.dao;

import org.example.models.Doctor;
import org.example.models.Patient;

import java.util.List;

public interface PatientDao {
    int add(Patient patient);

    void delete(int id);

    Patient getPatient(int id);

    List<Patient> getPatients();

    boolean update(Patient patient);
}

