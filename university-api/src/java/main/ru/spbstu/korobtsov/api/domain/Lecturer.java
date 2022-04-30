package ru.spbstu.korobtsov.api.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "lecturers")
public class Lecturer {

    private String id;

    public void setId(String id) {
        this.id = id;
    }

    @Id
    @GeneratedValue
    public String getId() {
        return id;
    }
}
