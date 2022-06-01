package ru.spbstu.korobtsov.api.domain;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Set;

import static javax.persistence.CascadeType.REMOVE;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "students")
@JsonFilter("studentFilter")
public class Student {

    @Id
    @JsonIgnore
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Length(min = 1)
    private String name;

    @Email
    private String email;

    @Length(min = 10, max = 11)
    private String phone;

    @JsonIgnore
    private boolean massagePassed = false;

    @OneToMany(cascade = REMOVE, mappedBy = "student", fetch = FetchType.EAGER)
    private Set<Attendance> attendance;

    @JsonIgnore
    @OneToMany(cascade = REMOVE, mappedBy = "student", fetch = FetchType.EAGER)
    private Set<Homework> homework;
}
