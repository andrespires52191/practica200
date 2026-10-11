package org.example.irepositories;

import org.example.models.Doctor;

public interface DoctorRepository {
    Doctor getDoctor(int id);

    void add(Doctor doctor);

    void update(Doctor doctor);

    void remove(Doctor doctor);
}
